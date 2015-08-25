/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.thread;

import cn.com.photop.sap.contactme.common.CommonConstant;
import cn.com.photop.sap.contactme.frame.MainJFrame;
import cn.com.photop.sap.contactme.model.WeatherInfo;
import cn.com.photop.sap.contactme.service.WeatherInfoService;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 通讯录数据修改更新到FTP服务器上的线程 新增更新指定公用盘符上指定路劲的数据
 *
 * @author Administrator
 */
public class WeatherThread extends Thread {

    private Log logger = LogFactory.getLog(getClass());
    private MainJFrame mainJFrame;
    private String city_id = null;

    public WeatherThread() {
    }

    public WeatherThread(MainJFrame mainJFrame) {
        this.mainJFrame = mainJFrame;

    }

    public WeatherThread(MainJFrame mainJFrame, String city_id) {
        this.mainJFrame = mainJFrame;
        this.city_id = city_id;

    }

    public void run() {
        try {
            WeatherInfoService wis = new WeatherInfoService();
            WeatherInfo wi = new WeatherInfo();
            if (StringUtil.isNull(city_id)) {
                wi = wis.getWeatherInfoByCity();
            } else {
                wi = wis.getWeatherInfoByCity(city_id);
            }
            if (wi != null) {
                this.mainJFrame.getJLabel6().setText(wi.getCity());
                //JLabel code = new JLabel(new ImageIcon(new URL("http://ptlogin2.qq.com/getimage?aid=wszf"))); 
               
                this.mainJFrame.getJLabel7().setText( wi.getNowDate() );
                this.mainJFrame.getJLabel8().setIcon(new ImageIcon(new URL(wi.getImg1Url())));
                this.mainJFrame.getJLabel8().setText(wi.getWeather());
                this.mainJFrame.getJLabel9().setText( wi.getTemp2()+"-"+wi.getTemp1() );
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(WeatherThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new WeatherThread().start();
    }
}
