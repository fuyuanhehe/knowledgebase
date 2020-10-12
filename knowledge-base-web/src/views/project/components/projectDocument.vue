<!--
@file：项目协作-项目详情-项目文档
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="projectDocument">
        <div class="table-top">
            <el-button class="btn_bg_blue" @click="newFolder">新建文件夹</el-button>
            <upload-file
                    class="file-upload"
                    :limit="10"
                    :size="102400"
                    :file-list.sync="files"
                    :width="100"
                    accept="application/msword,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/pdf"
                    :suffix="['.doc', '.docx', 'xls', 'xlsx', 'ppt', 'pptx', 'pdf']"
                    icon=""
                    :fileListShow="false"
                    :isMsgShow="false"
                    @success="handleUploadSuccess"
                    text="上传">
            </upload-file>
            <el-button class="btn_border_blue" @click="downloadBatchItem">下载</el-button>
            <el-button class="btn_border_blue" @click="deleteBatchItem">批量删除</el-button>
        </div>
        <!--分割线（虚线）-->
        <v-divider></v-divider>
        <ul class="list-box clearfix" @click.stop>
            <li class="list-item fl"
                ref="listItem"
                v-for="(item,index) in tableData" :title="item.name"
                :class="{ 'list-item-active': item.selected }"
                @contextmenu.prevent="rowRightClick(item,index, $event)"
                @click="rowClick(item,index)"
                @dblclick="rowDblclick(item)">
                <div class="list-item-img">
                    <img class="doc-icon" src="~assets/images/doc-type-folder-large.png" alt="" v-if="item.type==0">
                    <template v-if="item.type==1">
                        <img class="doc-icon" src="~assets/images/doc-type-w-large.png" alt=""
                             v-if="item.suffix == '.doc'">
                        <img class="doc-icon" src="~assets/images/doc-type-x-large.png" alt=""
                             v-if="item.suffix=='xls'">
                        <img class="doc-icon" src="~assets/images/doc-type-p-large.png" alt=""
                             v-if="item.suffix=='pptx' || item.suffix=='ppt'">
                        <img class="doc-icon" src="~assets/images/doc-type-other-large.png" alt="" v-else>
                    </template>
                </div>
                <p class="list-item-name-edit" v-if="item.isEdit">
                    <el-input class=""
                              autocomplete
                              v-focus="item.isFocus"
                              ref="nameEdit"
                              v-model="item.name"
                              @blur="tableInputBlur(index, item.id, item.name)"></el-input>
                </p>
                <p v-else class="list-item-name">{{ item.name }}</p>
            </li>
        </ul>
        <ul id="menu" class="menu-list">
            <li class="menu-item" v-for="item in menuLists" @click.stop="menuClick(item.fnHandler)">{{ item.btnName }}</li>
        </ul>
    </div>
</template>

<script>
import config from 'api/api_conf';
const { global } = config;
export default {
    name: 'projectDocument',
    data () {
        return {
            search: {
                name: '',
                weight: 5,
                pid: 0,
              projectId: sessionStorage.getItem('projectId'), // 项目id
            }, // 数据查询参数对象
            loading: false, // table是否显示加载动画
            tableData: [{
                selected: false
            }], // table数据集合
            newFolderId: null, // 新建文件夹id
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
                    fnHandler: 'downloadItem',
                    btnName: '下载'
                }, {
                    fnHandler: 'deleteItem',
                    btnName: '删除'
                }
            ], // 右键菜单列表
            multipleSelection: [], // table选中集合
            docIds: [], // 选中的文档id集合
            tableInputFocus: false, // table文件编辑框是否获取焦点
            astDbId: 0, // 上级文件夹id/当前文件夹父id （用于当前是最后一个层级且当前层级为空时查询数据）
            currentListId: '0', // 缩略图模式当前点击文档id
            currentRightClickData: {}, // 当前右键点击文档对象
            files: [], // 上传文件列表
            multiFilePaths: '', // 批量下载/文件夹文件地址
            isCtrl: null // 是否按下ctrl
        };
    },
    directives: {
        // 自定义v-focus指令，el-input自动获取焦点
        focus: {
            inserted: function (el) {
                el.querySelector('input').focus();
            }
        }
    },
    mounted () {

      console.log(this.search.projectId);
      this.getData();
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
         * 新建文件夹
         */
        async newFolder (item) {
            const currentTime = new Date();
            const param = {
                updateTime: currentTime,
                weight: this.search.weight,
                pid: this.search.pid,
                projectId: this.search.projectId
            };
            await this.$api.saveBaseDocument(param).then(res => {
                this.newFolderId = res;
                this.getData('new');
            });
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
         * 上传
         */
        upload (item) {
            this.search.url = '';
            this.getData();
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
            this.currentRightClickData.index = column;
        },
        /**
         * 某一行被点击时触发
         */
        rowClick (row, index) {
            this.hiddenMenu();
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
            this.multipleSelection = [];
            this.docIds = [];
            this.tableData.forEach(data => {
                data.selected = false;
            });
            const arr = document.getElementsByClassName('list-item');
            for (let i = 0; i < arr.length; i++) {
                arr[i].classList.remove('list-item-active');
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
         * 获取选中文件及文件夹下的所有文件（用于批量下载）
         */
        getMultiFile () {
            if (Array.isArray(this.docIds) && this.docIds.length > 0) {
                this.$api.getMultiFile(this.docIds).then(res => {
                    this.multiFilePaths = res.join(';');
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
        }
    }
};
</script>

<style scoped lang="scss">
    .projectDocument {
        .table-top {
            .file-upload{
                display: inline-block;
                width: 100px;
                height: 38px;
                vertical-align: middle;
                margin: 0 10px;
                /deep/.upload-file {
                    height: 38px !important;
                    .upload-file-btn {
                        border-color: $color-btn-primary;
                        color: $color-btn-primary;
                        font-size: 14px;
                    }
                }
            }
            .fr {
                vertical-align: middle;
                img {
                    vertical-align: middle;
                    cursor: pointer;
                }
            }
        }
        .list-box {
            .list-item {
                width: 132px;
                height: 147px;
                text-align: center;
                padding: 23px 10px;
                margin: 0 20px 20px 0;
                cursor: pointer;
                &:hover, &:focus {
                    background: #EAF6FF;
                }
                &-name {
                    width: 100%;
                    @extend %ellipsis;
                }
                &-name-edit {
                    .el-input {
                        width: 100%;
                    }
                }
            }
            .list-item-active {
                background: #EAF6FF;
            }
        }
        .menu-list {
            display: none;
            position: fixed;
            z-index: 9999;
            background: #fff;
            top: 0;
            left: 0;
            border-radius: 2px;
            box-shadow: 0 2px 2px 0 #ccc;
            font-size: 12px;
            border: 1px solid #ddd;
            .menu-item {
                width: 150px;
                position: relative;
                background: #fff;
                text-decoration: none;
                list-style: none;
                height: 30px;
                line-height: 30px;
                padding: 0 15px;
                border-bottom: 1px solid #ddd;
                &:hover, &:focus {
                    background: #ebeef5;
                    cursor: pointer;
                }
            }
        }
    }
</style>
