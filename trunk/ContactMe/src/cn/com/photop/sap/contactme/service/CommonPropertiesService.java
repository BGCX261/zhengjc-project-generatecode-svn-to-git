/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: CommonPropertiesService @Package
 * cn.com.photop.sap.contactme.service
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-12 10:28:42
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.util.Configuration;
import java.io.InputStream;

/**
 * @ClassName: CommonPropertiesService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-12 10:28:42
 * 
*
 */
public class CommonPropertiesService {

    private static InputStream is = null;
    private static Configuration rc = null;// 相对路径
    private static Boolean initFlg = false;

    /**
     * 初始化
     */
    private static void init() {
        if (!initFlg) {
            is = CommonPropertiesService.class.getResourceAsStream("/cn/com/photop/sap/contactme/config/common.properties");
            rc = new Configuration(is);// 相对路径
            initFlg = true;
        }

    }

    /**
     * 获取Key值
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String value = "";
        init();
        value = rc.getValue(key);
        return value;
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
