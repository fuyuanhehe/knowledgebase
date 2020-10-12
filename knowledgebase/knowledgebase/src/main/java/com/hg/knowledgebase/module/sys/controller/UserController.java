package com.hg.knowledgebase.module.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.config.security.CustomAuthenticationManager;
import com.hg.knowledgebase.config.security.TokenProvider;
import com.hg.knowledgebase.constant.ErrorConstant;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.constant.MsgConstant;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.global.factory.MessageFactory;
import com.hg.knowledgebase.module.global.service.impl.MailServiceImpl;
import com.hg.knowledgebase.module.global.service.impl.PhoneServiceImpl;
import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;
import com.hg.knowledgebase.module.sys.service.IUserService;

/**
 * 
 * @Description: 用户控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/sys/user")
public class UserController
{

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    CustomAuthenticationManager authenticationManager;

    @Autowired
    TokenProvider tokenProvider;

    /**
     * 
     * @Description: 保存用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveUser")
    @SystemLog(value = "保存用户")
    public ResponseResultBean saveUser(@RequestBody User user)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = userService.addUser(user);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyUser")
    @SystemLog(value = "修改用户")
    public ResponseResultBean modifyUser(@RequestBody User user)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = userService.modifyUser(user);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改用户状态
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyUserStatus")
    @SystemLog(value = "修改用户状态")
    public ResponseResultBean modifyUserStatus(@RequestBody User user)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = userService.modifyBaseUser(user);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改用户状态异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeUser")
    @SystemLog(value = "根据id移除用户")
    public ResponseResultBean removeUserById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = userService.removeUserById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据集合id批量移除用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:16:17
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:16:17
     * @UpdateRemark: 修改内容
     * @param ids
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchUser")
    @SystemLog(value = "根据集合id批量移除用户")
    public ResponseResultBean removeBatchUserById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = userService.removeBatchUserById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getUser")
    @SystemLog(value = "根据id查询用户")
    public ResponseResultBean getUserById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            UserVO userVO = userService.findUserById(id);
            flg = userVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, userVO);
        } catch (Exception e)
        {
            log.error("---根据id查询用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 查询当前用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentUser")
    @SystemLog(value = "查询当前用户")
    public ResponseResultBean getCurrentUser()
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            UserVO userVO = userService.findCurrentUser();
            flg = userVO != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, userVO);
        } catch (Exception e)
        {
            log.error("---查询当前用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllUser")
    @SystemLog(value = "获取所有用户")
    public ResponseResultBean getAllUser(@RequestBody UserVO user)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<User> users = userService.findAllUser(user);
            flg = users != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, users);
        } catch (Exception e)
        {
            log.error("---获取所有用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getUserPage")
    @SystemLog(value = "获取分页用户")
    public ResponseResultBean getUserPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            User user = JSONObject.parseObject(reqStr, User.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<UserVO> pageInfo = userService.findUserPage(user, pageNum,
                    pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页用户异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验用户名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkUserName")
    @SystemLog(value = "校验用户名是否存在")
    public ResponseResultBean checkUserName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            User user = userService.findUserByName(name);
            flg = user != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验用户名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 重置密码
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/resetPwd")
    @SystemLog(value = "重置密码")
    public ResponseResultBean resetPwd(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            List<String> ids = JSONObject.parseObject(reqStr)
                    .getJSONArray(GlobalConstant.IDS).toJavaList(String.class);
            boolean flg = userService.resetUserPwd(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---重置密码异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改密码
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyPassword")
    @SystemLog(value = "修改密码")
    public ResponseResultBean modifyPassword(@RequestBody String reqStr)
    {
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String code = jsonObj.getString(MailServiceImpl.MSG_CODE);
            String msgId = jsonObj.getString(PhoneServiceImpl.MSG_ID);
            String toUserName = jsonObj.getString(MsgConstant.TO_USERNAME);
            String password = jsonObj.getString(GlobalConstant.PASSWORD);
            boolean check = MessageFactory.getMessageService()
                    .checkCoder(toUserName, code, msgId);
            if (check)
            {
                boolean flg = userService.modifyPassword(toUserName, password,
                        code, msgId);
                result = new ResponseResultBean(true,
                        flg ? GlobalConstant.SUCCESS
                                : ErrorConstant.ERROR_EMAIL_NOTHINGNESS,
                        flg ? 200 : 1, null);
            } else
            {
                result = new ResponseResultBean(true,
                        ErrorConstant.ERROR_VERIFICATION_INVALID, 0, null);
            }
        } catch (Exception e)
        {
            log.error("---修改密码异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 用户登录
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:51
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:51
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/login")
    @SystemLog(value = "用户登录")
    public ResponseResultBean login(@RequestBody User user)
    {
        ResponseResultBean result = null;
        try
        {

            UserVO userVO = userService.loginUser(user);
            if (userVO == null)
            {
                result = new ResponseResultBean(true,
                        ErrorConstant.ERROR_ACCOUNT_OR_PASSWORD_INVALID, 0,
                        null);
            } else if (userVO.getStatus() != 1)
            {
                result = new ResponseResultBean(true,
                        ErrorConstant.ERROR_ACCOUNT_OF_FORBIDDEN, 0, null);
            } else if (userVO.getRoleStatus() == null
                    || userVO.getRoleStatus() != 1)
            {
                result = new ResponseResultBean(true,
                        ErrorConstant.ERROR_ROLE_OF_FORBIDDEN, 0, null);
            } else
            {
                // 生成验证对象
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        user.getName(), user.getPassword());
                // 进行验证
                Authentication authentication = authenticationManager
                        .authenticate(token);
                // 存放authenticationToken
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
                String jwt = tokenProvider.createToken(authentication,
                        GlobalConstant.TRUE, GlobalConstant.LOCAL,
                        servletRequestAttributes.getRequest());
                userVO.setToken(jwt);
                result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                        200, userVO);
            }
        } catch (Exception e)
        {
            log.error("---登录用户异常---", e.getMessage());
            result = new ResponseResultBean(false, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 导入用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:19:02
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:19:02
     * @UpdateRemark: 修改内容
     * @param multipart
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/inputUser")
    @SystemLog(value = "导入用户")
    public ResponseResultBean inputUser(
            @RequestParam(GlobalConstant.FILE_NAME) MultipartFile multipart)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        if (multipart == null)
        {
            result = new ResponseResultBean(flg,
                    ErrorConstant.ERROR_FILE_UPLOAD_FAIL);
        } else
        {
            try
            {
                flg = userService.inputUser(multipart.getInputStream());
                result = new ResponseResultBean(flg,
                        flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
            } catch (Exception e)
            {
                log.error("---导入用户异常---", e.getMessage());
                result = new ResponseResultBean(flg, GlobalConstant.RULE_FAIL);
            }
        }
        return result;
    }

    /**
     * 
     * @Description: 获取用户字节
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:19:02
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:19:02
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @RequestMapping(value = "/getUserByte")
    @SystemLog(value = "获取用户字节")
    public ResponseResultBean getUserByte(User user)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            byte[] bytes = userService.findUserByte(user);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    bytes);
        } catch (Exception e)
        {
            log.error("---获取用户字节异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取用户模板字节
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午4:42:36
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月12日下午4:42:36
     * @UpdateRemark: 修改内容 void
     * @Version: 1.0.0
     */
    @RequestMapping(value = "/getUserTempleteByte")
    @SystemLog(value = "获取用户模板字节")
    public ResponseResultBean getUserTempleteByte()
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            byte[] bytes = userService.findUserTempleteBype();
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    bytes);
        } catch (Exception e)
        {
            log.error("---获取用户模板字节异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 导出用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:19:02
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日上午10:19:02
     * @UpdateRemark: 修改内容
     * @param user
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @GetMapping(value = "/outputUser")
    @SystemLog(value = "导出用户")
    public void outputUser(User user)
    {
        try
        {
            userService.outputUser(user);
        } catch (Exception e)
        {
            log.error("---导出用户异常---", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 获取用户模板
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午4:42:36
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月12日下午4:42:36
     * @UpdateRemark: 修改内容 void
     * @Version: 1.0.0
     */
    @GetMapping(value = "/outputTemplete")
    @SystemLog(value = "获取用户模板")
    public void outputTemplete()
    {
        try
        {
            userService.outputUserTemplete();
        } catch (Exception e)
        {
            log.error("---获取用户模板异常---", e.getMessage());
        }
    }
}
