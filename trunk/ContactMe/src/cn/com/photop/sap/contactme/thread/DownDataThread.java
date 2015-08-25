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
public class DownDataThread extends Thread {

    private FtpUtil ftpUtil;
    private String ftpDownPath;
    private String ext;
    private Object lock = new Object();

    public DownDataThread() {
        String ip = CommonSession.getConfigMap().get("ftp_ip");  //"127.0.0.1";
        int port = Integer.valueOf(CommonSession.getConfigMap().get("ftp_port"));  //21;
        String userName = CommonSession.getConfigMap().get("ftp_name");  //"zhengjc";
        String password = CommonSession.getConfigMap().get("ftp_pwd");  //"zhengjc"
        ftpUtil = new FtpUtil(ip, port, userName, password);
        String projectPath = CommonSession.getProjectPath();
        String ftp_down_path = CommonPropertiesService.getValue("ftp_down_path");
        ftpDownPath = projectPath + ftp_down_path;
        ext = CommonPropertiesService.getValue("updata_file_ext");
    }

    public void run() {
        synchronized (CommonSession.lock) {
            //获取系统中的上传文件的信息
            String sql = " select * from version where type = 'down'";
            VersionService vs = new VersionService();
            List<Version> versionList = vs.find(sql);
            List<String> uploadFileNameList = new ArrayList<String>();
            for (Version v : versionList) {
                uploadFileNameList.add(v.getName());
            }
            if (ftpUtil.connectServer()) {
                List<String> fileNames = ftpUtil.getFileList("\\data");
                for (int i = 0; i < fileNames.size(); i++) {
                    String today = DateUtil.getToday("yyyyMMdd");
                    String fileDateStr = fileNames.get(i).substring(fileNames.get(i).lastIndexOf("_") + 1).replaceAll(ext, "");
                    //判断是否已经有存在 是否是同一天的数据，同一天的数据不进行更新
                    if (fileNames.get(i).endsWith(ext) && !uploadFileNameList.contains(fileNames.get(i))) {
                        //如果是同一天的数据就不进行更新
                        if (today.equalsIgnoreCase(fileDateStr)) {
                            continue;
                        }
                        String localFile = ftpDownPath + "\\" + fileNames.get(i);
                        long res = ftpUtil.downloadFile("\\data\\"+fileNames.get(i), localFile);
                        if (res > 0) {
                            //上传成功就做数据的记录
                            Version version = new Version();
                            version.setName(fileNames.get(i));
                            version.setType("down");
                            vs.save(version);
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

        new DownDataThread().start();
    }
}
