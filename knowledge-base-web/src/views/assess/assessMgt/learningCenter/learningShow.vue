<!--
@file：知识测评-学习中心-学习详情-课程详情
@createTime：2020-04-15
@updateTime：2020-04-16
@author：Claire
-->
<template>
    <div class="learningShow">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="learn-title">{{ learningInfo.curriculumName }}</div>
            <div class="learn-content">
                <ul class="learn-list display_ib">
                    <li class="learn-item learn-item-title">课程</li>
                    <li class="learn-item"
                        :class="currentShowIndex == index ? 'c_blue': ''"
                        :title="splitFileName(item.name)"
                        v-for="(item, index) in learningList"
                        @click="switchShow(item, index)">
                        {{ splitFileName(item.name) }}
                    </li>
                </ul>
                <div class="learn-show">
                    <template v-if="activeCurriculum.name.indexOf('.mp4') != -1">
                        <video class="learn-show-video w100p"
                               controls="controls"
                               :src="`${$img}${activeCurriculum.url}`">
                            您的浏览器不支持 video 标签。
                        </video>
                    </template>
                    <template v-else>
                        <div class="learn-show-ppt">
                            <officeEditor :fileUrl="activeCurriculum.url" @init="initOfficeEditor" v-if="officeEditorFlag"></officeEditor>
                        </div>
                    </template>
                    <div class="text_c mt40">
                        <el-button class="btn_border_blue"
                                   :disabled="currentShowIndex == 0"
                                   @click="lastShow">上一课程</el-button>
                        <el-button class="btn_bg_blue"
                                   :disabled="currentShowIndex == learningList.length-1"
                                   @click="nextShow">下一课程</el-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import officeEditor from 'components/OfficeEditor.vue'; // ONLYOFFICE文档编辑器
