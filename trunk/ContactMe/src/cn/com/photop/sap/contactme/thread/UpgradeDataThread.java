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
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 升级数据线程
 *
 * @author Administrator
 */
public class UpgradeDataThread extends Thread {

    private Log logger = LogFactory.getLog(getClass());
    private String strPath;
    private String ext;
    private Object lock = new Object();

    public UpgradeDataThread() {
//        strPath = "D:\\Workspaces\\ContactMe\\res\\update";
//        strPath = "D:\\NetBeansProjects\\trunk\\ContactMe\\res\\update";
        strPath = CommonSession.getConfigMap().get("update_data_path");
        ext = CommonPropertiesService.getValue("updata_file_ext");//".sql";
    }

    public void run() {
        synchronized (CommonSession.lock) {
            List<String> versionNameList = new ArrayList<String>();
            List<Version> versionList = new ArrayList<Version>();
            VersionService versionService = new VersionService();
            //获取之前更新过的版本名称
            String sql = " select * from version where type = 'dish'";
            versionList = versionService.find(sql);
            if (versionList != null && versionList.size() > 0) {
                for (int i = 0; i < versionList.size(); i++) {
                    versionNameList.add(versionList.get(i).getName());
                }
            }
            //FTP的盘符的保存
            String projectPath = CommonSession.getProjectPath();
            String ftp_down_path = CommonPropertiesService.getValue("ftp_down_path");
            String ftpDownPath = projectPath + ftp_down_path;
            //设置两处的文件路径
            Map<String, String> filePathMap = new HashMap<String, String>();
            
            if (CommonSession.getLocal_flg()) {
                filePathMap.put("remoteDishPath", strPath);
            }
            if (CommonSession.getFtp_flg()) {
                filePathMap.put("remoteFtpPath", ftpDownPath);
            }

            for (Iterator it = filePathMap.entrySet().iterator(); it.hasNext();) {
                Entry e = (Entry) it.next();
                String key = (String) e.getKey();
                String path = (String) e.getValue();
//                logger.info( "网络盘符的地址："+path );
                //获取网络公用盘的数据
                String fileNames[] = FileUtil.getFileName(path);
                if (fileNames == null) {
                    continue;
                }
//                logger.info( "fileNames.length："+fileNames.length );
                for (int i = 0; i < fileNames.length; i++) {
                    //不获取当天的更新数据
                    String today = DateUtil.getToday("yyyyMMdd");
                    String fileDateStr = fileNames[i].substring(fileNames[i].lastIndexOf("_") + 1).replaceAll(ext, "");
                    if (fileNames[i].endsWith(ext) && !versionNameList.contains(fileNames[i])) {
                        //如果是同一天的数据就不进行更新
                        if (today.equalsIgnoreCase(fileDateStr)) {
                            continue;
                        }

                        try {
                            //判断是否是本地操作过的,如果操作过的就不进行批处理，直接写入数据库
                            String cpuSerial = CommonSession.getCpuSerial();
                            //判断cpuSerial是否在文件名中
                            if (!fileNames[i].contains(cpuSerial)) {
                                //不在数据库中，就进行数据获取
                                String singleFilePath = path + "\\" + fileNames[i];
//                        String sqlStr = FileUtil.readFileAsString(singleFilePath);
//                        if (StringUtil.isNull(sqlStr)) {
//                            continue;
//                        }
                                List<String> sqlList = new ArrayList<String>();
                                sqlList = FileUtil.readFileContent(singleFilePath);
                                List<String> sqlListBase64 = new ArrayList<String>();
//                        sqlList = (List<String>) Arrays.asList(sqlStr.split("\r\n"));
                                for (int j = 0; j < sqlList.size(); j++) {
                                    sqlListBase64.add(new String(BASE64.decryptBASE64(sqlList.get(j))));
                                }
                                AccessUtil.batch(sqlListBase64);
//                        AccessUtil.batch(sqlList);

                                //把记录下进数据库
                                Version version = new Version();
                                version.setName(fileNames[i]);
                                version.setType("dish");
                                versionService.save(version);
                                //预防数据重复的导入
                                versionNameList.add(fileNames[i]);
                                //删除本地的文件
                                //如果是FTP下载下来的文件批处理完之后就进行删除处理
//                            if (key.equals("remoteFtpPath")) {
//                                FileUtil.removeFile(singleFilePath);
//                            }

                            }


                        } catch (Exception ex) {
                            Logger.getLogger(UpdateDataThread.class.getName()).log(Level.SEVERE, null, ex);
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
        new UpgradeDataThread().start();
    }
}
