import config from 'api/api_conf';
const { global } = config;

export default {
    name: 'documentMgt',
    data () {
        return {
            pageType: this.$route.params.type, // 页面类型：company--公司文档；departmental--部门文档；personal--个人文档
            pageName: '',
            breadcrumbNav: [
                {
                    name: '文档管理 ',
                    path: '/repository/documentMgt/company',
                    routeName: 'repositoryDocumentMgt'
                },
                {
                    name: '',
                    path: '',
                    routeName: 'repositoryDocumentMgt'
                }
            ], // 面包屑导航集合
            showType: 'table', // 数据展示方式： table-列表;thumbnail-缩略图
            search: {
                name: '',
                weight: 0,
                pid: 0
            }, // 数据查询参数对象
            loading: false, // table是否显示加载动画
            tableData: [{
                selected: false
            }], // table数据集合
            multipleSelection: [], // table选中集合
            docIds: [], // 选中的文档id集合
            tableInputFocus: false, // table文件编辑框是否获取焦点
            total: 1, // 总条目数
            menuLists: [
                {
                    fnHandler: 'newFolder', // (绑定事件)
                    btnName: '新建文件夹' // (菜单名称)
                }, {
                    fnHandler: 'rename',
                    btnName: '重命名'
                }, {
                    fnHandler: 'upload',
                    btnName: '上传'
                }, {
                    fnHandler: 'transfer',
                    btnName: '转存到'
                }, {
                    fnHandler: 'downloadItem',
                    btnName: '下载'
                }, {
                    fnHandler: 'deleteItem',
                    btnName: '删除'
                }
            ], // 右键菜单列表
            newFolderId: null, // 新建文件夹id
            lastDbId: 0, // 上级文件夹id/当前文件夹父id （用于当前是最后一个层级且当前层级为空时查询数据）
            currentListId: '0', // 缩略图模式当前点击文档id
            currentRightClickData: {}, // 当前右键点击文档对象
            files: [], // 上传文件列表
            dialogVisible: false, // 转存到对话框是否显示
            props: {
                label: 'name',
                children: 'zones',
                isLeaf: 'leaf'
            }, // 文档树配置配置选项
            transferSearchText: '', // 转存到对话框搜索框内容
            transferIds: [], // 转存的文档id集合
            multiFilePaths: '', // 批量下载/文件夹文件地址
            previewDialogVisible: false,
            isCtrl: null, // 是否按下ctrl
            isShift: false // 是否按下shift
        };
    },
    watch: {
        '$route.params.type': {
            handler (val) {
                let name = '';
                if (val === 'company') {
                    name = '公司文档';
                    this.search.weight = 0;
                    this.getData();
                } else if (val === 'departmental') {
                    name = '部门文档';
                    this.search.weight = 1;
                    this.getData();
                } else {
                    name = '个人文档';
                    this.search.weight = 2;
                    this.getData();
                }
                // this.pageName = name;
                this.breadcrumbNav[1].name = name;
                this.breadcrumbNav[1].path = '/repository/documentMgt/' + val;
            },
            immediate: true,
            deep: true
        },
        transferSearchText (val) {
            this.$refs.tree.filter(val);
        }
    },
    directives: {
        // 自定义v-focus指令，el-input自动获取焦点
        focus: {
            inserted: function (el) {
                el.querySelector('input').focus();
            }
        }
    },
    created () {
    },
    mounted () {
        this.keyevent();
    },
    methods: {
        /**
         * 获取table数据
         * @param type 调用情况： 'new'—新建文件夹；
         * @returns {Promise<void>}
         */
        async getData (type = '') {
            this.loading = true;
            this.tableData = await this.$api.getAllBaseDocument(this.search);
            this.loading = false;
            if (this.search.pid != '0') {
                this.tableData.unshift({
                    id: '-1',
                    name: '...（返回上一级）',
                    type: '',
                    size: '',
                    time: ''
                });
            }
            if (type === 'new') {
                let newFolderIndex = '';
                const tableArr = this.tableData;
                for (let i = 0; i < tableArr.length; i++) {
                    if (this.newFolderId == tableArr[i].id) {
                        newFolderIndex = i;
                    }
                }
                console.log('newFolderIndex:', newFolderIndex);
                this.$set(this.tableData[newFolderIndex], 'isEdit', true);
                this.$set(this.tableData[newFolderIndex], 'isFocus', true);
            }
        },
        /**
         * 查询某个文档获取到pid，查询table数据
         * @param id
         * @returns {Promise<void>}
         */
        async getDocDetail (id) {
            await this.$api.getBaseDocument({ id: id }).then(res => {
                this.search.pid = res.pid;
                if (this.search.pid != 0) {
                    this.getData();
                } else {
                    this.getData();
                }
            });
        },
        /**
         * 切换展示方式
         * @param type 展示方式：table-列表;thumbnail-缩略图
         */
        changeShowType (type) {
            this.showType = type;
            this.multipleSelection = [];
        },
        /**
         * 设置table禁用多选框(‘返回上级’行)
         * @param row 当前行对象
         */
        selectable (row) {
            let flg = true;
            if (row.type === '') {
                flg = false;
            }
            return flg;
        },
        /**
         * table选择项发生变化时会触发
         * @param val 返回选中集合
         */
        handleSelectionChange (val) {
            this.multipleSelection = val;
            this.docIds = val.map((item) => {
                return item.id;
            });
            // console.log('this.multipleSelection:', this.multipleSelection);
            // console.log('this.docIds:', this.docIds);
        },
        /**
         * 把每一行的索引放进row
         */
        tableRowClassName ({ row, rowIndex }) {
            row.index = rowIndex;
        },
        /**
         * 鼠标右键点击时触发
         */
        rowRightClick (row, column, event) {
            this.stop(); // 禁止滚动
            const menu = document.querySelector('#menu');
            event.preventDefault();
            // 根据事件对象中鼠标点击的位置，进行定位
            menu.style.left = event.clientX + 'px';
            menu.style.top = event.clientY + 'px';
            // 改变自定义菜单的隐藏与显示
            menu.style.display = 'block';
            this.currentRightClickData = row;
            if (this.showType === 'table') {
                this.$refs.elTable.clearSelection();
                this.$refs.elTable.toggleRowSelection(row, true);
            } else {
                this.currentRightClickData.index = column;
            }
        },
        /**
         * 某一行被点击时触发
         */
        rowClick (row, index) {
            this.hiddenMenu();
            if (this.showType === 'table') {
                this.$refs.elTable.clearSelection();
                this.$refs.elTable.toggleRowSelection(row, true);
            } else {
                // this.currentListId = row.id;
                console.log('this.isCtrl:', this.isCtrl);
                if (this.isCtrl) {
                    row.selected = true;
                    this.$set(this.tableData, index, row);
                    this.multipleSelection.push(this.tableData[index]);
                    this.docIds.push(this.tableData[index].id);
                } else if (!this.isCtrl) {
                    this.multipleSelection = [];
                    this.docIds = [];
                    this.tableData.forEach(data => {
                        data.selected = false;
                    });
                    row.selected = true;
                    this.$set(this.tableData, index, row);
                    this.multipleSelection.push(this.tableData[index]);
                    this.docIds.push(this.tableData[index].id);
                }
                // console.log('this.multipleSelection:', this.multipleSelection);
                // console.log('this.docIds:', this.docIds);
            }
            if (row.id === '-1') {
                let id = '';
                if (this.tableData.length <= 1) {
                    // 如果是最后一个层级且当前层级为空，用自己存的上级文件夹id查数据
                    id = this.lastDbId;
                } else {
                    // 如果不是最后一个层级，用当前层级中返回的数据的pid
                    id = this.tableData[1].pid;
                }
                this.getDocDetail(id);
            }
        },
        /**
         * 某一行被双击击时触发
         * @param row [type:文档类型 0：文件夹  1：文件]
         */
        rowDblclick (row) {
            if (row.type) { // 文件: 在线预览文件
                const routeData = this.$router.resolve({
                    name: 'docFilePreview',
                    query: { url: row.url },
                    params: { url: row.url }
                });
                window.open(routeData.href, '_blank');
            } else { // 文件夹
                if (row.id != '-1') { // '返回上一级'无双击事件
                    this.search.pid = row.id;
                    this.lastDbId = row.id;
                    this.getData();
                }
            }
        },
        /**
         * 点击页面空白处清除之前的操作
         */
        clearOperation () {
            this.hiddenMenu();
            if (this.showType === 'table') {
                this.$refs.elTable.clearSelection(); // 清空table的选择
            } else {
                this.multipleSelection = [];
                this.docIds = [];
                this.tableData.forEach(data => {
                    data.selected = false;
                });
                const arr = document.getElementsByClassName('list-item');
                for (let i = 0; i < arr.length; i++) {
                    arr[i].classList.remove('list-item-active');
                }
            }
        },
        /**
         * 隐藏菜单
         */
        hiddenMenu () {
            this.move();
            const menu = document.querySelector('#menu');
            menu.style.display = 'none';
        },
        /**
         * 菜单点击触发事件
         */
        menuClick (methodsWords) {
            const item = this.currentRightClickData;
            this[methodsWords](item);
            this.hiddenMenu();
        },
        /**
         * 新建文件夹
         */
        async newFolder (item) {
            const currentTime = new Date();
            const param = {
                updateTime: currentTime,
                weight: this.search.weight,
                pid: this.search.pid
            };
            await this.$api.saveBaseDocument(param).then(res => {
                this.newFolderId = res;
                this.getData('new');
            });
        },
        /**
         * 新增，编辑
         */
        editItem (type, id, $index) {
            if (type === 'edit') {
                this.$set(this.tableData[$index], 'isEdit', true);
                this.$set(this.tableData[$index], 'isFocus', true);
            }
        },
        /**
         * table新增编辑文件夹输入框失去焦点时触发
         */
        tableInputBlur ($index, id, name) {
            this.$set(this.tableData[$index], 'isEdit', false);
            this.$set(this.tableData[$index], 'isFocus', false);
            const param = {
                id: id,
                name: name
            };
            this.$api.modifyBaseDocument(param).then(res => {
                // this.$message.success('编辑成功');
            });
        },
        /**
         * 重命名
         */
        rename (item) {
            this.editItem('edit', item.id, item.index);
        },
        /**
         * 上传
         */
        upload (item) {
            this.search.url = '';
            this.getData();
            console.log('upload');
        },
        /**
         *上传成功后触发
         * @res
         * @file
         */
        handleUploadSuccess (res, file) {
            const fileData = res.data;
            const currentPid = this.search.pid;
            for (let i = 0; i < fileData.length; i++) {
                this.$set(fileData[i], 'pid', currentPid);
                this.$set(fileData[i], 'type', 1);
                this.$set(fileData[i], 'weight', this.search.weight);
            }
            this.$api.addMultiBaseDocument(fileData).then(res => {
                this.getData();
                this.$message.success('上传成功');
            });
        },
        /**
         * 转存到
         */
        transfer (item = {}) {
            if (Array.isArray(this.docIds) && this.docIds.length > 0) {
                this.transferIds = this.docIds;
                this.dialogVisible = true;
            } else {
                this.$message.warning('请先选择需要转存的文档！');
            }
        },
        /**
         * 转存到对话框树节点过滤
         * @param value
         * @param data
         * @returns {boolean}
         */
        filterNode (value, data) {
            if (!value) return true;
            return data.name.indexOf(value) !== -1;
        },
        /**
         * 转存到对话框加载子树数据的方法
         */
        async loadNode (node, resolve) {
            if (node.level === 0) {
                let firstLevel = [];
                const param = {
                    name: this.transferSearch,
                    weight: 0,
                    pid: 0,
                    type: 0
                };
                await this.$api.getAllBaseDocument(param).then(res => {
                    firstLevel = res;
                    return resolve(firstLevel);
                }).catch(res => {
                    resolve([]);
                });
            } else {
                const param = {
                    name: this.transferSearch,
                    weight: 0,
                    pid: node.data.id,
                    type: 0
                };
                await this.$api.getAllBaseDocument(param).then(res => {
                    const list = [];
                    res.forEach(e => {
                        list.push(e);
                    });
                    return resolve(list);
                }).catch(res => {
                    resolve([]);
                });
            }
        },
        /**
         * 转存到对话框确定提交
         */
        transferSubmit () {
            const checkedTreeId = this.$refs.tree.getCurrentKey();
            const list = [];
            for (let i = 0; i < this.transferIds.length; i++) {
                list.push({
                    id: this.transferIds[i],
                    pid: checkedTreeId
                });
            }
            this.$api.updMultiBaseDocument(list).then(res => {
                this.dialogVisible = false;
                this.getData();
                this.$message.success('转存成功');
            });
        },
        /**
         * 获取选中文件及文件夹下的所有文件（用于批量下载）
         */
        getMultiFile () {
            if (Array.isArray(this.docIds) && this.docIds.length > 0) {
                this.$api.getMultiFile(this.docIds).then(res => {
                    this.multiFilePaths = res.join(';');
                    console.log('this.multiFilePaths:', this.multiFilePaths);
                    window.location.href = global + '/resources/downloadPack/?paths=' + this.multiFilePaths;
                });
            } else {
                this.$message.warning('请先选择需要下载的文档！');
            }
        },
        /**
         * 批量下载
         */
        downloadBatchItem () {
            if (Array.isArray(this.docIds) && this.docIds.length > 0) {
                this.getMultiFile();
            } else {
                this.$message.warning('请先选择需要下载的文档！');
            }
        },
        /**
         * 下载
         */
        downloadItem (item) {
            if (item.type) { // 类型:文件
                // this.$api.download(item.url);
                window.location.href = global + '/resources/download/?path=' + item.url;
            } else { // 类型:文件夹
                this.$refs.elTable.clearSelection();
                this.$refs.elTable.toggleRowSelection(item, true);
                this.getMultiFile();
            }
        },
        /**
         * 批量删除
         */
        deleteBatchItem () {
            const mult = this.multipleSelection;
            if (mult.length === 0) {
                this.$message.warning('请先选择需要删除的文档！');
                return;
            }
            const ids = this.docIds;
            if (ids.length === 0) {
                this.$message.error('请选择需要删除的文档!');
                return;
            }
            this.$confirm('确认删除吗？', '提示', {
                center: true
            }).then(async () => {
                await this.$api.removeBaseDocumentList(ids);
                this.$message.success('删除成功');
                this.getData();
            });
        },
        /**
         * 删除
         * @param row 文档对象
         */
        deleteItem (row) {
            this.$confirm('确定删除“' + row.name + '”?', '删除', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                center: true
            }).then(() => {
                this.$api.removeBaseDocument({ id: row.id }).then(res => {
                    this.$message.success('删除成功');
                    this.getData();
                });
            }).catch(() => {
                this.$message.info('已取消删除');
            });
        },
        /**
         * 禁止滚动
         */
        stop () {
            const mo = function (e) {
                e.preventDefault();
            };
            document.body.parentNode.style.overflow = 'hidden';
            document.addEventListener('touchmove', mo, false); // 禁止页面滑动
        },
        /**
         * 取消滚动限制
         */
        move () {
            const mo = function (e) {
                e.preventDefault();
            };
            document.body.parentNode.style.overflow = 'auto'; // 出现滚动条
            document.removeEventListener('touchmove', mo, false);
        },
        /**
         * 键盘操作
         */
        keyevent () {
            const _this = this;
            document.onkeydown = function (e) { // 按下键盘
                if (e.keyCode === 17 || e.ctrlKey) {
                    _this.isCtrl = true;
                } else if (e.keyCode === 16 || e.shiftKey) {
                    _this.isShift = true;
                }
            };
            document.onkeyup = function (e) { // 放弃键盘
                if (e.keyCode === 17 || e.ctrlKey) {
                    _this.isCtrl = false;
                } else if (e.keyCode === 16 || e.shiftKey) {
                    _this.isShift = false;
                }
            };
            // console.log(_this.isCtrl);
        }
    }
};
