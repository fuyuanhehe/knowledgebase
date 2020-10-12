<template>
    <div class="userMgt">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search :selectOptions="deptOptions"
                              selectLabel="所属部门"
                              dateLabel="创建时间"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <div class="btn-box mb20">
                    <el-button class="btn_border_blue" @click="editUser('add')">新增用户</el-button>
                    <el-button class="btn_border_blue" @click="resetPsd()">重置密码</el-button>
                    <el-button class="btn_border_blue" @click="deleteBatchItem">批量删除</el-button>
                    <el-button class="btn_border_blue" @click="dialogVisible = true">导入</el-button>
                    <el-button class="btn_border_blue" @click="exportData">导出</el-button>
                    <input type="file" ref="uploadExcl" hidden @change="uploadExcl($event)"/>
                </div>
            </div>
            <div class="table-box">
                <el-table
                        :data="tableData"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        header-cell-class-name="table-th"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            align="center"
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="name"
                            label="工号">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="nickName"
                            label="用户名">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="sex"
                            label="性别">
                        <template slot-scope="scope">
                            <span>{{ scope.row.sex ? '女' : '男'}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="deptName"
                            label="所属部门">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="roleName"
                            label="角色">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="phone"
                            label="联系方式">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="账号状态">
                        <template slot-scope="scope">
                            <el-switch
                                    v-model="scope.row.status"
                                    active-color="#0092FF"
                                    inactive-color="#D3D2D5"
                                    :active-value="1"
                                    :inactive-value="0"
                                    @change="((val) => { statusChange(val,scope.row.id) })">
                            </el-switch>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editUser('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.nickName)">
                                <i class="table-icon el-icon-delete"></i>
                            </span>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <!--分页-->
            <div class="pagination-box">
                <el-pagination
                        layout="prev, pager, next"
                        background
                        prev-text="上一页"
                        next-text="下一页"
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="search.pageNum"
                        :page-size="search.pageSize"
                        :total="total">
                </el-pagination>
            </div>
        </div>
        <!--导入对话框-->
        <el-dialog
                class="dialog-box"
                center
                title="EXCEL导入"
                :visible.sync="dialogVisible"
                width="512px">
            <div class="dialog-main">
                <div class="import-item mr40" @click="downloadTemplate">
                    <img src="~assets/images/download.png" alt="">
                    <p>下载excel模板</p>
                </div>
                <div class="import-item" @click="$refs.uploadExcl.click()">
                    <img src="~assets/images/upload.png" alt="">
                    <p>上传用户文件</p>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button class="btn_border_blue" @click="dialogVisible = false">取 消</el-button>
                <el-button class="btn_bg_blue" @click="dialogVisible = false">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
// import axios from 'utils/axiosService.js';
import config from 'api/api_conf';
const { sys } = config;
export default {
    name: 'userMgt',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '系统设置',
                    path: '/system',
                    routeName: 'system'
                },
                {
                    name: '用户管理',
                    path: '/system/systemSetup/userMgt',
                    routeName: 'systemSetupUserMgt'
                }
            ], // 面包屑导航集合
            deptOptions: [], // 部门下拉选项
            search: {
                pageNum: 1,
                pageSize: 10,
                nickName: '',
                sex: '',
                status: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            multipleSelection: [], // table选中集合
            userIds: [], // table选中的用户id集合
            total: 0, // 总条目数
            dialogVisible: false // 导入对话框是否显示
        };
    },
    created () {
        this.getDeptList();
        this.getData(1, this.search.pageSize);
    },
    methods: {
        /**
         * 获取table数据
         * @param pageNum
         * @param pageSize
         * @returns {Promise<void>}
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getUserPage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 获取部门列表
         */
        async getDeptList () {
            const res = await this.$api.getAllDept({});
            for (let i = 0; i < res.length; i++) {
                this.deptOptions.push({
                    value: res[i].id,
                    label: res[i].name
                });
            }
        },
        /**
         * 查询
         */
        searchData (data) {
            this.search.nickName = data.msg;
            this.search.status = data.option;
            this.search.beginCreateTime = data.startDate;
            this.search.endCreateTime = data.endDate;
            this.getData(1, this.search.pageSize);
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
            this.userIds = val.map((item) => {
                return item.id;
            });
        },
        /**
         * 新增/编辑用户
         * @param type 操作类型：add--新增；edit--修改
         * @param id 用户id
         */
        editUser (type, id) {
            if (type === 'add') {
                this.$router.push('/system/systemSetup/userMgt/add');
            } else {
                this.$router.push({
                    path: '/system/systemSetup/userMgt/edit',
                    query: { id: id }
                });
            }
        },
        /**
         * 重置密码
         */
        resetPsd () {
            const ids = this.userIds;
            const mult = this.multipleSelection;
            if (ids.length === 0 || mult.length === 0) {
                this.$message.warning('请先选择需要重置密码的用户！');
                return;
            }
            this.$confirm('确定要重置用户密码？', '重置密码', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.resetPwd({ ids: ids }).then(res => {
                    this.$message.success('重置密码成功!');
                });
            }).catch(() => {
                this.$message.info('已取消重置密码');
            });
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的用户！');
                return;
            }
            const ids = this.userIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的用户!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBatchUser({ ids: ids });
                this.$message.success('删除成功');
                this.getData();
            });
        },
        /**
         * 导入excel
         */
        uploadExcl (event) {
            if (event != null && event != '') {
                var file = event.srcElement.files;
                var fileName = file[0].name;// 获取文件名字
                if (fileName == '') {
                    this.$message.warning('请选择上传的Excel文件');
                    return;
                }
                var fileType = (fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length)).toLowerCase();
                console.log(111223);
                if (fileType != 'xls' && fileType != 'xlsx') {
                    console.log('fileType', fileType);
                    this.$message.error('文件格式不对，请上传xlsx,xls的格式文件');
                    return;
                }
                var formData = new FormData();
                formData.append('fileName', file[0]);
                console.log('formData:', formData);
                this.$api.importUser(formData).then(res => {
                    this.$message.success('导入成功');
                    this.getData();
                    this.dialogVisible = false;
                });
            }
        },
        /**
         * 模板下载
         */
        downloadTemplate () {
            window.location.href = sys + '/user/outputTemplete';
            this.dialogVisible = false;
            // const _this = this;
            // axios({
            //     method: 'get',
            //     // url: sys + '/user/getUserTempleteByte',
            //     url: sys + '/user/outputTemplete',
            //     data: {},
            //     // responseType: 'blob'
            // }).then(function (res) {
            //     console.log('res----', res);
            //     const fileName = '用户上传模板.xls';
            //     _this.$utils.downloadFile(res, fileName);
            // }).catch(function (error) {
            //     console.log(error);
            // });
            // this.$api.outputTemplete({ responseType: 'blob' }).then(res => {
            //     // config.responseType = 'blob';
            //     console.log(res);
            //     const fileName = '用户上传模板.xls';
            //     _this.$utils.downloadFile(res, fileName);
            // });
        },
        /**
         * 导出用户列表
         */
        exportData () {
            window.location.href = sys + '/user/outputUser';
            // const _this = this;
            // axios({
            //     method: 'get',
            //     url: sys + '/user/getUserByte',
            //     responseType: 'blob'
            // }).then(function (res) {
            //     console.log(res.type);
            //     const fileName = '用户列表.xls';
            //     _this.$utils.downloadFile(res, fileName);
            // }).catch(function (error) {
            //     console.log(error);
            // });
            // this.$api.outputUser({ responseType: 'blob' }).then(res => {
            //     // config.responseType = 'blob';
            //     console.log(res);
            //     const fileName = '用户列表.xls';
            //     _this.$utils.downloadFile(res, fileName);
            // });
        },
        /**
         * 删除
         * @param id 用户id
         * @param name 用户名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeUser({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 用户启用状态改变
         * @param val 新状态值
         * @param id 角色id
         */
        statusChange (val, id) {
            const params = {
                id: id,
                status: val
            };
            this.$api.modifyUserStatus(params);
        },
        /**
         * pageSize 改变时会触发
         * @param pageSize
         */
        handleSizeChange (pageSize) {
            this.getData(1, pageSize);
        },
        /**
         *  currentPage 改变时会触发
         * @param pageNum
         */
        handleCurrentChange (pageNum) {
            this.getData(pageNum, this.search.pageSize);
        }
    }
};
</script>

<style scoped lang="scss">
.userMgt {
    .content {
        padding: 40px 50px;
    }
    .dialog-box {
        /deep/.el-dialog__body {
            padding: 38px;
        }
        .dialog-main {
            .import-item {
                display: inline-block;
                width:198px;
                height:152px;
                text-align: center;
                border:1px solid rgba(216,216,216,1);
                border-radius:4px;
                img {
                    margin-top: 29px;
                }
                p {
                    line-height: 40px;
                }
            }
        }
    }
}
</style>
