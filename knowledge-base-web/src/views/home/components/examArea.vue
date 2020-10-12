<!--
@file：首页-最新考试组件
@createTime：2020-04-24
@updateTime：2020-04-24
@author：Claire
-->
<template>
    <div class="examArea">
        <div class="examArea-title clearfix">
            <span class="title fl">最新考试</span>
            <el-button class="btn fr" @click="showMore">更多</el-button>
        </div>
        <ul class="examArea-list">
            <img src="~assets/images/home-exam-img.png" alt="">
            <li class="examArea-list-item"
                v-for="exam in examList"
                :key="exam.examId"
                @click="goDetails(exam.examId)">
                <p>{{ exam.examName }}</p>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'examArea',
    data () {
        return {
            examList: [
                /*{
                    id: 1,
                    examName: '19年xxxx第三季度电力知识模拟考试模拟考试'
                }, {
                    id: 2,
                    examName: '19年xxxx第三季度电力知识模拟考试'
                }, {
                    id: 3,
                    examName: '19年xxxx第三季度电力知识模拟考试'
                }, {
                    id: 4,
                    examName: '19年xxxx第三季度电力知识模拟考试'
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
            this.$router.push({ name: 'examMoreList' });
        },
        /**
         * 获取考试列表数据
         */
        async getData () {
          const params = {pageNum: 0, pageSize: 4};
          this.loading = true;
          let data = await this.$api.getCurrentUserExamPage(params);
          this.examList = data.list;
          this.loading = false;
        },
        /**
         * 查看详情(参加考试)
         * @param id
         */
        goDetails (id) {
            this.$router.push({
                name: 'assessMgtTakeExam',
                query: { id: id, pageForm: 'home' }
            });
        }
    }
};
</script>

<style scoped lang="scss">
    .examArea {
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
            padding: 20px 28px 6px;
            background:rgba(255,255,255,1);
            box-shadow:0 1px 24px 0 rgba(0, 0, 0, 0.06);
            &-item {
                cursor: pointer;
                p {
                    @extend %ellipsis;
                    width: 100%;
                    line-height: 35px;
                    &:before {
                        content: '';
                        display: inline-block;
                        width: 4px;
                        height: 4px;
                        margin-right: 15px;
                        vertical-align: middle;
                        background: $color-text-blue;
                        border-radius: 50%;
                    }
                }
                &:hover, &:focus {
                    background: #F7F7F7;
                }
            }
        }
    }
</style>
