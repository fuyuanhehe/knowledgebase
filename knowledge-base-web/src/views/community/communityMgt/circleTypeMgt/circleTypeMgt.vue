<!--
@file：知识社区-圈子类别管理
@createTime：2020-04-03
@updateTime：2020-04-03
@author：Claire
-->
<template>
    <div class="circleTypeMgt">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <el-button class="btn_bg_blue" @click="editCircleType('add')">新增圈子</el-button>
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
                            label="圈子名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.createTime | date('YYYY-MM-DD HH:ss')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editCircleType('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.name)">
                                <i class="table-icon el-icon-delete"></i>
                            </span>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <!--新增/编辑对话框-->
        <el-dialog
                class="dialog-box"
                center
                :title="editType == 'edit' ? '编辑圈子' : '新增圈子'"
                :visible.sync="dialogVisible"
                width="512px">
            <div class="dialog-main">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                    <el-form-item label="" prop="id">
                        <input v-model="ruleForm.id" hidden>
                    </el-form-item>
                    <el-form-item label="圈子名称：" prop="name">
                        <el-input v-model="ruleForm.name"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button class="btn_border_blue" @click="dialogVisible = false">取 消</el-button>
                <el-button class="btn_bg_blue" @click="onsubmit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'circleTypeMgt',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '知识社区',
                    path: '/community',
                    routeName: 'community'
                }, {
                    name: '圈子类别管理',
                    path: '/community/communityMgt/circleTypeMgt',
                    routeName: 'communityCircleTypeMgt',
                    disabled: true
                }
            ], // 面包屑导航集合
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            circleTotalNum: 0, // 现有圈子数量
            multipleSelection: [], // table选中集合
            dialogVisible: false, // 新增/编辑对话框是否显示
            editType: 'add', // 编辑类型 add:新增； edit:修改
            ruleForm: {
                id: '',
                name: ''
            },
            rules: this.validationRules(), // 表单验证规则
            search: {
                name: '',
                beginCreateTime: '',
                endCreateTime: ''
            } // 数据查询参数对象
        };
    },
    inject: ['reload'],
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取table数据
         */
        async getData () {
            this.loading = true;
            this.tableData = await this.$api.getAllCircle(this.search);
            this.loading = false;
            this.circleTotalNum = this.tableData.length;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
        },
        /**
         * 新增/编辑圈子类别
         * @param type 编辑类型:add:新增； edit:修改
         * @param id 编辑的圈子类别id
         */
        editCircleType (type, id) {
            this.editType = type;
            if (type === 'add') {
                if (this.circleTotalNum >= 10) {
                    this.$message.warning('最多创建10个圈子哦！');
                    return;
                }
                this.ruleForm = {};
            } else {
                this.$api.getCircle({ id: id }).then(res => {
                    this.ruleForm = res;
                });
            }
            this.dialogVisible = true;
        },
        /**
         * 圈子新增/编辑提交
         */
        onsubmit () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.editType === 'edit') {
                        this.$api.modifyCircle(params).then(res => {
                            this.dialogVisible = false;
                            this.$message.success('修改圈子成功');
                            this.getData();
                            this.reload();
                        });
                    } else {
                        this.$api.saveCircle(params).then(res => {
                            this.dialogVisible = false;
                            this.$message.success('新增圈子成功');
                            this.getData();
                            this.reload();
                        });
                    }
                }
            });
        },
        /**
         * 删除
         * @param id 圈子id
         * @param name 圈子名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                center: true
            }).then(() => {
                this.$api.removeCircle({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                    this.reload();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 表单验证规则
         * @returns {{name: *[]}}
         */
        validationRules () {
            const checkCircleOnly = (rule, value, callback) => {
                // 修改圈子时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditRoleName) {
                    callback();
                } else {
                    this.$api.checkCircleName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该圈子名已经存在，请重新输入'));
                        }
                    });
                }
            };
            return {
                name: [
                    { required: true, message: '请输入圈子名称', trigger: 'blur' },
                    { required: true, validator: checkCircleOnly, trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">

</style>
