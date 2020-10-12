package com.hg.knowledgebase.module.sys.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.constant.MsgConstant;
import com.hg.knowledgebase.module.eval.entity.UserCurriculum;
import com.hg.knowledgebase.module.eval.entity.UserExam;
import com.hg.knowledgebase.module.eval.mapper.ICurriculumMapper;
import com.hg.knowledgebase.module.eval.mapper.IExamMapper;
import com.hg.knowledgebase.module.global.bean.KeyValueBean;
import com.hg.knowledgebase.module.global.exception.GlobalException;
import com.hg.knowledgebase.module.global.factory.MessageFactory;
import com.hg.knowledgebase.module.proj.entity.UserProject;
import com.hg.knowledgebase.module.proj.mapper.IProjectMapper;
import com.hg.knowledgebase.module.stat.bean.SimpleStatisticsResult;
import com.hg.knowledgebase.module.sys.entity.Dept;
import com.hg.knowledgebase.module.sys.entity.User;
import com.hg.knowledgebase.module.sys.entity.VO.UserVO;
import com.hg.knowledgebase.module.sys.mapper.IDeptMapper;
import com.hg.knowledgebase.module.sys.mapper.IUserMapper;
import com.hg.knowledgebase.module.sys.service.IUserService;
import com.hg.knowledgebase.util.ExcelHandleUtil;
import com.hg.knowledgebase.util.FileUtil;
import com.hg.knowledgebase.util.StringUtil;

