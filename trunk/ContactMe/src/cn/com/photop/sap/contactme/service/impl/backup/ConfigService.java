/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: ConfigService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl.backup;

import cn.com.photop.sap.contactme.model.Config;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IConfigService;
import cn.com.photop.sap.contactme.util.AccessUtil;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: ConfigService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class ConfigService extends BaseService<Config> implements IConfigService {

    private AccessUtil accessUtil;

    @Override
    public void save(Config config) {
        //组装ConfigSQL
        String sql = buildInsertSQL(config);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Config config) {
        //组装ConfigSQL
        String sql = buildInsertSQL(config);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdateAll(Collection<Config> entities) {
        for (Config config : entities) {
            saveOrUpdate(config);
        }
    }

    @Override
    public void update(Config config) {
        //拼装sql数据
        String sql = buildUpdateSQL(config);
        accessUtil.update(sql);
    }

    @Override
    public void delete(Config config) {
        String sql = "delete from config where id = " + config.getId() + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from config where id = " + id + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from config where id = " + id + " ";
        accessUtil.delete(sql);
    }
    
    @Override
    public void deleteAll(Collection<Config> entities) {
        for (Config config : entities) {
            delete(config);
        }
    }

    @Override
    public Config get(String id) {
        Config config = null;
        String sql = " select * from config where id = " + id + " ";
        ResultSet rs = accessUtil.find(sql);
        try {
            //组装Config
            while (rs.next()) {
                config = buildModel(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return config;
    }

    @Override
    public List<Config> find(String queryString) {
        List<Config> configList = new ArrayList<Config>();
        ResultSet rs = accessUtil.find(queryString);
        try {
            //组装Config
            while (rs.next()) {
                configList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configList;
    }

    @Override
    public List<Config> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> list() {
        List<Config> configList = new ArrayList<Config>();
        String queryName = "select * from config ";
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Config
            while (rs.next()) {
                configList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configList;
    }

    @Override
    public List<Config> findByNamedQuery(String queryName) {
        List<Config> configList = new ArrayList<Config>();
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Config
            while (rs.next()) {
                configList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configList;
    }

    @Override
    public List<Config> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return accessUtil.count(hql);
    }

    @Override
    public List<Config> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int findCountByParams(String strHql, Map params) {
        StringBuilder sb = new StringBuilder();
        String key = "";
        String val = "";
        Iterator it = params.entrySet().iterator();
        boolean firstFlag = true;
        while (it.hasNext()) {
            Entry e = (Entry) it.next();
            key = (String) e.getKey();
            val = (String) e.getValue();
            if (firstFlag) {
                firstFlag = false;
                sb.append(key + "= '" + val + "'");
            } else {
                sb.append("and " + key + "= '" + val + "'");
            }
        }
        return accessUtil.count(strHql + " where " + sb.toString());
    }

    /**
     * 把数据库获取到的ResultSet 进行组装
     */
    protected Config buildModel(ResultSet rs) {
        Config config = new Config();
        //处理ResultSet
//        new String(rs.getBytes("DEPARTMENT"), "GBK");
        Field f[] = config.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            try {
                String rowName = f[i].getName();
                String rowVal = null;
                if ("id".equalsIgnoreCase(rowName)) {
                    rowVal = rs.getString(1);
                } else {
                    byte[] rowBytes = rs.getBytes(rowName);
                    if (rowBytes != null) {
                        rowVal = new String(rowBytes, "GBK");
                    } else {
                        rowVal = "";
                    }
                }

                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, config.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getWriteMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(config, rowVal);

            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
        return config;

    }

    /**
     * 把对象转为Update的SQL
     *
     * @param config
     * @return
     */
    protected String buildUpdateSQL(Config config) {
        StringBuilder sb = new StringBuilder("update ");
        String whereStr = new String();
        String clzName = config.getClass().getName();
        clzName = config.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" set ");
        Field f[] = config.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();
//                String rowVal = (String) f[i].get(config);
                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, config.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getReadMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(config, null);
                String rowVal = (String) obj;
                if (!"id".equalsIgnoreCase(rowName)) {

                    sb.append(" [" + rowName + "] = '" + rowVal + "', ");
                } else {
                    whereStr = " where [" + rowName + "] = " + rowVal + " ";
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(whereStr);

        return sb.toString();

    }

    @Override
    protected String buildInsertSQL(Config config) {
//        insert into department ( [cname],[dname] )values ( 'K2','ZJC' )";
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = config.getClass().getName();
        clzName = config.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = config.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(config);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, config.getClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
                    Object obj = m.invoke(config, null);
                    String rowVal = (String) obj;
                    rowVal = rowVal == null ? "" : rowVal;
                    srowName.append(" [" + rowName + "], ");
                    srowVal.append("'" + rowVal + "', ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }

    public Map<String, String> getConfigMap() {
        Map<String, String> configMap = new HashMap<String, String>();
        List<Config> configList = new ArrayList<Config>();
        String queryName = "select * from config ";
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Config
            while (rs.next()) {
                configList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        //把LIST转为MAP
        for (Config c : configList) {
            configMap.put(c.getKey(), c.getValue());
        }

        return configMap;
    }

    public static void main(String[] args) {
        ConfigService us = new ConfigService();
        String sql = "select count(*) from config ";
        List<Config> cl = us.list();
        for (Config c : cl) {
            System.out.println(c.getKey() + "=" + c.getValue());
        }
////        List<Config> configList = us.list();
////        System.out.println("aa");
////        Config config = us.get( "16" );
////        System.out.println(  "bb");
//        Config config = new Config();
//        config.setId("16");
////        config.setFlag("啊");
////        config.setName("哦");
////        config.setPassword("123456");
////        config.setRole("角色");
//        us.delete(config);
//        //        us.save(config);
////        config.setId("16");
////        config.setName("郑建");
////        us.update(config);
////        System.out.println( us.buildUpdateSQL(config) );
////        System.out.println(us.buildInsertSQL(config));
    }
}
