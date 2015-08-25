/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: VersionService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl.backup;

import cn.com.photop.sap.contactme.model.Version;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IVersionService;
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
 * @ClassName: VersionService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class VersionService extends BaseService<Version> implements IVersionService {

    private AccessUtil accessUtil;

    public VersionService() {
    }

    @Override
    public void save(Version version) {
        //组装VersionSQL
        String sql = buildInsertSQL(version);
        accessUtil.setSaveFlag(false);
        accessUtil.saveOrUpdate(sql);
        accessUtil.setSaveFlag(true);
    }

    @Override
    public void saveOrUpdate(Version version) {
        //组装VersionSQL
        String sql = buildInsertSQL(version);
        accessUtil.setSaveFlag(false);
        accessUtil.saveOrUpdate(sql);
        accessUtil.setSaveFlag(true);
    }

    @Override
    public void saveOrUpdateAll(Collection<Version> entities) {
        for (Version version : entities) {
            saveOrUpdate(version);
        }
    }

    @Override
    public void update(Version version) {
        //拼装sql数据
        String sql = buildUpdateSQL(version);
        accessUtil.setSaveFlag(false);
        accessUtil.update(sql);
        accessUtil.setSaveFlag(true);
    }

    @Override
    public void delete(Version version) {
        String sql = "delete from version where id = " + version.getId() + " ";
        accessUtil.setSaveFlag(false);
        accessUtil.delete(sql);
        accessUtil.setSaveFlag(true);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from version where id = " + id + " ";
        accessUtil.delete(sql);
    }
    
      @Override
    public void delete( int id) {
        String sql = "delete from version where id = " + id + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void deleteAll(Collection<Version> entities) {
        for (Version version : entities) {
            delete(version);
        }
    }

    @Override
    public Version get(String id) {
        Version version = null;
        String sql = " select * from version where id = " + id + " ";
        ResultSet rs = accessUtil.find(sql);

        try {
            //组装Version
            while (rs.next()) {
                version = buildModel(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return version;
    }

    @Override
    public List<Version> find(String queryString) {
        List<Version> versionList = new ArrayList<Version>();
        ResultSet rs = accessUtil.find(queryString);
        try {
            //组装Version
            while (rs.next()) {
                versionList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return versionList;
    }

    @Override
    public List<Version> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Version> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Version> list() {
        List<Version> versionList = new ArrayList<Version>();
        String queryName = "select * from version ";
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Version
            while (rs.next()) {
                versionList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return versionList;
    }

    @Override
    public List<Version> findByNamedQuery(String queryName) {
        List<Version> versionList = new ArrayList<Version>();
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Version
            while (rs.next()) {
                versionList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return versionList;
    }

    @Override
    public List<Version> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Version> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Version> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Version> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return accessUtil.count(hql);
    }

    @Override
    public List<Version> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
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
    protected Version buildModel(ResultSet rs) {
        Version version = new Version();
        //处理ResultSet
//        new String(rs.getBytes("DEPARTMENT"), "GBK");
        Field f[] = version.getClass().getDeclaredFields();
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
                PropertyDescriptor pd = new PropertyDescriptor(methodName, version.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getWriteMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(version, rowVal);

            } catch (IllegalAccessException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
        return version;

    }

    /**
     * 把对象转为Update的SQL
     *
     * @param version
     * @return
     */
    protected String buildUpdateSQL(Version version) {
        StringBuilder sb = new StringBuilder("update ");
        String whereStr = new String();
        String clzName = version.getClass().getName();
        clzName = version.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" set ");
        Field f[] = version.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();
//                String rowVal = (String) f[i].get(version);
                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, version.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getReadMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(version, null);
                String rowVal = (String) obj;
                if (!"id".equalsIgnoreCase(rowName)) {

                    sb.append(" [" + rowName + "] = '" + rowVal + "', ");
                } else {
                    whereStr = " where [" + rowName + "] = " + rowVal + " ";
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(whereStr);

        return sb.toString();

    }

    @Override
    protected String buildInsertSQL(Version version) {
//        insert into department ( [cname],[dname] )values ( 'K2','ZJC' )";
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = version.getClass().getName();
        clzName = version.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = version.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(version);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, version.getClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
                    Object obj = m.invoke(version, null);
                    String rowVal = (String) obj;
                    rowVal = rowVal == null ? "" : rowVal;
                    srowName.append(" [" + rowName + "], ");
                    srowVal.append("'" + rowVal + "', ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }

    public static void main(String[] args) {
        VersionService us = new VersionService();
        String sql = "select count(*) from version";
        int count = us.countByHql(sql);
        System.out.println(count);
        sql = "delete from version";
        AccessUtil.delete(sql);
////        List<Version> versionList = us.list();
////        System.out.println("aa");
////        Version version = us.get( "16" );
////        System.out.println(  "bb");
//        Version version = new Version();
//        version.setId("16");
////        version.setFlag("啊");
////        version.setName("哦");
////        version.setPassword("123456");
////        version.setRole("角色");
//        us.delete(version);
//        //        us.save(version);
////        version.setId("16");
////        version.setName("郑建");
////        us.update(version);
////        System.out.println( us.buildUpdateSQL(version) );
////        System.out.println(us.buildInsertSQL(version));
    }
}
