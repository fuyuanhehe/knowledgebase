package com.hg.knowledgebase.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.hg.knowledgebase.config.security.SecurityUtils;
import com.hg.knowledgebase.module.eval.entity.Subject;
import com.hg.knowledgebase.module.eval.entity.VO.SubjectVO;
import com.hg.knowledgebase.module.global.bean.KeyValueBean;

/**
 * 
 * @Description: 课程导入处理工具类
 * @Author: 况克冬
 * @CreateDate: 2020年3月16日下午4:53:49
 * @Version: 1.0.0
 */
public class SubjectExcelHanderUtil
{

    private static Logger log = LoggerFactory
            .getLogger(SubjectExcelHanderUtil.class);

    private SubjectExcelHanderUtil()
    {
    }

    /**
     * 题目excel最小列 规则：第一列：题目标题 中间列：答案选项 倒数第五列：题目分数 倒数第4列：题目难度 倒数第三列：题目类型
     * 倒数第二列：正确答案 最后一列：题目解析
     */
    private static final int SUBJECT_MIN_COLUMN_NUM = 7;

    /**
     * 题目excel最大列
     */
    private static final int SUBJECT_MAN_COLUMN_NUM = 13;

    /**
     * 默认字体宽度
     */
    public static final int DEFAULT_COLUMN_WIDTH = 17;

    /**
     * 2007excel最大导出行数（1048576）
     */
    public static final int EXCEL_MAX_OUT_ROW_NUM = 1048000;

    /**
     * 默认选项条数
     */
    public static final int DEFAULT_OPTION_SIZE = 4;

    /**
     * 答案选项
     */
    private static final String[] ANSWER_OPTIONS = new String[] { "A", "B", "C",
            "D", "E", "F", "G", "H", "I" };

    /**
     * 题目类型map
     */
    private static final HashMap<String, Integer> SUBJECT_TYPE_MAP = new HashMap<String, Integer>()
    {
        /**
         * 
         */
        private static final long serialVersionUID = -7811310024571694328L;

        {
            put("单选", 0);
            put("多选", 1);
            put("判断", 2);
        }
    };

    /**
     * 输出题目类型map
     */
    private static final HashMap<Integer, String> OUT_SUBJECT_TYPE_MAP = new HashMap<Integer, String>()
    {
        /**
         * 
         */
        private static final long serialVersionUID = -7811310024571694328L;

        {
            put(0, "单选");
            put(1, "多选");
            put(2, "判断");
        }
    };

    /**
     * 注意：1、参数调用方验证 2、调用方关闭输入流 规则：第一列：题目标题 中间列：答案选项 倒数第五列：题目类型 倒数第4列：题目难度
     * 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
     * 
     * @Description: 解析excel数据
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午9:52:28
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午9:52:28
     * @UpdateRemark: 修改内容
     * @param in
     *            excel文件流
     * @param subjectDifficultyMap
     *            题目难度map
     * @return List<T>
     * @throws IOException
     * @throws InvalidFormatException
     * @throws EncryptedDocumentException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @Version: 1.0.0
     */
    public static List<Subject> analysisExcel(InputStream in,
            Map<String, String> subjectDifficultyMap)
            throws EncryptedDocumentException, InvalidFormatException,
            IOException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException,
            NoSuchMethodException, SecurityException
    {
        Workbook workbook = null;
        List<Subject> dataList = null;
        try
        {
            workbook = WorkbookFactory.create(in);
            if (workbook instanceof XSSFWorkbook) // 仅支持excel2007即以后的数据解析
            {
                dataList = excel2007ConvertExcelData(workbook,
                        subjectDifficultyMap);
            } else // 仅支持excel2003即以前的数据解析
            {
                dataList = excel2003ConvertExcelData(workbook,
                        subjectDifficultyMap);
            }
        } finally
        {
            if (workbook != null)
            {
                workbook.close();
            }
        }
        return dataList;
    }

