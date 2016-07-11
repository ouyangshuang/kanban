package com.lianjia.sh.kanban.framework;


import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 日期的一些公共工具类
 *
 * @author ouyang
 * @since 2016-07-08 11:33:45
 * @version v1
 * @summary 日期的一些公共工具类
 */
public class DateUtils {

    public static final Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * 缺省的日期显示格式： yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 得到日期的字符串
     *
     * @param date   java.util.Date
     * @param format eg.yyyy-mm-dd
     * @return string
     * @author ouyang
     * @since 2015 -08-18 16:39
     */
    public static String getDateString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }


    /**
     * 根据日期字符串获取日期对象
     *
     * @param dateStr eg."2013-11-12 12:11:22"
     * @param format  eg."yyyy-MM-dd HH:mm:ss"
     * @return date by string
     * @throws ParseException the parse exception
     */
    public static Date getDateByString(String dateStr, String format) throws ParseException {
        if ((dateStr == null) || dateStr.trim().isEmpty()) {
            return null;
        }
        return new SimpleDateFormat(format).parse(dateStr);
    }

}
