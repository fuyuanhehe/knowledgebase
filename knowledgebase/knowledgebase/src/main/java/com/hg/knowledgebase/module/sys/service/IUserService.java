package com.hg.knowledgebase.module.sys.service;

import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;

/**
 * 
 * @Description: 用户服务层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:21:35
 * @Version: 1.0.0
 */
public interface IUserService
{

    /**
     * 
     * @Description: 添加用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean addUser(User user) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:24:56
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:24:56
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeUserById(String id) throws GlobalException;

    /**
     * 
     * @Description: 根据id删除批量用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:30:50
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:30:50
     * @UpdateRemark: 修改内容
     * @param uuids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean removeBatchUserById(List<String> ids) throws GlobalException;

    /**
     * 
     * @Description: 修改用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyUser(User user) throws GlobalException;

    /**
     * 
     * @Description: 修改基础用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:26:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:26:15
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyBaseUser(User user) throws GlobalException;

    /**
     * 
     * @Description: 根据id查询用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    UserVO findUserById(String id) throws GlobalException;

    /**
     * 
     * @Description: 查询当前用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    UserVO findCurrentUser() throws GlobalException;

    /**
     * 
     * @Description: 根据登录名查询用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    User findUserByName(String name) throws GlobalException;

    /**
     * 
     * @Description: 重置密码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午9:07:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午9:07:57
     * @UpdateRemark: 修改内容
     * @param ids
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean resetUserPwd(List<String> ids) throws GlobalException;

    /**
     * 
     * @Description: 修改密码
     * @Author: 况克冬
     * @CreateDate: 2020年4月24日下午4:06:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月24日下午4:06:09
     * @UpdateRemark: 修改内容
     * @param toUserName
     *            邮件或者手机号
     * @param password
     *            密码
     * @param code
     *            验证码
     * @param msgId
     *            验证信息id(手机号时有效)
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean modifyPassword(String toUserName, String password, String code,
            String msgId) throws GlobalException;

    /**
     * 
     * @Description: 查询所有用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<User>
     * @Version: 1.0.0
     */
    List<User> findAllUser(UserVO userVO) throws GlobalException;

    /**
     * 
     * @Description: 查询用户分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:34:28
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:34:28
     * @UpdateRemark: 修改内容
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             List<User>
     * @Version: 1.0.0
     */
    PageInfo<UserVO> findUserPage(User user, Integer pageNum, Integer pageSize)
            throws GlobalException;

    /**
     * 
     * @Description: 登录用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:42:33
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:42:33
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    UserVO loginUser(User user) throws GlobalException;

    /**
     * 
     * @Description: 查询用户总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:53:12
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:53:12
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int findUserCount(User user) throws GlobalException;

    /**
     * 
     * @Description: 获取性别统计
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午2:38:59
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午2:38:59
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             SimpleStatistics
     * @Version: 1.0.0
     */
    SimpleStatisticsResult findSexStat(User user) throws GlobalException;

    /**
     * 
     * @Description: 导入用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param in
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    boolean inputUser(InputStream in) throws GlobalException;

    /**
     * 
     * @Description: 获取用户二进制
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    byte[] findUserByte(User user) throws GlobalException;

    /**
     * 
     * @Description: 获取用户模板二进制
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    byte[] findUserTempleteBype() throws GlobalException;

    /**
     * 
     * @Description: 导出用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    void outputUser(User user) throws GlobalException;

    /**
     * 
     * @Description: 导出用户模板
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws GlobalException 业务异常时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    void outputUserTemplete() throws GlobalException;
}
