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
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 通讯录数据修改更新到FTP服务器上的线程 新增更新指定公用盘符上指定路劲的数据
 * 
 * @author Administrator
 */
public class UploadDataThread extends Thread {

    private FtpUtil ftpUtil;
    private String ext;
    private Object lock = new Object();

    public UploadDataThread() {
        String ip = CommonSession.getConfigMap().get("ftp_ip");  //"127.0.0.1";
        int port = Integer.valueOf(CommonSession.getConfigMap().get("ftp_port"));  //21;
        String userName = CommonSession.getConfigMap().get("ftp_name");  //"zhengjc";
        String password = CommonSession.getConfigMap().get("ftp_pwd");  //"zhengjc";
        ftpUtil = new FtpUtil(ip, port, userName, password);
        ext = CommonPropertiesService.getValue("updata_file_ext");
    }

    public void run() {
        synchronized ( CommonSession.UpdateUploadlock  ) {
            String projectPath = CommonSession.getProjectPath();
            String ftp_upload_path = CommonPropertiesService.getValue("ftp_upload_path");
            String ftpUpdatePath = projectPath + ftp_upload_path;
            //获取系统中的上传文件的信息
            String sql = " select * from version where type = 'upload'";
            VersionService vs = new VersionService();
            List<Version> versionList = vs.find(sql);
            List<String> uploadFileNameList = new ArrayList<String>();
            for (Version v : versionList) {
                uploadFileNameList.add(v.getName());
            }
            if (ftpUtil.connectServer()) {
                String fileNames[] = FileUtil.getFileName(ftpUpdatePath);
                for (int i = 0; i < fileNames.length; i++) {
                    //不获取当天的更新数据
                    String today = DateUtil.getToday("yyyyMMdd");
                    String fileDateStr = fileNames[i].substring(fileNames[i].lastIndexOf("_") + 1).replaceAll(ext, "");
                    //判断是否已经有存在 如果是存在的要判断是否是同一天
                    if ((fileNames[i].endsWith(ext) && !uploadFileNameList.contains(fileNames[i]))
                            || (uploadFileNameList.contains(fileNames[i]) && today.equals(fileDateStr))) {
                        String localFile = ftpUpdatePath + "\\" + fileNames[i];
                        boolean uploadFlag = ftpUtil.upload(localFile, "\\data\\"+fileNames[i]);
                        if (uploadFlag && !uploadFileNameList.contains(fileNames[i])) {
                            //上传成功就做数据的记录
                            Version version = new Version();
                            version.setName(fileNames[i]);
                            version.setType("upload");
                            vs.save(version);
//                        //删除本地的文件
//                        FileUtil.removeFile( localFile );
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

        new UploadDataThread().start();
    }
}
