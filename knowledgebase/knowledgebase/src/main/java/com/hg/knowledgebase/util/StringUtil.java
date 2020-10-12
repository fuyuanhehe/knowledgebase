/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hg.knowledgebase.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.hg.knowledgebase.constant.GlobalConstant;

/**
 * @Description: 字符串工具类
 * @Author: 张润东
 * @CreateDate: 2019/5/30 11:04
 * @Version: 1.0.0
 */
public class StringUtil
{

    private StringUtil()
    {
    }

    /**
     * 数字小数两位格式化
     */
    public static final DecimalFormat df = new DecimalFormat("#0.00");

    /**
     * 时间戳
     */
    private static final SimpleDateFormat trsdf = new SimpleDateFormat(
            "yyyyMMddhhmmss");

    /**
     * 
     * @Description: 获取uuid
     * @Author: 况克冬
     * @CreateDate: 2019年6月28日下午5:54:45
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年6月28日下午5:54:45
     * @UpdateRemark: 修改内容
     * @return String
     * @Version: 1.0.0
     */
    public static String getUUID()
    {
        return UUID.randomUUID().toString()
                .replaceAll(GlobalConstant.HORIZONTAL_SPLITTER, "");
    }

    /**
     * 
     * @Description: 获取时间戳
     * @Author: 况克冬
     * @CreateDate: 2019年7月25日下午4:25:32
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年7月25日下午4:25:32
     * @UpdateRemark: 修改内容
     * @return String
     * @Version: 1.0.0
     */
    public static String getTimeStamp()
    {
        return trsdf.format(new Date());
    }

    /**
     * 
     * @Description: 获取尾部字符串
     * @Author: 况克冬
     * @CreateDate: 2019年12月5日下午9:45:03
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年12月5日下午9:45:03
     * @UpdateRemark: 修改内容
     * @param tagetStr
     *            目标字符串
     * @param retainLength
     *            保留长度
     * @return String
     * @Version: 1.0.0
     */
    public static String getFoodStr(String tagetStr, int retainLength)
    {
        String foodStr = "";
        if (!StringUtils.isEmpty(tagetStr) && retainLength > 0)
        {
            if (tagetStr.length() <= retainLength)
            {
                foodStr = tagetStr;
            } else
            {
                int topLength = tagetStr.length() - retainLength;
                foodStr = tagetStr.substring(topLength);
            }
        }
        return foodStr;
    };

    /**
     * 注意：1、参数调用方验证 2、必须是小写字母开始，否则转换错误
     * 
     * @Description: 首字母转大写
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午6:48:50
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午6:48:50
     * @UpdateRemark: 修改内容
     * @param str
     * @return String
     * @Version: 1.0.0
     */
    public static String firstToUpperCase(String str)
    {
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

    /**
     * 注意：map value不能为null
     * 
     * @Description: 反转map数据
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午10:40:52
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午10:40:52
     * @UpdateRemark: 修改内容
     * @param map
     * @return Map<String,String>
     * @Version: 1.0.0
     */
    public static Map<String, String> reversalMap(Map<String, String> map)
    {
        if (map == null)
        {
            return null;
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            resultMap.put(entry.getValue(), entry.getKey());
        }
        return resultMap;
    }

    /**
     * 
     * @Description: 转成多位字符串
     * @Author: 况克冬
     * @CreateDate: 2020年3月4日下午6:46:58
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月4日下午6:46:58
     * @UpdateRemark: 修改内容
     * @param num
     * @return String
     * @Version: 1.0.0
     */
    public static String toTwoPosition(int num)
    {
        return num > 9 ? num + "" : "0" + num;
    }

    /**
     * 
     * @Description: 获取六位随机数
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午7:32:48
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午7:32:48
     * @UpdateRemark: 修改内容
     * @return String
     * @Version: 1.0.0
     */
    public static String getRandom()
    {
        int coder = (int) (Math.random() * 1000000);
        return coder + "";
    }

    public static String GenerateRevisionId(String expectedKey)
    {
        if (expectedKey.length() > 20)
            expectedKey = Integer.toString(expectedKey.hashCode());
        String key = expectedKey.replace("[^0-9-.a-zA-Z_=]", "_");
        return key.substring(0, Math.min(key.length(), 20));
    }
}
