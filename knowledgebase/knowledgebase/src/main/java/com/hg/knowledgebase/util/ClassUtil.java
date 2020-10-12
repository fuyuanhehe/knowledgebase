package com.hg.knowledgebase.util;

import java.lang.reflect.Method;

import com.hg.knowledgebase.constant.GlobalConstant;

/**
 * 
 * @Description: 类工具类
 * @Author: 况克冬
 * @CreateDate: 2020年2月28日上午8:46:43
 * @Version: 1.0.0
 */
public class ClassUtil
{

    private ClassUtil()
    {
    }

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 根据类属性名称获取属性类型
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日上午8:47:51
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日上午8:47:51
     * @UpdateRemark: 修改内容
     * @param cla
     *            类模版（注意：类类型必须符合java pojo规范）
     * @param fieldName
     * @return
     * @throws NoSuchMethodException
     *             反射方法找不到出现该异常 Class<?>
     * @Version: 1.0.0
     */
    public static Class<?> getFieldTypeByFieldName(Class<?> cla,
            String fieldName) throws NoSuchMethodException
    {
        Class<?> returnType = null;
        if (!cheakIsExtends(cla))
        {
            return returnType;
        }
        Method method = cla.getMethod(
                GlobalConstant.GET + StringUtil.firstToUpperCase(fieldName));
        return method.getReturnType();
    }

    /**
     * 
     * @Description: 校验当前类是否还有继承关系（Object类型除外）
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日上午8:52:06
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日上午8:52:06
     * @UpdateRemark: 修改内容
     * @param cla
     *            校验类对象（注意：类类型必须符合java pojo规范）
     * @return boolean
     * @Version: 1.0.0
     */
    public static boolean cheakIsExtends(Class<?> cla)
    {
        if (cla == null
                || GlobalConstant.OBJECT_CLASS_NAME.equals(cla.getName()))
        {
            return false;
        }
        return true;
    }
}
