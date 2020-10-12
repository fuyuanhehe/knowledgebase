<!--
@file：table页搜索查询组件
@time：20200306
@author：Claire
-->
<template>
    <div class="search-box c_3">
        <el-input
                class="search-item search-ipt w250 mr30"
                clearable
                v-if="isInputShow"
                v-model.trim="search.msg"
                placeholder="请输入关键字"
                suffix-icon="el-icon-search"></el-input>
        <div class="search-select-box display_ib mr30" v-if="isSelectShow">
            <span class="">{{ selectLabel }}：</span>
            <el-select class="search-item search-select"
                       placeholder="请选择"
                       v-model="search.option">
                <el-option label="全部" value="" v-if="isOptionsAllShow"></el-option>
                <el-option
                        v-for="item in selectOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
        </div>
        <div class="search-date-box display_ib mr20" v-if="isDatepickerShow">
            <span>{{ dateLabel }}：</span>
            <el-date-picker
                    class="search-item search-date"
                    v-model="search.startDate"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker>
            <span class="mlr10">—</span>
            <el-date-picker
                    class="search-item search-date"
                    v-model="search.endDate"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker>
        </div>
        <el-button class="btn_bg_blue" v-if="isBtnShow" @click="searchData">查询</el-button>
    </div>
</template>

<script>
export default {
    name: 'TableSearch',
    props: {
        selectLabel: {
            type: String,
            default: ''
        }, // select名称
        dateLabel: {
            type: String,
            default: ''
        }, // datepicker名称
        selectOptions: {
            type: Array,
            default: () => []
        }, // select下拉选项
        isInputShow: {
            type: Boolean,
            default: true
        }, // 是否显示搜索框
        isSelectShow: {
            type: Boolean,
            default: true
        }, // 是否显示下拉选择框
        isDatepickerShow: {
            type: Boolean,
            default: true
        }, // 是否显示日期选择器
        isBtnShow: {
            type: Boolean,
            default: true
        }, // 是否显示查询按钮
        isOptionsAllShow: {
            type: Boolean,
            default: true
        }, // 下拉选项是否显示全部项
        defaultOption: {} // 下拉选项默认选中值
    },
    watch: {
        defaultOption: {
            handler (val) {
                if (val) {
                    this.search.option = val;
                }
            },
            immediate: true,
            deep: true
        }
    },
    data () {
        return {
            search: {
                msg: '', // 搜索框内容
                option: '', // 下拉选项值
                startDate: '', // 开始时间
                endDate: '' // 结束时间
            } // 搜索查询对象
        };
    },
    created () {
    },
    methods: {
        searchData () {
            this.$emit('search', this.search);
        }
    }
};
</script>

<style scoped lang="scss">
.search-box {
    .search-select-box {
        .search-select {
            width: 156px;
        }
    }
    .search-date {
        width: 150px;
    }
}
</style>
