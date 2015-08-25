/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.util;

import cn.com.photop.sap.contactme.service.CommonPropertiesService;
import cn.com.photop.sap.contactme.thread.UpdateDataThread;
import cn.com.photop.sap.contactme.thread.UploadDataThread;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AccessUtil {

    private static String dbPath;
    private static Connection conn = null;
    private static Statement statement;
    private static boolean saveFlag = true;
    

    /**
     * 初始化连接数据
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public synchronized static void init() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            String projectPath = new File("").getAbsolutePath();
            dbPath = projectPath + "//res//cmdb.mdb";
            String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + dbPath;
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Properties prop = new Properties();
            prop.put("charSet", "gb2312");
            //数据库密码
            String dBPwd = CommonPropertiesService.getValue("DBPwd");
            prop.put("password", dBPwd);
            //数据库用户
            prop.put("user", "admin");
//            conn = DriverManager.getConnection(url, "", "");
            conn = DriverManager.getConnection(url, prop);
            statement = (Statement) conn.createStatement();
        }
    }

    /**
     * 结束连接
     *
     * @throws SQLException
     */
    public synchronized static void close() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }

    /**
     *
     * @param sql
     * @return
     */
    public synchronized static int update(String sql) {
        int res = 0;
        try {
            init();
            res = statement.executeUpdate(sql);
            //++++++++判断返回的结果是否正确
            if ( saveFlag ) {
                new UpdateDataThread( sql ).start();  
                new UploadDataThread().start();
            }
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                close();
            } catch (SQLException ex) {
                Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return res;
    }

    /**
     *
     * @param sql
     * @return
     */
    public synchronized static ResultSet find(String sql) {
        ResultSet rs = null;
        try {
            init();
            rs = statement.executeQuery(sql);
            //close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * 统计总数
     *
     * @param sql
     * @return
     */
    public synchronized static int count(String sql) {
        int count = 0;
        ResultSet rs = null;
        try {
            init();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                count = Integer.valueOf(rs.getString(1));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                close();
            } catch (SQLException ex) {
                Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

    /**
     * 统计总数
     *
     * @param sql
     * @return
     */
    public synchronized static void delete(String sql) {
        int count = 0;
        ResultSet rs = null;
        try {
            init();
            boolean result = statement.execute(sql);
            //++++++++判断返回的结果是否正确
            if (saveFlag) {
                new UpdateDataThread( sql ).start();    
                new UploadDataThread().start();
            }
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                close();
            } catch (SQLException ex) {
                Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param sql
     * @return
     */
    public synchronized static void saveOrUpdate(String sql) {
        int count = 0;
        ResultSet rs = null;
        try {
            init();
            boolean result = statement.execute(sql);
            //++++++++判断返回的结果是否正确
           if ( saveFlag ) {
                new UpdateDataThread( sql ).start();  
                new UploadDataThread().start();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                close();
            } catch (SQLException ex) {
                Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 批处理
     *
     * @param sql
     * @return
     */
    public synchronized static int[] batch(List<String> sqlList) {
        int[] count = null;
        ResultSet rs = null;
        try {
            init();
            conn.setAutoCommit(false);//取消自动提交  
            for (String str : sqlList) {
                if (StringUtil.isNotNull(str)) {
                    try {
                        
                        statement.addBatch( new String( str.getBytes("gbk"),"gb2312" ) );
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            count = statement.executeBatch();
            //如果没有异常，则执行此段代码  
            //提交事务，真正向数据库中提交数据  
            conn.commit();
           

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                close();
            } catch (SQLException ex) {
                Logger.getLogger(AccessUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

    public synchronized static Statement getStatement() {
        return statement;
    }
    
    public synchronized static void setSaveFlag( boolean flag ){
        saveFlag  = flag;
    }
    
    
    
    
    
    
    

    public static void main(String[] args) {
//        String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=E://helpdb.mdb"; 
//        String url="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=D://NetBeansProjects//trunk//ContactMe//res//cmdb.mdb"; 

//        System.out.println( AccessUtil.class.getResource("/").getPath() );
//        System.out.println( System.getProperty("user.dir") );
        System.out.println( new File("").getAbsolutePath() );
        

//        String projectPath = System.getProperty("user.dir");
        String projectPath = new File("").getAbsolutePath();
        projectPath = projectPath + "//res//cmdb.mdb";
////        System.out.println( projectPath );
////        System.out.println( Thread.currentThread().getContextClassLoader().getResource("") );
////        System.out.println( ClassLoader.getSystemResource("") );
////        System.out.println(new File("").getAbsolutePath());
//
        String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=" + projectPath;
//        String url = "jdbc:odbc:helpdb";//helpdb为ODBC数据源名称 
        Properties prop = new Properties();
        prop.put("charSet", "gb2312");
        Connection conn = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String dBPwd = CommonPropertiesService.getValue("DBPwd");
            prop.put("password", dBPwd);
//            prop.put( "password","zhengjc" );
            prop.put("user", "admin");

//            conn = DriverManager.getConnection(url, "admin","zhengjc" );
            conn = DriverManager.getConnection(url, prop);
            Statement statement = (Statement) conn.createStatement();
//            String hql = "UPDATE user SET role = 'USER'";
//            statement.executeUpdate( hql);
//            String hql = "insert into user ( [role],[password] ) values ( 'K2','ZJC' )";
//              String hql = "insert into User (  [name], [password],[role],[flag] ) values ( '哦', '123456', '角色', '啊' )";
////              "insert into User (  [name],  [password],  [role],  [flag] ) values ( '哦啊的', '123456', '角色', '的啊' ) "
////              "insert into User (  [name],  [password],  [role],  [flag] ) values ( '哦', '123456', '角色', '啊' ) "
//            statement.execute(hql);
//            String ss = "delete from contactInfo";
            
            String sql = " insert into config ( [key], [value] ) values ( 'update_data_excel_path','T:\\it\\sap\\abap\\ContactMe\\excel' )";
            ResultSet rs = statement.executeQuery( sql );
            rs = statement.executeQuery("select count(*) from contactInfo");
//            ResultSet rs = statement.executeQuery(ss);
            while (rs.next()) {
//                String a = new String(rs.getBytes("id"), "GBK");
             
//                System.out.println( a );
//                String a = rs.getString(1);
                
                 int count =  Integer.valueOf(  rs.getString(1) );
                System.out.println( count );
//                System.out.println(a);
//                System.out.println(rs.getString(1) + "_" + a + "_" + rs.getString(2));
            }

//            rs = statement.executeQuery("select count(*) as aa from department where [cname] = 'K2'");
//            while (rs.next()) {
//                System.out.println(rs.getString(1));
//                String a = new String(rs.getBytes("aa"), "GBK");
//                System.out.println(a);
//
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//end of main 
    
    
    
}
