package com.zhisen.cslcp.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
import javax.servlet.http.HttpServletResponse;
 
 
public class ZipDownloadUtil {
 
    private static final Logger logger = LoggerFactory.getLogger(ZipDownloadUtil.class);
 
    /**
     * 获取当前系统的临时目录
     */
    private static final String FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
 
    private static final int ZIP_BUFFER_SIZE = 8192;
 
    /**
     * zip打包下载
     *
     * @param response
     * @param zipFileName
     * @param fileList
     */
    public static void zipDownload(HttpServletResponse response, String zipFileName, List<File> fileList) {
        // zip文件路径
        String zipPath = FILE_PATH + zipFileName;
        try {
            //创建zip输出流
            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath))) {
                //声明文件集合用于存放文件
                byte[] buffer = new byte[1024];
                //将文件放入zip压缩包
                for (int i = 0; i < fileList.size(); i++) {
                    File file = fileList.get(i);
                    try (FileInputStream fis = new FileInputStream(file)) {
                        out.putNextEntry(new ZipEntry(file.getName()));
                        int len;
                        // 读入需要下载的文件的内容，打包到zip文件
                        while ((len = fis.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                        out.closeEntry();
                    }
                }
            }
            //下载zip文件
            downFile(response, zipFileName);
        } catch (Exception e) {
            logger.error("文件下载出错", e);
        } finally {
            // zip文件也删除
            fileList.add(new File(zipPath));
        }
    }
 
 
    /**
     * 文件下载
     *
     * @param response
     * @param zipFileName
     */
    private static void downFile(HttpServletResponse response, String zipFileName) {
        try {
            String path = FILE_PATH + zipFileName;
            File file = new File(path);
            if (file.exists()) {
                try (InputStream ins = new FileInputStream(path);
                     BufferedInputStream bins = new BufferedInputStream(ins);
                     OutputStream outs = response.getOutputStream();
                     BufferedOutputStream bouts = new BufferedOutputStream(outs)) {
                    response.setContentType("application/x-download");
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(zipFileName, "UTF-8"));
                    int bytesRead = 0;
                    byte[] buffer = new byte[ZIP_BUFFER_SIZE];
                    while ((bytesRead = bins.read(buffer, 0, ZIP_BUFFER_SIZE)) != -1) {
                        bouts.write(buffer, 0, bytesRead);
                    }
                    bouts.flush();
                }
            }
        } catch (Exception e) {
            logger.error("文件下载出错", e);
        }
    }
 
    /**
     * 删除文件
     *
     * @param fileList
     * @return
     */
    public static void deleteFile(List<File> fileList) {
        for (File file : fileList) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}