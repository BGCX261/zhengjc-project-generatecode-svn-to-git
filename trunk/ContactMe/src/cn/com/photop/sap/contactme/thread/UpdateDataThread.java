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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 通讯录数据修改更新到 新增更新指定公用盘符上指定路劲的数据
 *
 * @author Administrator
 */
public class UpdateDataThread extends Thread {

    private String strPath;
    private String ext;
    private String saveFileName;
    private String sql;
    private Object lock = new Object();

    public UpdateDataThread(String sql) {
//        strPath = "D:\\Workspaces\\ContactMe\\res\\update";
//        strPath = "D:\\NetBeansProjects\\trunk\\ContactMe\\res\\update";
        strPath = CommonSession.getConfigMap().get("update_data_path");
        String today = DateUtil.getToday("yyyyMMdd");
        String fileNamePrefix = "CM_";
        String cpuSerial = CommonSession.getCpuSerial();
        ext = CommonPropertiesService.getValue("updata_file_ext");//".sql";
        String userName = CommonSession.user==null?"":CommonSession.user.getName().toUpperCase();
        saveFileName = fileNamePrefix + userName + "_" + cpuSerial + "_" + today + ext;
        try {
            this.sql = new String(BASE64.encryptBASE64(sql.getBytes()));
            this.sql = this.sql.replaceAll("\r\n", "").replaceAll("77u/", "");
            this.sql = this.sql + "\r\n";
        } catch (Exception ex) {
            Logger.getLogger(UpdateDataThread.class.getName()).log(Level.SEVERE, null, ex);
        }
//        this.sql = sql;
    }

    public void run() {
        synchronized ( CommonSession.UpdateUploadlock ) {
            //网络共享盘符的保存
            FileUtil.writeStringToFile(strPath, saveFileName, sql);
            //FTP的盘符的保存
            String projectPath = CommonSession.getProjectPath();//(String) System.getProperty("user.dir");
            //"\\update\\data\\upload"
            String ftp_upload_path = CommonPropertiesService.getValue("ftp_upload_path");
            String ftpUpdatePath = projectPath + ftp_upload_path;
            FileUtil.writeStringToFile( ftpUpdatePath, saveFileName, sql );

            //把生产的文件记录下进数据库
            VersionService versionService = new VersionService();
            String countSql = " select count(*) from version where name = '" + saveFileName + "' and type = 'dish'";
            int count = versionService.countByHql(countSql);
            if (count == 0) {
                Version version = new Version();
                version.setName(saveFileName);
                version.setType("dish");
                versionService.save(version);
            }

            
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sql = "insert into company ( [company],[status] ) values ( 'z1','1' )";
        new UpdateDataThread(sql).start();
    }
}
