<!--
@file：统计分析-员工统计
@createTime：2020-04-22
@updateTime：2020-04-27
@author：Claire
-->
<template>
    <div class="staffStatistic">
        <!--面包屑-->
        <div class="breadcrumb-box">
            <v-breadcrumb :nav="breadcrumbNav"/>
        </div>
        <div class="content">
            <div class="content-top clearfix">
                <div class="content-top-left fl">
                    <div class="content-top-left-item">
                        <img src="~assets/images/statistic-staff-img.png" alt="">
                        <div class="content-top-left-item-info">
                            <p class="">{{ userTotal }}</p>
                            <p>员工总数</p>
                        </div>
                    </div>
                    <div class="content-top-left-item">
                        <img src="~assets/images/statistic-dept-img.png" alt="">
                        <div class="content-top-left-item-info">
                            <p class="">{{ deptTotal }}</p>
                            <p>部门总数</p>
                        </div>
                    </div>
                </div>
                <div class="content-top-right fr">
                    <div class="content-top-right-title">男女人数</div>
                    <div class="chart-box">
                        <span class="male-legend"><i></i>女</span>
                        <span class="female-legend"><i></i>男</span>
                        <div id="sexStatChart" :style="{width: '100%', height: '380px'}"></div>
                    </div>
                </div>
            </div>
            <div class="content-bottom">
                <div class="content-bottom-title">部门人数</div>
                <div class="chart-box">
                    <div id="deptStatChart" :style="{width: '100%', height: '400px'}"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'staffStatistic',
    data () {
        return {
            breadcrumbNav: [
                {
                    name: '统计分析',
                    path: '/statistic',
                    routeName: 'statistic'
                }, {
                    name: '员工统计',
                    path: '/statistic/staffStatistic',
                    routeName: 'staffStatistic',
                    disabled: true
                }
            ], // 面包屑导航集合
            userTotal: '', // 员工总数
            deptTotal: '' // 部门总数
        };
    },
    created () {
        this.getUserCount();
        this.getDeptCount();
    },
    mounted () {
        this.getSexStat();
        this.getDeptStat();
    },
    methods: {
        /**
         * 获取员工总数
         */
        getUserCount () {
            this.$api.getUserCount({}).then(res => {
                this.userTotal = res;
            });
        },
        /**
         * 获取部门总数
         */
        getDeptCount () {
            this.$api.getDeptCount({}).then(res => {
                this.deptTotal = res;
            });
        },
        /**
         * 获取性别统计
         */
        getSexStat () {
            this.$api.getSexStat({}).then(res => {
                const dataAxis = res.dataAxis;
                const dataSeries = res.dataSeries;
                for (let i = 0; i < dataSeries.length; i++) {
                    dataSeries[i] = {
                        name: dataSeries[i].key,
                        value: dataSeries[i].value
                    };
                }
                this.drawSexStatChart(dataAxis, dataSeries);
            });
        },
        /**
         * 性别统计图渲染
         * @returns {string}
         */
        drawSexStatChart (xAxisData, dataSeries) {
            const sexStatChart = this.$echarts.init(document.getElementById('sexStatChart'));
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
                    formatter: '{c}人' // a 是series中每条数据name值，c 是data数值
                },
                xAxis: {
                    data: xAxisData
                },
                yAxis: {},
                color: ['#11A0FF', '#2ACFD2'],
                series: [{
                    name: '人数',
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
         * 获取部门统计
         */
        getDeptStat () {
            this.$api.getDeptStat({}).then(res => {
                const dataAxis = res.dataAxis;
                const dataSeries = res.dataSeries;
                for (let i = 0; i < dataSeries.length; i++) {
                    dataSeries[i] = {
                        name: dataSeries[i].key,
                        value: dataSeries[i].value
                    };
                }
                this.drawDeptStatChart(dataAxis, dataSeries);
            });
        },
        /**
         * 部门统计图渲染
         */
        drawDeptStatChart (xAxisData, dataSeries) {
            const deptStatChart = this.$echarts.init(document.getElementById('deptStatChart'));
            deptStatChart.setOption({
                tooltip: {
                    formatter: '{c}人' // a 是series中每条数据name值，c 是data数值
                },
                xAxis: {
                    data: xAxisData
                },
                yAxis: {},
                color: ['#11A0FF'],
                series: [{
                    name: '人数',
                    type: 'bar',
                    data: dataSeries,
                    barWidth: 34
                }]
            });
        }
    }
};
</script>

<style scoped lang="scss">
.staffStatistic {
    .content {
        &-top {
            &-left {
                width: 35%;
                &-item {
                    width:100%;
                    height:218px;
                    margin-bottom: 34px;
                    padding-top: 40px;
                    box-shadow:0 4px 18px 0 rgba(153,151,151,0.24);
                    border-radius:10px;
                    img {
                        vertical-align: middle;
                        margin: 15px 34px;
                    }
                    &-info {
                        display: inline-block;
                        vertical-align: middle;
                        p {
                            line-height: 30px;
                            color: $color-text-regular;
                        }
                        p:first-child {
                            font-size: 40px;
                            color: $color-text-primary;
                            margin-bottom: 10px;
                        }
                    }
                }
            }
            &-right {
                width: 62%;
                height: 470px;
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
                .chart-box {
                    padding: 20px 40px;
                    span {
                        i {
                            display: inline-block;
                            width:10px;
                            height:10px;
                            border-radius:3px;
                            margin-right: 10px;
                        }
                    }
                    .male-legend {
                        margin: 0 20px 0 50px;
                        i {
                            background:rgba(17,160,255,1);
                        }
                    }
                    .female-legend {
                        i {
                            background:rgba(42,207,210,1);
                        }
                    }
                }
            }
        }
        &-bottom {
            width: 100%;
            height: 488px;
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
            .chart-box {
                padding: 20px 40px;
            }
        }
    }
}
</style>
