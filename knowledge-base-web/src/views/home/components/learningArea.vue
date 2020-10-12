<!--
@file：首页-最新学习组件
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="learningArea">
        <div class="learningArea-title clearfix">
            <span class="title fl">最新学习</span>
            <el-button class="btn fr" @click="showMore">更多</el-button>
        </div>
        <div class="learningArea-list">
            <el-row class="w100p ml0" :gutter="20">
                <el-col :span="8"
                        v-for="item in learningList"
                        :key="item.curriculumId">
                    <div  class="learningArea-list-item" @click="goDetails(item.curriculumId)">
                        <el-image
                        class="learningArea-list-item-cover w100p"
                        :src="item.curriculumCoverUrl ? `${$img}${item.curriculumCoverUrl}` : require('assets/images/default-cover.png')"></el-image>
                        <p :title="item.curriculumName">{{ item.curriculumName }}</p>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'learningArea',
    data () {
        return {
            learningList: [
                /*{
                    id: 1,
                    curriculumCoverUrl: '',
                    curriculumName: '学习知识培训学习知识培训学习知识知知识知识知识知识识'
                },
                {
                    id: 2,
                    curriculumCoverUrl: '',
                    curriculumName: '学习知识培训学习知识培训学习知识知知识知识知识知识识'
                },
                {
                    id: 3,
                    curriculumCoverUrl: '',
                    curriculumName: '学习知识培训学习知识培训学习知识知知识知识知识知识识'
                }*/
            ]
        };
    },
    created () {
      this.getData();
    },
    methods: {
        /**
         * 更多
         */
        showMore () {
            this.$router.push({ name: 'learningMoreList' });
        },
        /**
         * 获取学习列表数据
         */
        async getData () {
          const params = {pageNum: 0, pageSize: 3};
          this.loading = true;
          const res = await this.$api.getCurrentUserCurriculumPage(params);
          this.learningList = res.list;
          console.log(this.learningList);
          this.loading = false;
        },
        /**
         * 查看详情
         * @param id
         */
        goDetails (id) {
            this.$router.push({
                name: 'assessMgtLearningDetail',
                query: { id: id, pageForm: 'home' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .learningArea {
        &-title {
            height: 46px;
            line-height: 46px;
            margin-bottom: 20px;
            .title {
                font-size:26px;
            }
            .btn {
                width:74px;
                height:28px;
                line-height: 28px;
                padding: 0;
                margin-top: 10px;
            }
        }
        &-list {
            width: 100%;
            padding: 36px 28px;
            background:rgba(255,255,255,1);
            box-shadow:0 1px 24px 0 rgba(0, 0, 0, 0.06);
            &-item {
                padding: 18px 10px;
                cursor: pointer;
                &:hover, &:focus {
                    box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                    border-radius:4px;
                }
                &-cover {
                    height: 116px;
                }
                p {
                    margin-top: 10px;
                    text-overflow: -o-ellipsis-lastline;
                    overflow: hidden;
                    text-overflow: ellipsis;
                    display: -webkit-box;
                    -webkit-line-clamp: 2;
                    line-clamp: 2;
                    -webkit-box-orient: vertical;
                    line-height: 26px;
                }
            }
        }
    }
</style>
