/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project CMUpdate
* @Title: CMUpdate
* @Package cn.com.photop.sap.cmupdate.action 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-6-12 13:50:47 
* @Copyright photop.cn.Co.,Ltd
* @All right reserved
* @version V1.0   
* @Description: TODO(用一句话描述该文件做什么) 
*
*/
package cn.com.photop.sap.cmupdate.action;

import cn.com.photop.sap.cmupdate.common.CommonSession;
import cn.com.photop.sap.cmupdate.util.FileUtil;
import cn.com.photop.sap.cmupdate.util.ProcessCloser;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
* @ClassName: CMUpdate
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-6-12 13:50:47 
* 
*  
*/
public class CMUpdate {

    /** 
    * @Title: main(这里用一句话描述这个方法的作用) 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param args    设定文件 
    * @return void    返回类型 
    * @throws 
    *
    */
    public static void main(String[] args) {
        
        System.out.println( args[0] );
        System.out.println( args[1] );
        
        String programName = args[0];
        String updatePath = args[1];
        String strFilePath = CommonSession.getProjectPath() + "\\" + programName;
        String oldPath = updatePath;    
        String newPath = CommonSession.getProjectPath() + "\\" + programName;
        try {
            String command = "taskkill /f /im " + programName;
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(CMUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        //删除文件
        FileUtil.removeFile( strFilePath );
        //拷贝新的文件
        FileUtil.copyFile( oldPath, newPath);
        
        
    }
    
    
   

}
