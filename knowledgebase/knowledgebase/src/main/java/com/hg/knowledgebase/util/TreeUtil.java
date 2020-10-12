package com.hg.knowledgebase.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.global.bean.TreeBean;

/**
 * 
 * @Description: 树工具类
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午4:25:26
 * @Version: 1.0.0
 */
public class TreeUtil
{

    private TreeUtil()
    {
    }

    private static Logger log = LoggerFactory.getLogger(TreeUtil.class);

    /**
     * 
     * @Description: 解析树
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午5:28:05
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午5:28:05
     * @UpdateRemark: 修改内容
     * @param treeList
     *            待解析为树bean的集合（注意：集合类型必须符合java jopo规范）
     * @return List<TreeBean>
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @Version: 1.0.0
     */
    public static List<TreeBean> analysis(List<? extends Object> treeList)
            throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException, InstantiationException,
            InvocationTargetException
    {
        // 参数集合对象实例
        Object obj = null;
        // 参数集合数据class
        Class<?> cla = null;
        // 存放集合对象的树实例
        TreeBean tree = null;
        // 参数集合对象方法对象
        Method paramMethod = null;
        if (treeList == null || treeList.size() == 0)
        {
            return null;
        }
        // 解析树集合
        List<TreeBean> analysisTreeList = new LinkedList<TreeBean>();
        Iterator<?> itor = treeList.iterator();
        // 树class
        Class<TreeBean> treeCla = TreeBean.class;
        // 获取树所有字段
        Field[] filds = treeCla.getDeclaredFields();
        while (itor.hasNext())
        {
            obj = itor.next();
            cla = obj.getClass();
            // 获取一个树实例
            tree = treeCla.newInstance();
            for (Field field : filds)
            {
                // 设置修改属性权限
                field.setAccessible(true);
                try
                {
                    paramMethod = cla.getMethod(GlobalConstant.GET
                            + StringUtil.firstToUpperCase(field.getName()));
                    paramMethod.setAccessible(true);
                    // 设置属性值
                    field.set(tree, paramMethod.invoke(obj));
                } catch (NoSuchMethodException e)
                {
                    // 放行字段不存在的异常
                    log.warn("---警告解析树对象的方法不存在，属性值{}---", field.getName());
                }
            }
            analysisTreeList.add(tree);
        }
        return analysisTreeList;
    }

    /**
     * 
     * @Description: 组合树结构扩展
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午4:43:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午4:43:42
     * @UpdateRemark: 修改内容
     * @param treeList
     *            待组合树结构bean的集合（（注意：集合类型必须符合java jopo规范））
     * @return List<TreeBean>
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @Version: 1.0.0
     */
    public static List<TreeBean> composeTreeExpand(
            List<? extends Object> treeList) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            NoSuchFieldException, SecurityException, InvocationTargetException
    {
        return composeTree(analysis(treeList));
    }

    /**
     * 
     * @Description: 组合树结构
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午4:43:42
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午4:43:42
     * @UpdateRemark: 修改内容
     * @param treeList
     * @return List<TreeBean>
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @Version: 1.0.0
     */
    public static List<TreeBean> composeTree(List<TreeBean> treeList)
            throws InstantiationException, IllegalAccessException
    {
        // 结果树集合
        List<TreeBean> resultTreeList = new LinkedList<TreeBean>();
        if (treeList == null || treeList.size() == 0)
        {
            return resultTreeList;
        }
        for (TreeBean tree : treeList)
        {
            // 获取最顶层的父数据
            if ("0".equals(tree.getPid()) || "".equals(tree.getPid()))
            {
                // 添加子列表
                tree.setChildren(getChildren(tree.getId(), treeList));
                // 添加到树集合
                resultTreeList.add(tree);
            }
        }
        return resultTreeList;
    }

    /**
     * 
     * @Description: 获取树的子类
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午7:34:26
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午7:34:26
     * @UpdateRemark: 修改内容
     * @param pid
     * @param treeList
     * @return List<TreeBean>
     * @Version: 1.0.0
     */
    private static List<TreeBean> getChildren(String pid,
            List<TreeBean> treeList)
    {
        // 结果树集合
        List<TreeBean> resultTreeList = new LinkedList<TreeBean>();
        for (TreeBean tree : treeList)
        {
            if (pid.equals(tree.getPid()))
            {
                // 添加子列表
                tree.setChildren(getChildren(tree.getId(), treeList));
                resultTreeList.add(tree);
            }
        }
        return resultTreeList;
    }
}
