package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.common.CommonConstant;
import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.model.WeatherInfo;
import cn.com.photop.sap.contactme.pool.DBInitInfo;
import cn.com.photop.sap.contactme.util.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherInfoService extends NetBaseService<WeatherInfo> {

    private Log logger = LogFactory.getLog(getClass());

    protected WeatherInfo getBaseModel(JSONObject obj) {
        WeatherInfo weatherInfo = null;
        try {
            if (obj != null) {
                weatherInfo = new WeatherInfo();
                JSONObject weatherinfoobj = obj.getJSONObject("weatherinfo");
                weatherInfo.setCity(weatherinfoobj.optString("city"));
                weatherInfo.setCityid(weatherinfoobj.optString("cityid"));
                String img1 = weatherinfoobj.optString("img1");
                if (img1 != null && !"".equals(img1)) {
                    //a 最大 b中等 c最小
                    img1 = "c" + img1.substring(1);
                }
                weatherInfo.setImg1(img1);
                String img2 = weatherinfoobj.optString("img2");
                if (img2 != null && !"".equals(img2)) {
                    img2 = "c" + img2.substring(1);
                }
                weatherInfo.setImg2(img2);
                weatherInfo.setTemp1(weatherinfoobj.optString("temp1"));
                weatherInfo.setTemp2(weatherinfoobj.optString("temp2"));
                weatherInfo.setWeather(weatherinfoobj.optString("weather"));
                String imageBaseUrl = CommonConstant.WeatherInfo.WEATHER_IMAGE_URL;
                weatherInfo.setImg1Url(imageBaseUrl + img1);
                weatherInfo.setImg2(imageBaseUrl + img2);
//                weatherInfo.setNowDate(DateUtil.getToday("yyyy年MM月dd日"));
                weatherInfo.setNowDate(DateUtil.getToday("MM月dd日"));
            }
        } catch (JSONException e) {
            logger.error(e);
        }
        return weatherInfo;
    }

    /**
     * 获取天气信息
     *
     * @return
     */
    public WeatherInfo getWeatherInfoByCity() {
        String path = CommonPropertiesService.getValue("IniFilePath");//".\\res\\config.ini";
        path = CommonSession.getProjectPath() + path;
        String city_id = IniUtil.getPropValue(path, "city");//"101230101";//getCityIdByIP();
        if (StringUtil.isNull(city_id)) {
            city_id = CommonConstant.CONSTANT_DEFAULT_DEFAULT_CITY_CODE;
        }

        return getWeatherInfoByCity(city_id);

    }

    /**
     * 获取天气信息
     *
     * @return
     */
    public WeatherInfo getWeatherInfoByCity(String city_id) {
        WeatherInfo weatherInfo = null;
        if (city_id != null && !"".equals(city_id)) {
            String url = CommonConstant.WeatherInfo.CITY_WEATHER_URL + city_id + ".html";
//            logger.info(url);
            String jsonStr = HttpRequest.sendGet(url, "");
            try {
                jsonStr = java.net.URLDecoder.decode( jsonStr,"UTF-8" );
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WeatherInfoService.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                JSONObject obj = new JSONObject(jsonStr);
                if (obj != null) {
                    weatherInfo = getBaseModel(obj);
                }
            } catch (Exception e) {
                logger.error( e );
            }
        } else {
            
        }
        return weatherInfo;

    }

    /**
     * 根据IP获取城市ID
     *
     * @return
     */
    private String getCityIdByIP() {
        String city_id = "";
        String url = CommonConstant.WeatherInfo.CITY_ID_BY_IP_URL;
        String backStr = HttpRequest.sendGet(url, "");
        //var ip="125.77.107.153";var id=101230101;if(typeof(id_callback)!="undefined"){id_callback();}
        if (backStr != null && !"".equals(backStr)) {
            String[] arrTmp = backStr.split(";");
            backStr = arrTmp[1];
            city_id = backStr.substring(backStr.indexOf("id=") + 3);
        }
        return city_id;

    }

    public static void main(String[] args) {
        WeatherInfoService wis = new WeatherInfoService();
        WeatherInfo weatherInfo = wis.getWeatherInfoByCity();
        System.out.println(weatherInfo.getCity());
        System.out.println(weatherInfo.getImg1());
        System.out.println(weatherInfo.getImg1Url());
        System.out.println(weatherInfo.getNowDate());
        System.out.println(weatherInfo.getTemp1());
    }
}
