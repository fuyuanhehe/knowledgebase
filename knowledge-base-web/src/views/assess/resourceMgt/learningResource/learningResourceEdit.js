const isContained = (a, b) => {
    if (!(a instanceof Array) || !(b instanceof Array)) return false;
    if (a.length < b.length) return false;
    // const aStr = a.toString();
    for (let i = 0, len = b.length; i < len; i++) {
        if (a.indexOf(b[i]) == -1) return false;
    }
    return true;
};
export default {
    name: 'learningResourceEdit',
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型：add-新增；edit-编辑
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '资源管理',
                    path: '/assess/resourceMgt',
                    routeName: 'assessResourceMgt'
                }, {
                    name: '学习资源',
                    path: '/assess/resourceMgt/learningResource',
                    routeName: 'assessResourceMgtLearningResource'
                }, {
                    name: this.$route.params.type === 'add' ? '新增学习资源' : '编辑学习资源',
                    path: '/assess/resourceMgt/learningResource' + this.$route.params.type,
                    routeName: 'assessResourceMgtLearningResourceEdit'
                }
            ], // 面包屑导航集合
            ruleForm: {
                name: '',
                learnBeginTime: '',
                learnEndTime: '',
                coverUrl: '',
                introduction: '',
                userIds: [],
                resources: []
            }, // 表单对象
            rules: this.validationRules(), // 表单验证规则
            learners: 0, // 学习人员
            coverUrl: [], // 封面图文件
            files: [],
            suffix: ['mp4'], // 课程资源可上传的文件后缀名
            coursewareSuffix: ['pptx', 'ppt'], // 课件可上传的文件后缀名
            coursewareFiles: [],
            resourcesList: [
                {
                    videoUrl: [], // 资源上传文件地址
                    data: [] // 课件上传文件地址
                }
            ], // 资源附件组集合
            resourcesObject: {
                videoUrl: [], // 资源上传文件地址
                data: [] // 课件上传文件地址
            }, // 资源附件对象
            dialogVisible: false, // 学习人员：指定人员对话框是否可见
            deptData: [], // 组织结构树数据
            defaultProps: {
                children: 'children',
                label: 'name'
            }, // 组织结构树配置配置选项
            userCheckAll: false, // 指定人员用户是否全选
            checkedUsers: [], // 选中人员对象集合
            checkedUserIds: [], // 选中人员id集合
            userList: [], // 用户列表
            userAllIds: [], // 所有用户id集合
            deptId: '', // 当前部门id
        };
    },
    watch: {
        userList: {
            handler (val) {
                const userIdArr = [];
                if (Array.isArray(val) && val.length > 0) {
                    const userArr = val[0].userArr;
                    for (let i = 0; i < userArr.length; i++) {
                        userIdArr.push(userArr[i].id);
                    }
                }
                this.userCheckAll = isContained(this.checkedUserIds, userIdArr);
            },
            immediate: true,
            deep: true
        },
        checkedUserIds: {
            handler (val) {
                const userIdArr = [];
                const userList = this.userList;
                if (Array.isArray(userList) && userList.length > 0) {
                    const userArr = userList[0].userArr;
                    for (let i = 0; i < userArr.length; i++) {
                        userIdArr.push(userArr[i].id);
                    }
                }
                this.userCheckAll = isContained(val, userIdArr);
            },
            immediate: true,
            deep: true
        }
    },
    beforeRouteEnter (to, from, next) {
        if (to.params.type === 'add') { // 新增
            next(vm => {
                vm.getDeptTree();
                vm.getAllUser({});
            });
        } else { // 修改
            next(vm => {
                vm.getAllUser({});
                vm.getDeptTree();
                vm.getData(to.query.id);
            });
        }
    },
    methods: {
        /**
         * 获取学习资源信息
         * @param id 学习资源id
         */
        async getData (id) {
            const params = { id: id };
            this.ruleForm = await this.$api.getCurriculum(params);
            this.resourcesList = JSON.parse(this.ruleForm.resources);
            if (this.userAllIds.length == this.ruleForm.userIds.length) {
                this.learners = 0;
            } else {
                this.learners = 1;
                this.checkedUserIds = this.ruleForm.userIds;
                this.$api.getAllUser({ ids: this.checkedUserIds }).then(res => {
                    this.checkedUsers = res;
                });
            }
        },
        /**
         * 获取组织结构树数据
         */
        async getDeptTree () {
            this.deptData = await this.$api.getDeptTree({});
            this.deptId = this.deptData[0].id;
        },
        /**
         * 获取所有用户
         */
        getAllUser (param) {
            this.$api.getAllUser(param).then(res => {
                this.userList = [{
                    deptId: this.deptId,
                    userArr: res
                }];
                for (let i = 0; i < res.length; i++) {
                    this.userAllIds.push(res[i].id);
                }
            });
            console.log(' this.userAllIds:', this.userAllIds);
        },
        /**
         * 学习人员选中变化时触发
         * @param label 选中的 Radio label 值
         */
        handleLearnersChange (label) {
            if (label) {
                this.dialogVisible = true;
            }
        },
        /**
         * 组织结构树节点被点击时的回调
         * @param data 该节点所对应的对象
         */
        handleNodeClick (data) {
            const param = {
                deptId: data.id
            };
            this.deptId = data.id;
            this.getAllUser(param);
        },
        /**
         * 指定人员用户全选框变化时触发
         * @val 更新后的值（是否选中）
         */
        handleCheckAllChange (val, item) {
            if (val) {
                for (var i = 0; i < item.userArr.length; i++) {
                    let flag = true, flag1 = true;
                    if (Array.isArray(this.checkedUserIds) && this.checkedUserIds.length > 0) {
                        for (var k = 0; k < this.checkedUserIds.length; k++) {
                            if (item.userArr[i].id === this.checkedUserIds[k]) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (Array.isArray(this.checkedUsers) && this.checkedUsers.length > 0) {
                        for (var j = 0; j < this.checkedUsers.length; j++) {
                            if (item.userArr[i] === this.checkedUsers[j]) {
                                flag1 = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        this.checkedUserIds.push(item.userArr[i].id);
                    }
                    if (flag1) {
                        this.checkedUsers.push(item.userArr[i]);
                    }
                }
            } else {
                Array.prototype.remove = function (val) {
                    let index = this.indexOf(val);
                    if (index > -1) {
                        this.splice(index, 1);
                    }
                };
                for (var x = 0; x < item.userArr.length; x++) {
                    this.checkedUserIds.remove(item.userArr[x].id);
                    for (var y = 0; y < this.checkedUsers.length; y++) {
                        if (item.userArr[x].id == this.checkedUsers[y].id) {
                            this.checkedUsers.splice(y, 1);
                        }
                    }
                }
            }
        },
        /**
         * 指定人员用户多选框组变化时触发
         * @val 更新后的值（选中的数组）
         */
        handleCheckedUsersChange (val) {
        },
        /**
         * 指定人员用户选择框变化时触发
         * @val 更新后的值（是否选中）
         * @item 当前用户对象
         */
        handleCheckUserChange (val, item) {
            if (val) {
                this.checkedUsers.push(item);
            } else {
                for (let i = 0; i < this.checkedUsers.length; i++) {
                    if (this.checkedUsers[i].id === item.id) {
                        this.checkedUsers.splice(i, 1);
                    }
                }
            }
        },
        /**
         * 取消用户选择
         */
        cancelCheckedUser (id) {
            this.$confirm('确定删除?', '删除', {
                center: true
            }).then(() => {
                for (let i = 0; i < this.checkedUserIds.length; i++) {
                    if (id == this.checkedUserIds[i]) {
                        this.checkedUserIds.splice(i, 1);
                    }
                }
                for (let j = 0; j < this.checkedUsers.length; j++) {
                    if (id == this.checkedUsers[j].id) {
                        this.checkedUsers.splice(j, 1);
                    }
                }
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 追加课程资源及课件
         */
        addResources () {
            this.resourcesList.push(this.resourcesObject);
        },
        delResources (i) {
            this.$confirm('确定删除该资源？', '删除', {
                center: true
            }).then(() => {
                this.resourcesList.splice(i, 1);
                this.$message.success('删除成功!');
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 提交表单
         */
        submitForm () {
            this.ruleForm.resources = JSON.stringify(this.resourcesList);
            if (this.learners) {
                this.ruleForm.userIds = this.checkedUserIds;
            } else {
                this.ruleForm.userIds = this.userAllIds;
            }
            this.$refs.ruleForm.validate(async (valid) => {
                if (valid) {
                    const params = this.ruleForm;
                    if (this.pageType === 'add') { // 新增
                        await this.$api.saveCurriculum(params).then(res => {
                            this.$message.success('新增学习资源成功!');
                        });
                    } else { // 修改
                        await this.$api.modifyCurriculum(params).then(res => {
                            this.$message.success('修改学习资源成功!');
                        });
                    }
                    this.$router.push({ path: '/assess/resourceMgt/learningResource' });
                }
            });
        },
        /**
         * 取消（返回学习资源）
         */
        goBack () {
            this.$router.push({ path: '/assess/resourceMgt/learningResource' });
        },
        /**
         * 表单验证规则
         * @returns {{learners: {trigger: string, message: string, required: boolean}[], name: *[], introduction: *[]}}
         */
        validationRules () {
            const checkCoverUrl = (rule, value, callback) => {
                if (this.ruleForm.coverUrl.length > 0) {
                    callback();
                } else {
                    callback(new Error('请选择封面图'));
                }
            };
            const checkResources = (rule, value, callback) => {
                if (this.resourcesList[0].videoUrl.length > 0) {
                    callback();
                } else {
                    callback(new Error('请至少选择一个课程资源'));
                }
            };
            return {
                name: [
                    { required: true, message: '请输入名称', trigger: 'blur' },
                    { min: 1, max: 30, message: '长度在 1 到 30 个字符', trigger: 'blur' }
                ],
                learnBeginTime: [
                    { required: true, message: '请选择日期', trigger: 'change' }
                ],
                learnEndTime: [
                    { required: true, message: '请选择日期', trigger: 'change' }
                ],
                coverUrl: [
                    { required: true, validator: checkCoverUrl }
                ],
                introduction: [
                    { required: true, message: '请输入课程简介', trigger: 'blur' },
                    { min: 1, max: 1000, message: '长度在 1 到 1000 个字符', trigger: 'blur' }
                ],
                learners: [
                    { required: true, message: '请选择学习人员', trigger: 'change' }
                ],
                resources: [
                    { type: Array, required: true, validator: checkResources }
                ]
            };
        }
    }
};
