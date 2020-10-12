package com.hg.knowledgebase.module.repo.service.impl;

import com.hg.knowledgebase.module.repo.entity.BaseStatistics;
import com.hg.knowledgebase.module.repo.mapper.BaseStatisticsMapper;
import com.hg.knowledgebase.module.repo.service.BaseStatisticsService;
import com.hg.knowledgebase.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;

@Service
public class BaseStatisticsServiceImpl implements BaseStatisticsService {

    @Resource
    private BaseStatisticsMapper baseStatisticsMapper;

    @Override
    @Transactional
    public int addBaseStatistics(BaseStatistics record) {
        if(record == null){
            return 0;
        }
        record.setId(StringUtil.getUUID());
        record.setCreateTime(new Date());
        return baseStatisticsMapper.insertSelective(record);
    }
}
