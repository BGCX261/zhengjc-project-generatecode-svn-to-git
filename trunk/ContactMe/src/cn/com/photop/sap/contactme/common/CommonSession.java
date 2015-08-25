/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: CommonSession @Package
 * cn.com.photop.sap.contactme.common
 *
 * @author Jiancheng.Zheng @email dahaha @189.cn @date 2014-5-12 10:47:47
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.common;

import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.impl.ConfigService;
import cn.com.photop.sap.contactme.util.JarUtil;
import cn.com.photop.sap.contactme.util.StringUtil;
import cn.com.photop.sap.contactme.util.WindowsInfoUtil;
import java.io.File;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: CommonSession @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-12 10:47:47
 *
 */
public class CommonSession {

    private Log logger = LogFactory.getLog(getClass());
    //登陆管理账号的用户
    public static User user = null;
    //CPU序列号
    public static String cpuSerial = "";
    //配置MAP
    public static Map<String, String> configMap = null;
    //Project Path
//    public static String projectPath = (String)System.getProperty("user.dir");
    public static String projectPath = null;//new File("").getAbsolutePath();
    public static String groupName;
    public static Object lock = new Object();
    public static Object UpdateUploadlock = new Object();
    //以FTP形式更新的标志
    private static Boolean ftp_flg = null;
    //以LOCAL形式更新的标志
    private static Boolean local_flg = null;

    //CPU 序列号
    public static String getCpuSerial() {
        if (StringUtil.isNull(CommonSession.cpuSerial)) {
            CommonSession.cpuSerial = WindowsInfoUtil.cpuSerial();
        }
        return CommonSession.cpuSerial;
    }

    //
    public static Map<String, String> getConfigMap() {
        if (configMap == null) {
            ConfigService cs = new ConfigService();
            configMap = cs.getConfigMap();
        }
        return configMap;
    }

    public static String getGroupName() {
        return getConfigMap().get("GroupName");
    }

    public static String getProjectPath() {
        if (projectPath == null) {
            if (CommonConstant.CONSTANT_JAR) {
                JarUtil ju = new JarUtil((new CommonSession()).getClass());
                projectPath = ju.getJarPath();
            } else {
                projectPath = new File("").getAbsolutePath();
            }

//            JarUtil ju = new JarUtil((new CommonSession()).getClass());
//            projectPath = ju.getJarPath();
//            if (projectPath.contains("ContactMe\\build")) {
//                projectPath = new File("").getAbsolutePath();
//            }


        }
        return projectPath;
    }

    /**
     * 以FTP形式更新的标志
     *
     * @return
     */
    public static boolean getFtp_flg() {
        if (ftp_flg == null) {
            ftp_flg = Boolean.valueOf(getConfigMap().get("ftp_flg"));
        }

        return ftp_flg;
    }

    /**
     * 以LOCAL形式更新的标志
     *
     * @return
     */
    public static boolean getLocal_flg() {
        if (local_flg == null) {
            local_flg = Boolean.valueOf(getConfigMap().get("local_flg"));
        }

        return local_flg;
    }
}
