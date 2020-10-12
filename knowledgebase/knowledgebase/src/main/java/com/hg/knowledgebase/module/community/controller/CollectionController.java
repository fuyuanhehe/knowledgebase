package com.hg.knowledgebase.module.community.controller;

import java.util.List;

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
import com.hg.knowledgebase.module.community.entity.Collections;
import com.hg.knowledgebase.module.community.entity.VO.CollectionVO;
import com.hg.knowledgebase.module.community.service.ICollectionService;
import com.hg.knowledgebase.module.global.bean.ResponseResultBean;
import com.hg.knowledgebase.module.repo.entity.VO.BaseDocumentVO;

/**
 * 
 * @Description: 收藏控制层
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日上午9:55:44
 * @Version: 1.0.0
 */

@RestController
@RequestMapping(value = "/api/community/collection")
public class CollectionController
{

    private static Logger log = LoggerFactory
            .getLogger(CollectionController.class);

    @Autowired
    private ICollectionService collectionService;

    /**
     * 
     * @Description: 保存收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午9:55:58
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日上午9:55:58
     * @UpdateRemark: 修改内容
     * @param collection
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/saveCollection")
    @SystemLog(value = "保存收藏")
    public ResponseResultBean saveCollection(
            @RequestBody Collections collection)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            flg = collectionService.addCollection(collection);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---添加收藏异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id移除收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:15:20
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日上午10:15:20
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/removeCollection")
    @SystemLog(value = "根据id移除收藏")
    public ResponseResultBean removeCollectionById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            Collections collection = new Collections();
            collection.setId(id);
            flg = collectionService.removeCollection(collection);
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL);
        } catch (Exception e)
        {
            log.error("---根据id删除收藏异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 根据id查询收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日上午10:17:16
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日上午10:17:16
     * @UpdateRemark: 修改内容
     * @param id
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCollection")
    @SystemLog(value = "根据id查询收藏")
    public ResponseResultBean getCollectionById(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            JSONObject jsonObj = JSONObject.parseObject(reqStr);
            String id = jsonObj.getString(GlobalConstant.ID);
            Collections collection = collectionService.findCollectionById(id);
            flg = collection != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    collection);
        } catch (Exception e)
        {
            log.error("---根据id查询收藏异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取所有收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param collection
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getAllCollection")
    @SystemLog(value = "获取所有收藏")
    public ResponseResultBean getAllCollection(
            @RequestBody Collections collection)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<CollectionVO> collections = collectionService
                    .findAllCollection(collection);
            flg = collections != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    collections);
        } catch (Exception e)
        {
            log.error("---获取所有收藏异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前用户所有收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param collection
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentAllCollection")
    @SystemLog(value = "获取当前用户所有收藏")
    public ResponseResultBean getCurrentAllCollection(
            @RequestBody Collections collection)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            List<Collections> collections = collectionService
                    .findCurrentAllCollection(collection);
            flg = collections != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    collections);
        } catch (Exception e)
        {
            log.error("---获取当前用户所有收藏异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取分页收藏
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param collection
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCollectionPage")
    @SystemLog(value = "获取分页收藏")
    public ResponseResultBean getCollectionPage(@RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Collections collection = JSONObject.parseObject(reqStr,
                    Collections.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<CollectionVO> pageInfo = collectionService
                    .findCollectionPage(collection, pageNum, pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取分页收藏异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取当前收藏文档分页
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param collection
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCurrentCollectionDocPage")
    @SystemLog(value = "获取当前收藏文档分页")
    public ResponseResultBean getCurrentCollectionPage(
            @RequestBody String reqStr)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            Collections collection = JSONObject.parseObject(reqStr,
                    Collections.class);
            JSONObject json = JSONObject.parseObject(reqStr);
            Integer pageNum = json.getInteger(GlobalConstant.PAGE_NUM);
            Integer pageSize = json.getInteger(GlobalConstant.PAGE_SIZE);
            PageInfo<BaseDocumentVO> pageInfo = collectionService
                    .findCurrentCollectionDocPage(collection, pageNum,
                            pageSize);
            flg = pageInfo != null;
            result = new ResponseResultBean(flg,
                    flg ? GlobalConstant.SUCCESS : GlobalConstant.FAIL,
                    pageInfo);
        } catch (Exception e)
        {
            log.error("---获取当前收藏文档分页异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }

    /**
     * 
     * @Description: 获取收藏条数
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午12:28:33
     * @UpdateCollection: 况克冬
     * @UpdateDate: 2020年2月27日下午12:28:33
     * @UpdateRemark: 修改内容
     * @param collection
     * @return ResponseResult
     * @Version: 1.0.0
     */
    @PostMapping(value = "/getCollectionCount")
    @SystemLog(value = "获取收藏条数")
    public ResponseResultBean getCollectionCount(
            @RequestBody Collections collection)
    {
        boolean flg = false;
        ResponseResultBean result = null;
        try
        {
            int count = collectionService.findCollectionCount(collection);
            result = new ResponseResultBean(true, GlobalConstant.SUCCESS,
                    count);
        } catch (Exception e)
        {
            log.error("---获取收藏条数异常---", e.getMessage());
            result = new ResponseResultBean(flg, e.getMessage());
        }
        return result;
    }
}