/**
 * 
 * @Description: 用户业务实现类
 * @Author: 况克冬
 * @CreateDate: 2020年2月26日下午4:46:29
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = GlobalException.class)
public class UserServiceImpl implements IUserService
{

    /**
     * 加密对象
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private ICurriculumMapper curriculumMapper;

    @Autowired
    private IExamMapper examMapper;

    @Autowired
    private IProjectMapper projectMapper;

    @Autowired
    private IDeptMapper deptMapper;

    /**
     * 默认密码
     */
    @Value("${custom.user.default.password}")
    private String defaultPassword;

    /**
     * 缓存-查询性别统计常量
     */
    private static final String SEX_STAT_CACHE = "findSexStat";

    /**
     * 缓存-查询用户总数常量
     */
    private static final String USER_COUNT_CACHE = "findUserCount";

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
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = { SEX_STAT_CACHE,
            USER_COUNT_CACHE }, allEntries = true)
    @Override
    public boolean addUser(User user) throws GlobalException
    {
        if (user == null)
        {
            throw new GlobalException("添加用户信息不能为空，入参【用户信息：{}】", user);
        }
        // 执行条数
        int exec = 0;
        user.setId(StringUtil.getUUID());
        user.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
        // 如果不存在密码，则使用系统默认密码，否则加密自定义密码
        if (StringUtils.isEmpty(user.getPassword()))
        {
            user.setPassword(encoder.encode(defaultPassword));
        } else
        {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        try
        {
            exec = userMapper.insertUser(user);
        } catch (SQLException e)
        {
            throw new GlobalException("添加用户SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = { SEX_STAT_CACHE,
            USER_COUNT_CACHE }, allEntries = true)
    @Override
    public boolean removeUserById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id删除用户信息不能为空，入参【主键：{}】", id);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = userMapper.deleteUserById(id);
            // 删除用户课程
            UserCurriculum userCurriculum = new UserCurriculum();
            userCurriculum.setUserId(id);
            curriculumMapper.deleteUserCurriculum(userCurriculum);
            // 删除用户考试
            UserExam userExam = new UserExam();
            userExam.setUserId(id);
            examMapper.deleteUserExam(userExam);
            // 删除用户项目
            UserProject userProject = new UserProject();
            userProject.setUserId(id);
            projectMapper.deleteUserProject(userProject);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除用户SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = { SEX_STAT_CACHE,
            USER_COUNT_CACHE }, allEntries = true)
    @Override
    public boolean removeBatchUserById(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("根据id删除批量用户用户信息不能为空，入参【主键集合：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = userMapper.deleteBatchUserById(ids);
            // 删除用户课程
            curriculumMapper.deleteBatchUserCurriculumByUserId(ids);
            // 删除用户考试
            examMapper.deleteBatchUserExamByExamId(ids);
            // 删除用户项目
            projectMapper.deleteBatchUserProjectByUserId(ids);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id删除批量用户SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = { SEX_STAT_CACHE,
            USER_COUNT_CACHE }, allEntries = true)
    @Override
    public boolean modifyUser(User user) throws GlobalException
    {
        if (user == null || StringUtils.isEmpty(user.getId()))
        {
            throw new GlobalException("修改用户信息主键不能为空，入参【用户信息：{}】", user);
        }
        // 执行条数
        int exec = 0;
        // 如果有密码数据，则加密后存入数据库
        if (!StringUtils.isEmpty(user.getPassword()))
        {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        user.setUpdateUser(SecurityUtils.getCurrentUserLogin().get());
        user.setUpdateTime(new Date());
        try
        {
            exec = userMapper.updateUser(user);
        } catch (SQLException e)
        {
            throw new GlobalException("修改用户SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @Version: 1.0.0
     */
    @CacheEvict(cacheNames = { SEX_STAT_CACHE,
            USER_COUNT_CACHE }, allEntries = true)
    @Override
    public boolean modifyBaseUser(User user) throws GlobalException
    {
        if (user == null || StringUtils.isEmpty(user.getId()))
        {
            throw new GlobalException("修改用户信息主键不能为空，入参【用户信息：{}】", user);
        }
        // 执行条数
        int exec = 0;
        // 如果有密码数据，则加密后存入数据库
        if (!StringUtils.isEmpty(user.getPassword()))
        {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        try
        {
            exec = userMapper.updateUser(user);
        } catch (SQLException e)
        {
            throw new GlobalException("修改用户SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 User
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserVO findUserById(String id) throws GlobalException
    {
        if (StringUtils.isEmpty(id))
        {
            throw new GlobalException("根据id查询用户信息主键不能为空，入参【主键：{}】", id);
        }
        try
        {
            return userMapper.selectUserById(id);
        } catch (SQLException e)
        {
            throw new GlobalException("根据id查询用户SQL异常：{}", e.getMessage());
        }
    }

    /**
     * 
     * @Description: 查询当前用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午4:28:37
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午4:28:37
     * @UpdateRemark: 修改内容
     * @return
     * @throws GlobalException
     *             业务异常时候出现的异常 User
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserVO findCurrentUser() throws GlobalException
    {
        String userName = SecurityUtils.getCurrentUserLogin().get();
        try
        {
            return userMapper.selectUserByName(userName);
        } catch (SQLException e)
        {
            throw new GlobalException("查询当前用户SQL异常：{}", e.getMessage());
        }
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 User
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public User findUserByName(String name) throws GlobalException
    {
        if (StringUtils.isEmpty(name))
        {
            throw new GlobalException("根据登录名查询用户信息账号不能为空，入参【账号：{}】", name);
        }
        try
        {
            return userMapper.selectUserByName(name);
        } catch (SQLException e)
        {
            throw new GlobalException("根据登录名查询用户SQL异常：{}", e.getMessage());
        }
    }

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
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean resetUserPwd(List<String> ids) throws GlobalException
    {
        if (ids == null || ids.size() == 0)
        {
            throw new GlobalException("重置密码用户信息不能为空，入参【用户id：{}】", ids);
        }
        // 执行条数
        int exec = 0;
        try
        {
            exec = userMapper.updateUserPwd(ids,
                    encoder.encode(defaultPassword));
        } catch (SQLException e)
        {
            throw new GlobalException("重置密码SQL异常：{}", e.getMessage());
        }
        return exec > 0;
    }

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
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @Override
    public boolean modifyPassword(String toUserName, String password,
            String code, String msgId) throws GlobalException
    {
        if (StringUtils.isEmpty(toUserName) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code))
        {
            throw new GlobalException("修改密码信息不能为空，入参【手机号或者邮件：{}，密码：{}，验证码：{}】",
                    toUserName, password, code);
        }
        // 执行条数
        int exec = 0;
        // 查询当前验证类型
        String type = MessageFactory.getType();
        if (type != null)
        {
            password = encoder.encode(password);
            switch (type)
            {
            case MsgConstant.MAIL_TYPE:
                // 邮件类型
                exec = userMapper.updateUserPasswordByPhoneOrMail(null,
                        toUserName, password);
                break;
            case MsgConstant.PHONE_TYPE:
                // 电话短信类型
                exec = userMapper.updateUserPasswordByPhoneOrMail(toUserName,
                        null, password);
                break;
            default:
                break;
            }
        }
        return exec > 0;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 List<User>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUser(UserVO userVO) throws GlobalException
    {
        try
        {
            return userMapper.selectBaseUserList(userVO);
        } catch (SQLException e)
        {
            throw new GlobalException("查询所有用户SQL异常：{}", e.getMessage());
        }
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 List<User>
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserVO> findUserPage(User user, Integer pageNum,
            Integer pageSize) throws GlobalException
    {
        PageInfo<UserVO> pageInfo = null;
        if (pageSize == null || pageSize == 0 || pageNum == null)
        {
            throw new GlobalException("查询用户分页信息条数不能为空，入参【页码：{}，条数：{}】", pageNum,
                    pageSize);
        }
        try
        {
            PageHelper.startPage(pageNum, pageSize);
            List<UserVO> userVOList = userMapper.selectUserList(user);
            pageInfo = new PageInfo<>(userVOList);
        } catch (SQLException e)
        {
            throw new GlobalException("查询用户分页SQL异常：{}", e.getMessage());
        } finally
        {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 User
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public UserVO loginUser(User user) throws GlobalException
    {
        if (user == null || StringUtils.isEmpty(user.getName())
                || StringUtils.isEmpty(user.getPassword()))
        {
            throw new GlobalException("登录用户信息账号、密码不能为空，入参【用户信息：{}】", user);
        }
        /*
         * 已经使用加密代替 UserVO userVO =
         * userMapper.selectUserByNameAndPwd(user.getName(),
         * user.getPassword());
         */
        /*
         * 更新废弃接口 UserVO userVO =
         * userMapper.selectUserByNameAndPwd(user.getName(),
         * encoder.encode(user.getPassword()));
         */
        UserVO userVO;
        try
        {
            userVO = userMapper.selectUserByName(user.getName());
            // 校验密码是否相同
            if (!encoder.matches(user.getPassword(), userVO.getPassword()))
            {
                userVO = null;
            }
        } catch (SQLException e)
        {
            throw new GlobalException("登录用户SQL异常：{}", e.getMessage());
        }
        return userVO;
    }

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
     * @throws Exception
     *             int
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = {
            USER_COUNT_CACHE }, key = "#user.status+ '-' + #user.sex + '-' "
                    + "+ #user.deptId + '-' + #user.beginCreateTime + '-' "
                    + "+ #user.endCreateTime", condition = "#user != null")
    @Override
    public int findUserCount(User user) throws GlobalException
    {
        try
        {
            return userMapper.selectUserCount(user);
        } catch (SQLException e)
        {
            throw new GlobalException("查询用户总数SQL异常：{}", e.getMessage());
        }
    }

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
     * @throws Exception
     *             SimpleStatistics
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    @Cacheable(cacheNames = {
            SEX_STAT_CACHE }, key = "#user.status + '-' + #user.deptId + '-' + #user.beginCreateTime "
                    + "+ '-' + #user.endCreateTime", condition = "#user != null")
    public SimpleStatisticsResult findSexStat(User user) throws GlobalException
    {
        // 统计结果对象
        SimpleStatisticsResult stat = null;
        // 查询用户性别分组map
        Map<String, Integer> dataMap = null;
        try
        {
            dataMap = userMapper.selectSexGroup(user);
        } catch (SQLException e)
        {
            throw new GlobalException("获取性别统计SQL异常：{}", e.getMessage());
        }
        // 如果查询用户分组有数据，则执行组合统计结果
        if (dataMap != null && dataMap.size() > 0)
        {
            stat = new SimpleStatisticsResult();
            // x轴数据集合
            List<String> dataAxis = new ArrayList<String>(dataMap.size());
            // 数据值集合
            List<KeyValueBean> dataSeries = new ArrayList<KeyValueBean>(
                    dataMap.size());
            for (Map.Entry<String, Integer> entry : dataMap.entrySet())
            {
                dataAxis.add(entry.getKey());
                dataSeries.add(
                        new KeyValueBean(entry.getKey(), entry.getValue()));
            }
            stat.setDataAxis(dataAxis);
            stat.setDataSeries(dataSeries);
        }
        return stat;
    }

    /**
     * 
     * @Description: 导入用户
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @param userFile
     * @return
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InvalidFormatException
     * @Version: 1.0.0
     */
    @Override
    public boolean inputUser(InputStream in) throws GlobalException
    {
        if (in == null)
        {
            throw new GlobalException("导入用户文件为空");
        }
        // 执行条数
        int exec = 0;
        try
        {
            List<UserVO> userList = ExcelHandleUtil.analysisExcel(in,
                    UserVO.class);
            // 如果解析的excel没有数据，则抛出异常
            if (userList == null || userList.size() == 0)
            {
                throw new GlobalException("导入用户信息解析为空");
            }
            List<Dept> deptList = deptMapper.selectBaseDeptList(null);
            for (UserVO user : userList)
            {
                user.setId(StringUtil.getUUID());
                // user.setPassword(defaultPassword);
                user.setPassword(encoder.encode(defaultPassword));
                user.setCreateUser(SecurityUtils.getCurrentUserLogin().get());
                // 如果数据库有查询的部门，则遍历部门
                if (deptList != null)
                {
                    deptList.parallelStream().forEach(dept ->
                    {
                        // 如果当前解析用户部门名称和查询的部门名称相同，则设置部门id到用户信息中
                        if (dept.getName().equals(user.getDeptName()))
                        {
                            user.setDeptId(dept.getId());
                            return;
                        }
                    });
                }
            }
            exec = userMapper.insertBatchUser(userList);
        } catch (Exception e)
        {
            throw new GlobalException("导入用户异常：{}", e.getMessage());
        } finally
        {
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.getMessage();
            }
        }
        return exec > 0;
    }

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
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public byte[] findUserByte(User user) throws GlobalException
    {
        FileOutputStream out = null;// 输出流
        FileInputStream in = null;
        File file = null;
        byte[] bytes = new byte[2048];
        List<UserVO> userVOList;
        try
        {
            userVOList = userMapper.selectUserList(user);
            if (userVOList != null)
            {
                FileUtil.createFilePath(FileUtil.TEMP_FILE_PATH);
                file = new File(FileUtil.TEMP_FILE_PATH + "/用户表模板.xlsx");
                out = new FileOutputStream(file);
                ExcelHandleUtil.exportSimpleExcel(userVOList, out, null, 0);
                in = new FileInputStream(file);
                in.read(bytes);
            }
        } catch (Exception e)
        {
            throw new GlobalException("获取用户二进制SQL异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
            if (file != null && file.isFile())
            {
                // 删除临时文件(有可能删除不成功，将在定时任务中删除)
                file.delete();
            }
        }
        return bytes;
    }

    /**
     * 
     * @Description: 获取用户模板二进制
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public byte[] findUserTempleteBype() throws GlobalException
    {
        File file = null;
        OutputStream out = null;// 输出流
        FileInputStream in = null;
        byte[] bytes = new byte[2048];
        try
        {
            FileUtil.createFilePath(FileUtil.TEMP_FILE_PATH);
            file = new File(FileUtil.TEMP_FILE_PATH + "/用户表模板.xlsx");
            List<UserVO> userVOList = new ArrayList<>();
            userVOList.add(new UserVO());
            out = new FileOutputStream(file);
            ExcelHandleUtil.exportSimpleExcel(userVOList, out, null, 0);
            in = new FileInputStream(file);
            in.read(bytes);
        } catch (Exception e)
        {
            throw new GlobalException("获取用户模板二进制SQL异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
            if (in != null)
            {
                try
                {
                    in.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
            if (file != null && file.isFile())
            {
                // 删除临时文件(有可能删除不成功，将在定时任务中删除)
                file.delete();
            }
        }
        return bytes;
    }

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
     * @throws GlobalException
     *             业务异常时候出现的异常 boolean
     * @throws IOException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public void outputUser(User user) throws GlobalException
    {
        OutputStream out = null;// 输出流
        try
        {
            List<UserVO> userVOList = userMapper.selectUserList(user);
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = servletRequestAttributes
                    .getResponse();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("用户表.xlsx",
                            GlobalConstant.SYS_ENCODER));
            out = response.getOutputStream();
            ExcelHandleUtil.exportSimpleExcel(
                    userVOList == null ? new ArrayList<>() : userVOList, out,
                    null, 0);
        } catch (Exception e)
        {
            throw new GlobalException("导出用户异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
    }

    /**
     * 
     * @Description: 导出用户模板
     * @Author: 况克冬
     * @CreateDate: 2020年2月26日下午5:34:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月26日下午5:34:25
     * @UpdateRemark: 修改内容
     * @return
     * @throws Exception
     *             boolean
     * @Version: 1.0.0
     */
    @Transactional(readOnly = true)
    @Override
    public void outputUserTemplete() throws GlobalException
    {
        OutputStream out = null;// 输出流
        try
        {
            List<UserVO> userVOList = new ArrayList<>();
            userVOList.add(new UserVO());
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = servletRequestAttributes
                    .getResponse();
            response.setHeader("content-type", "application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("用户表模板.xlsx",
                            GlobalConstant.SYS_ENCODER));
            out = response.getOutputStream();
            ExcelHandleUtil.exportSimpleExcel(userVOList, out, null, 0);
        } catch (Exception e)
        {
            throw new GlobalException("导出用户模板异常：{}", e.getMessage());
        } finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
    }
}
