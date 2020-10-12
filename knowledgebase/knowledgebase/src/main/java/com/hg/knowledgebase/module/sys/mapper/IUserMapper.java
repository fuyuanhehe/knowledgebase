package com.hg.knowledgebase.module.sys.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;

/**
 * 
 * @Description: 用户数据层
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:38:26
 * @Version: 1.0.0
 */
public interface IUserMapper
{
    /**
     * 
     * @Description: 插入用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:23:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:23:09
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int insertUser(User user) throws SQLException;

    /**
     * 
     * @Description: 插入批量用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:15
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:15
     * @UpdateRemark: 修改内容
     * @param users
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int insertBatchUser(@Param("users") List<UserVO> users) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int deleteUserById(@Param("id") String id) throws SQLException;

    /**
     * 
     * @Description: 删除批量用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:41:51
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:41:51
     * @UpdateRemark: 修改内容
     * @param uuid
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int deleteBatchUserById(@Param("ids") List<String> ids) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             boolean
     * @Version: 1.0.0
     */
    int updateUser(User user) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    UserVO selectUserById(@Param("id") String id) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    UserVO selectUserByName(@Param("name") String name) throws SQLException;

    /**
     * 
     * @Description: 修改用户密码
     * @Author: 况克冬
     * @CreateDate: 2020年3月6日下午9:12:31
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月6日下午9:12:31
     * @UpdateRemark: 修改内容
     * @param ids
     * @param password
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int updateUserPwd(@Param("ids") List<String> ids,
            @Param("password") String password) throws SQLException;

    /**
     * 
     * @Description: 根据手机或者邮件修改密码
     * @Author: 况克冬
     * @CreateDate: 2020年4月24日下午4:15:35
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月24日下午4:15:35
     * @UpdateRemark: 修改内容
     * @param phone
     * @param mail
     * @param password
     * @return int
     * @Version: 1.0.0
     */
    int updateUserPasswordByPhoneOrMail(@Param("phone") String phone,
            @Param("mail") String mail, @Param("password") String password);

    /**
     * 
     * @Description: 根据用户名和密码查询用户 请使用selectUserByName代替
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:42:33
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:42:33
     * @UpdateRemark: 修改内容
     * @param name
     * @param pwd
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             User
     * @Version: 1.0.0
     */
    @Deprecated
    UserVO selectUserByNameAndPwd(@Param("name") String name,
            @Param("password") String pwd) throws SQLException;

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
     * @throws SQLException SQL错误时候出现的异常
     *             List<User>
     * @Version: 1.0.0
     */
    List<UserVO> selectUserList(User user) throws SQLException;

    /**
     * 
     * @Description: 查询所有基础用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:32:07
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:32:07
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             List<User>
     * @Version: 1.0.0
     */
    List<User> selectBaseUserList(UserVO userVO) throws SQLException;

    /**
     * 
     * @Description: 查询用户总数
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午1:56:24
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午1:56:24
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws SQLException SQL错误时候出现的异常
     *             int
     * @Version: 1.0.0
     */
    int selectUserCount(User user) throws SQLException;

    /**
     * 
     * @Description: 获取性别分组
     * @Author: 况克冬
     * @CreateDate: 2020年3月3日下午2:38:59
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月3日下午2:38:59
     * @UpdateRemark: 修改内容
     * @param user
     * @return
     * @throws Exception
     *             Map<String,Integer> key:性别名称 value:人数
     * @Version: 1.0.0
     */
    Map<String, Integer> selectSexGroup(User user) throws SQLException;
}
