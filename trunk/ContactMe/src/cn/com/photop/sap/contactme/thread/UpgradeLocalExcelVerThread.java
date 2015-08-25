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

/**
 * 升级软件，本地或者共享盘的软件
 *
 * @author Administrator
 */
public class UpgradeLocalExcelVerThread extends Thread {

    private String strPath;
    private String ext;
    private String fix;
    private Object lock = new Object();

    public UpgradeLocalExcelVerThread() {
        //数据库要新增 update_soft_path  就是盘符存放的位置
        strPath = CommonSession.getConfigMap().get("update_data_excel_path");
        ext = CommonPropertiesService.getValue("updata_data_excel_ext");//".exe";
        fix = CommonPropertiesService.getValue("Excel_Fix");//"ContacMe_Data_";
    }

    public void run() {
        synchronized (lock) {
            List<Version> versionList = new ArrayList<Version>();
            VersionService versionService = new VersionService();
            //获取之前更新过的版本名称
            String sql = " select * from version where type = 'excel'";
            versionList = versionService.find(sql);
            //获取数据库的数据转到String List中去
            List<String> dataSaveFileNameList = new ArrayList<String>();
            for (Version v : versionList) {
                dataSaveFileNameList.add(v.getName());
            }

            //获取网络公用盘的数据
            List<String> fileNames = new ArrayList<String>();
            for (String str : FileUtil.getFileName(strPath)) {
                //剔除无关的文件数据
                if (str.toLowerCase().contains(fix.toLowerCase()) && str.endsWith(ext)) {
                    fileNames.add(str);
                }
            }

            if (fileNames != null && !fileNames.isEmpty()) {
//                //对集合对象进行排序         
//                StringComparator comparator = new StringComparator();
//                Collections.sort(fileNames, comparator);
//                //设置值取第一笔数据
                for (int i = 0; i < fileNames.size(); i++) {
                    //判断是否已经有存在 必须
                    if (!dataSaveFileNameList.contains(fileNames.get(i))) {
                        String netDishFile = strPath + "\\" + fileNames.get(i);
                        String localFile = CommonSession.getProjectPath() + CommonPropertiesService.getValue("ExcelPath")  + fileNames.get(i);
                        //下载文件到excel指定路径中去
                        FileUtil.copyFile( netDishFile, localFile );
                        //把生产的文件记录下进数据库
                        String countSql = " select count(*) from version where name = '" + fileNames.get(i) + "' and type = 'excel'";
                        int count = versionService.countByHql(countSql);
                        if (count == 0) {
                            Version version = new Version();
                            version.setName(fileNames.get(i));
                            version.setType("excel");
                            versionService.save(version);
                            //添加到当前List中取
                            dataSaveFileNameList.add( fileNames.get( i ) );
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
        new UpgradeLocalExcelVerThread().start();
    }
}
