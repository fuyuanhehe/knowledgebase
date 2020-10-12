<!--
@file：知识测评-试卷编辑
@createTime：2020-04-09
@updateTime：2020-04-13
@author：Claire
-->
<template>
    <div class="testPaperEdit">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="table-top mb20">
                <el-form :inline="true" ref="testPaperForm" :model="testPaperForm" :rules="rules" class="demo-form-inline">
                    <el-form-item label="试卷名称：" prop="name">
                        <el-input v-model="testPaperForm.name" placeholder="请输入试卷名称"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <!--</div>-->
            <div class="table-box">
                <el-table
                        ref="multiTable"
                        :data="tableData"
                        v-loading="loading"
                        element-loading-text="拼命加载中"
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        header-cell-class-name="table-th"
                        style="width: 100%"
                        :row-key="getRowKeys"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            align="center"
                            type="selection"
                            :selectable="checkSelectable"
                            :reserve-selection="true"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="type"
                            label="题型">
                        <template slot-scope="scope">
                            <span class="cursor_p">{{ scope.row.type ? '多选' : '单选' }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="name"
                            label="题目名称">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="difficultyName"
                            label="难度">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="score"
                            label="分值">
                    </el-table-column>
                    <el-table-column
                            align="center"
                            prop="commentCount"
                            label="创建时间">
                        <template slot-scope="scope">
                            <span>{{ scope.row.createTime | date }}</span>
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
            <!--分割线（虚线）-->
            <v-divider></v-divider>
            <div class="selected-box">
                <h5>已选择试题</h5>
                <div class="btn-box mb20 clearfix">
                    <div class="fl">
                        <el-button class="btn_border_blue" @click="deleteBatchItem">批量删除</el-button>
                        <span class="ml30">
                            <span class="mr10">排序</span>
                            <i class="btn-sort el-icon-arrow-up" @click="sortUp"></i>
                            <i class="btn-sort el-icon-arrow-down" @click="sortDown"></i>
                        </span>
                    </div>
                    <div class="mt10 fr">
                        <span class="mr30">题数：{{ testPaperForm.totalNum }}</span>
                        <span>总分：{{ testPaperForm.totalScore }}分</span>
                    </div>
                </div>
                <div class="table-box">
                    <el-table
                            :data="multipleSelection"
                            header-cell-class-name="table-th"
                            style="width: 100%"
                            :row-class-name="tableRowClassName"
                            @selection-change="handleSelectedSelectionChange">
                        <el-table-column
                                align="center"
                                type="selection"
                                width="55">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="sort"
                                type="index"
                                label="排序">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="type"
                                label="题型">
                            <template slot-scope="scope">
                                <span class="cursor_p">{{ scope.row.type ? '多选' : '单选' }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="name"
                                label="题目名称">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="difficultyName"
                                label="难度">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="score"
                                label="分值">
                        </el-table-column>
                        <el-table-column
                                align="center"
                                prop="commentCount"
                                label="创建时间">
                            <template slot-scope="scope">
                                <span>{{ scope.row.createTime | date }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                align="center"
                                label="操作">
                            <template slot-scope="scope">
                                <span class="cursor_p" @click="deleteItem(scope.row.id,scope.row.name)">
                                <i class="table-icon el-icon-delete"></i>
                                </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div class="btn-box mt40">
                    <el-button class="btn_bg_blue" @click="submitTestPaper()">确定</el-button>
                    <el-button class="btn_border_blue" @click="goBack">取消</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'testPaperEdit',
    data () {
        return {
            testPaperId: this.$route.params.id, // 试卷id
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '试卷库',
                    path: '/assess/resourceMgt/testPaperLibrary',
                    routeName: 'assessResourceMgtTestPaperLibrary'
                }, {
                    name: this.$route.params.type === 'add' ? '新增试卷' : '编辑试卷',
                    path: '/assess/resourceMgt/testPaperEdit',
                    routeName: 'assessResourceMgtTestPaperEdit',
                    disabled: true
                }
            ], // 面包屑导航集合
            testPaperName: '', // 试卷名称
            testPaperForm: {
                name: '',
                totalNum: 0,
                totalScore: 0
            }, //  试卷对象
            rules: this.validationRules(), // 表单验证规则
            search: {
                pageNum: 1,
                pageSize: 3,
                type: null,
                difficulty: '',
                score: '',
                beginCreateTime: '',
                endCreateTime: ''
            },
            difficultyOptions: [], // 题目难度下拉选项
            loading: false, // table是否显示加载动画
            tableData: [], // table数据集合
            multipleSelection: [], // table选中集合
            subjectIds: [], // table选中的题目id集合
            total: 0, // 总条目数
            subjectSelectedData: [], // 试题选中
            selectedSubjectIds: [], // 已选择试题table选中id集合
            selectedMultipleSelection: [], // 已选择试题table选中对象集合
            currentEditTestPaperName: '' // 修改时当前编辑页面的试卷名
        };
    },
    watch: {
        multipleSelection: {
            handler (val) {
                this.testPaperForm.totalScore = 0;
                if (Array.isArray(val) && val.length > 0) {
                    this.testPaperForm.totalNum = val.length;
                    for (let i = 0; i < val.length; i++) {
                        this.testPaperForm.totalScore += val[i].score;
                    }
                }
            },
            immediate: true
        }
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getDifficultyList();
                vm.getData();
            });
        } else { // 修改
            next(vm => {
                vm.testPaperId = to.params.id;
                vm.getDifficultyList();
                vm.getTestPaper();
            });
        }
    },
    methods: {
        /**
         * 获取table数据
         * @param pageNum
         * @param pageSize
         * @returns {Promise<void>}
         */
        async getData (pageNum = this.search.pageNum, pageSize = this.search.pageSize) {
            const _this = this;
            _this.search.pageNum = pageNum;
            _this.search.pageSize = pageSize;
            _this.loading = true;
            await _this.$api.getSubjectPage(_this.search).then(res => {
                _this.tableData = res.list;
                _this.total = res.total;
                _this.loading = false;
                if (Array.isArray(this.multipleSelection) && this.multipleSelection.length > 0) {
                    for (let j = 0; j < this.tableData.length; j++) {
                        for (let i = 0; i < this.multipleSelection.length; i++) {
                            if (this.tableData[j].id == this.multipleSelection[i].id) {
                                this.$set(this.tableData[j], 'disabled', true);
                            }
                        }
                    }
                }
                console.log('this.tableData.:', this.tableData);
            });
        },
        /**
         *获取题目难度下拉选项（字典查询）
         */
        async getDifficultyList () {
            const params = { groupCoder: 'subject_type' };
            this.difficultyOptions = await this.$api.getAllDict(params);
        },
        /**
         * 获取table数据
         * @returns {Promise<void>}
         */
        async getTestPaper () {
            this.loading = true;
            const res = await this.$api.getTestPaper({ id: this.testPaperId });
            this.testPaperForm = res;
            this.multipleSelection = JSON.parse(res.subjects);
            this.loading = false;
            this.getData();
            if (this.pageType === 'edit') {
                this.currentEditTestPaperName = this.testPaperForm.name;
            }
        },
        /**
         * 设置禁用table选择框
         */
        checkSelectable (row) {
            return !row.disabled;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            if (this.pageType === 'add') {
                this.multipleSelection = val;
                this.subjectIds = val.map((item) => {
                    return item.id;
                });
                for (let j = 0; j < this.tableData.length; j++) {
                    for (let i = 0; i < this.subjectIds.length; i++) {
                        if (this.tableData[j].id == this.subjectIds[i]) {
                            this.$set(this.tableData[j], 'disabled', true);
                        }
                    }
                }
            } else {
                const multiArr = val;
                for (let i = 0; i < multiArr.length; i++) {
                    const selectedStr = JSON.stringify(this.multipleSelection);
                    const multiStr = JSON.stringify(multiArr[i]);
                    if (selectedStr.indexOf(multiStr) == -1) {
                        this.multipleSelection.push(multiArr[i]);
                    }
                }
                this.subjectIds = this.multipleSelection.map((item) => {
                    return item.id;
                });
                for (let j = 0; j < this.tableData.length; j++) {
                    for (let i = 0; i < this.subjectIds.length; i++) {
                        if (this.tableData[j].id == this.subjectIds[i]) {
                            this.$set(this.tableData[j], 'disabled', true);
                        }
                    }
                }
            }
        },
        getRowKeys (row) {
            return row.id;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectedSelectionChange (val) {
            this.selectedMultipleSelection = val;
            this.selectedSubjectIds = val.map((item) => {
                return item.id;
            });
        },
        /**
         * 把每一行的索引放进row
         */
        tableRowClassName ({ row, rowIndex }) {
            row.index = rowIndex;
        },
        /**
         * 检查是否选择可操作
         */
        checkSelect (name) {
            const mult = this.selectedMultipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要' + name + '的试题！');
                return;
            }
            const ids = this.selectedSubjectIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要' + name + '的试题！');
            }
        },
        /**
         * 排序：获取选中对象中的最小索引值
         */
        getMinIndex (arr) {
            var list = [];
            for (let i = 0; i < arr.length; i++) {
                list.push(arr[i].index);
            }
            list.sort(function (a, b) {
                return a - b;
            });
            return list[0];
        },
        /**
         * 排序：获取选中对象中的最大索引值
         */
        getMaxIndex (arr) {
            var list = [];
            for (let i = 0; i < arr.length; i++) {
                list.push(arr[i].index);
            }
            list.sort(function (a, b) {
                return a - b;
            });
            return list[arr.length - 1];
        },
        /**
         * 排序：上移
         */
        sortUp () {
            this.checkSelect('排序');
            const multiArr = this.selectedMultipleSelection.sort(function (a, b) {
                return a.index - b.index;
            });
            const index = this.getMinIndex(multiArr);
            if (index === 0) {
                this.$message.warning('已经是列表中第一个素材！');
            } else {
                const temp = this.multipleSelection[index - 1];
                const len = multiArr.length;
                for (let i = 0; i < len; i++) {
                    this.$set(this.multipleSelection, index - 1 + i, multiArr[i]);
                }
                this.$set(this.multipleSelection, index + len - 1, temp);
            }
        },
        /**
         * 排序：下移
         */
        sortDown () {
            this.checkSelect('排序');
            const multiArr = this.selectedMultipleSelection.sort(function (a, b) {
                return a.index - b.index;
            });
            const maxIndex = this.getMaxIndex(multiArr);
            const minIndex = this.getMinIndex(multiArr);
            if (maxIndex === (this.multipleSelection.length - 1)) {
                this.$message.warning('已经是列表中最后一个素材！');
            } else {
                const temp = this.multipleSelection[maxIndex + 1];
                const len = multiArr.length;
                for (let i = 0; i < len; i++) {
                    this.$set(this.multipleSelection, minIndex + 1 + i, multiArr[i]);
                }
                this.$set(this.multipleSelection, maxIndex - len + 1, temp);
            }
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.selectedMultipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的试题！');
                return;
            }
            const ids = this.selectedSubjectIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的试题！');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                for (let i = 0; i < this.multipleSelection.length; i++) {
                    for (let j = 0; j < this.selectedSubjectIds.length; j++) {
                        if (this.multipleSelection[i].id == this.selectedSubjectIds[j]) {
                            if (this.pageType === 'add') {
                                this.$refs.multiTable.toggleRowSelection(this.multipleSelection[i], false);
                            } else {
                                this.multipleSelection.splice(i, 1);
                            }
                        }
                        for (let k = 0; k < this.tableData.length; k++) {
                            if (this.tableData[k].id === this.selectedSubjectIds[j]) {
                                this.$set(this.tableData[k], 'disabled', false);
                            }
                        }
                    }
                }
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 删除
         */
        deleteItem (id, name) {
            this.$confirm('确认删除' + name + '吗？', '提示', {
                center: true
            }).then(() => {
                for (const i in this.multipleSelection) {
                    if (this.multipleSelection[i].id === id) {
                        if (this.pageType === 'add') {
                            this.$refs.multiTable.toggleRowSelection(this.multipleSelection[i], false);
                        } else {
                            this.multipleSelection.splice(i, 1);
                        }
                        for (let j = 0; j < this.tableData.length; j++) {
                            if (this.tableData[j].id === id) {
                                this.$set(this.tableData[j], 'disabled', false);
                            }
                        }
                        break;
                    }
                }
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 保存试卷
         */
        submitTestPaper () {
            if (!this.testPaperForm.name) {
                this.$message.warning('请输入试卷名称！');
                return;
            }
            this.$refs.testPaperForm.validate(async (valid) => {
                if (valid) {
                    if (Array.isArray(this.multipleSelection) && this.multipleSelection.length > 0) {
                        if (this.pageType === 'add') { // 新增
                            const params = {
                                name: this.testPaperForm.name,
                                subjects: JSON.stringify(this.multipleSelection)
                            };
                            await this.$api.saveTestPaper(params).then(res => {
                                this.$message.success('试卷新增成功！');
                            });
                        } else { // 修改
                            const params = {
                                id: this.testPaperId,
                                name: this.testPaperForm.name,
                                subjects: JSON.stringify(this.multipleSelection)
                            };
                            await this.$api.modifyTestPaper(params).then(res => {
                                this.$message.success('试卷修改成功！');
                            });
                        }
                        this.goBack();
                    } else {
                        this.$message.warning('请选择需要添加的试题！');
                    }
                }
            });
        },
        /**
         * 取消（返回试卷库列表）
         */
        goBack () {
            this.$router.push('/assess/resourceMgt/testPaperLibrary');
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
        },
        /**
         * 表单验证规范
         * @returns
         */
        validationRules () {
            const checkTestPaperOnly = (rule, value, callback) => {
                // 修改题目时，判断当前输入名称与原名称是否一致，如一致则不验证
                if (this.pageType === 'edit' && value === this.currentEditTestPaperName) {
                    callback();
                } else {
                    this.$api.checkTestPaperName({ name: value }).then(res => {
                        if (!res) {
                            callback();
                        } else {
                            callback(new Error('该试卷名已经存在，请重新输入'));
                        }
                    });
                }
            };
            return {
                name: [
                    { required: true, message: '请输入试卷名称', trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在30个字符以内', trigger: 'blur' },
                    { validator: checkTestPaperOnly, trigger: 'blur' }
                ]
            };
        }
    }
};
</script>

<style scoped lang="scss">
    .testPaperEdit {
        .content {
            .selected-box {
                h5 {
                    margin-bottom: 25px;
                    font-size: 16px;
                    font-weight: 400;
                }
                .btn-box {
                    .btn-sort {
                        margin-right: 5px;
                        font-size: 18px;
                        cursor: pointer;
                        &:hover,&:focus {
                            color: $color-text-blue;
                        }
                    }
                }
            }
        }
    }
</style>
