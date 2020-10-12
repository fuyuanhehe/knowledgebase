package com.hg.knowledgebase.module.repo.mapper;

import com.hg.knowledgebase.module.repo.entity.BaseStatistics;

public interface BaseStatisticsMapper
{
    int deleteByPrimaryKey(String id);

    int insert(BaseStatistics record);

    int insertSelective(BaseStatistics record);

    BaseStatistics selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseStatistics record);

    int updateByPrimaryKey(BaseStatistics record);
}
