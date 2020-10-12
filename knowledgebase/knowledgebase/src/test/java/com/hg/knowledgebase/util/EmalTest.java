/*
package com.hg.knowledgebase.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

import com.hg.knowledgebase.module.global.service.IMsgService;

*/
/**
 * 
* @Description: 邮件测试
* @Author: 况克冬
* @CreateDate: 2020年3月6日下午7:38:53
* @Version: 1.0.0
 *//*

@SpringBootTest
@RunWith(SpringRunner.class)
@EnableCaching
public class EmalTest
{

    @Autowired
    private IMsgService mailService;
    
    */
/**
     * 
    * @Description: 测试邮件验证码
    * @Author: 况克冬
    * @CreateDate: 2020年3月6日下午7:39:32
    * @UpdateUser: 况克冬
    * @UpdateDate: 2020年3月6日下午7:39:32
    * @UpdateRemark: 修改内容
    * void
    * @Version: 1.0.0
     *//*

    @Test
    public void testCoder()
    {
        try
        {
            String coder = mailService.sendCoder("1743233282@qq.com");
            //延迟半分钟
            Thread.sleep(30000);
            String cachCoder = mailService.findCoder("1743233282@qq.com");
            System.out.println("发送真实验证码：" + coder + "-------------获取缓存中的验证码：" + cachCoder);
            boolean flg = mailService.checkCoder("1743233282@qq.com", coder, null);
            System.out.println("校验状态：" + flg);
            //延迟半分钟
            Thread.sleep(30000);
            cachCoder = mailService.findCoder("1743233282@qq.com");
            System.out.println("验证后重新获取的验证码：" + cachCoder);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
*/
