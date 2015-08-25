/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.thread;

import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.model.Version;
import cn.com.photop.sap.contactme.service.CommonPropertiesService;
import cn.com.photop.sap.contactme.service.impl.VersionService;
import cn.com.photop.sap.contactme.util.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 升级软件，本地或者共享盘的软件
 *
 * @author Administrator
 */
public class UpgradeLocalExeVerThread extends Thread {

    private String strPath;
    private String ext;
    private String fix;
    private String softNowVersion;
    private Object lock = new Object();

    public UpgradeLocalExeVerThread() {
        //数据库要新增 update_soft_path  就是盘符存放的位置
        strPath = CommonSession.getConfigMap().get("update_soft_exe_path");
        ext = CommonPropertiesService.getValue("updata_soft_ext");//".exe";
        fix = CommonPropertiesService.getValue("Exe_Fix");    //"ContactMe";
        softNowVersion = CommonPropertiesService.getValue("Version");
    }

    public void run() {
        synchronized (lock) {
            List<Version> versionList = new ArrayList<Version>();
            VersionService versionService = new VersionService();
            //获取之前更新过的版本名称
            String sql = " select * from version where type = 'exe'";
            versionList = versionService.find(sql);
            //获取数据库的数据转到String List中去
            List<String> dataSaveFileNameList = new ArrayList<String>();
            for (Version v : versionList) {
                dataSaveFileNameList.add(v.getName());
            }

            //获取网络公用盘的数据
//            List<String> fileNames = Arrays.asList( FileUtil.getFileName( strPath ) );

            List<String> fileNames = new ArrayList<String>();
            for (String str : FileUtil.getFileName(strPath)) {
                if (str.toLowerCase().contains(fix.toLowerCase()) && str.endsWith(ext)) {
                    fileNames.add(str);
                }
            }


            if (fileNames != null && !fileNames.isEmpty()) {
//                //剔除无关的文件数据
//                for (int i = 0; i < fileNames.size(); i++) {
//                    if (!fileNames.get(i).toLowerCase().contains(fix.toLowerCase())
//                            || !fileNames.get(i).endsWith(ext)) {
//                        fileNames.remove(i);
//                    }
//                }
                //判断版本最大的一个EXE文件
                //对集合对象进行排序         
                StringComparator comparator = new StringComparator();
                Collections.sort(fileNames, comparator);
                //设置值取第一笔数据
                for (int i = 0; i < 1; i++) {
                    String setupExeVerStr = fileNames.get(i).substring(fileNames.get(i).lastIndexOf("_") + 1).replaceAll(ext, "");
                    //判断是否已经有存在 必须
                    if (!dataSaveFileNameList.contains(fileNames.get(i))) {
                        //判断本地的版本是否大于升级的版本
                        if (setupExeVerStr.compareToIgnoreCase(softNowVersion) <= 0) {
                            continue;
                        }
                        String localFile = strPath + "\\" + fileNames.get(i);
                        String message = "发现新的软件版本，是否更新";
                        int res = javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                                message, "提示", javax.swing.JOptionPane.OK_CANCEL_OPTION);
                        if (res == 0) {
                            //确定后，做关闭，替换操作
                            String programName = "ContactMe.exe";
                            String cMUpdatePath = CommonSession.getProjectPath() + "\\CMUpdate.jar";
                            
                            try {
                                String command = " java -jar " + cMUpdatePath + programName + " " +localFile;
                                Runtime.getRuntime().exec(command);
                            } catch (IOException ex) {
                                Logger.getLogger(ProcessCloser.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }

                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new UpgradeLocalExeVerThread().start();
    }
}
