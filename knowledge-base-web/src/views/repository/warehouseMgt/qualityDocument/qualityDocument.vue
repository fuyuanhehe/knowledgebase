<!--
@file：知识仓库-精品文档管理
@time：20200319
@author：Claire
-->
<template>
    <div class="qualityDocument">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <el-button class="btn_bg_blue" @click="editQualityDoc('add')">添加精品</el-button>
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
                            label="文档名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="suffix"
                            label="类型">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="coverUrl"
                            label="文档封面">
                        <template slot-scope="scope">
                            <el-image
                                    style="width: 100px; height: 100px"
                                    :src="`${$img}${scope.row.coverUrl}`"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="createUser"
                            label="创建人">
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
                            <span class="cursor_p" @click="editQualityDoc('edit', scope.row.id)">
                                <i class="table-icon el-icon-edit-outline mr30"></i>
                            </span>
                            <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.name)">
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
    name: 'qualityDocument',
    data () {
        return {
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
                    name: '精品文档管理',
                    path: '/repository/warehouseMgt/qualityDocument',
                    routeName: 'repositoryQualityDocumentMgt'
                }
            ], // 面包屑导航集合
            search: {
                pageNum: 1,
                pageSize: 10,
                name: '', // 文档名称模糊搜索,
                reserve3: '0'
            }, // 数据搜索对象
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            total: 1, // 总条目数
            multipleSelection: [] // table选中集合
        };
    },
    created () {
        this.getData();
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
            const res = await this.$api.getBaseDocumentPage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
        },
        /**
         * 新增/编辑精品文档
         * @param type 编辑类型:add:新增； edit:修改
         * @param id 编辑的精品文档id
         */
        editQualityDoc (type, id) {
            if (type === 'add') {
                this.$router.push('/repository/warehouseMgt/qualityDocument/add');
            } else {
                this.$router.push({
                    path: '/repository/warehouseMgt/qualityDocument/edit',
                    query: { id: id }
                });
            }
        },
        /**
         * 删除
         * @param id 精品文档id
         * @param name 精品文档名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                center: true
            }).then(() => {
                this.$api.modifyBaseDocument({ id: id, reserve3: '1'}).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
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
.qualityDocument {
    .content {
        padding: 40px 50px;
        min-height: 600px;
    }
}
</style>
