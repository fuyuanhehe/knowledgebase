package com.hg.knowledgebase.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
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
import org.springframework.util.StringUtils;

import com.hg.knowledgebase.annotation.ExcelColumn;
import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.customenum.SexEnum;

/**
 * 
 * @Description: excel处理工具
 * @Author: 况克冬
 * @CreateDate: 2020年2月27日下午8:34:47
 * @Version: 1.0.0
 */
public class ExcelHandleUtil
{

    private ExcelHandleUtil()
    {
    }

    /**
     * 日前格式化对象
     */
    public static final SimpleDateFormat format = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");

    /**
     * 默认字体宽度
     */
    public static final int DEFAULT_COLUMN_WIDTH = 17;

    /**
     * 2007excel最大导出行数（1048576）
     */
    public static final int EXCEL_MAX_OUT_ROW_NUM = 1048000;

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 加载处理列名信息
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午9:35:26
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午9:35:26
     * @UpdateRemark: 修改内容
     * @param cla
     *            查询的类模版（（注意：类型必须符合java jopo规范））
     * @param columnMap
     *            处理列数据<属性名,excel列名> void
     * @Version: 1.0.0
     */
    public static void loadHandleColumnMap(Class<? extends Object> cla,
            Map<String, String> columnMap)
    {
        // 校验cla数据，避免Object类传入
        if (!ClassUtil.cheakIsExtends(cla))
        {
            return;
        }
        // 先获取继承类处理字段map信息，满足当前类可以覆盖继承类的处理字段map信息
        loadHandleColumnMap(cla.getSuperclass(), columnMap);
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields)
        {
            // 查询该字段属性上面的@ExcelColumn注解
            ExcelColumn excelColumn = field
                    .getDeclaredAnnotation(ExcelColumn.class);
            // 如果有注解则保存到处理列map集合
            if (excelColumn != null)
            {
                columnMap.put(field.getName(), excelColumn.value());
            }
        }
    }

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 加载处理性别列值替换信息
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午9:35:26
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午9:35:26
     * @UpdateRemark: 修改内容
     * @param cla
     *            查询的类模版（（注意：类型必须符合java jopo规范））
     * @param columnMap
     *            处理列数据<属性名,excel列名> void
     * @Version: 1.0.0
     */
    public static void loadHandleColumnReplaceSex(Class<? extends Object> cla,
            Map<String, SexEnum[]> sexReplaceMap)
    {
        // 校验cla数据，避免Object类传入
        if (!ClassUtil.cheakIsExtends(cla))
        {
            return;
        }
        // 先获取继承类处理字段map信息，满足当前类可以覆盖继承类的处理字段map信息
        loadHandleColumnReplaceSex(cla.getSuperclass(), sexReplaceMap);
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields)
        {
            // 查询该字段属性上面的@ExcelColumn注解
            ExcelColumn excelColumn = field
                    .getDeclaredAnnotation(ExcelColumn.class);
            // 如果有注解则保存到处理列map集合
            if (excelColumn != null && excelColumn.isSex()
                    && excelColumn.sexs().length > 0)
            {
                sexReplaceMap.put(field.getName(), excelColumn.sexs());
            }
        }
    }

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 加载处理列排序map信息
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日上午9:44:41
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日上午9:44:41
     * @UpdateRemark: 修改内容
     * @param cla
     *            查询的类模版（（注意：类型必须符合java jopo规范））
     * @param columnSortMap
     *            处理列数据<属性名,排序号> void
     * @Version: 1.0.0
     */
    public static void loadHandleColumnSort(Class<? extends Object> cla,
            Map<String, Integer> columnSortMap)
    {
        // 校验cla数据，避免Object类传入
        if (!ClassUtil.cheakIsExtends(cla))
        {
            return;
        }
        // 先获取继承类处理列排序字段map信息，满足当前类可以覆盖继承类的处理字段map信息
        loadHandleColumnSort(cla.getSuperclass(), columnSortMap);
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields)
        {
            // 查询该字段属性上面的@ExcelColumn注解
            ExcelColumn excelColumn = field
                    .getDeclaredAnnotation(ExcelColumn.class);
            // 如果有注解则保存到处理列map集合
            if (excelColumn != null)
            {
                columnSortMap.put(field.getName(), excelColumn.sort());
            }
        }
    }

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 获取有序@ExcelColumn集合
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日上午9:44:41
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日上午9:44:41
     * @UpdateRemark: 修改内容
     * @param cla
     *            查询的类模版（（注意：类型必须符合java jopo规范））
     * @param sortColumnList
     *            处理后的属性列排序（规则：升序） void
     * @Version: 1.0.0
     */
    public static List<Map.Entry<String, Integer>> getHandleColumnSort(
            Class<? extends Object> cla)
    {
        Map<String, Integer> columnSortMap = new HashMap<>(1);
        loadHandleColumnSort(cla, columnSortMap);
        List<Map.Entry<String, Integer>> entryList = new ArrayList(
                columnSortMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Entry<String, Integer> beforeObj,
                    Entry<String, Integer> afterObj)
            {
                return beforeObj.getValue() - afterObj.getValue();
            }
        });
        return entryList;
    }

    /**
     * 注意：1、参数调用方验证 2、调用方关闭输入流
     * 
     * @Description: 解析excel数据
     * @Author: 况克冬
     * @CreateDate: 2020年2月27日下午9:52:28
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月27日下午9:52:28
     * @UpdateRemark: 修改内容
     * @param in
     *            excel文件流
     * @param cla
     *            待解析的类模版（（注意：类型必须符合java jopo规范））
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
    public static <T> List<T> analysisExcel(InputStream in, Class<T> cla)
            throws EncryptedDocumentException, InvalidFormatException,
            IOException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, InstantiationException,
            NoSuchMethodException, SecurityException
    {
        Workbook workbook = null;
        List<T> dataList = null;
        try
        {
            workbook = WorkbookFactory.create(in);
            if (workbook instanceof XSSFWorkbook) // 仅支持excel2007即以后的数据解析
            {
                dataList = excel2007ConvertExcelData(workbook, cla);
            } else // 仅支持excel2003即以前的数据解析
            {
                dataList = excel2003ConvertExcelData(workbook, cla);
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
     * @Description: 解析excel2007即以后的数据 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2019年12月4日下午4:08:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年12月4日下午4:08:13
     * @UpdateRemark: 修改内容
     * @param workbook
     * @param cla
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     *             List<T>
     * @Version: 1.0.0
     */
    public static <T> List<T> excel2007ConvertExcelData(Workbook workbook,
            Class<T> cla) throws NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, IOException
    {
        // 转换类型
        T t = null;
        // 获取当前工作表最后一行的行号,开始下标为0
        int lastRowNum = 0;
        // 单元格对象值
        Object value = null;
        // 获取第一行数据最后一列的列号，开始下标为1
        short lastCellNum = 0;
        // 类属性名
        String fieldName = null;
        // 方法对象
        Method method = null;
        // 存放excel解析后类集合
        List<T> dataList = null;
        // 工作薄对象
        XSSFWorkbook xwb = null;
        // 列对象
        XSSFCell cell = null;
        // 行对象
        XSSFRow hssfRow = null;
        // 获取第一行数据
        XSSFRow firstRow = null;
        // 性别枚举
        SexEnum[] sexEnums = null;
        // 顺序存放excel的列名称集合
        List<String> keyList = null;
        Map<String, String> columnMap = null;
        // 性别替换数据
        Map<String, SexEnum[]> sexReplaceMap = null;
        try
        {
            xwb = (XSSFWorkbook) workbook;
            // 获取工作薄
            Iterator<Sheet> itor = xwb.sheetIterator();
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
                    // 加载存储数据集合
                    dataList = new ArrayList<>(lastRowNum);
                    columnMap = new HashMap<>(1);
                    // 加载类属性名和@ExcelColumn的关系map数据
                    loadHandleColumnMap(cla, columnMap);
                    // 反转数据map
                    columnMap = StringUtil.reversalMap(columnMap);
                    // 性别替换数据
                    sexReplaceMap = new HashMap<>(1);
                    loadHandleColumnReplaceSex(cla, sexReplaceMap);

                    // 获取第一行数据最后一列的列号，开始下标为1
                    lastCellNum = firstRow.getLastCellNum();
                    // 顺序存放excel的列名称集合
                    keyList = new ArrayList<>(lastCellNum);
                    // 组合列数据字段名
                    for (int i = 0; i < lastCellNum; i++)
                    {
                        cell = firstRow.getCell(i);
                        keyList.add(cell.getRichStringCellValue().getString());
                    }
                    // 组合列数据行
                    for (int i = 1; i <= lastRowNum; i++)
                    {
                        // 创建数据新实例
                        t = cla.newInstance();
                        hssfRow = hssfSheet.getRow(i);
                        // 组合列数据值
                        for (int j = 0; j < lastCellNum; j++)
                        {
                            fieldName = columnMap.get(keyList.get(j));
                            // 如果字段不存在，则跳过当前循环
                            if (fieldName == null || "".equals(fieldName))
                            {
                                continue;
                            }
                            method = cla.getMethod(
                                    GlobalConstant.SET + StringUtil
                                            .firstToUpperCase(fieldName),
                                    ClassUtil.getFieldTypeByFieldName(cla,
                                            fieldName));
                            method.setAccessible(true);
                            cell = hssfRow.getCell(j);
                            if (cell != null)
                            {
                                value = getCellValue(cell);
                            } else
                            {
                                value = "";
                            }
                            // 替换性别值
                            sexEnums = sexReplaceMap.get(fieldName);
                            if (sexEnums != null)
                            {
                                for (SexEnum sexEnum : sexEnums)
                                {
                                    if (value.equals(sexEnum.getValue()))
                                    {
                                        value = sexEnum.getKey();
                                        break;
                                    }
                                }
                            }
                            method.invoke(t, value);
                        }
                        dataList.add(t);
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
     * @Description: 解析excel2003即以前的数据 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2019年12月4日下午4:08:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019年12月4日下午4:08:13
     * @UpdateRemark: 修改内容
     * @param workbook
     * @param cla
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     *             List<T>
     * @Version: 1.0.0
     */
    public static <T> List<T> excel2003ConvertExcelData(Workbook workbook,
            Class<T> cla) throws  NoSuchMethodException, InstantiationException,
    IllegalAccessException, IllegalArgumentException,
    InvocationTargetException, IOException
    {
        // 转换类型
        T t = null;
        // 类属性名
        String fieldName = null;
        // 方法对象
        Method method = null;
        // 存放excel解析后类集合
        List<T> dataList = null;
        // 列对象
        HSSFCell cell = null;
        // 行对象
        HSSFRow hssfRow = null;
        // 单元格对象值
        Object value = null;
        // 工作薄对象
        HSSFWorkbook hSSFWorkbook = null;
        // 性别枚举
        SexEnum[] sexEnums = null;
        // 工作表
        HSSFSheet hssfSheet = null;
        // 获取当前工作表最后一行的行号,开始下标为0
        int lastRowNum = 0;
        // 行数据
        HSSFRow firstRow = null;
        Map<String, String> columnMap = null;
        // 性别替换数据
        Map<String, SexEnum[]> sexReplaceMap = null;
        // 获取第一行数据最后一列的列号，开始下标为1
        short lastCellNum = 0;
        List<String> keyList = null;
        try
        {
            hSSFWorkbook = (HSSFWorkbook) workbook;
            // 获取工作簿列表
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
                    // 加载存储数据集合
                    dataList = new ArrayList<>(lastRowNum);
                    columnMap = new HashMap<>(1);
                    // 加载类属性名和@ExcelColumn的关系map数据
                    loadHandleColumnMap(cla, columnMap);
                    // 反转数据map
                    columnMap = StringUtil.reversalMap(columnMap);
                    // 性别替换数据
                    sexReplaceMap = new HashMap<>(1);
                    loadHandleColumnReplaceSex(cla, sexReplaceMap);

                    // 获取第一行数据最后一列的列号，开始下标为1
                    lastCellNum = firstRow.getLastCellNum();
                    keyList = new ArrayList<>(lastCellNum);
                    // 组合列数据字段名
                    for (int i = 0; i < lastCellNum; i++)
                    {
                        cell = firstRow.getCell(i);
                        keyList.add(cell.getRichStringCellValue().getString());
                    }
                    // 组合列数据行
                    for (int i = 1; i <= lastRowNum; i++)
                    {
                        // 创建数据新实例
                        t = cla.newInstance();
                        hssfRow = hssfSheet.getRow(i);
                        // 组合列数据值
                        for (int j = 0; j < lastCellNum; j++)
                        {
                            fieldName = columnMap.get(keyList.get(j));
                            // 如果字段不存在，则跳过当前循环
                            if (fieldName == null || "".equals(fieldName))
                            {
                                continue;
                            }
                            method = cla.getMethod(
                                    GlobalConstant.SET + StringUtil
                                            .firstToUpperCase(fieldName),
                                    ClassUtil.getFieldTypeByFieldName(cla,
                                            fieldName));
                            method.setAccessible(true);

                            cell = hssfRow.getCell(j);
                            if (cell != null)
                            {
                                value = getCellValue(cell);
                            } else
                            {
                                value = "";
                            }
                            // 替换性别值
                            sexEnums = sexReplaceMap.get(fieldName);
                            if (sexEnums != null)
                            {
                                for (SexEnum sexEnum : sexEnums)
                                {
                                    if (value.equals(sexEnum.getValue()))
                                    {
                                        value = sexEnum.getKey();
                                        break;
                                    }
                                }
                            }
                            method.invoke(t, value);
                        }
                        dataList.add(t);
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
     * @Description: 导出简单数据Excel 注意：参数调用方验证有效性，并且注意外部关闭输出流
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日上午9:17:40
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日上午9:17:40
     * @UpdateRemark: 修改内容
     * @param dataList
     * @param out
     * @param sheetName
     * @param colWidth
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IOException
     * @Version: 1.0.0
     */
    public static void exportSimpleExcel(List<? extends Object> dataList,
            OutputStream out, String sheetName, int colWidth)
            throws  NoSuchMethodException, InstantiationException,
            IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, IOException
    {
        // 行对象
        SXSSFRow row = null;
        // 解析值对象
        Object value = null;
        // 泛型方法
        Method method = null;
        // 泛型类
        Class<?> objClass = null;
        // 存放数据值对象
        Object obj = null;
        // 字段属性名
        String fieldName = null;
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
        if (dataList == null || dataList.size() == 0 || out == null)
        {
            return;
        }
        try
        {
            Class<?> cla = dataList.get(0).getClass();
            // 获取排序后的列名顺序
            List<Entry<String, Integer>> cellList = getHandleColumnSort(cla);
            Map<String, String> cellMap = new HashMap<>(cellList.size());
            // 加载列名的中文
            loadHandleColumnMap(cla, cellMap);
            // 加载性别替换值
            Map<String, SexEnum[]> sexMap = new HashMap<>();
            loadHandleColumnReplaceSex(cla, sexMap);

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
            CellStyle headerStyle = getDefultHeaderStyle(workbook);
            headerStyle.setFont(headerFont);
            // 单元格样式
            Font cellFont = workbook.createFont();
            cellFont.setBold(false);
            CellStyle cellStyle = getDefultCellStyle(workbook);
            cellStyle.setFont(cellFont);

            // 设置列宽
            int minBytes = colWidth < DEFAULT_COLUMN_WIDTH
                    ? DEFAULT_COLUMN_WIDTH : colWidth;// 至少字节数

            // 数据导出条数
            int dataListSize = dataList.size();
            // 预计xcel表个数
            int sheetSize = dataListSize % EXCEL_MAX_OUT_ROW_NUM == 0
                    ? dataListSize / EXCEL_MAX_OUT_ROW_NUM
                    : dataListSize / EXCEL_MAX_OUT_ROW_NUM + 1;
            for (int a = 0; a < sheetSize; a++)
            {
                // 创建工作薄的Excel表
                sheet = sheetName == null || "".equals(sheetName)
                        ? workbook.createSheet()
                        : workbook.createSheet(sheetName);
                // 创建行
                firstRow = sheet.createRow(0);
                // 添加Excel表头字段
                for (int i = 0, len = cellList.size(); i < len; i++)
                {
                    cell = firstRow.createCell(i);
                    cell.setCellStyle(headerStyle);
                    cell.setCellValue(new XSSFRichTextString(
                            cellMap.get(cellList.get(i).getKey())));
                    sheet.setColumnWidth(i, minBytes * 256);
                }
                objClass = dataList.get(0).getClass();
                // 运算工作表一次遍历条数
                sheetOutLenth = dataListSize > EXCEL_MAX_OUT_ROW_NUM
                        ? EXCEL_MAX_OUT_ROW_NUM : dataListSize;
                // 添加Excel表数据
                for (int i = 0, len = sheetOutLenth; i < len; i++)
                {
                    // 因为设置了列名，因此此处数据从excel的第二行开始出入数据
                    row = sheet.createRow(i + 1);
                    obj = dataList.get(i);
                    for (int j = 0, len1 = cellList.size(); j < len1; j++)
                    {
                        fieldName = cellList.get(j).getKey();
                        method = objClass.getMethod(GlobalConstant.GET
                                + StringUtil.firstToUpperCase(fieldName));
                        method.setAccessible(true);
                        value = method.invoke(obj);
                        // 查询当前字段属性有无替换字段，有则覆盖原有数据
                        if (sexMap.get(fieldName) != null && value != null)
                        {
                            for (SexEnum sexEnum : sexMap.get(fieldName))
                            {
                                if (value.equals(sexEnum.getKey()))
                                {
                                    value = sexEnum.getValue();
                                    break;
                                }
                            }
                        }

                        cell = row.createCell(j);
                        cell.setCellStyle(cellStyle);
                        if (value == null) // 如果数据内容为空，则默认添加空串
                        {
                            cell.setCellValue(new XSSFRichTextString(""));
                        } else if (value instanceof Number) // 如果是数字类型,则添加双精度类型
                        {
                            cell.setCellValue(Double.valueOf(value.toString()));
                        } else if (value instanceof String) // 字符串类型
                        {
                            cell.setCellValue(
                                    new XSSFRichTextString((String) value));
                        } else if (value instanceof Boolean) // 布尔类型
                        {
                            cell.setCellValue((boolean) value);
                        } else if (value instanceof Date) // java日期类型
                        {
                            cell.setCellValue(new XSSFRichTextString(
                                    format.format((Date) value)));
                        } else if (value instanceof java.sql.Date) // sql日期类型
                        {
                            cell.setCellValue(new XSSFRichTextString(
                                    format.format((Date) value)));
                        } else if (value instanceof Timestamp) // sql
                                                               // Timestamp日期类型
                        {
                            cell.setCellValue(new XSSFRichTextString(
                                    format.format((Date) value)));
                        }
                    }
                }
                // 每次工作薄完成，重置剩余数据条数
                dataListSize -= EXCEL_MAX_OUT_ROW_NUM;
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
     * @Description: 获取单元格值 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2020年3月30日下午3:50:25
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月30日下午3:50:25
     * @UpdateRemark: 修改内容
     * @param cell
     *            单元格对象
     * @return Object
     * @Version: 1.0.0
     */
    private static Object getCellValue(Cell cell)
    {
        // 单元格值对象
        Object value = null;
        // 单元格字符串值
        String valueStr = null;
        // 单元格中有数据，如果无数据默认添加内容null到集合
        switch (cell.getCellTypeEnum())
        {
        case STRING:// 字符串类型
            valueStr = cell.getRichStringCellValue().getString();
            // 兼容excel数据首字符串为符号“'”的信息
            if (!StringUtils.isEmpty(valueStr) && valueStr.startsWith("'")
                    && valueStr.length() > 1)
            {
                valueStr = valueStr.substring(1);
            }
            value = valueStr;
            break;
        case BLANK:// 空类型
            value = cell.getRichStringCellValue().getString();
            break;
        case NUMERIC:// 数字类型,注意：此处日期也会是数字类型
            // 判断单元格是否为日期类型。是则用日期取法，否用数字取法
            if (HSSFDateUtil.isCellDateFormatted(cell)) // 日期获取方法
            {
                value = cell.getDateCellValue();
            } else // 数字获取方法
            {
                // 设置获取数字时出现科学计数
                cell.setCellType(CellType.STRING);
                value = cell.getStringCellValue();
            }
            break;
        case BOOLEAN:// 布尔类型
            value = cell.getBooleanCellValue();
            cell.getDateCellValue();
            break;
        case FORMULA:// 公式类型
            value = cell.getCellFormula();
            break;
        default:
            value = "";
            break;
        }
        return value;
    }

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 获取默认单元格样式
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午3:29:45
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午3:29:45
     * @UpdateRemark: 修改内容
     * @param workbook
     * @return CellStyle
     * @Version: 1.0.0
     */
    public static CellStyle getDefultCellStyle(SXSSFWorkbook workbook)
    {
        CellStyle cellStyle = workbook.createCellStyle();
        // cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    /**
     * 注意：参数调用方验证
     * 
     * @Description: 获取默认表头样式
     * @Author: 况克冬
     * @CreateDate: 2020年2月28日下午3:29:45
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年2月28日下午3:29:45
     * @UpdateRemark: 修改内容
     * @param workbook
     * @return CellStyle
     * @Version: 1.0.0
     */
    public static CellStyle getDefultHeaderStyle(SXSSFWorkbook workbook)
    {
        CellStyle headerStyle = workbook.createCellStyle();
        // headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        return headerStyle;
    }
}
