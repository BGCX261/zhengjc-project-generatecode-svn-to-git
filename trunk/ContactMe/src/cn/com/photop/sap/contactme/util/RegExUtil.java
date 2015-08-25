/**
 * @project ContactMe 
 * @Title: RegExUtil
 * @Package
 * cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng 
 * @email dahaha@189.cn 
 * @date 2014-6-5 11:12:48
 * @Copyright photop.cn.Co.,Ltd 
 * @All right reserved
 * @version V1.0 
 * @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.util;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @ClassName: RegExUtil 
 * @Description: 这是个正则表达式应用类，用来匹配和替换字串用的
 *
 * @author Jiancheng.Zheng 
 * @email dahaha@189.cn 
 * @date 2014-6-5 11:12:48
 * 
*
 */
public class RegExUtil {
    
    /**
     * 正则表达式的相关
     */
    public interface regexData {
        public static String intege = "^-?[1-9]\\d*$";                                 //整数
        public static String intege1 = "^[1-9]\\d*$";                                  //正整数
        public static String intege2 = "^-[1-9]\\d*$";                                 //负整数
        public static String num = "^([+-]?)\\d*\\.?\\d+$";                    //数字
        public static String num1 = "^[1-9]\\d*|0$";                            //正数（正整数 + 0）
        public static String num2 = "^-[1-9]\\d*|0$";                            //负数（负整数 + 0）
        public static String decmal = "^([+-]?)\\d*\\.\\d+$";                  //浮点数
        public static String decmal1 = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";      //正浮点数
        public static String decmal2 = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";   //负浮点数
        public static String decmal3 = "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$";      //浮点数
        public static String decmal4 = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";          //非负浮点数（正浮点数 + 0）
        public static String decmal5 = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";     //非正浮点数（负浮点数 + 0）
        public static String email = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"; //邮件
        public static String color = "^[a-fA-F0-9]{6}$";                               //颜色
        public static String url = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";    //url
        public static String chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";                                  //仅中文
        public static String ascii = "^[\\x00-\\xFF]+$";                               //仅ACSII字符
        public static String zipcode = "^\\d{6}$";                                             //邮编
        public static String mobile = "^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$";                            //手机
        public static String ip4 = "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";  //ip地址
        public static String notempty = "^\\S+$";                                              //非空
        public static String picture = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";   //图片
        public static String rar = "(.*)\\.(rar|zip|7zip|tgz)$";                            //压缩文件                                     //压缩文件
        public static String date = "^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$";          //日期
        public static String qq = "^[1-9]*[1-9][0-9]*$";                                //QQ号码
        public static String tel = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";       //电话号码的函数(包括验证国内区号,国际区号,分机号)
        public static String username = "^\\w+$";                                      //用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
        public static String letter = "^[A-Za-z]+$";                                   //字母
        public static String letter_u = "^[A-Z]+$";                                    //大写字母
        public static String letter_l = "^[a-z]+$";                                    //小写字母
        public static String idcard = "^[1-9]([0-9]{14}|[0-9]{17})$";                //身份证
    }

    /**
     * 要求大小写都匹配正则表达式
     *
     * @param pattern 正则表达式模式
     * @param str 要匹配的字串
     * @return boolean值
     * @since 1.0
     */
    public static final boolean ereg(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.find();
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹配且替换字串
     *
     * @param pattern 正则表达式模式
     * @param newstr 要替换匹配到的新字串
     * @param str 原始字串
     * @return 匹配后的字符串
     * @since 1.0
     */
    public static final String ereg_replace(String pattern, String newstr, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            return m.replaceAll(newstr);
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 主要用于模板中模块标记分析函数 把查找到的元素加到vector中
     *
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @return vector
     * @since 1.0
     */
    public static final Vector splitTags2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(ereg_replace("(\\[\\#)|(\\#\\])", "", m.group()));
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 模块标记分析函数 功能主要是把查找到的元素加到vector中
     *
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @since 1.0
     */
    public static final String[] splitTags(String pattern, String str) {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = ereg_replace("(\\[\\#)|(\\#\\])", "", m.group());
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹配所有符合模式要求的字串并加到矢量vector数组中
     *
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @return vector
     * @since 1.0
     */
    public static final Vector regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException {
        Vector vector = new Vector();
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                vector.add(m.group());
            }
            return vector;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 匹配所有符合模式要求的字串并加到字符串数组中
     *
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @return array
     * @since 1.0
     */
    public static final String[] regMatchAll2Array(String pattern, String str) throws PatternSyntaxException {
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            String[] array = new String[m.groupCount()];
            int i = 0;
            while (m.find()) {
                array[i] = m.group();
                i++;
            }
            return array;
        } catch (PatternSyntaxException e) {
            throw e;
        }
    }

    /**
     * 转义正则表达式字符(之所以需要将\和$字符用escapeDollarBackslash方法的方式是因为用repalceAll是不行的，简单的试试"$".repalceAll("\\$","\\\\$")你会发现这个调用会导致数组越界错误)
     *
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @return array
     * @since 1.0
     */
    public static String escapeDollarBackslash(String original) {
        StringBuffer buffer = new StringBuffer(original.length());
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if (c == '\\' || c == '$') {
                buffer.append("\\").append(c);
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    /**
     * 提取指定字串的函数 功能主要是把查找到的元素
     *
     * @param pattern为正则表达式模式
     * @param str 原始字串
     * @since 1.0
     */
    public static final String fetchStr(String pattern, String str) {
        String returnValue = null;
        try {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                returnValue = m.group();
            }
            return returnValue;
        } catch (PatternSyntaxException e) {
            return returnValue;
        }
    }

    /**
     * @Title: main(这里用一句话描述这个方法的作用) @Description: TODO(这里用一句话描述这个方法的作用)
     *
     * @param args 设定文件
     * @return void 返回类型
     * @throws
     *
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
