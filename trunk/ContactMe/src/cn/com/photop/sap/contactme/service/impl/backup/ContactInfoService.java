/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: ContactInfoService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl.backup;

import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IContactInfoService;
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
 * @ClassName: ContactInfoService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class ContactInfoService extends BaseService<ContactInfo> implements IContactInfoService {

    private AccessUtil accessUtil;

    @Override
    public void save(ContactInfo contactInfo) {
        //组装ContactInfoSQL
        String sql = buildInsertSQL(contactInfo);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(ContactInfo contactInfo) {
        //组装ContactInfoSQL
        String sql = buildInsertSQL(contactInfo);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdateAll(Collection<ContactInfo> entities) {
        for (ContactInfo contactInfo : entities) {
            saveOrUpdate(contactInfo);
        }
    }

    @Override
    public void update(ContactInfo contactInfo) {
        //拼装sql数据
        String sql = buildUpdateSQL(contactInfo);
        accessUtil.update(sql);
    }

    @Override
    public void delete(ContactInfo contactInfo) {
        String sql = "delete from contactInfo where id = " + contactInfo.getId() + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from contactInfo where id = " + id + " ";
        accessUtil.delete(sql);
    }
    
    
    @Override
    public void delete(int id) {
        String sql = "delete from contactInfo where id = " + id + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void deleteAll(Collection<ContactInfo> entities) {
        for (ContactInfo contactInfo : entities) {
            delete(contactInfo);
        }
    }

    @Override
    public ContactInfo get(String id) {
        ContactInfo contactInfo = null;
        String sql = " select * from contactInfo where id = " + id + " ";
        ResultSet rs = accessUtil.find(sql);
        try {
            //组装ContactInfo
            while (rs.next()) {
                contactInfo = buildModel(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactInfo;
    }

    @Override
    public List<ContactInfo> find(String queryString) {
        List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
        ResultSet rs = accessUtil.find(queryString);
        try {
            //组装ContactInfo
            while (rs.next()) {
                contactInfoList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactInfoList;
    }

    @Override
    public List<ContactInfo> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ContactInfo> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ContactInfo> list() {
        List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
        String queryName = "select * from contactInfo ";
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装ContactInfo
            while (rs.next()) {
                contactInfoList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactInfoList;
    }

    @Override
    public List<ContactInfo> findByNamedQuery(String queryName) {
        List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装ContactInfo
            while (rs.next()) {
                contactInfoList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contactInfoList;
    }

    @Override
    public List<ContactInfo> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ContactInfo> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ContactInfo> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ContactInfo> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return accessUtil.count(hql);
    }

    @Override
    public List<ContactInfo> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
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
    protected ContactInfo buildModel(ResultSet rs) {
        ContactInfo contactInfo = new ContactInfo();
        //处理ResultSet
//        new String(rs.getBytes("DEPARTMENT"), "GBK");
        Field f[] = contactInfo.getClass().getDeclaredFields();
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
                PropertyDescriptor pd = new PropertyDescriptor(methodName, contactInfo.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getWriteMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(contactInfo, rowVal);

            } catch (IllegalAccessException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
        return contactInfo;

    }

    /**
     * 把对象转为Update的SQL
     *
     * @param contactInfo
     * @return
     */
    protected String buildUpdateSQL(ContactInfo contactInfo) {
        StringBuilder sb = new StringBuilder("update ");
        String whereStr = new String();
        String clzName = contactInfo.getClass().getName();
        clzName = contactInfo.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" set ");
        Field f[] = contactInfo.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();
//                String rowVal = (String) f[i].get(contactInfo);
                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, contactInfo.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getReadMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(contactInfo, null);
                String rowVal = (String) obj;
                if (!"id".equalsIgnoreCase(rowName)) {

                    sb.append(" [" + rowName + "] = '" + rowVal + "', ");
                } else {
                    whereStr = " where [" + rowName + "] = " + rowVal + " ";
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(whereStr);

        return sb.toString();

    }

    @Override
    protected String buildInsertSQL(ContactInfo contactInfo) {
//        insert into department ( [cname],[dname] )values ( 'K2','ZJC' )";
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = contactInfo.getClass().getName();
        clzName = contactInfo.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = contactInfo.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(contactInfo);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, contactInfo.getClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
                    Object obj = m.invoke(contactInfo, null);
                    String rowVal = (String) obj;
                    rowVal = rowVal == null ? " " : rowVal;
                    srowName.append(" [" + rowName + "], ");
                    srowVal.append("'" + rowVal + "', ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }

    public static void main(String[] args) {
        ContactInfoService us = new ContactInfoService();
//        String sql = "select count(*) from company ";
//        int count = us.countByHql( sql );
//        System.out.println( count );
////        List<ContactInfo> contactInfoList = us.list();
////        System.out.println("aa");
////        ContactInfo contactInfo = us.get( "16" );
////        System.out.println(  "bb");
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setCompany("集团管理");
        contactInfo.setDepartment("财务部");
        contactInfo.setEmail("dahaha@189.cn");
        contactInfo.setFax("88358212");
        contactInfo.setInside("8212");
        contactInfo.setMobile("18906916683");
        contactInfo.setName("郑建诚");
        contactInfo.setRemark("ABAP开发");
        contactInfo.setTel("88358212");
//        us.delete(contactInfo);
        us.save(contactInfo);
////        contactInfo.setId("16");
////        contactInfo.setName("郑建");
////        us.update(contactInfo);
////        System.out.println( us.buildUpdateSQL(contactInfo) );
////        System.out.println(us.buildInsertSQL(contactInfo));
    }
}
