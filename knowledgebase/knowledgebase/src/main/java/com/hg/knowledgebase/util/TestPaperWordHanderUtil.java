package com.hg.knowledgebase.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.TestPaper;
import com.hg.knowledgebase.module.global.bean.KeyValueBean;

/**
 * 
 * @Description: 试卷word文档处理工具类
 * @Author: 况克冬
 * @CreateDate: 2020年3月17日上午11:28:58
 * @Version: 1.0.0
 */
public class TestPaperWordHanderUtil
{

    private TestPaperWordHanderUtil()
    {
    }

    /**
     * 题目标题模板
     */
    private static final String SUBJECT_TITLE_FOMAT = "%s %s、%s:(%s分)";

    /**
     * 题目选项模板
     */
    private static final String SUBJECT_OPTION_FOMAT = "     %s、%s";

    /**
     * 题目类型map
     */
    private static final HashMap<Integer, String> SUBJECT_TYPE_MAP = new HashMap<Integer, String>()
    {

        /**
         * 
         */
        private static final long serialVersionUID = -2523926337285774774L;

        {
            put(0, "单选");
            put(1, "多选");
            put(2, "判断");
        }
    };

    /**
     * 
     * @Description: 导出试卷 注意：调用方关闭流
     * @Author: 况克冬
     * @CreateDate: 2020年3月17日上午11:32:09
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月17日上午11:32:09
     * @UpdateRemark: 修改内容
     * @param dataList
     * @param out
     *            void
     * @throws IOException
     * @Version: 1.0.0
     */
    public static void exportTestPaperWord(TestPaper data, OutputStream out)
            throws IOException
    {
        if (out == null || data == null)
        {
            return;
        }
        XWPFDocument doc = null;
        try
        {
            doc = new XWPFDocument();
            String name = data.getName();
            /**
             * 设置第一行标题
             */
            XWPFParagraph titlePar = doc.createParagraph();
            // 设置居中显示
            titlePar.setAlignment(ParagraphAlignment.CENTER);
            // 创建标题段落
            XWPFRun titleRun = titlePar.createRun();
            // 设置文档标题
            titleRun.setText(name);
            // 设置文本加粗
            titleRun.setBold(true);
            // 2号字体
            titleRun.setFontSize(22);
            titleRun.addBreak();
            /**
             * 设置正文
             */
            String subjectstr = data.getSubjects();
            if (!StringUtils.isEmpty(subjectstr))
            {
                List<Subject> subjectList = JSONArray.parseArray(subjectstr,
                        Subject.class);
                Subject sub = null;
                XWPFRun run = null;
                String options = null;
                XWPFParagraph par = null;
                List<KeyValueBean> keyValueList = null;
                for (int i = 0, len = subjectList.size(); i < len; i++)
                {
                    sub = subjectList.get(i);
                    par = doc.createParagraph();
                    // 设置左对齐
                    par.setAlignment(ParagraphAlignment.LEFT);
                    // 第一行：题目标题
                    run = par.createRun();
                    // 设置字体大小
                    run.setFontSize(12);
                    run.setText(String.format(SUBJECT_TITLE_FOMAT,
                            SUBJECT_TYPE_MAP.get(sub.getType()), i + 1,
                            sub.getName(), sub.getScore()));
                    run.addBreak();
                    // 中间题目选项
                    options = sub.getOptions();
                    if (!StringUtils.isEmpty(options))
                    {
                        keyValueList = JSONArray.parseArray(options,
                                KeyValueBean.class);
                        for (KeyValueBean kv : keyValueList)
                        {
                            run = par.createRun();
                            // 设置字体大小
                            run.setFontSize(12);
                            run.setText(String.format(SUBJECT_OPTION_FOMAT,
                                    kv.getKey(), kv.getValue()));
                            run.addBreak();
                        }
                    }
                    // 最后一行：解析
                    run = par.createRun();
                    // 设置字体大小
                    run.setFontSize(12);
                    run.setText("     解析：" + sub.getAnalysis());
                    run.addBreak();
                }
            }
            doc.write(out);
        } finally
        {
            if (doc != null)
            {
                try
                {
                    doc.close();
                } catch (IOException e)
                {
                    e.getMessage();
                }
            }
        }
    }
}
