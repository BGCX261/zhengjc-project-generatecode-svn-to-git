/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.thread;

import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.model.Version;
import cn.com.photop.sap.contactme.service.CommonPropertiesService;
import cn.com.photop.sap.contactme.service.impl.VersionService;
import cn.com.photop.sap.contactme.util.DateUtil;
import cn.com.photop.sap.contactme.util.FtpUtil;
import cn.com.photop.sap.contactme.util.StringComparator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 升级软件版本线程 直接更新可执行文件
 *
 * @author Administrator
 */
public class UpgradFtpExeVerThread extends Thread {
    //更新的ContactMe_V1.00Beta.exe

    private FtpUtil ftpUtil;
    private String ftpDownPath;
    private String ext;
    private Object lock = new Object();
    private String softNowVersion;
    private String fix;

    public UpgradFtpExeVerThread() {

        String ip = CommonSession.getConfigMap().get("ftp_ip");  //"127.0.0.1";
        int port = Integer.valueOf(CommonSession.getConfigMap().get("ftp_port"));  //21;
        String userName = CommonSession.getConfigMap().get("ftp_name");  //"zhengjc";
        String password = CommonSession.getConfigMap().get("ftp_pwd");  //"zhengjc"
        ftpUtil = new FtpUtil(ip, port, userName, password);
        String projectPath = CommonSession.getProjectPath();
        String ftp_down_path = CommonPropertiesService.getValue("ftp_down_exe_path");
        ftpDownPath = projectPath + ftp_down_path;
        ext = CommonPropertiesService.getValue("updata_soft_ext");
        fix = "ContactMe";
        softNowVersion = CommonPropertiesService.getValue("Version");
    }

    public UpgradFtpExeVerThread(String name) {
        super(name);
    }

    public void run() {


        synchronized (lock) {

            //获取系统中的上传文件的信息
            String sql = " select * from version where type = 'exe'";
            VersionService vs = new VersionService();
            List<Version> versionList = vs.find(sql);
            List<String> uploadFileNameList = new ArrayList<String>();
            for (Version v : versionList) {
                uploadFileNameList.add(v.getName());
            }
            if (ftpUtil.connectServer()) {
                List<String> fileNames = ftpUtil.getFileList("\\soft\\exe");
                if (fileNames != null && !fileNames.isEmpty()) {
                    //剔除无关的文件数据
                    for (int i = 0; i < fileNames.size(); i++) {
                        if (!fileNames.get(i).toLowerCase().contains(fix.toLowerCase())
                                || !fileNames.get(i).endsWith(ext)) {
                            fileNames.remove(i);
                        }
                    }
                    //判断版本最大的一个EXE文件
                    //对集合对象进行排序         
                    StringComparator comparator = new StringComparator();
                    Collections.sort(fileNames, comparator);
                    //设置值取第一笔数据
                    for (int i = 0; i < 1; i++) {
                        String exeExeVerStr = fileNames.get(i).substring(fileNames.get(i).lastIndexOf("_") + 1).replaceAll(ext, "");
                        //判断是否已经有存在 必须
                        if (!uploadFileNameList.contains(fileNames.get(i))) {
                            //如果是同一天的数据就不进行更新
                            if (exeExeVerStr.compareToIgnoreCase(softNowVersion) <= 0) {
                                continue;
                            }
                            String localFile = ftpDownPath + "\\" + fileNames.get(i);
                            long res = ftpUtil.downloadFile("\\soft\\exe\\" + fileNames.get(i), localFile);

                            if (res > 0) {
                                //上传成功就做数据的记录
                                Version version = new Version();
                                version.setName(fileNames.get(i));
                                version.setType("exe");
                                vs.save(version);
                                //提示下载成功信息提示
                                String message = "更新软件已经下载成功是否需要重新安装";
                                res = javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                                        message, "提示", javax.swing.JOptionPane.OK_CANCEL_OPTION);
                                if (res == 0) {
//                                    try {
//                                        //确定
//                                        //执行删除 覆盖原来的EXE文件
//                                        Runtime.getRuntime().exec(localFile);
//                                    } catch (IOException ex) {
//                                        Logger.getLogger(UpgradFtpExeVerThread.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
                                }

                            }
                        } else {
                            //已经存在，的情况先判断之前下载了但是没安装
                            if (exeExeVerStr.compareToIgnoreCase(softNowVersion) <= 0) {
                                continue;
                            }
                            String localFile = ftpDownPath + "\\" + fileNames.get(i);
                            File file = new File(localFile);
                            long res = 0;
                            if (!file.exists()) {
                                //文件不存在就做重新下载
                                res = ftpUtil.downloadFile("\\soft\\exe\\" + fileNames.get(i), localFile);
                            } else {
                                res = 1;
                            }
                            if (res > 0) {
                                //提示下载成功信息提示
                                String message = "更新软件已经下载成功是否需要重新安装";
                                res = javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
                                        message, "提示", javax.swing.JOptionPane.OK_CANCEL_OPTION);
                                if (res == 0) {
                                    //调用Process Closer
//                                    ProcessClose
//                                    try {
//                                        //确定
//                                        //执行删除 覆盖原来的EXE文件
//                                        Runtime.getRuntime().exec(localFile);
//                                    } catch (IOException ex) {
//                                        Logger.getLogger(UpgradFtpExeVerThread.class.getName()).log(Level.SEVERE, null, ex);
//                                    }
                                }
                            }
                        }

                    }
                }

            }
            ftpUtil.closeServer();

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String localFile = "D:\\Program Files\\UE\\Uedit32.exe";
//        try {
//            //确定
//            //执行重新安装
//            Runtime.getRuntime().exec(localFile);
//        } catch (IOException ex) {
//            Logger.getLogger(UpgradFtpExeVerThread.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        String message = "更新软件已经下载成功是否需要重新安装";
//                                int res = javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
//                                        message, "提示", javax.swing.JOptionPane.OK_CANCEL_OPTION);

        new UpgradFtpExeVerThread().start();
    }
}
