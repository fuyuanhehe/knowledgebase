package com.hg.knowledgebase.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hg.knowledgebase.constant.GlobalConstant;
import com.hg.knowledgebase.customenum.OfficeFileTypeEnum;

/**
 * Created by lenovo12 on 2018/8/18.
 */
public final class FileUtil
{

    private FileUtil()
    {
    }

    /**
     * 上传的文件路径
     */
    public static final String UPLOAD_PATH;

    /**
     * 百度编辑器上传的文件路径
     */
    public static final String UPLOAD_UEDITER_PATH;

    /**
     * 上传文件根路径
     */
    public static final String UPLOAD = "upload";

    /**
     * 临时文件路径
     */
    public static final String TEMP_FILE_PATH = "tempFilePath";

    /**
     * 日期格式转换对象
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 扩展文件集合类型常量
     */
    public static final List<String> EXTS_DOCUMENT_TYPES = Arrays.asList(".doc",
            ".docx", ".docm", ".dot", ".dotx", ".dotm", ".odt", ".fodt", ".ott",
            ".rtf", ".txt", ".html", ".htm", ".mht", ".pdf", ".djvu", ".fb2",
            ".epub", ".xps");

    /**
     * 扩展电子表格集合类型常量
     */
    public static final List<String> EXTS_SPREADSHEET_TYPES = Arrays.asList(
            ".xls", ".xlsx", ".xlsm", ".xlt", ".xltx", ".xltm", ".ods", ".fods",
            ".ots", ".csv");

    /**
     * 扩展演示集合类型常量
     */
    public static final List<String> EXTS_PRESENTATION_TYPES = Arrays.asList(
            ".pps", ".ppsx", ".ppsm", ".ppt", ".pptx", ".pptm", ".pot", ".potx",
            ".potm", ".odp", ".fodp", ".otp");

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    static
    {
        UPLOAD_PATH = new File("").getAbsolutePath() + File.separator + UPLOAD
                + File.separator;
        UPLOAD_UEDITER_PATH = UPLOAD_PATH + "uediter" + File.separator;
        // 初始化普通文件上传目录
        File file = new File(UPLOAD_PATH);
        if (!file.exists())
        {
            file.mkdirs();
        }
        // 初始化百度编辑器文件上传目录
        file = new File(UPLOAD_UEDITER_PATH);
        if (!file.exists())
        {
            file.mkdirs();
        }
        log.info("----------初始化上传路径,路径：{}-------------", UPLOAD_PATH);
    }

    /**
     * @Description: 获取路径文件中的文件名
     * @Author: 况克冬
     * @CreateDate: 2019/4/25 15:57
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019/4/25 15:57
     * @UpdateRemark: 修改内容
     * @param: pathFileName
     *             文件路径名
     * @return: String 文件名
     * @Version: 1.0.0
     */
    public static String getFileName(String pathFileName)
    {
        String fileName = null;
        if (!StringUtils.isEmpty(pathFileName))
        {
            if (pathFileName.contains(GlobalConstant.BACKSLASH))
            {
                fileName = pathFileName.substring(
                        pathFileName.lastIndexOf(GlobalConstant.BACKSLASH) + 1);
            } else
            {
                fileName = pathFileName;
            }
        }
        return fileName;
    }

    /**
     * @param fileName
     *            文件名
     * @return String
     * @Description: 获取文件后缀
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日上午10:41:26
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月11日上午10:41:26
     * @UpdateRemark: 修改内容
     * @Version: 1.0.0
     */
    public static String getFileSuffix(String fileName)
    {
        String suffix = null;
        if (StringUtils.isEmpty(fileName)
                || !fileName.contains(GlobalConstant.SPOT))
        {
            return suffix;
        }
        suffix = fileName.substring(fileName.lastIndexOf(GlobalConstant.SPOT));
        return suffix;
    }

    /**
     * @param fileName
     *            文件名
     * @return String
     * @Description: 获取文件类型
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日上午10:41:26
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月11日上午10:41:26
     * @UpdateRemark: 修改内容
     * @Version: 1.0.0
     */
    public static String getFileType(String fileName)
    {
        String type = null;
        if (StringUtils.isEmpty(fileName))
        {
            return type;
        }
        String suffix = getFileSuffix(fileName);
        if (suffix == null || suffix.length() < 2)
        {
            return type;
        }
        return suffix.substring(1);
    }

    /**
     * @param fileName
     *            文件名
     * @return OfficeFileTypeEnum
     * @Description: 获取Office类型
     * @Author: 况克冬
     * @CreateDate: 2020年3月11日上午10:41:26
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月11日上午10:41:26
     * @UpdateRemark: 修改内容
     * @Version: 1.0.0
     */
    public static OfficeFileTypeEnum getOfficeFileType(String fileName)
    {
        OfficeFileTypeEnum type = null;
        String suffix = getFileSuffix(fileName);
        if (suffix == null)
        {
            return type;
        }
        if (EXTS_DOCUMENT_TYPES.contains(suffix))
        {
            type = OfficeFileTypeEnum.TEXT;
        } else if (EXTS_SPREADSHEET_TYPES.contains(suffix))
        {
            type = OfficeFileTypeEnum.SPREADSHEET;
        } else if (EXTS_PRESENTATION_TYPES.contains(suffix))
        {
            type = OfficeFileTypeEnum.PRESENTATION;
        }
        return type;
    }

