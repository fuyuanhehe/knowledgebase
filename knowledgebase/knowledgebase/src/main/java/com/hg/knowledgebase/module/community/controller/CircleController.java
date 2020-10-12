package com.hg.knowledgebase.module.community.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hg.knowledgebase.config.aop.SystemLog;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.module.community.entity.Circle;
import com.hg.knowledgebase.module.community.service.ICircleService;
import com.hg.knowledgebase.module.community.service.ITopicService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;

/**
 * 
 * @Description: 圈子控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */
@RestController
@RequestMapping(value = "/api/community/circle")
public class CircleController
{

    private static Logger log = LoggerFactory.getLogger(CircleController.class);

    @Autowired
    private ICircleService circleService;

    @Autowired
    private ITopicService topicService;

    /**
     * 
     * @Description: 保存圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param circle
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveCircle")
    @SystemLog(value = "保存圈子")
    public ResponseResultBean saveCircle(@RequestBody Circle circle)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = circleService.addCircle(circle);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 修改圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:08
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:08
     * @UpdateRemark: 修改内容
     * @param circle
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/modifyCircle")
    @SystemLog(value = "修改圈子")
    public ResponseResultBean modifyCircle(@RequestBody Circle circle)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = circleService.modifyCircle(circle);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---修改圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeCircle")
    @SystemLog(value = "根据id移除圈子")
    public ResponseResultBean removeCircleById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            flg = circleService.removeCircleById(id);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id批量移除圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeBatchCircle")
    @SystemLog(value = "根据id批量移除圈子")
    public ResponseResultBean removeBatchCircle(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            List<String> ids = jsonObj.getJSONArray(GlobalConstant.IDS)
                    .toJavaList(String.class);
            flg = circleService.removeBatchCircleById(ids);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id批量删除圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCircle")
    @SystemLog(value = "根据id查询圈子")
    public ResponseResultBean getCircleById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            Circle circle = circleService.findCircleById(id);
            flg = circle != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, circle);
        } catch (Exception e)
        {
            log.error("---根据id查询圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 校验圈子名是否存在
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:18:16
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日上午10:18:16
     * @UpdateRemark: 修改内容
     * @param name
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/checkCircleName")
    @SystemLog(value = "校验圈子名是否存在")
    public ResponseResultBean checkCircleName(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String name = jsonObj.getString(GlobalConstant.NAME);
            Circle circle = circleService.findCircleByName(name);
            flg = circle != null;
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS, flg);
        } catch (Exception e)
        {
            log.error("---校验圈子名是否存在异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param circle
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllCircle")
    @SystemLog(value = "获取所有圈子")
    public ResponseResultBean getAllCircle(@RequestBody Circle circle)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Circle> circles = circleService.findAllCircle(circle);
            flg = circles != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    circles);
        } catch (Exception e)
        {
            log.error("---获取所有圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页圈子
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCircle: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param circle
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCirclePage")
    @SystemLog(value = "获取分页圈子")
    public ResponseResultBean getCirclePage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Circle circle = JSONObject.parseObject(reqStr, Circle.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<Circle> pageInfo = circleService.findCirclePage(circle,
                    pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页圈子异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 功能描述: 查询圈子，话题总数
     * 
     * @auther: fuyuan
     * @date: 2020/4/13 0013 9:56
     */
    @PostMapping(value = "/getCircleStatistics")
    @SystemLog(value = "获取所有圈子")
    public ResponseResultBean getCircleStatistics()
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            int circles = circleService.findCircleCount(null);
            int topic = topicService.findTopicCount(null);

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("circles", circles);
            map.put("topic", topic);

            flg = circles > 0 || topic > 0;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL, map);
        } catch (Exception e)
        {
            log.error("---查询圈子，话题总数异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

}