    /**
     * 
     * @Description: 解析excel2007即以后的数据 注意：参数调用方验证 规则：第一列：题目标题 中间列：答案选项
     *               倒数第五列：题目类型 倒数第4列：题目难度 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
     * @Author: 况克冬
     * @CreateDate: 2019年12月4日下午4:08:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年12月4日下午4:08:13
     * @UpdateRemark: 修改内容
     * @param workbook
     * @param cla
     * @return
     * @throws EncryptedDocumentException
     * @throws InvalidFormatException
     * @throws IOException
     *             ExcelData
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @Version: 1.0.0
     */
    public static List<Subject> excel2007ConvertExcelData(Workbook workbook,
            Map<String, String> subjectDifficultyMap)
            throws EncryptedDocumentException, InvalidFormatException,
            IOException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException,
            NoSuchMethodException, SecurityException
    {
        // 题目对象
        Subject subject = null;
        // 存放excel解析后类集合
        List<Subject> dataList = null;
        // 工作薄对象
        XSSFWorkbook xwb = null;
        // 列对象
        XSSFCell cell = null;
        // 行对象
        XSSFRow hssfRow = null;
        // 获取第一行数据
        XSSFRow firstRow = null;
        // 获取当前工作表最后一行的行号,开始下标为0
        int lastRowNum = 0;
        // 获取第一行数据最后一列的列号，开始下标为1
        short lastCellNum = 0;
        // 答案集合
        List<String> answers = null;
        List<KeyValueBean> options = null;
        try
        {
            xwb = (XSSFWorkbook) workbook;
            // 解析excel工作薄
            // swb = new SXSSFWorkbook(xwb, 1000);
            Iterator<Sheet> itor = xwb.sheetIterator();
            // 获取工作簿中第一个工作表
            // XSSFSheet hssfSheet = xwb.getSheetAt(0);
            XSSFSheet hssfSheet = null;
            while (itor.hasNext())
            {
                hssfSheet = (XSSFSheet) itor.next();
                // 获取当前工作表最后一行的行号,开始下标为0
                lastRowNum = hssfSheet.getLastRowNum();
                // 获取第一行数据
                firstRow = hssfSheet.getRow(0);
                if (firstRow != null)
                {
                    // 获取第一行数据最后一列的列号，开始下标为1
                    lastCellNum = firstRow.getLastCellNum();
                    // 校验题目列规则
                    if (lastCellNum < SUBJECT_MIN_COLUMN_NUM
                            || lastCellNum > SUBJECT_MAN_COLUMN_NUM)
                    {
                        log.error("---导入题目解析规则错误，入参【当前列数：{}】---", lastCellNum);
                        return dataList;
                    }
                    dataList = new ArrayList<>(lastRowNum - 1);
                    // 组合列数据行
                    for (int i = 1; i <= lastRowNum; i++)
                    {
                        // 创建数据新实例
                        subject = new Subject();
                        options = new ArrayList<>(1);
                        hssfRow = hssfSheet.getRow(i);
                        // 组合列数据值
                        for (int j = 0; j < lastCellNum; j++)
                        {
                            /**
                             * 规则：第一列：题目标题 中间列：答案选项 倒数第五列：题目类型 倒数第4列：题目难度
                             * 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
                             */
                            cell = hssfRow.getCell(j);
                            if (j == 0) // 第一列(名称)
                            {
                                subject.setName(cell.getRichStringCellValue()
                                        .getString());
                            } else if (j == lastCellNum - 5) // 最后第三列（类型）
                            {
                                subject.setType(SUBJECT_TYPE_MAP.get(cell
                                        .getRichStringCellValue().getString()));
                            } else if (j == lastCellNum - 4) // 最后第四列（难度）
                            {
                                subject.setDifficulty(subjectDifficultyMap
                                        .get(cell.getRichStringCellValue()
                                                .getString()));
                            } else if (j == lastCellNum - 3) // 最后第三列（分数）
                            {
                                cell.setCellType(CellType.STRING);
                                subject.setScore(Double
                                        .valueOf(cell.getStringCellValue()));
                            } else if (j == lastCellNum - 2) // 最后第二列（答案）
                            {
                                String str = cell.getRichStringCellValue()
                                        .getString();
                                answers = new ArrayList<>();
                                for (int a = 0, l = str.length(); a < l; a++)
                                {
                                    answers.add(String.valueOf(str.charAt(a)));
                                }
                                subject.setAnswers(
                                        JSONArray.toJSONString(answers));
                            } else if (j == lastCellNum - 1) // 最后一列
                            {
                                subject.setAnalysis(cell
                                        .getRichStringCellValue().getString());
                            } else // 中间列
                            {
                                cell.setCellType(CellType.STRING);
                                options.add(
                                        new KeyValueBean(ANSWER_OPTIONS[j - 1],
                                                cell.getStringCellValue()));
                            }
                        }
                        subject.setOptions(JSONArray.toJSONString(options));
                        subject.setId(StringUtil.getUUID());
                        subject.setCreateUser(
                                SecurityUtils.getCurrentUserLogin().get());
                        dataList.add(subject);
                    }
                }
            }
        } finally
        {
            if (xwb != null)
            {
                xwb.close();
            }
        }
        return dataList;
    }

