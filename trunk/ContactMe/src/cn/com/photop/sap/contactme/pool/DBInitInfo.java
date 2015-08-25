/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.pool;

import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.service.CommonPropertiesService;
import cn.com.photop.sap.contactme.util.JarUtil;
import java.io.File;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 初始化，模拟加载所有的配置文件
 *
 * @author Ran
 *
 */
public class DBInitInfo {
    private Log logger = LogFactory.getLog(getClass()); 
    public static List<DBbean> beans = null;

    static {
        beans = new ArrayList<DBbean>();
        // 这里数据 可以从xml 等配置文件进行获取   
        // 为了测试，这里我直接写死   
        DBbean beanOracle = new DBbean();
        beanOracle.setDriverName("sun.jdbc.odbc.JdbcOdbcDriver");

        String projectPath = CommonSession.getProjectPath();//new File("").getAbsolutePath();
//        projectPath = "D://NetBeansProjects//trunk//ContactMe";
        String dbPath = projectPath + "//res//cmdb.mdb";
        
        String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + dbPath;
        beanOracle.setUrl(url);
        beanOracle.setCharSet("gb2312");
        beanOracle.setUserName("admin");
        //数据库密码
        String dBPwd = CommonPropertiesService.getValue("DBPwd");
        beanOracle.setPassword(dBPwd);

        beanOracle.setMinConnections(5);
        beanOracle.setMaxConnections(100);

        beanOracle.setPoolName("ContactMePool");
        beans.add(beanOracle);
    }

    public static void main(String[] args) {
        String projectPath = new File("").getAbsolutePath();
        System.out.println(projectPath);
        Properties props = System.getProperties(); //获得系统属性集     
        String userDir = props.getProperty("user.dir"); //操作系统名称   
        System.out.println(userDir);
        String path = DBInitInfo.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8"); //转换处理中文及空格     
        } catch (java.io.UnsupportedEncodingException e) {
        }
        System.out.println(path);
        String p = Thread.currentThread().getContextClassLoader().getResource("").getPath();
         System.out.println( p );
        URL filePath = DBInitInfo.class.getResource("/");
        projectPath = filePath.getPath();
        System.out.println( projectPath );
        
        URL  aa = new DBInitInfo().getClass().getProtectionDomain().getCodeSource().getLocation();
        System.out.println(  aa.getPath() );


    }
}
