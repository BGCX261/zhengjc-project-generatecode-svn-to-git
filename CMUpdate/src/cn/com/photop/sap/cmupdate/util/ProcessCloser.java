/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: ProcessCloser @Package
 * cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-20 10:31:51
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.cmupdate.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ProcessCloser @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-20 10:31:51
 * 
*
 */
public class ProcessCloser {

    private ProcessCloser() {
    }

    public static void closeProcess() {
        Process listprocess;
//        String programName = "SurekamIM.exe";
//        String programName = "QQ.exe";
        String programName = "ContactMe.exe";
        try {
            listprocess = Runtime.getRuntime().exec("cmd.exe /c tasklist");
            InputStream is = listprocess.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            //StringBuffer sb = new StringBuffer();
            String str = null;
            while ((str = r.readLine()) != null) {
                String id = null;
                Matcher matcher = Pattern.compile(programName + "[ ]*([0-9]*)").matcher(str);
                while (matcher.find()) {
                    if (matcher.groupCount() >= 1) {
                        id = matcher.group(1);
                        if (id != null) {
                            Integer pid = null;
                            try {
                                pid = Integer.parseInt(id);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            if (pid != null) {
                                Runtime.getRuntime().exec("cmd.exe /c taskkill /f /im /pid " + pid);
                                //System.out.println("kill progress"+pid);    
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 关闭ContactMe进程
     */
    public static void closeContactMeProcessByPid() {
        Process listprocess;
        String programName = "ContactMe.exe";
        try {
            listprocess = Runtime.getRuntime().exec("cmd.exe /c tasklist");
            InputStream is = listprocess.getInputStream();
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            //StringBuffer sb = new StringBuffer();
            String str = null;
            while ((str = r.readLine()) != null) {
                String id = null;
                Matcher matcher = Pattern.compile(programName + "[ ]*([0-9]*)").matcher(str);
                while (matcher.find()) {
                    if (matcher.groupCount() >= 1) {
                        id = matcher.group(1);
                        if (id != null) {
                            Integer pid = null;
                            try {
                                pid = Integer.parseInt(id);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            if (pid != null) {
                                Runtime.getRuntime().exec("cmd.exe /c taskkill /f /im /pid " + pid);
                                //System.out.println("kill progress"+pid);    
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 关闭ContactMe进程 根据进程的名称
     */
    public static void closeContactMeProcessByPName( String programName ) {
       try {
            String command = "taskkill /f /im " + programName;
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(ProcessCloser.class.getName()).log(Level.SEVERE, null, ex);
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
        ProcessCloser.closeProcess();
        try {
            String command = "taskkill /f /im qq.exe";
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(ProcessCloser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //删除文件
        String strFilePath = "f:\\test\\ContactMe.gif";
        FileUtil.removeFile( strFilePath );
        String oldPath = "f:\\test2\\ContactMe_V2.00Beta.gif";
        String newPath = "f:\\test\\ContactMe.gif";
        FileUtil.copyFile( oldPath, newPath);
        
        
        
        
        
    }
}
