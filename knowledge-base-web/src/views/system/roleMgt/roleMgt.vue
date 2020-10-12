<!--
@file：系统设置-角色管理
@time：20200306
@author：Claire
-->
<template>
    <div class="roleMgt">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top">
                <!--table页搜索查询-->
                <table-search :selectOptions="enabledStateOptions"
                              :selectLabel="'启用状态'"
                              :dateLabel="'创建时间'"
                              @search="searchData"></table-search>
                <!--分割线（虚线）-->
                <v-divider></v-divider>
                <el-button class="btn_border_blue mb20" @click="editRole('add')">新增角色</el-button>
            </div>
            <div class="table-box">
                <el-table
                        :data="tableData"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        header-cell-class-name="table-th"
                        style="width: 100%">
                    <el-table-column
                            align="center"
                            type="index"
                            label="序号">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="name"
                            label="角色">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="userCount"
                            label="员工数量">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="createTime"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.createTime | date('YYYY-MM-DD')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="启用状态">
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
                            <span class="cursor_p" @click="editRole('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id, scope.row.name)">
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
    </div>
</template>

<script>
export default {
    name: 'roleMgt',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '系统设置',
                    path: '/system',
                    routeName: 'system'
                },
                {
                    name: '角色管理',
                    path: '/system/systemSetup/roleMgt',
                    routeName: 'systemSetupRoleMgt'
                }
            ], // 面包屑导航集合
            enabledStateOptions: [
                { label: '已开启', value: 1 },
                { label: '已关闭', value: 0 }
            ], // 角色启用状态下拉选项
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                status: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            total: 1 // 总条目数
        };
    },
    created () {
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
            const res = await this.$api.getRolePage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 查询
         */
        searchData (data) {
            this.search.name = data.msg;
            this.search.status = data.option;
            this.search.beginCreateTime = data.startDate;
            this.search.endCreateTime = data.endDate;
            this.getData(1, this.search.pageSize);
        },
        /**
         * 新增/编辑角色
         * @param type 操作类型：add--新增；edit--修改
         * @param id 角色id
         */
        editRole (type, id) {
            if (type === 'add') {
                this.$router.push('/system/systemSetup/roleMgt/add');
            } else {
                this.$router.push({
                    path: '/system/systemSetup/roleMgt/edit',
                    query: { id: id }
                });
            }
        },
        /**
         * 删除
         * @param id
         * @param name
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeRole({ id: id }).then(res => {
                    this.$message.success('删除成功!');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 角色启用状态改变
         * @param val 新状态值
         * @param id 角色id
         */
        statusChange (val, id) {
            const params = {
                id: id,
                status: val
            };
            this.$api.modifyRoleStatus(params);
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
.roleMgt {
    .content {
        padding: 40px 50px;
    }
}
</style>
