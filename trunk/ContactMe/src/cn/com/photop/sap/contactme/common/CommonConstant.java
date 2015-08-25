/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.common;

/**
 * 常量定义
 *
 * @author Administrator
 */
public class CommonConstant {
    public final static boolean CONSTANT_JAR = false;//true; //
    //Select Item Name
//    public final static String CONSTANT_II_VI = "II-VI";
    //Select Department Item Name
    public final static String CONSTANT_DEFAULT_DEPARTMENT = "All";
    //GetBase64Data
    public final static Boolean CONSTANT_BASE64_DATA = true;
    //GetBase64Data ext
    public final static String CONSTANT_BASE64_FILE_EXT = "ZJC";
    
    //选择城市
    public final static String CONSTANT_DEFAULT_PROVINCE = "请选择省份";
    //选择城市
    public final static String CONSTANT_DEFAULT_CITY = "请选择城市";
    //如果INI文件中CITY code 为空的话就取此常量
    public final static String CONSTANT_DEFAULT_DEFAULT_CITY_CODE = "101230101";

    public interface WeatherInfo {

        public static final String FURTHER_SIX_DAY_WEATHER_URL = "http://m.weather.com.cn/data/";//101110101.html（六天预报）
        public static final String NOW_WEATHER_URL = "http://www.weather.com.cn/data/sk/";//101110101.html（实时天气信息）
        public static final String CITY_WEATHER_URL = "http://www.weather.com.cn/data/cityinfo/";//101010100.html

        // 根据连接的IP获取天气城市ID
        public static final String CITY_ID_BY_IP_URL = "http://61.4.185.48:81/g/";
        public static final String WEATHER_IMAGE_URL = "http://m.weather.com.cn/img/";
    }
}