    /**
     * 
     * @Description: 解析excel2003即以前的数据 注意：参数调用方验证 规则：第一列：题目标题 中间列：答案选项
     *               倒数第五列：题目类型 倒数第4列：题目难度 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
     * @Author: 况克冬
     * @CreateDate: 2019年12月4日下午4:08:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年12月4日下午4:08:13
     * @UpdateRemark: 修改内容
     * @param workbook
     * @return
     * @throws EncryptedDocumentException
     * @throws InvalidFormatException
     * @throws IOException
     *             ExcelData
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @Version: 1.0.0
     */
    public static List<Subject> excel2003ConvertExcelData(Workbook workbook,
            Map<String, String> subjectDifficultyMap)
            throws EncryptedDocumentException, InvalidFormatException,
            IOException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException,
            NoSuchMethodException, SecurityException
    {
        // 题目对象
        Subject subject = null;
        // 存放excel解析后类集合
        List<Subject> dataList = null;
        // 列对象
        HSSFCell cell = null;
        // 行对象
        HSSFRow hssfRow = null;
        // 工作薄对象
        HSSFWorkbook hSSFWorkbook = null;
        // 工作表
        HSSFSheet hssfSheet = null;
        // 获取当前工作表最后一行的行号,开始下标为0
        int lastRowNum = 0;
        // 行数据
        HSSFRow firstRow = null;
        // 获取第一行数据最后一列的列号，开始下标为1
        short lastCellNum = 0;
        // 答案集合
        List<String> answers = null;
        List<KeyValueBean> options = null;
        try
        {
            hSSFWorkbook = (HSSFWorkbook) workbook;
            // 获取工作簿中第一个工作表
            // HSSFSheet hssfSheet = hSSFWorkbook.getSheetAt(0);
            Iterator<Sheet> itor = hSSFWorkbook.sheetIterator();
            while (itor.hasNext())
            {
                hssfSheet = (HSSFSheet) itor.next();
                // 获取当前工作表最后一行的行号,开始下标为0
                lastRowNum = hssfSheet.getLastRowNum();
                // 获取第一行数据
                firstRow = hssfSheet.getRow(0);
                if (firstRow != null)
                {
                    // 获取第一行数据最后一列的列号，开始下标为1
                    lastCellNum = firstRow.getLastCellNum();
                    // 校验题目列规则
                    if (lastCellNum < SUBJECT_MIN_COLUMN_NUM
                            || lastCellNum > SUBJECT_MAN_COLUMN_NUM)
                    {
                        log.error("---导入题目解析规则错误，入参【当前列数：{}】---", lastCellNum);
                        return dataList;
                    }
                    dataList = new ArrayList<>(lastRowNum - 1);
                    // 组合列数据行
                    for (int i = 1; i <= lastRowNum; i++)
                    {
                        // 创建数据新实例
                        subject = new Subject();
                        hssfRow = hssfSheet.getRow(i);
                        options = new ArrayList<>(1);
                        // 组合列数据值
                        for (int j = 0; j < lastCellNum; j++)
                        {
                            cell = hssfRow.getCell(j);
                            if (j == 0) // 第一列(名称)
                            {
                                subject.setName(cell.getRichStringCellValue()
                                        .getString());
                            } else if (j == lastCellNum - 5) // 最后第三列（类型）
                            {
                                subject.setType(SUBJECT_TYPE_MAP.get(cell
                                        .getRichStringCellValue().getString()));
                            } else if (j == lastCellNum - 4) // 最后第四列（难度）
                            {
                                subject.setDifficulty(subjectDifficultyMap
                                        .get(cell.getRichStringCellValue()
                                                .getString()));
                            } else if (j == lastCellNum - 3) // 最后第三列（分数）
                            {
                                cell.setCellType(CellType.STRING);
                                subject.setScore(Double
                                        .valueOf(cell.getStringCellValue()));
                            } else if (j == lastCellNum - 2) // 最后第二列（答案）
                            {
                                String str = cell.getRichStringCellValue()
                                        .getString();
                                answers = new ArrayList<>();
                                for (int a = 0, l = str.length(); a < l; a++)
                                {
                                    answers.add(String.valueOf(str.charAt(a)));
                                }
                                subject.setAnswers(
                                        JSONArray.toJSONString(answers));
                            } else if (j == lastCellNum - 1) // 最后一列
                            {
                                subject.setAnalysis(cell
                                        .getRichStringCellValue().getString());
                            } else // 中间列
                            {
                                cell.setCellType(CellType.STRING);
                                options.add(
                                        new KeyValueBean(ANSWER_OPTIONS[j - 1],
                                                cell.getStringCellValue()));
                            }
                        }
                        subject.setOptions(JSONArray.toJSONString(options));
                        subject.setId(StringUtil.getUUID());
                        subject.setCreateUser(
                                SecurityUtils.getCurrentUserLogin().get());
                        dataList.add(subject);
                    }
                }
            }
        } finally
        {
            if (hSSFWorkbook != null)
            {
                hSSFWorkbook.close();
            }
        }
        return dataList;
    }