    /**
     * @Description: 获取路径文件中的文件路径
     * @Author: 况克冬
     * @CreateDate: 2019/4/26 12:34
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019/4/26 12:34
     * @UpdateRemark: 修改内容
     * @param: pathFileName
     *             文件路径名
     * @return: String 路径名
     * @Version: 1.0.0
     */
    public static String getFilePath(String pathFileName)
    {
        String filePath = null;
        if (!StringUtils.isEmpty(pathFileName)
                && pathFileName.contains(GlobalConstant.BACKSLASH))
        {
            filePath = pathFileName.substring(0,
                    pathFileName.lastIndexOf(GlobalConstant.BACKSLASH));
        }
        return filePath;
    }

    /**
     * 创建文件 注意：参数调用方验证
     *
     * @param filePath
     *            文件路径
     * @param fileName
     *            文件名
     * @return
     */
    public static File createFile(String filePath, String fileName)
    {
        createFilePath(filePath);
        return new File(filePath, fileName);
    }

    /**
     * 创建文件路径
     *
     * @param filePath
     */
    public static void createFilePath(String filePath)
    {
        File path = new File(filePath);
        if (!path.exists())
        {
            path.mkdirs();
        }
    }

    /**
     * @param fileUrl
     *            文件访问地址
     * @return String
     * @Description: 获取文件真实地址路径 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午3:25:10
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月12日下午3:25:10
     * @UpdateRemark: 修改内容
     * @Version: 1.0.0
     */
    public static String getFileAddrPath(String fileUrl)
    {
        String fileAddrPath = fileUrl.replace(GlobalConstant.BACKSLASH
                + GlobalConstant.REQ_RES_PATH + GlobalConstant.BACKSLASH,
                UPLOAD_PATH);
        return fileAddrPath;
    }

    /**
     * @param filePath
     * @return boolean
     * @Description: 验证文件是否存在 注意：参数调用方验证
     * @Author: 况克冬
     * @CreateDate: 2020年3月12日下午3:30:13
     * @UpdateUser: 况克冬
     * @UpdateDate: 2020年3月12日下午3:30:13
     * @UpdateRemark: 修改内容
     * @Version: 1.0.0
     */
    public static boolean cheakFileExist(String filePath)
    {
        return new File(filePath).isFile();
    }

    /**
     * 保存文件到目标路径 注： 目标路径调用方验证是否存在
     *
     * @param in
     *            输入流
     * @param targetPath
     *            目标路径
     * @param fileName
     *            文件名称
     * @return String 保存后的文件路径
     * @throws Exception
     */
    public static String saveToTargetPath(InputStream in, String fileNamePath)
            throws IOException
    {
        String filePathName = null;// 保存成功后的文件路径名称
        if (in != null && !StringUtils.isEmpty(fileNamePath))
        {
            FileOutputStream fos = null;
            try
            {
                File file = new File(fileNamePath);
                fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int b = -1;
                while ((b = in.read(bytes)) != -1)
                {
                    fos.write(bytes, 0, b);
                }
                fos.flush();
                filePathName = file.getPath();
            } catch (IOException e)
            {
                throw new IOException();
            } finally
            {
                closeStream(in, null, fos, null);
            }
        }
        return filePathName;
    }

    /**
     * 保存文件到目标路径 注： 目标路径调用方验证是否存在
     *
     * @param in
     *            输入流
     * @param targetPath
     *            目标路径
     * @param fileName
     *            文件名称
     * @return String 保存后的文件路径
     * @throws Exception
     */
    public static String saveToTargetPath(InputStream in, String targetPath,
            String fileName) throws IOException
    {
        String filePath = targetPath + File.separator + sdf.format(new Date());
        createFilePath(filePath);
        return saveToTargetPath(in, filePath + File.separator + fileName);
    }

    /**
     * @Description: 获取目标路径文件 注意：调用方需要关闭输入流
     * @Author: 况克冬
     * @CreateDate: 2019/4/29 11:52
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019/4/29 11:52
     * @UpdateRemark: 修改内容
     * @param: targetPathName
     *             目标路径文件
     * @return: InputStream 文件流信息
     * @Version: 1.0.0
     */
    public static InputStream getTargetPathFile(String targetPathName)
            throws FileNotFoundException
    {
        FileInputStream fis = null;
        if (!StringUtils.isEmpty(targetPathName))
        {
            File file = new File(targetPathName);
            if (file.isFile())
            {
                fis = new FileInputStream(file);
            }
        }
        return fis;
    }

    /**
     * @Description: 关闭流
     * @Author: 况克冬
     * @CreateDate: 2019/4/25 14:12
     * @UpdateUser: 况克冬
     * @UpdateDate: 2019/4/25 14:12
     * @UpdateRemark: 修改内容
     * @param: in
     *             输入流
     * @param: bis
     *             输入缓存流
     * @param: out
     *             输出流
     * @param: bos
     *             输出缓存流
     * @return:
     * @Version: 1.0.0
     */
    public static void closeStream(InputStream in, BufferedInputStream bis,
            OutputStream out, BufferedOutputStream bos)
    {
        if (bos != null)
        {
            try
            {
                bos.close();
            } catch (IOException e)
            {
                e.getMessage();
            }
        }
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
        if (bis != null)
        {
            try
            {
                bis.close();
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
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     * 
     * @param sPath
     *            要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath)
    {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists())
        { // 不存在返回 false
            return flag;
        } else
        {
            // 判断是否为文件
            if (file.isFile())
            { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else
            { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath)
    {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath
     *            被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath)
    {
        /*
         * System.out.println(sPath); //如果sPath不以文件分隔符结尾，自动添加文件分隔符 if
         * (!sPath.endsWith(File.separator)) { sPath = sPath + File.separator; }
         * System.out.println(sPath);
         */
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory())
        {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            // 删除子文件
            if (files[i].isFile())
            {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else
            {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete())
        {
            return true;
        } else
        {
            return false;
        }

    }

}
