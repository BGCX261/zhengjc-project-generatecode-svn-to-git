package cn.com.photop.sap.contactme.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dengdahong214
 *
 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class DateUtil {

    public static final int MILLISECONDS_PER_SECOND = 1000;
    public static final int MILLISECONDS_PER_DAY = 86400000;
    public static final int MILLISECONDS_PER_HOUR = 3600000;
    public static final int MILLISECONDS_PER_MINUTE = 60000;

    /*
     * 日期按指定格式转化成字符串
     */
    public static String dateToStr(String aMask, Date date) {
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        String dateAsString = df.format(date);
        return dateAsString;
    }

    public static String getCurrentTime() {
        return getToday("HH:mm:ss");
    }
    /*
     * 按指定格式返回当天日期的字符串形式
     */

    public static String getToday(String aMask) {
        Date today = new Date();
        String todayAsString = dateToStr(aMask, today);
        return todayAsString;
    }
    /*
     * 按默认格式返回当天日期的字符串形式
     */

    public static String getToday() {
        return getToday("yyyy-MM-dd");
    }
    /*
     * 把字符串按指定格式转化成Date
     */

    public static Date strToDate(String aMask, String strDate) {
        SimpleDateFormat format = new SimpleDateFormat(aMask);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /*
     * 把字符串按默认格式转化成Date
     */

    public static Date strToDate(String strDate) {
        return strToDate("yyyy-MM-dd", strDate);
    }
    /*
     * 日期相减得到的毫秒数
     */

    public static long sub(String aMask, String strBeginDate, String strEndDate) {
        long dateRange = 0;
        int num = 0;
        Date beginDate = strToDate(aMask, strBeginDate);
        Date endDate = strToDate(aMask, strEndDate);
        dateRange = endDate.getTime() - beginDate.getTime();
        return dateRange;
    }
    /*
     * 日期相减得到的毫秒数
     */

    public static long sub(String strBeginDate, String strEndDate) {
        long dateRange = 0;
        int num = 0;
        Date beginDate = strToDate(strBeginDate);
        Date endDate = strToDate(strEndDate);
        dateRange = endDate.getTime() - beginDate.getTime();
        return dateRange;
    }
    /*
     * 日期相减得到的天数
     */

    public static String subToDay(String strBeginDate, String strEndDate) {
        String dayNum = "";
        long dateRange = sub(strBeginDate, strEndDate);
        dayNum = "" + (dateRange / MILLISECONDS_PER_DAY);
        return dayNum;
    }
    /*
     * 日期相减得到的秒数
     */

    public static String subToSecond(String aMask, String strBeginDate, String strEndDate) {
        String secNum = "";
        long dateRange = sub(aMask, strBeginDate, strEndDate);
        secNum = "" + (dateRange / MILLISECONDS_PER_SECOND);
        return secNum;
    }

    public static String subToSecond(String strBeginDate, String strEndDate) {
        String secNum = "";
        long dateRange = sub("yyyy-MM-dd HH:mm:ss", strBeginDate, strEndDate);
        secNum = "" + (dateRange / MILLISECONDS_PER_SECOND);
        return secNum;
    }

    public static void main(String[] args) {
        System.out.println("按默认格式返回今天日期:" + getToday());
        System.out.println("按指定格式返回今天日期:" + getToday("yyyy-MM-dd HH:mm:ss"));
        System.out.println("日期相差天数:" + subToDay("2006-08-02 23:02:01", "2006-08-03 01:02:01"));
        System.out.println("日期相差秒数:" + subToSecond("2006-08-02 23:02:01", "2006-08-03 01:02:01"));
        System.out.println("当前时间:" + getCurrentTime());
    }
}
