<!--
@file：首页管理-通知公告
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="notice">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <el-input class="w260 mr20"
                          clearable
                          suffix-icon="el-icon-search"
                          v-model="search.name"
                          placeholder="请输入关键字"
                          @change="getData(search.pageNum, search.pageSize)"></el-input>
                <el-button class="btn_bg_blue" @click="editNotice('add')">新增公告</el-button>
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
                            prop="name"
                            label="标题">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop=""
                            label="发布时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="userName"
                            label="发布人">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            label="操作">
                        <template slot-scope="scope">
                            <span class="cursor_p" @click="editNotice('edit', scope.row.id)">
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
    name: 'notice',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '首页管理',
                    path: '/system/homeMgt',
                    routeName: 'homeMgt',
                    disabled: true
                }, {
                    name: '通知公告',
                    path: '/system/homeMgt/notice',
                    routeName: 'notice',
                    disabled: true
                }
            ], // 面包屑导航集合
            search: {
                pageNum: 1,
                pageSize: 10,
                name: ''
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            total: 0 // 总条目数
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
            const res = await this.$api.getNoticePage(this.search);
            this.tableData = res.list;
            this.total = res.total;
            this.loading = false;
        },
        /**
         * 新增/编辑公告
         * @param type 操作类型：add--新增；edit--修改
         * @param id 公告id
         */
        editNotice (type, id) {
            this.$router.push({
                name: 'noticeEdit',
                query: { type: type, id: id }
            });
        },
        /**
         * 删除
         * @param id 通知公告id
         */
        deleteItem (id, name) {
          this.$confirm('确定删除“' + name + '”?', '删除', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            center: true
          }).then(() => {
            this.$api.removeNotice({ id: id }).then(res => {
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

</style>
