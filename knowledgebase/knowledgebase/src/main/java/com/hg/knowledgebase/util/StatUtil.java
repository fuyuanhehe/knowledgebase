package com.hg.knowledgebase.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hg.knowledgebase.customenum.StatTypeEnum;
import com.hg.knowledgebase.module.global.bean.KeyValueBean;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.stat.bean.StatisticsCondition;

/**
 * 
 * @Description: 统计工具类
 * @Author: 况克冬
 * @CreateDate: 2020年3月4日下午3:55:07
 * @Version: 1.0.0
 */
public class StatUtil
{

    private StatUtil()
    {
    }

    /**
     * 分隔符
     */
    public static final String SPLITTER = "-";

    /**
     * 周数常量类
     */
    public static final String WEEK_NUM = "weekNum";

    /**
     * 日期格式:yyyy-MM-dd
     */
    public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 日期格式:yyyy-MM
     */
    public static SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat(
            "yyyy-MM");

    /**
     * 日期格式:yyyy
     */
    public static SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");

    /**
     * 
     * @Description: 获取周模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午5:19:40
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午5:19:40
     * @UpdateRemark: 修改内容
     * @param year
     *            年份
     * @return week 周数 LinkedHashMap<String,String> key:周 value:周包含的年月份
     * @Version: 1.0.0
     */
    public static LinkedHashMap<String, String> getWeekFormat(int year,
            int week)
    {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1);
        // 算出第一周还剩几天 +1是因为1号是1天
        int dayOfWeek = 7 - calendar.get(Calendar.DAY_OF_WEEK) + 1;
        // 周数减去第一周再减去要得到的周
        week = week - 2;
        calendar.add(Calendar.DAY_OF_YEAR, week * 7 + dayOfWeek);
        map.put("周日", DAY_FORMAT.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        map.put("周一", DAY_FORMAT.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        map.put("周二", DAY_FORMAT.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        map.put("周三", DAY_FORMAT.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        map.put("周四", DAY_FORMAT.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        map.put("周五", DAY_FORMAT.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        map.put("周六", DAY_FORMAT.format(calendar.getTime()));
        return map;
    }

    /**
     * 
     * @Description: 获取半年模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午5:00:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午5:00:57
     * @UpdateRemark: 修改内容
     * @return LinkedHashMap<String,List<String>> key:半年 value:半年包含的月份集合
     * @Version: 1.0.0
     */
    public static LinkedHashMap<String, List<String>> getHalfYearFormat()
    {
        return getHalfYearFormat(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 
     * @Description: 获取半年模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午5:00:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午5:00:57
     * @UpdateRemark: 修改内容
     * @param year
     *            年份
     * @return LinkedHashMap<String,List<String>> key:半年 value:半年包含的月份集合
     * @Version: 1.0.0
     */
    public static LinkedHashMap<String, List<String>> getHalfYearFormat(
            int year)
    {
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        List<String> upList = new ArrayList<>(6);
        List<String> downList = new ArrayList<>(6);
        String tempYear = year + SPLITTER;
        for (int i = 1; i <= 6; i++)
        {
            upList.add(tempYear + StringUtil.toTwoPosition(i));
            downList.add(tempYear + StringUtil.toTwoPosition(i + 6));
        }
        map.put("上半年", upList);
        map.put("下半年", downList);
        return map;
    }

    /**
     * 
     * @Description: 获取季度模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午5:00:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午5:00:57
     * @UpdateRemark: 修改内容
     * @return LinkedHashMap<String,List<String>> key:季度 value:季度包含的月份集合
     * @Version: 1.0.0
     */
    public static LinkedHashMap<String, List<String>> getQuarterFormat()
    {
        return getQuarterFormat(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 
     * @Description: 获取季度模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午5:00:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午5:00:57
     * @UpdateRemark: 修改内容
     * @param year
     *            年份
     * @return LinkedHashMap<String,List<String>> key:季度 value:季度包含的月份集合
     * @Version: 1.0.0
     */
    public static LinkedHashMap<String, List<String>> getQuarterFormat(int year)
    {
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        List<String> oneList = new ArrayList<>(3);
        List<String> twoList = new ArrayList<>(3);
        List<String> threeList = new ArrayList<>(3);
        List<String> fourList = new ArrayList<>(3);
        String tempYear = year + SPLITTER;
        for (int i = 1; i <= 3; i++)
        {
            oneList.add(tempYear + StringUtil.toTwoPosition(i));
            twoList.add(tempYear + StringUtil.toTwoPosition(i + 3));
            threeList.add(tempYear + StringUtil.toTwoPosition(i + 6));
            fourList.add(tempYear + (i + 9));
        }
        map.put("一季度", oneList);
        map.put("二季度", twoList);
        map.put("三季度", threeList);
        map.put("四季度", fourList);
        return map;
    }

    /**
     * 
     * @Description: 获取年模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月17日下午5:44:54
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月17日下午5:44:54
     * @UpdateRemark: 修改内容
     * @param beginYear
     *            开始年
     * @param endYear
     *            结束年
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getYearFormat(int beginYear, int endYear)
    {
        List<String> list = new ArrayList<>(1);
        for (int i = beginYear; i <= endYear; i++)
        {
            list.add(beginYear + "");
        }
        return list;
    }

    /**
     * 
     * @Description: 获取月份模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午3:59:54
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午3:59:54
     * @UpdateRemark: 修改内容
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getMonthFormat()
    {
        return getMonthFormat(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 
     * @Description: 获取月份模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午5:40:05
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午5:40:05
     * @UpdateRemark: 修改内容
     * @param year
     *            年份
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getMonthFormat(int year)
    {
        List<String> list = new ArrayList<String>(12);
        String tempYear = year + SPLITTER;
        for (int i = 1; i <= 12; i++)
        {
            list.add(tempYear + StringUtil.toTwoPosition(i));
        }
        return list;
    }

    /**
     * 
     * @Description: 获取天模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午4:32:56
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午4:32:56
     * @UpdateRemark: 修改内容
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getDayFormat()
    {
        Calendar cal = Calendar.getInstance();
        return getDayFormat(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1);
    }

    /**
     * 
     * @Description: 获取天模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午4:30:12
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午4:30:12
     * @UpdateRemark: 修改内容
     * @param year
     *            年份
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getDayByYearFormat(int year)
    {
        return getDayFormat(year,
                Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    /**
     * 
     * @Description: 获取天模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午4:30:12
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午4:30:12
     * @UpdateRemark: 修改内容
     * @param month
     *            月份
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getDayFormat(int month)
    {
        return getDayFormat(Calendar.getInstance().get(Calendar.YEAR), month);
    }

    /**
     * 
     * @Description: 获取天模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午4:02:45
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午4:02:45
     * @UpdateRemark: 修改内容
     * @param year
     *            年
     * @param month
     *            月
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getDayFormat(int year, int month)
    {
        List<String> list = null;
        list = new ArrayList<String>(28);
        String yearMonth = year + SPLITTER + StringUtil.toTwoPosition(month)
                + SPLITTER;
        switch (month)
        {
        case 1:
        case 3:
        case 5:
        case 7:
        case 10:
        case 12:
            for (int i = 1; i <= 31; i++)
            {
                list.add(yearMonth + StringUtil.toTwoPosition(i));
            }
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            for (int i = 1; i <= 30; i++)
            {
                list.add(yearMonth + StringUtil.toTwoPosition(i));
            }
            break;
        case 2:
            // 判断是否为闰年
            boolean flg = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)
                    ? true : false;
            for (int i = 1; i <= 28; i++)
            {
                list.add(yearMonth + StringUtil.toTwoPosition(i));
            }
            if (flg)
            {
                list.add(yearMonth + 29);
            }
            break;
        }
        return list;
    }

    /**
     * 
     * @Description: 获取日期模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月18日下午3:23:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月18日下午3:23:34
     * @UpdateRemark: 修改内容
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getDayFormat(Date startDate, Date endDate)
    {
        List<String> list = null;
        if (startDate == null || endDate == null)
        {
            return list;
        }
        // 判断日期大小,如果日期传入错误，则矫正开始日期和结束日期
        if (!startDate.before(endDate))
        {
            Date startTempDate = startDate;
            startDate = endDate;
            endDate = startTempDate;
        }
        list = new ArrayList<>(1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        list.add(DAY_FORMAT.format(cal.getTime()));
        while (startDate.before(endDate))
        {
            // 每次加一天
            cal.add(Calendar.DATE, 1);
            // 赋值开始时间
            startDate = cal.getTime();
            list.add(DAY_FORMAT.format(cal.getTime()));
        }
        return list;
    }

    /**
     * 
     * @Description: 获取年模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月18日下午3:23:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月18日下午3:23:34
     * @UpdateRemark: 修改内容
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getYearFormat(Date startDate, Date endDate)
    {
        List<String> list = null;
        if (startDate == null || endDate == null)
        {
            return list;
        }
        // 判断日期大小,如果日期传入错误，则矫正开始日期和结束日期
        if (!startDate.before(endDate))
        {
            Date startTempDate = startDate;
            startDate = endDate;
            endDate = startTempDate;
        }
        list = new ArrayList<>(1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        list.add(YEAR_FORMAT.format(cal.getTime()));
        while (startDate.before(endDate))
        {
            // 每次加一年
            cal.add(Calendar.YEAR, 1);
            // 赋值开始时间
            startDate = cal.getTime();
            list.add(YEAR_FORMAT.format(cal.getTime()));
        }
        return list;
    }

    /**
     * 
     * @Description: 获取月模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月18日下午3:23:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月18日下午3:23:34
     * @UpdateRemark: 修改内容
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return List<String>
     * @Version: 1.0.0
     */
    public static List<String> getMonthFormat(Date startDate, Date endDate)
    {
        List<String> list = null;
        if (startDate == null || endDate == null)
        {
            return list;
        }
        // 判断日期大小,如果日期传入错误，则矫正开始日期和结束日期
        if (!startDate.before(endDate))
        {
            Date startTempDate = startDate;
            startDate = endDate;
            endDate = startTempDate;
        }
        list = new ArrayList<>(1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        list.add(MONTH_FORMAT.format(cal.getTime()));
        while (startDate.before(endDate))
        {
            // 每次加一月
            cal.add(Calendar.MONTH, 1);
            // 赋值开始时间
            startDate = cal.getTime();
            list.add(MONTH_FORMAT.format(cal.getTime()));
        }
        return list;
    }

    /**
     * 
     * @Description: 组合统计 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2020年4月27日上午11:47:47
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月27日上午11:47:47
     * @UpdateRemark: 修改内容
     * @param statisticsCondition
     *            统计条件
     * @param statTypeEnum
     *            统计类型
     * @param datas
     *            日期类型
     * @param statList
     *            数据列表
     * @return
     * @throws ParseException
     *             SimpleStatisticsResult
     * @Version: 1.0.0
     */
    public static SimpleStatisticsResult combinationStat(
            StatisticsCondition statisticsCondition, StatTypeEnum statTypeEnum,
            String[] datas, List<StatisticsCondition> statList)
            throws ParseException
    {
        KeyValueBean kvb = null;
        SimpleStatisticsResult result = null;
        // 数据格式
        List<String> dataFormatList = null;
        LinkedHashMap<String, String> dataFormatMap = null;
        LinkedHashMap<String, List<String>> dataFormatMapList = null;
        switch (statTypeEnum)
        {
        case YEAR: // 年
            dataFormatList = StatUtil.getYearFormat(
                    Integer.valueOf(statisticsCondition.getBeginTime()),
                    Integer.valueOf(statisticsCondition.getEndTime()));
            break;
        case MONTH: // 月
            dataFormatList = StatUtil.getMonthFormat(Integer.valueOf(datas[0]));
            break;
        case DAY: // 日
            dataFormatList = StatUtil.getDayFormat(Integer.valueOf(datas[0]),
                    Integer.valueOf(datas[1]));
            break;
        case HALF_YEAR: // 半年
            dataFormatMapList = StatUtil
                    .getHalfYearFormat(Integer.valueOf(datas[0]));
            break;
        case QUARTER: // 季度
            dataFormatMapList = StatUtil
                    .getQuarterFormat(Integer.valueOf(datas[0]));
            break;
        case WEEK: // 周
            Map<String, Object> otherCondition = statisticsCondition
                    .getOtherCondition();
            if (otherCondition != null
                    && otherCondition.containsKey(StatUtil.WEEK_NUM)
                    && otherCondition.get(StatUtil.WEEK_NUM) != null)
            {
                dataFormatMap = StatUtil.getWeekFormat(
                        Integer.valueOf(datas[0]),
                        Integer.valueOf(otherCondition.get(StatUtil.WEEK_NUM)
                                .toString()));
            }
            break;
        case CUSTOM: // 自定义
            /*
             * dataFormatList = StatUtil.getDayFormat(StatUtil.DAY_FORMAT.parse(
             * statisticsCondition.getBeginTime()),
             * StatUtil.DAY_FORMAT.parse(statisticsCondition.getEndTime()));
             */
            // 优化自定义查询支持年月日
            dataFormatList = getCustomFormat(datas.length,
                    statisticsCondition.getBeginTime(),
                    statisticsCondition.getEndTime());
            break;
        }
        if (statList != null)
        {
            // 年、月、日、自定义组合数据
            if (dataFormatList != null)
            {
                result = new SimpleStatisticsResult();
                List<KeyValueBean> dataSeries = new ArrayList<>();
                for (String dataFormat : dataFormatList)
                {
                    // 默认数据为0
                    kvb = new KeyValueBean(dataFormat, 0);
                    for (StatisticsCondition sc : statList)
                    {
                        if (dataFormat.equals(sc.getData()))
                        {
                            kvb.setValue(sc.getCount());
                            break;
                        }
                    }
                    dataSeries.add(kvb);
                }
                result.setDataAxis(dataFormatList);
                result.setDataSeries(dataSeries);
            }
            // 周组合数据
            else if (dataFormatMap != null)
            {
                result = new SimpleStatisticsResult();
                List<KeyValueBean> dataSeries = new ArrayList<>();
                List<String> axis = new ArrayList<>();
                for (Map.Entry<String, String> entry : dataFormatMap.entrySet())
                {
                    // 默认数据为0
                    kvb = new KeyValueBean(entry.getKey(), 0);
                    for (StatisticsCondition sc : statList)
                    {
                        if (entry.getValue().equals(sc.getData()))
                        {
                            kvb.setValue(sc.getCount());
                            break;
                        }
                    }
                    dataSeries.add(kvb);
                    axis.add(entry.getKey());
                }
                result.setDataAxis(dataFormatList);
                result.setDataSeries(dataSeries);
            }
            // 半年、季度组合数据
            else if (dataFormatMapList != null)
            {
                Integer count = null;
                result = new SimpleStatisticsResult();
                List<KeyValueBean> dataSeries = new ArrayList<>();
                List<String> axis = new ArrayList<>();
                for (Map.Entry<String, List<String>> entry : dataFormatMapList
                        .entrySet())
                {
                    // 默认数据为0
                    count = 0;
                    for (StatisticsCondition sc : statList)
                    {
                        for (String data : entry.getValue())
                        {
                            if (data.equals(sc.getData()))
                            {
                                count += sc.getCount();
                            }
                        }
                    }
                    kvb = new KeyValueBean(entry.getKey(), count);
                    dataSeries.add(kvb);
                    axis.add(entry.getKey());
                }
                result.setDataAxis(dataFormatList);
                result.setDataSeries(dataSeries);
            }
        }
        return result;
    }

    /**
     * 
     * @Description: 获取自定义模板数据 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2020年4月27日下午6:24:54
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月27日下午6:24:54
     * @UpdateRemark: 修改内容
     * @param formatType
     *            模板类型(1：年；2：月；3：日)
     * @param beginTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return
     * @throws ParseException
     *             List<String>
     * @Version: 1.0.0
     */
    public static List<String> getCustomFormat(int formatType, String beginTime,
            String endTime) throws ParseException
    {
        // 数据格式
        List<String> dataFormatList = null;
        switch (formatType)
        {
        case 1:
            // 格式为年。例如：2018
            dataFormatList = StatUtil.getYearFormat(
                    StatUtil.YEAR_FORMAT.parse(beginTime),
                    StatUtil.YEAR_FORMAT.parse(endTime));
            break;
        case 2:
            // 格式为年月。例如：2018-11
            dataFormatList = StatUtil.getMonthFormat(
                    StatUtil.MONTH_FORMAT.parse(beginTime),
                    StatUtil.MONTH_FORMAT.parse(endTime));
            break;
        case 3:
            // 格式为年月日。例如：2018-11-12
            dataFormatList = StatUtil.getDayFormat(
                    StatUtil.DAY_FORMAT.parse(beginTime),
                    StatUtil.DAY_FORMAT.parse(endTime));
            break;
        default:
            break;
        }
        return dataFormatList;
    }
}
