<!--
@file：统计分析-知识仓库统计
@createTime：2020-04-22
@updateTime：2020-04-22
@author：Claire
-->
<template>
    <div class="repositoryStatistic">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <el-row :gutter="20" class="content-item">
                <el-col :span="8">
                    <div class="content-item-statistics">
                        <div class="content-item-statistics-item">
                            <img src="~assets/images/statistic-exam-img.png" alt="">
                            <div class="content-item-statistics-item-info">
                                <p class="">{{classNum}}</p>
                                <p>知识分类数</p>
                            </div>
                        </div>
                        <div class="content-item-statistics-item">
                            <img src="~assets/images/statistic-exam-img.png" alt="">
                            <div class="content-item-statistics-item-info">
                                <p class="">{{totalNum}}</p>
                                <p>知识分类文档数</p>
                            </div>
                        </div>
                    </div>
                </el-col>
                <el-col :span="16">
                    <div class="content-item-chart">
                        <div class="content-item-chart-title">
                            <span>知识库文档数</span>
                        </div>
                        <div class="content-item-chart-content">
                            <div class="chart-box">

                              <div id="documentsCount" :style="{width: '100%', height: '380px'}"></div>

                            </div>

                        </div>
                    </div>
                </el-col>
            </el-row>
            <el-row :gutter="20" class="content-item">
                <el-col :span="12">
                    <div class="content-item-chart">
                        <div class="content-item-chart-title">
                            <span>知识库文档下载数</span>
                            <span>（近一月数据）</span>
                        </div>
                        <div class="content-item-chart-content">
                            <div class="chart-box">

                              <div id="downloadCount" :style="{width: '100%', height: '380px'}"></div>

                            </div>
                        </div>
                    </div>
                </el-col>
                <el-col :span="12">
                    <div class="content-item-chart">
                        <div class="content-item-chart-title">
                            <span>活跃人数</span>
                            <span>（近一周）</span>
                        </div>
                        <div class="content-item-chart-content">
                            <div class="chart-box">
                              <div id="statisticsByTime" :style="{width: '100%', height: '480px'}"></div>
                            </div>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    name: 'repositoryStatistic',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '统计分析',
                    path: '/statistic',
                    routeName: 'statistic'
                }, {
                    name: '知识仓库统计',
                    path: '/statistic/repositoryStatistic',
                    routeName: 'repositoryStatistic',
                    disabled: true
                }
            ], // 面包屑导航集合
         classNum: 0,
          totalNum: 0
        };
    },
  mounted () {
    this.getSexStat();
    this.getdownloadCount();
    this.getUserLearnedStat();
},
  methods: {

    /**
     * 文档数统计
     */
      getSexStat () {
        this.$api.getNumberOfDocumentsCount({}).then(res => {

          const dataAxis = [];
          const dataSeries = res;
          this.classNum = dataSeries.length; // 分类数数
          for (let i = 0; i < dataSeries.length; i++) {
            this.totalNum += dataSeries[i].value; // 总数

            dataAxis[i] = dataSeries[i].name;
            dataSeries[i] = {
              name: dataSeries[i].name,
              value: dataSeries[i].value
            };
          }
         // console.log( dataAxis)
         // console.log( dataSeries)
          this.drawSexStatChart(dataAxis, dataSeries);
        });
      },
      /**
       * 文档数统计渲染
       */
      drawSexStatChart (xAxisData, dataSeries) {
        const sexStatChart = this.$echarts.init(document.getElementById('documentsCount'));
        // const series = dataSeries.map((ele) => {
        //     return {
        //         name: ele.name,
        //         // 重点 data的设置，一次一个数据
        //         data: [ele.value],
        //         type: 'bar',
        //         barWidth: 118
        //     };
        // });
        sexStatChart.setOption({
          tooltip: {
            formatter: '{c}次' // a 是series中每条数据name值，c 是data数值
          },
          xAxis: {
            data: xAxisData
          },
          yAxis: {},
          color: ['#11A0FF', '#2ACFD2'],
          series: [{
            name: '数量',
            type: 'bar',
            itemStyle: {
              normal: {
                color (params) {
                  const colorList = ['#11A0FF', '#2ACFD2'];
                  return colorList[params.dataIndex];
                }
              }
            },
            data: dataSeries,
            barWidth: 118
          }]
          // series: series
        });
      },

      /**
       * 近一个月，下载统计
       */
      getdownloadCount () {
        this.$api.getDownloadCount({}).then(res => {
          const dataAxis = [];
          const dataSeries = res;
          for (let i = 0; i < dataSeries.length; i++) {

            dataAxis[i] = dataSeries[i].name;
            dataSeries[i] = {
              name: dataSeries[i].name,
              value: dataSeries[i].value
            };
          }
          this.downloadCountStatChart(dataAxis, dataSeries);
        });
      },
      /**
       * 近一个月，下载统计 渲染
       */
      downloadCountStatChart (xAxisData, dataSeries) {
        const sexStatChart = this.$echarts.init(document.getElementById('downloadCount'));
        // const series = dataSeries.map((ele) => {
        //     return {
        //         name: ele.name,
        //         // 重点 data的设置，一次一个数据
        //         data: [ele.value],
        //         type: 'bar',
        //         barWidth: 118
        //     };
        // });
        sexStatChart.setOption({
          tooltip: {
            formatter: '{c}次' // a 是series中每条数据name值，c 是data数值
          },
          xAxis: {
            data: xAxisData
          },
          yAxis: {},
          color: ['#11A0FF', '#2ACFD2'],
          series: [{
            name: '数量',
            type: 'bar',
            itemStyle: {
              normal: {
                color (params) {
                  const colorList = ['#11A0FF', '#2ACFD2'];
                  return colorList[params.dataIndex];
                }
              }
            },
            data: dataSeries,
            barWidth: 118
          }]
          // series: series
        });
      },

      /**
       * 活跃人数
       */
      getUserLearnedStat () {
        this.$api.getStatisticsByTime({}).then(res => {
            const dataAxis = [];
            const dataSeries = res;

            for (let i = 0; i < dataSeries.length; i++) {
              dataAxis[i] = this.timeUtils(dataSeries[i].name);
                dataSeries[i] = {
                    name:  this.timeUtils(dataSeries[i].name),
                    value: dataSeries[i].value
                };
            }
            this.drawUserLearnedStatChart(dataAxis, dataSeries);
        });
       /* const dataAxis = ['04/01', '04/02', '04/03', '04/04', '04/05', '04/06', '04/07'];
        const dataSeries = [
          { name: '04/01', value: '25' },
          { name: '04/02', value: '10' },
          { name: '04/03', value: '5' },
          { name: '04/04', value: '35' },
          { name: '04/05', value: '20' },
          { name: '04/06', value: '25' },
          { name: '04/07', value: '35' }
        ];*/
       // this.drawUserLearnedStatChart(dataAxis, dataSeries);
      },

      /**
       * 活跃人数渲染
       */
      drawUserLearnedStatChart (xAxisData, dataSeries) {
        const userLearnedStatChart = this.$echarts.init(document.getElementById('statisticsByTime'));
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
            name: '活跃人数',
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
       * 时间工具
       * @param timestamp
       * @returns {string}
       */
      timeUtils(timestamp){
        let date = new Date(timestamp );//时间戳为10位需*1000，时间戳为13位的话不需乘1000
     //   let Y = date.getFullYear() + '-';
        let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
        let D = date.getDate() + ' ';
      /*  var h = date.getHours() + ':';
        var m = date.getMinutes() + ':';
        var s = date.getSeconds();*/
        return  M + D ;  // + h + m + s
      }

    }
};
</script>

<style scoped lang="scss">
.repositoryStatistic {
    .content {
        &-item {
            &-statistics {
                &-item {
                    width: 100%;
                    height:212px;
                    margin-bottom: 34px;
                    box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                    border-radius:10px;
                    padding-top: 40px;
                    &:nth-child(1) {
                        background: #008CFF;
                    }
                    &:nth-child(2) {
                        background: #01CEFB;
                    }
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
                            color: #fff;
                        }
                        p:first-child {
                            font-size: 34px;
                            margin-bottom: 10px;
                        }
                    }
                }
            }
            &-chart {
                height: 458px;
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
