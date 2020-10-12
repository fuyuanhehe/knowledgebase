<!--
@file：项目协作-项目详情-项目进度
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="projectProgress">
        <div class="table-top">
            <span>项目状态：</span>
            <el-select v-model="search.progress" placeholder="请选择项目状态">
                <el-option
                        v-for="item in statusOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
            <el-button class="ml20 btn_bg_blue" @click="searchData">查询</el-button>
            <v-divider></v-divider>
            <div class="btn-box mb20">
                <el-button class="btn_border_blue" @click="editProjectStage('add')">增加阶段</el-button>
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
                    style="width: 100%">
                <el-table-column
                        align="center"
                        prop="name"
                        label="阶段名称">
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="blameUser"
                        label="阶段负责人">
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="beginDate"
                        label="开始时间">
                    <template slot-scope="scope">
                        <span>{{ scope.row.beginDate | date }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="endDate"
                        label="结束时间">
                    <template slot-scope="scope">
                        <span>{{ scope.row.endDate | date }}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        prop="progress"
                        label="进度">
                    <template slot-scope="scope">
                        <span>{{ scope.row.progress == '0'?'进行中': scope.row.progress == '1'?'已完工':
                          scope.row.progress == '2'?'失败': '无'}}</span>
                    </template>
                </el-table-column>
                <el-table-column
                        align="center"
                        label="操作">
                    <template slot-scope="scope">
                        <span class="cursor_p" @click="editProjectStage('edit', scope.row.id, scope.row)">
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
        <editStageDialog ref="editStageDialog" @submitForm="submitStage"></editStageDialog>
    </div>
</template>

<script>
import editStageDialog from '../dialog/editStageDialog';
export default {
    name: 'projectProgress',
    components: {
        editStageDialog
    },
    data () {
        return {
            statusOptions: [
                { value: '0', label: '进行中' },
                { value: '1', label: '已完工' },
                { value: '2', label: '失败' },
                { value: '3', label: '暂停' }
            ], // 项目状态下拉选项
            search: {
                pageNum: 1,
                pageSize: 10,
              progress: '',
              projectId: sessionStorage.getItem("projectId")
            },
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            total: 0, // 总条目数
            stageInfo: {}, // 项目阶段对象
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
             const res = await this.$api.getAllProjectStage(this.search);
             this.tableData = res.list;
             this.total = res.total;
            this.loading = false;
        },
        /**
         * 新增/编辑项目阶段
         * @param type 操作类型：add--新增；edit--修改
         * @param id 当前项目阶段id
         * @param item 当前项目阶段对象
         */
        editProjectStage (type, id, item) {
            if (type === 'add') {
              this.$refs.editStageDialog.handleShow();
            } else {
              this.$refs.editStageDialog.handleShow(item);
            }
        },
        /**
         * 删除
         * @param id 项目阶段id
         * @param name 项目阶段名
         */
        deleteItem (id, name) {
            this.$confirm('确定删除“' + name + '”?', '删除', {
                center: true
            }).then(() => {
                this.$api.removeProjectStage({ id: id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 提交阶段新增/修改
         */
        submitStage () {
            this.getData();
        },
        /**
         * pageSize 改变时会触发
         * @param pageSize
         */
        handleSizeChange (pageSize) {
            this.getData(1, pageSize);
        },
      /**
       * 查询
       */
      searchData () {
        this.getData(this.search.pageNum, this.search.pageSize);
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
    .projectProgress {
    }
</style>
