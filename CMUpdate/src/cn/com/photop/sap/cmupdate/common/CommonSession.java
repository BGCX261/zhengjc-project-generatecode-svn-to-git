/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe 
 * @Title: CommonSession 
 * @Package
 * cn.com.photop.sap.contactme.common
 *
 * @author Jiancheng.Zheng 
 * @email dahaha @189.cn 
 * @date 2014-5-12 10:47:47
 * @Copyright photop.cn.Co.,Ltd 
 * @All right reserved
 * @version V1.0 
 * @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.cmupdate.common;


import cn.com.photop.sap.cmupdate.util.JarUtil;
import java.io.File;


/**
 * @ClassName: CommonSession 
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng 
 * @email dahaha@189.cn 
 * @date 2014-5-12 10:47:47
 *
 */
public class CommonSession {

    //Project Path
    public static String projectPath = null;
  

    public static String getProjectPath() {
        if (projectPath == null) {
            if (CommonConstant.CONSTANT_JAR) {
                JarUtil ju = new JarUtil((new CommonSession()).getClass());
                projectPath = ju.getJarPath();
            } else {
                projectPath = new File("").getAbsolutePath();
            }

        }
        return projectPath;
    }

   
}
