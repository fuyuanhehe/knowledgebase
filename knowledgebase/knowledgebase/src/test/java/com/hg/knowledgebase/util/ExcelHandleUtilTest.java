/*
package com.hg.knowledgebase.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.hg.knowledgebase.module.global.bean.TreeBean;
import com.hg.knowledgebase.module.global.factory.MessageFactory;
import com.hg.knowledgebase.module.sys.entity.Menu;
import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;
import com.hg.knowledgebase.module.sys.mapper.IUserMapper;
import com.hg.knowledgebase.module.sys.service.IMenuService;
import com.hg.knowledgebase.module.sys.service.IUserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelHandleUtilTest
{

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IUserMapper userMapper;
    
    @Autowired
    private IMenuService menuService;

    */
/**
     * 
    * @Description: 测试菜单树
    * @Author: 况克冬
    * @CreateDate: 2020年3月10日下午4:35:36
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月10日下午4:35:36
    * @UpdateRemark: 修改内容
    * void
    * @Version: 1.0.0
     *//*

 */
/*   @Test
    public void testMenuTree()
    {
        Menu menu = new Menu();
        menu.setMenuGroup("sys");
        try
        {
            List<Menu> menus = menuService.findAllMenu(menu);
            List<TreeBean> treeList = TreeUtil.composeTreeExpand(menus);
            System.out.println(JSONObject.toJSON(treeList));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }*//*

    
    @Test
    public void tt() 
    {
        try
        {
            List<UserVO> list = new ArrayList<>();
            UserVO user = new UserVO();
            user.setId(StringUtil.getUUID());
            user.setName("000001");
            user.setNickName("王五");
            user.setSex(0);
            user.setPhone("111111");
            
            UserVO user2 = new UserVO();
            user2.setId(StringUtil.getUUID());
            user2.setName("0000011");
            user2.setNickName("王五1");
            user2.setSex(1);
            user2.setPhone("1111111");
            list.add(user);
            list.add(user2);
            int flg = userMapper.insertBatchUser(list);
            */
/*boolean flg = userService.addUser(user);*//*

            System.out.println("---执行状态--" + flg);
            
            */
/*
            UserVO userVO = userService.findUserById("111");
            System.out.println("--------"+JSONObject.toJSONString(userVO));
            *//*

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    */
/**
     * 
    * @Description: 测试导入用户excel
    * @Author: 况克冬
    * @CreateDate: 2020年2月28日下午3:50:00
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年2月28日下午3:50:00
    * @UpdateRemark: 修改内容
    * void
    * @Version: 1.0.0
     *//*

    @Test
    public void textInputUserExcel()
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream("D:\\test\\temp\\xxx.xlsx");
            try
            {
                boolean flg = userService.inputUser(in);
                System.out.println("插入用户状态：" + flg);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            if (in != null) 
            {
                try
                {
                    in.close();
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    */
/**
     * 
    * @Description: 测试excel导出
    * @Author: 况克冬
    * @CreateDate: 2020年2月28日上午11:54:57
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年2月28日上午11:54:57
    * @UpdateRemark: 修改内容
    * void
    * @Version: 1.0.0
     *//*

    @Test
    public void testOuptSimpleExcel() 
    {
        User user1 = new User();
        user1.setName("0001");
        user1.setNickName("张三");
        user1.setSex(1);
        user1.setPhone("136547921");
        
        User user2 = new User();
        user2.setName("0002");
        user2.setNickName("李四");
        user2.setSex(0);
        user2.setPhone("136547922");
        
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        
        FileOutputStream out = null;
        try
        {
            out = new FileOutputStream("D:\\test\\temp\\xxx.xlsx");
            ExcelHandleUtil.exportSimpleExcel(userList, out, null, 0);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (SecurityException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null) 
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    */
/**
     * 
    * @Description: 测试短信
    * @Author: 况克冬
    * @CreateDate: 2020年3月10日下午4:36:30
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月10日下午4:36:30
    * @UpdateRemark: 修改内容
    * void
    * @Version: 1.0.0
     *//*

    @Test
    public void testPhoneMsg()
    {
        try
        {
            String msg = MessageFactory.getMessageService().sendCoder("13983312435");
            System.out.println(msg);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
*/
