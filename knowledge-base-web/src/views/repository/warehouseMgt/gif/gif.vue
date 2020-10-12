<!--
@file：知识仓库管理-动态图管理
@createTime：20200319
@updateTime：20200328
@author：Claire
-->
<template>
    <div class="gif">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <el-button class="btn_bg_blue" @click="editGif('add')">新建</el-button>
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
                            type="index"
                            label="排序"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="url"
                            label="图片">
                        <template slot-scope="scope">
                            <el-image
                                    style="width: 100px; height: 100px"
                                    :src="`${$img}${scope.row.url}`"></el-image>
                        </template>
                    </el-table-column>
                    <!--<el-table-column-->
                            <!--align="center"-->
                            <!--prop="size"-->
                            <!--label="大小">-->
                    <!--</el-table-column>-->
                    <el-table-column
                            align="center"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{scope.row.createTime | date('YYYY-MM-DD')}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editGif('edit', scope.row.id)">
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
    </div>
</template>

<script>
export default {
    name: 'gif',
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
                    name: '动态图管理',
                    path: '/repository/warehouseMgt/gif',
                    routeName: 'repositoryGifMgt'
                }
            ], // 面包屑导航集合
            search: {
                pageNum: 1,
                pageSize: 10,
                weight: 4
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
         * 新增/编辑动态图
         * @param type 编辑类型:add:新增； edit:修改
         * @param id 编辑的动态图id
         */
        editGif (type, id) {
            if (type === 'add') {
                this.$router.push('/repository/warehouseMgt/gif/add');
            } else {
                this.$router.push({
                    path: '/repository/warehouseMgt/gif/edit',
                    query: { id: id }
                });
            }
        },
        /**
         * 删除
         * @param id 动态图id
         * @param name 动态图名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                center: true
            }).then(() => {
                this.$api.removeBaseDocument({ id: id }).then(res => {
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
.gif {
}
</style>
