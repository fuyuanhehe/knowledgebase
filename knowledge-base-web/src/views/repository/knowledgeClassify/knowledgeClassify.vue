<!--
@file：知识仓库-知识分类库列表页
@createTime：20200319
@updateTime：20200328
@author：Claire
-->
<template>
    <div class="knowledgeClassify">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="list-top mb20">
                <el-input class="w260 mr20" v-model="searchMsg" placeholder="请输入关键字" @change="handleSearch"></el-input>
                <el-button class="btn_bg_blue" @click="edit('add')">新建</el-button>
            </div>
            <el-divider></el-divider>
            <ul class="list-box"
                v-loading="loading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)">
                <template v-if="Array.isArray(listData) && listData.length > 0">
                    <li class="list-item" v-for="item in listData" :key="item.id" @click="goDetail(item.id, item.browseNum )">
                        <h6 class="list-item-title">{{ item.name }}</h6>
                        <div class="list-item-info">
                        <span class="list-item-info-item">
                            <v-icon class="list-icon" name="iconquanbufenlei"></v-icon>
                            <span>分类：{{ categoryObj.name }}</span>
                        </span>
                            <span class="list-item-info-item">
                            <v-icon class="list-icon" name="icontouxiang"></v-icon>
                            <span>上传人：{{ item.createUser }}</span>
                        </span>
                            <span class="list-item-info-item">
                            <v-icon class="list-icon" name="iconshijian-tianchong"></v-icon>
                            <span>上传时间：{{ item.createTime | date('YYYY-MM-DD HH:ss') }}</span>
                        </span>
                        </div>
                    </li>
                    <!--<el-divider></el-divider>-->
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
                </template>
                <template v-else>
                    <no-data v-show="!loading"></no-data>
                </template>
            </ul>
        </div>
        <!--新建知识分类对话框-->
        <el-dialog
                class="dialog-box"
                center
                title="新建知识分类"
                :visible.sync="dialogVisible"
                width="494px">
            <div class="dialog-main">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                    <el-form-item label="文档附件：" prop="file">
                        <upload-file
                                class="file-upload"
                                :limit="1"
                                :file-list.sync="files"
                                :width="260"
                                :height="40"
                                text="上传文件，请点击上传"
                                accept="application/msword,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/pdf"
                                :suffix="['.doc', '.docx', 'xls', 'xlsx', 'ppt', 'pptx', 'pdf']"
                                @success="handleUploadSuccess">
                        </upload-file>
                    </el-form-item>
                    <el-form-item label="文档分类：" prop="category">
                        <!--<span>{{ ruleForm.category }}</span>-->
                        <el-input v-model="categoryObj.name" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="上传人：" prop="userName">
                        <el-input v-model="$store.state.user.nickName" disabled></el-input>
                        <!--<span>{{ ruleForm.userName }}</span>-->
                    </el-form-item>
                    <el-form-item label="所属部门：" prop="department">
                        <el-input v-model="$store.state.user.deptName" disabled></el-input>
                        <!--<span>{{ ruleForm.department }}</span>-->
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
    name: 'knowledgeClassify',
    data () {
        return {
            categoryId: this.$route.params.id,
            categoryObj: {}, // 当前知识分类对象
            breadcrumbNav: [
                {
                    name: '知识仓库',
                    path: '/repository',
                    routeName: 'repository'
                },
                {
                    name: '知识分类',
                    path: '/repository/knowledgeClassify',
                    routeName: 'repositoryKnowledgeClassify'
                }, {
                    name: '',
                    path: '',
                    routeName: 'repositoryKnowledgeClassifyBase'
                }
            ], // 面包屑导航集合
            listData: [],
            search: {
                pageNum: 1,
                pageSize: 10,
                weight: 3,
                categoryId: '',
                type: 1,
                name: '',
                createUser: ''
            },
            searchMsg: '', // 搜索框内容
            loading: false, // table是否显示加载动画
            total: 0, // 总条目数
            dialogVisible: false, // 新建文档分类对话框是否显示
            ruleForm: {
                file: '',
                category: '',
                userName: '',
                department: ''
            },
            rules: {
                file: [
                ]
            },
            files: []
        };
    },
    watch: {
        '$route.params.id': {
            handler (val) {
                this.getCategory(val);
                this.search.categoryId = val;
                this.getData();
            },
            immediate: true,
            deep: true
        }
    },
    created () {
        this.getData();
    },
    methods: {
        /**
         * 获取文档分类列表数据
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            this.search.pageNum = pageNum;
            this.search.pageSize = pageSize;
            this.loading = true;
            const res = await this.$api.getBaseDocumentPage(this.search);
            this.listData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 根据分类id查询分类详情
         */
        async getCategory (id) {
            await this.$api.getBaseDocumentCategory({ id: id }).then(res => {
                this.categoryObj = res;
                this.breadcrumbNav[2].name = this.categoryObj.name;
                this.breadcrumbNav[2].path = '/repository/knowledgeClassify/' + id;
            });
        },
        /**
         * 搜索框变化时（失去焦点或点击回车）触发
         */
        handleSearch () {
            this.search.name = this.searchMsg;
            this.getData();
        },
        /**
         * 新建/编辑
         * @param type
         * @param id
         */
        edit (type, id) {
            this.dialogVisible = true;
            this.files = [];
        },
        /**
         *上传成功后触发
         * @res
         * @file
         */
        handleUploadSuccess (res, file) {
            // const fileData = this.files;
            // for (let i = 0; i < this.files.length; i++) {
            //     this.$set(this.files[i], 'categoryId', 1);
            //     this.$set(this.files[i], 'type', 1);
            //     this.$set(this.files[i], 'weight', this.search.weight);
            // }
            // console.log(111123333, this.files);
        },
        /**
         * 新建文档提交
         */
        onsubmit () {
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    this.search.name = this.files[0].name;
                    this.search.url = this.files[0].url;
                    const params = this.search;
                    this.$api.saveBaseDocument(params).then(res => {
                        this.dialogVisible = false;
                        this.$message.success('新建文档成功');
                        this.getData();
                    });
                }
            });
        },
        /**
         * 跳转到详情
         * @param id 文档id
         * @param browseNum 文档浏览数量
         */
        goDetail (id, browseNum) {
            this.$router.push({
                path: '/repository/knowledgeClassify/' + this.categoryId + '/knowledgeDetails',
                query: { id: id }
            });
            const param = {
                id: id,
                browseNum: browseNum ? browseNum + 1 : 1
            };
            this.$api.modifyBaseDocument(param).then(res => {});
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
.knowledgeClassify {
    .content {
        .el-divider {
            margin: 0;
        }
        .list-box {
            padding: 0 40px;
            .el-divider {
                margin: 0;
            }
            .list-item {
                padding: 20px 0;
                cursor: pointer;
                border-bottom: 1px solid $color-border-base;
                &:hover, &:focus {
                    background: #F7F7F7;
                }
                &-title {
                    width: 50%;
                    margin-bottom: 10px;
                    font-size: $font-size-middle;
                    font-weight:400;
                    color:rgba(0,0,51,1);
                    line-height:25px;
                    @extend %ellipsis
                }
                &-info {
                    &-item {
                        margin-right: 30px;
                        .list-icon {
                            margin-right: 8px;
                            font-size: 20px;
                        }
                        span {
                            color: $color-text-regular;
                        }
                    }
                }
            }
        }
        .dialog-box {
            .dialog-main {
                .file-upload {
                }
            }
        }
    }
}
</style>