export default {
    name: 'learningShow',
    components: {
        officeEditor
    },
    data () {
        return {
            learningId: this.$route.query.id, // 当前学习课程id
            currentShowIndex: this.$route.query.showIndex, // 当前展示课程资源
            breadcrumbNav: [
                {
                    name: '知识测评',
                    path: '/assess',
                    routeName: 'assess'
                }, {
                    name: '学习中心',
                    path: '/assess/assessMgt/learningCenter',
                    routeName: 'assessMgtLearningCenter'
                }, {
                    name: '学习详情',
                    path: '/assess/assessMgt/learningDetail/' + this.$route.query.id,
                    routeName: 'assessMgtLearningDetail'
                }, {
                    name: '课程详情',
                    path: '/assess/assessMgt/learningShow',
                    routeName: 'assessMgtLearningShow',
                    disabled: true
                }
            ], // 面包屑导航集合
            learningInfo: '', // 课程对象
            learningList: [], // 课程资源列表
            activeCurriculum: {
                name: '',
                url: ''
            }, // 当前展示课程资源
            learnRecord: [], // 学习记录
            officeEditor: null, // ONLYOFFICE文档编辑器对象
            officeEditorFlag: false // 是否显示ONLYOFFICE文档编辑器
        };
    },
    watch: {
        'activeCurriculum.name': {
            handler (val) {
                if (val && val.indexOf('.mp4') == -1) {
                    this.officeEditorFlag = true;
                }
            },
            immediate: true,
            deep: true
        },
        '$route.query.pageForm': {
            handler (val) {
                if (val === 'learningCenter') {
                    this.breadcrumbNav = [
                        {
                            name: '知识测评',
                            path: '/assess',
                            routeName: 'assess'
                        }, {
                            name: '学习中心',
                            path: '/assess/assessMgt/learningCenter',
                            routeName: 'assessMgtLearningCenter'
                        }, {
                            name: '学习详情',
                            path: '/assess/assessMgt/learningDetail?id=' + this.learningId + '&pageForm=' + val,
                            routeName: 'assessMgtLearningDetail'
                        }, {
                            name: '课程详情',
                            path: '/assess/assessMgt/learningShow',
                            routeName: 'assessMgtLearningShow',
                            disabled: true
                        }
                    ];
                } else if (val === 'home') {
                    this.breadcrumbNav = [
                        {
                            name: '首页',
                            path: '/home',
                            routeName: 'home'
                        }, {
                            name: '学习详情',
                            path: '/assess/assessMgt/learningDetail?id=' + this.learningId + '&pageForm=' + val,
                            routeName: 'assessMgtLearningDetail'
                        }, {
                            name: '课程详情',
                            path: '/assess/assessMgt/learningShow',
                            routeName: 'assessMgtLearningShow',
                            disabled: true
                        }
                    ];
                } else if (val === 'learningMoreList') {
                    this.breadcrumbNav = [
                        {
                            name: '首页',
                            path: '/home',
                            routeName: 'home'
                        }, {
                            name: '最新学习',
                            path: '/home/learningMoreList',
                            routeName: 'learningMoreList'
                        }, {
                            name: '学习详情',
                            path: '/assess/assessMgt/learningDetail?id=' + this.learningId + '&pageForm=' + val,
                            routeName: 'assessMgtLearningDetail'
                        }, {
                            name: '课程详情',
                            path: '/assess/assessMgt/learningShow',
                            routeName: 'assessMgtLearningShow',
                            disabled: true
                        }
                    ];
                }
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
         * 获取课程信息
         */
        getData () {
            const params = { curriculumId: this.learningId };
            this.$api.getCurrentUserCurriculum(params).then(res => {
                this.learningInfo = res;
                this.learningList = [];
                this.learnRecord = res.learnRecord ? JSON.parse(res.learnRecord) : [];
                const arr = JSON.parse(res.curriculumResources);
                for (let i = 0; i < arr.length; i++) {
                    this.learningList.push({
                        url: arr[i].videoUrl[0].url,
                        name: arr[i].videoUrl[0].name
                    });
                    if (this.currentShowIndex == i) {
                        this.activeCurriculum = {
                            url: arr[i].videoUrl[0].url,
                            name: arr[i].videoUrl[0].name,
                            index: i
                        };
                    }
                }
            });
        },
        /**
         * 初始化ONLYOFFICE文档编辑器
         */
        initOfficeEditor (data) {
            this.officeEditor = data;
        },
        /**
         * 切换资源展示
         */
        switchShow (item, index) {
            this.currentShowIndex = index;
            this.activeCurriculum = {
                name: item.name,
                url: item.url,
                index: index
            };
            this.modifyCurriculum(index);
        },
        /**
         * 上一课程
         */
        lastShow () {
            const lastInd = this.activeCurriculum.index - 1;
            for (let i = 0; i < this.learningList.length; i++) {
                if (lastInd == i) {
                    this.activeCurriculum = {
                        url: this.learningList[i].url,
                        name: this.learningList[i].name,
                        index: i
                    };
                    this.currentShowIndex = i;
                    this.modifyCurriculum(i);
                }
            }
        },
        /**
         * 下一课程
         */
        nextShow () {
            const nextInd = this.activeCurriculum.index + 1;
            for (let i = 0; i < this.learningList.length; i++) {
                if (nextInd == i) {
                    this.activeCurriculum = {
                        url: this.learningList[i].url,
                        name: this.learningList[i].name,
                        index: i
                    };
                    this.currentShowIndex = i;
                    this.modifyCurriculum(i);
                }
            }
        },
        /**
         * 修改课程资源学习记录
         */
        modifyCurriculum (index) {
            const includesIndex = this.learnRecord.indexOf(index);
            if (includesIndex > -1) {
                this.learnRecord.splice(includesIndex, 1);
                this.learnRecord.push(index);
            } else {
                this.learnRecord.push(index);
            }
            const params = {
                userId: this.learningInfo.userId,
                curriculumId: this.learningId,
                learnRecord: this.learnRecord
            };
            this.$api.modifyUserCurriculum(params);
        },
        /**
         * 截取文件名
         * @param text
         * @returns {*}
         */
        splitFileName (text) {
            return text.substring(0, text.lastIndexOf('.'));
        }
    }
};
</script>

<style scoped lang="scss">
    .learningShow {
        .content {
            .learn-title {
                width: 100%;
                height:80px;
                line-height: 80px;
                text-align: center;
                background: $bg-color-1;
                border-radius:4px;
            }
            .learn-content {
                margin-top: 50px;
                .learn-list {
                    width: 20%;
                    vertical-align: top;
                    padding-bottom: 60px;
                    box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                    .learn-item {
                        @extend %ellipsis;
                        width: 100%;
                        height: 60px;
                        line-height: 60px;
                        text-align: center;
                        padding: 0 20px;
                        border-bottom: 1px solid $color-border-2;
                        cursor: pointer;
                    }
                    .learn-item-title {
                        color: $color-text-blue;
                        background: $bg-color-1;
                        cursor: default;
                    }
                }
                .learn-show {
                    display: inline-block;
                    margin-left: 40px;
                    width: 75%;
                    &-ppt {
                        width: 100%;
                        height: 550px;
                    }
                }
            }
        }
    }
</style>
