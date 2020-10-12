package com.hg.knowledgebase.config.schedule;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hg.knowledgebase.module.eval.entity.Curriculum;
import com.hg.knowledgebase.module.eval.service.ICurriculumService;
import com.hg.knowledgebase.util.FileUtil;

/**
 * 
 * @Description: 定时任务
 * @Author: 况克冬
 * @CreateDate: 2019年7月15日下午1:38:24
 * @Version: 1.0.0
 */
@Component
@Configuration
@EnableScheduling
public class TaskScheduleConfig
{

    private static Logger log = LoggerFactory
            .getLogger(TaskScheduleConfig.class);

    @Autowired
    private ICurriculumService curriculumService;

    /**
     * 
     * @Description: 每天定时删除压缩的临时文件
     * @Author: 况克冬
     * @CreateDate: 2019年10月31日下午5:50:47
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年10月31日下午5:50:47
     * @UpdateRemark: 修改内容 void
     * @Version: 1.0.0
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteTempFile()
    {
        log.info("---每天定时删除压缩的临时文件【开始】---");
        File file = new File(FileUtil.TEMP_FILE_PATH);
        if (file.exists())
        {
            File[] fis = file.listFiles();
            for (File fi : fis)
            {
                fi.delete();
            }
        }
        log.info("---每天定时删除压缩的临时文件【结束】---");
    }

    /**
     * 
     * @Description: 每天凌晨更新课程状态
     * @Author: 况克冬
     * @CreateDate: 2020年3月16日下午3:49:52
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月16日下午3:49:52
     * @UpdateRemark: 修改内容 void
     * @Version: 1.0.0
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCurriculumStatus()
    {
        log.info("---每天凌晨更新课程状态【开始】---");
        // 当前时间
        Date date = new Date();
        Curriculum curriculum = new Curriculum();
        curriculum.setLearnBeginTime(date);
        try
        {
            // 查询学习未开始的课程
            curriculum.setStatus(0);
            List<String> ids = curriculumService
                    .findAllLearnCurriculum(curriculum);
            if (ids == null || ids.size() == 0)
            {
                // 将未可开始的学习课程，更新为学习中
                curriculumService.modifyBatchCurriculumStatus(ids, 1);
            }
            // 查询学习中的课程
            curriculum = new Curriculum();
            curriculum.setEndCreateTime(date);
            curriculum.setStatus(1);
            ids = curriculumService.findAllLearnCurriculum(curriculum);
            if (ids == null || ids.size() == 0)
            {
                // 将学习中的课程，更新为已结束
                curriculumService.modifyBatchCurriculumStatus(ids, 2);
            }
        } catch (Exception e)
        {
            log.info("---每天凌晨更新课程状态异常---", e);
        }
        log.info("---每天凌晨更新课程状态【结束】---");
    }
}
