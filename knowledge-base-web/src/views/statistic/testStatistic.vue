<!--
@file：统计分析-测评考试统计
@createTime：2020-04-22
@updateTime：2020-04-27
@author：Claire
-->
<template>
    <div class="testStatistic">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="content-item">
                <el-row :gutter="20">
                    <el-col class="" :span="6">
                        <div class="content-item-statistics">
                            <img src="~assets/images/statistic-exam-img.png" alt="">
                            <div class="content-item-statistics-info">
                                <p class="">{{ examNum }}次</p>
                                <p>发布考试</p>
                            </div>
                        </div>
                    </el-col>
                    <el-col class="" :span="6">
                        <div class="content-item-statistics">
                            <img src="~assets/images/statistic-paper-img.png" alt="">
                            <div class="content-item-statistics-info">
                                <p class="">{{ paperNum }}份</p>
                                <p>试卷总份数</p>
                            </div>
                        </div>
                    </el-col>
                    <el-col class="" :span="6">
                        <div class="content-item-statistics">
                            <img src="~assets/images/statistic-subject-img.png" alt="">
                            <div class="content-item-statistics-info">
                                <p class="">{{ subjectNum }}份</p>
                                <p>题库总份数</p>
                            </div>
                        </div>
                    </el-col>
                    <el-col class="" :span="6">
                        <div class="content-item-statistics">
                            <img src="~assets/images/statistic-learn-img.png" alt="">
                            <div class="content-item-statistics-info">
                                <p class="">{{ curriculumNum }}次</p>
                                <p>发布学习</p>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div class="content-item">
                <el-row :gutter="20">
                    <el-col class="" :span="12">
                        <div class="content-item-chart">
                            <div class="content-item-chart-title">
                                <span>员工考试次数</span>
                                <span>（近半年数据）</span>
                            </div>
                            <div class="content-item-chart-content">
                                <div class="mb20">
                                    <el-cascader :props="props"
                                                 :options="deptOptions"
                                                 @change = "userChange"
                                                 @active-item-change="cascaderItemChange"
                                                 expand-trigger="click"></el-cascader>
                                  <!--deptVal-->
                            <!--        <el-select class="w150 mr15" v-model="deptVal" placeholder="部门">
                                        <el-option
                                                v-for="item in deptOptions"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.name">
                                        </el-option>
                                    </el-select>-->
                                    <el-select  @change="getUserExamStat" class="w150" v-model="userVal" placeholder="姓名">
                                        <el-option
                                                v-for="item in userOptions"
                                                :key="item.id"
                                                :label="item.name"
                                                :value="item.id"
                                        >
                                        </el-option>
                                    </el-select>
                                </div>
                                <div class="chart-box">
                                    <div id="userExamStatChart" :style="{width: '100%', height: '480px'}"></div>
                                </div>
                            </div>
                        </div>
                    </el-col>
                    <el-col class="" :span="12">
                        <div class="content-item-chart">
                            <div class="content-item-chart-title">
                                <span>学习人数</span>
                                <span>（近一周）</span>
                            </div>
                            <div class="content-item-chart-content">
                                <div class="chart-box">
                                    <div id="userLearnedStatChart" :style="{width: '100%', height: '480px'}"></div>
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <div class="content-item">
                <el-row :gutter="20">
                    <el-col class="" :span="15">
                        <div class="content-item-chart">
                            <div class="content-item-chart-title">
                                <span>题库更新数</span>
                                <span>（近一月数据）</span>
                            </div>
                            <div class="content-item-chart-content">
                                <div class="chart-box">
                                  <div id="updateCount" :style="{width: '100%', height: '600px'}"></div>
                                </div>
                            </div>
                        </div>
                    </el-col>
                    <el-col class="" :span="9">
                        <div class="content-item-chart">
                            <div class="content-item-chart-title">
                                <span>题型分类</span>
                            </div>
                            <div class="content-item-chart-content">
                                <div class="chart-box">
                                    <div id="subjectTypeStatChart" :style="{width: '100%', height: '480px'}"></div>
                                </div>
                            </div>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'testStatistic',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '统计分析',
                    path: '/statistic',
                    routeName: 'statistic'
                }, {
                    name: '测评考试统计',
                    path: '/statistic/testStatistic',
                    routeName: 'testStatistic',
                    disabled: true
                }
            ], // 面包屑导航集合
            examNum: '', // 考试总数
            paperNum: '', // 试卷总数
            subjectNum: '', // 题目总数
            curriculumNum: '', // 学习总数
            props: {
                label: 'name',
                value: 'id',
                children: 'children'
            },
            deptVal: '', // 员工考试次数条件筛选：部门下拉选中
            deptOptions: [], // 员工考试次数条件筛选：部门下拉选项
            userVal: '', // 员工考试次数条件筛选：姓名下拉选中
            userOptions: [] // 员工考试次数条件筛选：姓名下拉选项
        };
    },
    created () {
        this.getExamCount();
        this.getTestPaperCount();
        this.getSubjectCount();
        this.getCurriculumCount();
        this.getDeptList();
    },
    mounted () {
        this.getUserExamStat();
        this.getUserLearnedStat();
        this.getSubjectTypeStat();
        this.getupdateCountChart();

   /*   let day = this.getDay(0);
      let day7 = this.getDay(-7);
      console.log(day)
      console.log(day7)*/


    },
    methods: {
        /**
         * 查询考试总数
         */
        getExamCount () {
            this.$api.getExamCount({}).then(res => {
                this.examNum = res;
            });
        },
        /**
         * 查询试卷总数
         */
        getTestPaperCount () {
            this.$api.getTestPaperCount({}).then(res => {
                this.paperNum = res;
            });
        },
        /**
         * 查询题目总数
         */
        getSubjectCount () {
            this.$api.getSubjectCount({}).then(res => {
                this.subjectNum = res;
            });
        },
        /**
         * 查询学习总数
         */
        getCurriculumCount () {
            this.$api.getCurriculumCount({}).then(res => {
                this.curriculumNum = res;
            });
        },
        /**
         * 获取部门列表
         */
        async getDeptList () {
            this.deptOptions = await this.$api.getAllDept({});
        },

        userChange (data) {
            this.deptVal = '';
          this.userOptions = [];
            this.$api.getAllUser({ "deptId":data[0] }).then(res => {
              this.userOptions = res;
              this.userVal = res[0];
            });

        },

        cascaderItemChange (item) {
          console.log(item)
        },

      // 获取最近6个月
      getMonths(){
        //创建现在的时间
        let data = new Date();
        //获取年
        let year = data.getFullYear();
        //获取月
        let mon = data.getMonth()+2;
        let arry = new Array();
        for(let i=0; i<6; i++){
          mon = mon - 1;

          if(mon <= 0 ){
            year = year-1;
            mon = mon + 12;
          }
          if(mon<10){
            mon="0"+mon;
          }
          arry[i] = year+"-"+mon;
        }
        return arry;
      },

        /**
         * 获取员工考试统计
         */
        getUserExamStat () {

          let da = this.getMonths();
            const params = {
                type: 'custom',
                otherCondition: {
                 //   deptId: this.deptVal,
                    userId: this.userVal
                },
                beginTime: da[5],
                endTime: da[0]
            };
            this.$api.getUserExamStat(params).then(res => {
                const dataAxis = res.dataAxis;
                const dataSeries = res.dataSeries;
                for (let i = 0; i < dataSeries.length; i++) {
                    dataSeries[i] = {
                        name: dataSeries[i].key,
                        value: dataSeries[i].value
                    };
                }
                this.drawUserExamStatChart(dataAxis, dataSeries);
            });
        },

        /**
         * 员工考试统计图渲染
         */
        drawUserExamStatChart (xAxisData, dataSeries) {
            const userExamStatChart = this.$echarts.init(document.getElementById('userExamStatChart'));
            userExamStatChart.setOption({
                tooltip: {
                    formatter: '{c}人' // a 是series中每条数据name值，c 是data数值
                },
                xAxis: {
                    data: xAxisData
                },
                yAxis: {},
                color: ['#11A0FF'],
                series: [{
                    name: '次数',
                    type: 'bar',
                    data: dataSeries,
                    barWidth: 30
                }]
            });
        },

      /**
       * 题库更新
       */
      getupdateCountChart () {

        let day = this.getDay(0);
   let day7 = this.getDay(-30);

     const params = {
       type: 'custom',
       beginTime: day7,
       endTime: day
     };
     this.$api.getSubjectStat(params).then(res => {
       const dataAxis = res.dataAxis;
       const dataSeries = res.dataSeries;
       for (let i = 0; i < dataSeries.length; i++) {
         dataSeries[i] = {
           name: dataSeries[i].key,
           value: dataSeries[i].value
         };
       }
       this.updateCountChart(dataAxis, dataSeries);
     });
   },
   /**
    * 题库更新渲染
    */
      updateCountChart (xAxisData, dataSeries) {
        const userExamStatChart = this.$echarts.init(document.getElementById('updateCount'));
        userExamStatChart.setOption({
          tooltip: {
            formatter: '{c}道' // a 是series中每条数据name值，c 是data数值
          },
          xAxis: {
            data: xAxisData
          },
          yAxis: {},
          color: ['#11A0FF','#FF6B5B','#EDFF3C'],
          series: [{
            name: '次数',
            type: 'bar',
            data: dataSeries,
            barWidth: 30
          }]
        });
      },

       // 获取 最近几天的日期
       getDay(day){
  let today = new Date();
  let targetday_milliseconds = today.getTime() + 1000*60*60*24*day;
  today.setTime(targetday_milliseconds); //注意，这行是关键代码
  let tYear = today.getFullYear();
  let tMonth = today.getMonth();
  let tDate = today.getDate();
  tMonth = this.doHandleMonth(tMonth + 1);
  tDate = this.doHandleMonth(tDate);
  return tYear+"-"+tMonth+"-"+tDate;
},


     doHandleMonth(month){
        let m = month;
      if(month.toString().length == 1){
    m = "0" + month;
       }
      return m;
         },

        /**
         * 获取学习人数统计
         */
        getUserLearnedStat () {

          let day = this.getDay(0);
          let day7 = this.getDay(-6);

          const params = {
              type: 'custom',
              beginTime: day7,
              endTime: day
          };

            this.$api.getUserLearnedStat(params).then(res => {
                const dataAxis = res.dataAxis;
                const dataSeries = res.dataSeries;
                for (let i = 0; i < dataSeries.length; i++) {
                    dataSeries[i] = {
                        name: dataSeries[i].key,
                        value: dataSeries[i].value
                    };
                }
                this.drawUserLearnedStatChart(dataAxis, dataSeries);
            }) ;

        },
        /**
         * 学习人数统计图渲染
         */
        drawUserLearnedStatChart (xAxisData, dataSeries) {
            const userLearnedStatChart = this.$echarts.init(document.getElementById('userLearnedStatChart'));
            userLearnedStatChart.setOption({
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    type: 'category',
                    data: xAxisData
                },
                yAxis: {
                    type: 'value',
                    boundaryGap: [0, '30%']
                },
                color: ['#11A0FF'],
                series: [{
                    name: '学习人数',
                    type: 'line',
                    smooth: 0.7,
                    symbol: 'none',
                    lineStyle: {
                        color: '#11A0FF'
                    },
                    areaStyle: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0, color: '#11A0FF' // 0% 处的颜色
                            }, {
                                offset: 1, color: '#FFFFFF' // 100% 处的颜色
                            }],
                            global: false // 缺省为 false
                        }
                    },
                    data: dataSeries
                }]
            });
        },
        /**
         * 获取题型分类统计
         */
        getSubjectTypeStat () {
            this.$api.getSubjectTypeStat({}).then(res => {
                const dataAxis = res.dataAxis;
                const dataSeries = res.dataSeries;
                for (let i = 0; i < dataSeries.length; i++) {
                    dataSeries[i] = {
                        name: dataSeries[i].key,
                        value: dataSeries[i].value
                    };
                }
                this.drawSubjectTypeStatChart(dataAxis, dataSeries);
            });

        },
        /**
         * 题型分类统计图渲染
         */
        drawSubjectTypeStatChart (xAxisData, dataSeries) {
            const subjectTypeStatChart = this.$echarts.init(document.getElementById('subjectTypeStatChart'));
            subjectTypeStatChart.setOption({
                tooltip: {
                    trigger: 'item',
                    formatter: '{b}: {c} ({d}%)'
                },
                legend: {
                    left: 10,
                    data: xAxisData
                },
                color: ['#67BAFE', '#34A3FE'],
                series: [
                    {
                        name: '题型分类',
                        type: 'pie',
                        radius: ['30%', '45%'],
                        label: {
                            formatter: '{b}（{c}）'
                        },
                        data: dataSeries
                    }
                ]
            });
        }
    }
};
</script>

<style scoped lang="scss">
.testStatistic {
    .content {
        &-item {
            &-statistics {
                height:134px;
                margin-bottom: 34px;
                box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                border-radius:10px;
                img {
                    vertical-align: middle;
                    margin: 15px 20px;
                }
                &-info {
                    display: inline-block;
                    vertical-align: middle;
                    p {
                        line-height: 30px;
                        font-size: 22px;
                        color: $color-text-regular;
                    }
                    p:first-child {
                        font-size: 34px;
                        color: $color-text-primary;
                        margin-bottom: 10px;
                    }
                }
            }
            &-chart {
                height: 552px;
                margin-bottom: 34px;
                box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                border-radius:10px;
                &-title {
                    height: 60px;
                    line-height: 60px;
                    padding-left: 26px;
                    font-size: $font-size-middle;
                    border-bottom: 1px solid $color-border-base;
                }
                &-content {
                    padding: 20px 40px;
                }
            }
        }
    }
}
</style>
