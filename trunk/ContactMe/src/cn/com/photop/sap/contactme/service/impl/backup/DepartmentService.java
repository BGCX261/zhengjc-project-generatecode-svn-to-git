/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: DepartmentService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl.backup;

import cn.com.photop.sap.contactme.model.Department;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IDepartmentService;
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
 * @ClassName: DepartmentService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class DepartmentService extends BaseService<Department> implements IDepartmentService {

    private AccessUtil accessUtil;

    @Override
    public void save(Department department) {
        //组装DepartmentSQL
        String sql = buildInsertSQL(department);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Department department) {
        //组装DepartmentSQL
        String sql = buildInsertSQL(department);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdateAll(Collection<Department> entities) {
        for (Department department : entities) {
            saveOrUpdate(department);
        }
    }

    @Override
    public void update(Department department) {
        //拼装sql数据
        String sql = buildUpdateSQL(department);
        accessUtil.update(sql);
    }

    @Override
    public void delete(Department department) {
        String sql = "delete from department where id = " + department.getId() + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from department where id = " + id + " ";
        accessUtil.delete(sql);
    }
    
     @Override
    public void delete(int id) {
        String sql = "delete from department where id = " + id + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void deleteAll(Collection<Department> entities) {
        for (Department department : entities) {
            delete(department);
        }
    }

    @Override
    public Department get(String id) {
        Department department = null;
        String sql = " select * from department where id = " + id + " ";
        ResultSet rs = accessUtil.find(sql);
        try {
            //组装Department
            while (rs.next()) {
                department = buildModel(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }

    @Override
    public List<Department> find(String queryString) {
        List<Department> departmentList = new ArrayList<Department>();
        ResultSet rs = accessUtil.find(queryString);
        try {
            //组装Department
            while (rs.next()) {
                departmentList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departmentList;
    }

    @Override
    public List<Department> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Department> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Department> list() {
        List<Department> departmentList = new ArrayList<Department>();
        String queryName = "select * from department ";
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Department
            while (rs.next()) {
                departmentList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departmentList;
    }

    @Override
    public List<Department> findByNamedQuery(String queryName) {
        List<Department> departmentList = new ArrayList<Department>();
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Department
            while (rs.next()) {
                departmentList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departmentList;
    }

    @Override
    public List<Department> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Department> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Department> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Department> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return accessUtil.count(hql);
    }

    @Override
    public List<Department> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
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
    protected Department buildModel(ResultSet rs) {
        Department department = new Department();
        //处理ResultSet
//        new String(rs.getBytes("DEPARTMENT"), "GBK");
        Field f[] = department.getClass().getDeclaredFields();
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
                PropertyDescriptor pd = new PropertyDescriptor(methodName, department.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getWriteMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(department, rowVal);

            } catch (IllegalAccessException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
        return department;

    }

    /**
     * 把对象转为Update的SQL
     *
     * @param department
     * @return
     */
    protected String buildUpdateSQL(Department department) {
        StringBuilder sb = new StringBuilder("update ");
        String whereStr = new String();
        String clzName = department.getClass().getName();
        clzName = department.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" set ");
        Field f[] = department.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();
//                String rowVal = (String) f[i].get(department);
                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, department.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getReadMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(department, null);
                String rowVal = (String) obj;
                if (!"id".equalsIgnoreCase(rowName)) {

                    sb.append(" [" + rowName + "] = '" + rowVal + "', ");
                } else {
                    whereStr = " where [" + rowName + "] = " + rowVal + " ";
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(whereStr);

        return sb.toString();

    }

    @Override
    protected String buildInsertSQL(Department department) {
//        insert into department ( [cname],[dname] )values ( 'K2','ZJC' )";
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = department.getClass().getName();
        clzName = department.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = department.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(department);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, department.getClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
                    Object obj = m.invoke(department, null);
                    String rowVal = (String) obj;
                    rowVal = rowVal == null ? "" : rowVal;
                    srowName.append(" [" + rowName + "], ");
                    srowVal.append("'" + rowVal + "', ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }

    public static void main(String[] args) {
        DepartmentService us = new DepartmentService();
        String sql = "select count(*) from company ";
        int count = us.countByHql(sql);
        System.out.println(count);
////        List<Department> departmentList = us.list();
////        System.out.println("aa");
////        Department department = us.get( "16" );
////        System.out.println(  "bb");
//        Department department = new Department();
//        department.setId("16");
////        department.setFlag("啊");
////        department.setName("哦");
////        department.setPassword("123456");
////        department.setRole("角色");
//        us.delete(department);
//        //        us.save(department);
////        department.setId("16");
////        department.setName("郑建");
////        us.update(department);
////        System.out.println( us.buildUpdateSQL(department) );
////        System.out.println(us.buildInsertSQL(department));
    }
}
