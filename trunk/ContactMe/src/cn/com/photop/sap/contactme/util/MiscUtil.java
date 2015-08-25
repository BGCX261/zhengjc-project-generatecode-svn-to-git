/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: MiscUtil @Package cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-9 13:00:37
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @ClassName: MiscUtil 
 * @Description: Motherboard serial number
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-9 13:00:37
 * 
*
 */
public class MiscUtil {

    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n"
                    + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
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
        String cpuId = MiscUtil.getMotherboardSN();
        javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null, cpuId, "Motherboard serial number", javax.swing.JOptionPane.DEFAULT_OPTION);
    }
}
