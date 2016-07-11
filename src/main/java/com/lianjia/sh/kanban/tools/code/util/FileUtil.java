package com.lianjia.sh.kanban.tools.code.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 文件读写工具类
 *
 * @author ouyang
 * @since 2015-02-09 12:09
 */
public class FileUtil {

    //换行字符 忽略检查 使用系统属性
    @SuppressWarnings("AccessOfSystemProperties")
    private static final String LINE = System.getProperty("line.separator");

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 复制文件
     *
     * @param src     源文件
     * @param newFile 目标文件
     * @author ouyang
     * @since 2014/12/12 15:36
     */
    public static void copyFile(String src, String newFile) {
        try (InputStream in = FileUtil.class.getResourceAsStream(src)) {
            File file = new File(newFile);
            if (!file.exists()) {
                if(file.createNewFile()){
                    LOGGER.debug("建立文件{}", file.getAbsolutePath());
                }
            }
            try (FileOutputStream out = new FileOutputStream(file)) {
                int len;
                //noinspection CheckForOutOfMemoryOnLargeArrayAllocation
                byte[] buffer = new byte[1024];
                //忽略检查
                //noinspection NestedAssignment
                while ((len = in.read(buffer)) != -1) {
                    for (int i = 0; i < len; i++) {
                        out.write(buffer[i]);
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getClass().getName(), e);
            }
        } catch (IOException e) {
            LOGGER.error(e.getClass().getName(), e);
        }
    }

    /**
     * 读文件到StringBuilder
     *
     * @param stringBuilder 字符串StringBuilder
     * @param filePath      读入的文件
     * @author ouyang
     * @since 2014/12/15 17:01
     */
    public static void readFile(StringBuilder stringBuilder, String filePath) {
        try (InputStream inputStream = FileUtil.class.getResourceAsStream(filePath);
             Reader readerIn = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
             BufferedReader reader = new BufferedReader(readerIn)) {
            String line = reader.readLine();        // 用来保存每行读取的内容
            while (line != null) {          // 如果 line 为空说明读完了
                stringBuilder.append(line);        // 将读到的内容添加到 buffer 中
                stringBuilder.append(LINE);        // 添加换行符
                line = reader.readLine();   // 读取下一行
            }
        } catch (IOException e) {
            LOGGER.error(e.getClass().getName(), e);
        }
    }

    /**
     * 将字符写入到文件
     *
     * @param stringBuilder 字符
     * @param filePath      目标文件
     * @author ouyang
     * @since 2014/12/12 16:11
     */
    public static void writeFile(StringBuilder stringBuilder, String filePath) {
        File oldFile = new File(filePath);
        if (oldFile.exists()) {
            if(oldFile.delete()){
                LOGGER.debug("删除文件{}",oldFile.getAbsolutePath());
            }
        }
        File file = new File(filePath);
        try {
            if(file.createNewFile()) {
                LOGGER.info("建立文件：{}", file.getPath());
            }
        } catch (IOException e) {
            LOGGER.error(e.getClass().getName(), e);
        }
        try (PrintStream ps = new PrintStream(new FileOutputStream(file), false, "UTF-8")) {
            ps.print(stringBuilder.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            LOGGER.error(e.getClass().getName(), e);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @author ouyang
     * @since 2014/12/15 18:08
     */
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (String aChildren : children) {
                deleteDir(new File(dir, aChildren));
            }
        }
        // 目录此时为空，可以删除
        if(dir.delete()) {
            LOGGER.info("删除：{}", dir.getPath());
        }
    }
}
