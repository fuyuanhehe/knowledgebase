<template>
    <div class="document">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content" @click="clearOperation">
            <div class="table-top mb20 clearfix">
                <div class="fl">
                    <el-button class="btn_bg_blue" @click="newFolder">新建文件夹</el-button>
                    <!--<el-button class="btn_border_blue" @click="upload">上传</el-button>-->
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
                    <el-button class="btn_border_blue" @click="transfer">转存到</el-button>
                    <el-button class="btn_border_blue" @click="downloadBatchItem">下载</el-button>
                    <el-button class="btn_border_blue" @click="deleteBatchItem">批量删除</el-button>
                </div>
                <div class="fr">
                    <el-input v-model.trim="search.name" class="w250 mr10" suffix-icon="el-icon-search"></el-input>
                    <img :src="require(showType === 'table' ? 'assets/images/doc-icon-list-active.png' : 'assets/images/doc-icon-list.png')" alt=""
                         @click.stop="changeShowType('table')">
                    <img :src="require(showType === 'thumbnail' ? 'assets/images/doc-icon-thumbnail-active.png' : 'assets/images/doc-icon-thumbnail.png')" alt=""
                         @click.stop="changeShowType('thumbnail')">
                </div>
            </div>
            <template v-if="showType === 'table'">
                <div class="table-box" @click.stop>
                    <el-table
                            ref="elTable"
                            :data="tableData"
                            v-loading="loading"
                            element-loading-text="拼命加载中"
                            element-loading-spinner="el-icon-loading"
                            element-loading-background="rgba(0, 0, 0, 0.8)"
                            header-cell-class-name="table-th"
                            highlight-current-row
                            style="width: 100%"
                            @selection-change="handleSelectionChange"
                            @row-contextmenu="rowRightClick"
                            @row-click="rowClick"
                            @row-dblclick="rowDblclick"
                            :row-class-name="tableRowClassName">
                        <el-table-column
                                align="center"
                                type="selection"
                                width="55"
                                :selectable="selectable">
                        </el-table-column>
                        <el-table-column
                                class-name="table-column-name"
                                label="名称"
                                width="400"
                                show-overflow-tooltip>
                            <template slot-scope="scope">
                                <img class="doc-icon" src="~assets/images/doc-type-folder.png" alt="" v-if="scope.row.type==0">
                                <template v-if="scope.row.type==1">
                                    <img class="doc-icon" src="~assets/images/doc-type-w.png" alt=""
                                         v-if="scope.row.suffix == '.doc'">
                                    <img class="doc-icon" src="~assets/images/doc-type-x.png" alt=""
                                         v-if="scope.row.suffix=='xls'">
                                    <img class="doc-icon" src="~assets/images/doc-type-p.png" alt=""
                                         v-if="scope.row.suffix=='pptx' || scope.row.suffix=='ppt'">
                                    <img class="doc-icon" src="~assets/images/doc-type-other.png" alt="" v-else>
                                </template>
                                <span class="table-column-name-edit" v-if="scope.row.isEdit">
                                    <el-input class="w250 ml20"
                                              autocomplete
                                              v-focus="scope.row.isFocus"
                                              ref="nameEdit"
                                              v-model="scope.row.name"
                                              @blur="tableInputBlur(scope.$index, scope.row.id, scope.row.name)"></el-input>
                                </span>
                                <span v-else>
                                    <span class="doc-name ml20">{{ scope.row.name }}</span>
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                label="类型">
                            <template slot-scope="scope">
                                <span v-if="scope.row.type===0">文件夹</span>
                                <span v-else>{{ scope.row.suffix }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="size"
                                label="大小">
                        </el-table-column>
                        <el-table-column
                                prop="updateTime"
                                label="更新时间">
                            <template slot-scope="scope">
                                <span>{{scope.row.updateTime | date('YYYY-MM-DD HH:ss')}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                align="center"
                                label="操作">
                            <template slot-scope="scope" v-if="scope.row.type!==''">
                                <span class="cursor_p" @click.stop="editItem('edit', scope.row.id, scope.$index)">
                                    <i class="table-icon el-icon-edit-outline mr15"></i>
                                </span>
                                    <span class="cursor_p" @click.stop="downloadItem(scope.row)">
                                    <i class="table-icon el-icon-download mr15"></i>
                                </span>
                                    <span class="cursor_p" @click.stop="deleteItem(scope.row)">
                                    <i class="table-icon el-icon-delete"></i>
                                </span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </template>
            <template v-else>
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
            </template>
            <ul id="menu" class="menu-list">
                <li class="menu-item" v-for="item in menuLists" @click.stop="menuClick(item.fnHandler)">{{ item.btnName }}</li>
            </ul>
        </div>
        <!--转存到对话框-->
        <el-dialog
                class="transfer-dialog"
                title="转存到"
                center
                :visible.sync="dialogVisible"
                width="600px">
            <div class="dialog-main">
                <el-input class="w100p" suffix-icon="el-icon-search" clearable
                          v-model="transferSearchText"></el-input>
                <div class="dialog-bottom mt20">
                    <el-tree
                            ref="tree"
                            lazy
                            :props="props"
                            :load="loadNode"
                            highlight-current
                            check-on-click-node
                            node-key="id"
                            :filter-node-method="filterNode">
                        <span class="custom-tree-node"  slot-scope="{ node, data }">
                            <img width="22" alt="" class="va_m"
                                 :src="!data.type ? require('assets/images/doc-type-folder.png') : require('assets/images/doc-type-other.png')">
                            <span>{{ node.label }}</span>
                        </span>
                    </el-tree>
                </div>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="transferSubmit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script src="./documentMgt.js"></script>

<style scoped lang="scss">
    .document {
        .content {
            min-height: 600px;
            padding: 40px 50px;
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
            .table-box {
                .doc-icon, .doc-name {
                    vertical-align: middle;
                }
                .table-column-name {
                    &-edit {
                        position: relative;
                        .el-input {
                            /*position: absolute;*/
                        }
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
        .transfer-dialog{
            .dialog-main {
                .dialog-bottom {
                    max-height: 400px;
                    overflow: auto;
                }
            }
        }
    }
</style>
