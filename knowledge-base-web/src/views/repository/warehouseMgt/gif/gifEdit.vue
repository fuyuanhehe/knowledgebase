<!--
@file：知识仓库管理-动态图管理-添加/编辑动态图
@createTime：20200319
@updateTime：20200328
@author：Claire
-->
<template>
    <div class="gifEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px">
                <el-form-item label="图片：" prop="url">
                    <upload-image :src.sync="ruleForm.url" :width="210" :height="140"></upload-image>
                </el-form-item>
                <el-form-item label="文档内容" prop="reserve2">
                    <ueditor v-model="ruleForm.reserve2" />
                </el-form-item>
                <el-form-item>
                    <el-button class="btn_bg_blue" @click="submitForm('ruleForm')">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    name: 'gifEdit',
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型： add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '知识仓库',
                    path: '/repository',
                    routeName: 'repository'
                }, {
                    name: '知识仓库管理',
                    path: '/repository/warehouseMgt',
                    routeName: 'repositoryWarehouseMgt'
                }, {
                    name: '动态图管理',
                    path: '/repository/warehouseMgt/gif',
                    routeName: 'repositoryGifMgt'
                }, {
                    name: this.$route.params.type === 'add' ? '添加动态图' : '编辑动态图',
                    path: '/repository/warehouseMgt/gif/' + this.$route.params.type,
                    routeName: 'repositoryGifEdit'
                }
            ], // 面包屑导航集合
            ruleForm: {
                weight: 4,
                url: '',
                reserve2: ''
            },
            gifId: '', // 当前编辑动态图id
            rules: this.validationRules() // 表单验证规则
        };
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
            });
        } else { // 修改
            next(vm => {
                vm.gifId = to.query.id;
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取动态图信息
         * @param id 精品文档id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getBaseDocument(params);
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    this.$set(params, 'type', 1);
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveBaseDocument(params).then(res => {
                            this.$message.success('新增动态图成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyBaseDocument(params).then(res => {
                            this.$message.success('修改动态图成功!');
                        });
                    }
                    this.$router.push({ path: '/repository/warehouseMgt/gif' });
                }
            });
        },
        /**
         * 取消（返回动态图列表）
         */
        goBack () {
            this.$router.push({ path: '/repository/warehouseMgt/gif' });
        },
        validationRules () {
            return {
                url: [
                    { required: true, message: '请选择动态图', trigger: 'blur' }
                ],
                reserve2: [
                    { required: true, message: '请填写文档内容', trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
.gifEdit {

}
</style>