    /**
     * 
     * @Description: 导出简单数据Excel 注意：参数调用方验证有效性，并且注意外部关闭输出流 规则：第一列：题目标题 中间列：答案选项
     *               倒数第五列：题目类型 倒数第4列：题目难度 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日上午9:17:40
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日上午9:17:40
     * @UpdateRemark: 修改内容
     * @param dataList
     * @param out
     * @param sheetName
     * @param colWidth
     * @throws IOException
     *             void
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @Version: 1.0.0
     */
    public static void exportSimpleExcel(List<SubjectVO> dataList,
            OutputStream out, String sheetName, int colWidth)
            throws IOException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException
    {
        // 行对象
        SXSSFRow row = null;
        // 工作薄对象
        SXSSFWorkbook workbook = null;
        // 工作表对象
        SXSSFSheet sheet = null;
        // 行对象
        SXSSFRow firstRow = null;
        // 列对象
        SXSSFCell cell = null;
        // 工作表输出条数
        int sheetOutLenth = 0;
        // 题目对象
        SubjectVO subject = null;
        // 最大题目选项条数
        int maxOptionSize = 0;
        // 题目选项条数
        int optionSize = 0;
        // 题目选项json字符串
        String optionsJsonStr = null;
        // 题目选项内容集合
        List<KeyValueBean> optionContentList = null;
        // 答案buff
        StringBuffer answerBuf = null;
        // 题目答案json字符串
        String answersJsonStr = null;
        // 题目答案内容集合
        List<String> answerContentList = null;
        if (dataList == null || out == null)
        {
            return;
        }
        try
        {
            // 声明一个工作薄
            workbook = new SXSSFWorkbook(1000);// 缓存
            workbook.setCompressTempFiles(true);
            // 表头样式
            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            Font titleFont = workbook.createFont();
            titleFont.setFontHeightInPoints((short) 20);
            titleFont.setBold(true);
            titleStyle.setFont(titleFont);
            // 列头样式
            Font headerFont = workbook.createFont();
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setBold(true);
            CellStyle headerStyle = ExcelHandleUtil
                    .getDefultHeaderStyle(workbook);
            headerStyle.setFont(headerFont);
            // 单元格样式
            Font cellFont = workbook.createFont();
            cellFont.setBold(false);
            CellStyle cellStyle = ExcelHandleUtil.getDefultCellStyle(workbook);
            cellStyle.setFont(cellFont);

            // 设置列宽
            int minBytes = colWidth < ExcelHandleUtil.DEFAULT_COLUMN_WIDTH
                    ? ExcelHandleUtil.DEFAULT_COLUMN_WIDTH : colWidth;// 至少字节数

            // 数据导出条数
            int dataListSize = dataList.size();
            // 预计xcel表个数
            int sheetSize = dataListSize
                    % ExcelHandleUtil.EXCEL_MAX_OUT_ROW_NUM == 0
                            ? dataListSize
                                    / ExcelHandleUtil.EXCEL_MAX_OUT_ROW_NUM
                            : dataListSize
                                    / ExcelHandleUtil.EXCEL_MAX_OUT_ROW_NUM + 1;
            subject = dataList.get(0);
            maxOptionSize = getMaxOptionNum(dataList);
            for (int a = 0; a < sheetSize; a++)
            {
                // 创建工作薄的Excel表
                sheet = sheetName == null || "".equals(sheetName)
                        ? workbook.createSheet()
                        : workbook.createSheet(sheetName);
                // 创建行
                firstRow = sheet.createRow(0);
                /**
                 * 添加Excel表头字段 规则：第一列：题目标题 中间列：答案选项 倒数第五列：题目类型 倒数第4列：题目难度
                 * 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
                 */
                // 标题列
                cell = firstRow.createCell(0);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(new XSSFRichTextString("题目"));
                sheet.setColumnWidth(0, minBytes * 256);
                // 选项列
                for (int i = 0; i < maxOptionSize; i++)
                {
                    cell = firstRow.createCell(i + 1);
                    cell.setCellStyle(headerStyle);
                    cell.setCellValue(
                            new XSSFRichTextString("选项" + ANSWER_OPTIONS[i]));
                    sheet.setColumnWidth(i + 1, minBytes * 256);
                }
                // 类型列
                cell = firstRow.createCell(maxOptionSize + 1);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(new XSSFRichTextString("类型"));
                sheet.setColumnWidth(maxOptionSize + 1, minBytes * 256);
                // 难度列
                cell = firstRow.createCell(maxOptionSize + 2);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(new XSSFRichTextString("难度"));
                sheet.setColumnWidth(maxOptionSize + 2, minBytes * 256);
                // 分数列
                cell = firstRow.createCell(maxOptionSize + 3);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(new XSSFRichTextString("分数"));
                sheet.setColumnWidth(maxOptionSize + 3, minBytes * 256);
                // 正确答案列
                cell = firstRow.createCell(maxOptionSize + 4);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(new XSSFRichTextString("正确答案"));
                sheet.setColumnWidth(maxOptionSize + 4, minBytes * 256);
                // 题目解析列
                cell = firstRow.createCell(maxOptionSize + 5);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(new XSSFRichTextString("解析"));
                sheet.setColumnWidth(maxOptionSize + 5, minBytes * 256 * 3);

                // 运算工作表一次遍历条数
                sheetOutLenth = dataListSize > EXCEL_MAX_OUT_ROW_NUM
                        ? EXCEL_MAX_OUT_ROW_NUM : dataListSize;
                // 添加Excel表数据
                for (int i = 0, len = sheetOutLenth; i < len; i++)
                {
                    // 因为设置了列名，因此此处数据从excel的第二行开始出入数据
                    row = sheet.createRow(i + 1);
                    row.setHeightInPoints(100);
                    subject = dataList.get(i);
                    /**
                     * 设置内容 规则：第一列：题目标题 中间列：答案选项 倒数第五列：题目类型 倒数第4列：题目难度
                     * 倒数第三列：题目分数 倒数第二列：正确答案 最后一列：题目解析
                     */
                    // 标题列
                    cell = row.createCell(0);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(
                            new XSSFRichTextString(subject.getName()));
                    // 答案选项列
                    optionsJsonStr = subject.getOptions();
                    if (!StringUtils.isEmpty(optionsJsonStr))
                    {
                        optionContentList = JSONArray.parseArray(optionsJsonStr)
                                .toJavaList(KeyValueBean.class);
                        optionSize = optionContentList.size();
                        // 如果实际选项条数大于最大数据条数，则舍去多的条数
                        if (optionSize > maxOptionSize)
                        {
                            optionSize = maxOptionSize;
                        }
                        for (int j = 0; j < optionSize; j++)
                        {
                            cell = row.createCell(j + 1);
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue(
                                    new XSSFRichTextString(optionContentList
                                            .get(j).getValue().toString()));
                        }
                        // 不全缺失的选项内容
                        if (maxOptionSize > optionSize)
                        {
                            for (int j = 0, l = maxOptionSize
                                    - optionSize; j < l; j++)
                            {
                                cell = row.createCell(j + 1 + optionSize);
                                cell.setCellStyle(cellStyle);
                                cell.setCellValue(new XSSFRichTextString(""));
                            }
                        }
                    } else // 创建空数据
                    {
                        for (int j = 0; j < maxOptionSize; j++)
                        {
                            cell = row.createCell(j + 1);
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue(new XSSFRichTextString(""));
                        }
                    }
                    // 题目类型列(题目类型（0：单选；1：多选;2：判断）)
                    cell = row.createCell(maxOptionSize + 1);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new XSSFRichTextString(
                            OUT_SUBJECT_TYPE_MAP.get(subject.getType())));
                    // 题目题目难度列
                    cell = row.createCell(maxOptionSize + 2);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(subject.getDifficultyName() == null ? ""
                            : subject.getDifficultyName());
                    // 题目分数列
                    cell = row.createCell(maxOptionSize + 3);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(subject.getScore() == null ? ""
                            : subject.getScore().toString());
                    // 正确答案列
                    cell = row.createCell(maxOptionSize + 4);
                    cell.setCellStyle(cellStyle);
                    answersJsonStr = subject.getAnswers();
                    answerBuf = new StringBuffer();
                    if (!StringUtils.isEmpty(answersJsonStr))
                    {
                        answerContentList = JSONArray.parseArray(answersJsonStr)
                                .toJavaList(String.class);
                        for (String answer : answerContentList)
                        {
                            answerBuf.append(answer);
                        }
                    }
                    cell.setCellValue(
                            new XSSFRichTextString(answerBuf.toString()));
                    // 题目解析
                    cell = row.createCell(maxOptionSize + 5);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(
                            new XSSFRichTextString(subject.getAnalysis()));
                }
                // 每次工作薄完成，重置剩余数据条数
                dataListSize -= ExcelHandleUtil.EXCEL_MAX_OUT_ROW_NUM;
            }
            workbook.write(out);
        } finally
        {
            if (workbook != null)
            {
                workbook.close();
            }
        }
    }

    /**
     * 
     * @Description: 获取最大选项列 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2020年4月8日下午4:37:58
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年4月8日下午4:37:58
     * @UpdateRemark: 修改内容
     * @param subjectList
     * @return int
     * @Version: 1.0.0
     */
    private static int getMaxOptionNum(List<? extends Subject> subjectList)
    {
        // 最大选项数(默认为4个)
        int maxNum = DEFAULT_OPTION_SIZE;
        // 题目选项json字符串
        String optionsJsonStr = null;
        // 题目选项内容集合
        List<KeyValueBean> optionContentList = null;
        for (Subject subject : subjectList)
        {
            optionsJsonStr = subject.getOptions();
            if (!StringUtils.isEmpty(optionsJsonStr))
            {
                optionContentList = JSONArray.parseArray(optionsJsonStr)
                        .toJavaList(KeyValueBean.class);
                if (optionContentList.size() > maxNum)
                {
                    maxNum = optionContentList.size();
                }
            }
        }
        // 最大值不能超过答案选项长度
        if (maxNum > ANSWER_OPTIONS.length)
        {
            maxNum = ANSWER_OPTIONS.length;
        }
        return maxNum;
    }
}
