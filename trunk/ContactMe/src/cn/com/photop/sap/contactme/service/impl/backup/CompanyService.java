/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: CompanyService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl.backup;

import cn.com.photop.sap.contactme.model.Company;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.ICompanyService;
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
 * @ClassName: CompanyService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class CompanyService extends BaseService<Company> implements ICompanyService {

    private AccessUtil accessUtil;

    @Override
    public void save(Company company) {
        //组装CompanySQL
        String sql = buildInsertSQL(company);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Company company) {
        //组装CompanySQL
        String sql = buildInsertSQL(company);
        accessUtil.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdateAll(Collection<Company> entities) {
        for (Company company : entities) {
            saveOrUpdate(company);
        }
    }

    @Override
    public void update(Company company) {
        //拼装sql数据
        String sql = buildUpdateSQL(company);
        accessUtil.update(sql);
    }

    @Override
    public void delete(Company company) {
        String sql = "delete from company where id = " + company.getId() + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void delete(String id) {
        String sql = "delete from company where id = " + id + " ";
        accessUtil.delete(sql);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from company where id = " + id + " ";
        accessUtil.delete(sql);
    }
    
    @Override
    public void deleteAll(Collection<Company> entities) {
        for (Company company : entities) {
            delete(company);
        }
    }

    @Override
    public Company get(String id) {
        Company company = null;
        String sql = " select * from company where id = " + id + " ";
        ResultSet rs = accessUtil.find(sql);
        try {
            //组装Company
            while (rs.next()) {
                company = buildModel(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return company;
    }

    @Override
    public List<Company> find(String queryString) {
        List<Company> companyList = new ArrayList<Company>();
        ResultSet rs = accessUtil.find(queryString);
        try {
            //组装Company
            while (rs.next()) {
                companyList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companyList;
    }

    @Override
    public List<Company> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Company> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Company> list() {
        List<Company> companyList = new ArrayList<Company>();
        String queryName = "select * from company ";
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Company
            while (rs.next()) {
                companyList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companyList;
    }

    @Override
    public List<Company> findByNamedQuery(String queryName) {
        List<Company> companyList = new ArrayList<Company>();
        ResultSet rs = accessUtil.find(queryName);
        try {
            //组装Company
            while (rs.next()) {
                companyList.add(buildModel(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            accessUtil.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companyList;
    }

    @Override
    public List<Company> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Company> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Company> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Company> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return accessUtil.count(hql);
    }

    @Override
    public List<Company> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
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
    protected Company buildModel(ResultSet rs) {
        Company company = new Company();
        //处理ResultSet
//        new String(rs.getBytes("DEPARTMENT"), "GBK");
        Field f[] = company.getClass().getDeclaredFields();
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
                PropertyDescriptor pd = new PropertyDescriptor(methodName, company.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getWriteMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(company, rowVal);

            } catch (IllegalAccessException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            }



        }
        return company;

    }

    /**
     * 把对象转为Update的SQL
     *
     * @param company
     * @return
     */
    protected String buildUpdateSQL(Company company) {
        StringBuilder sb = new StringBuilder("update ");
        String whereStr = new String();
        String clzName = company.getClass().getName();
        clzName = company.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" set ");
        Field f[] = company.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();
//                String rowVal = (String) f[i].get(company);
                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, company.getClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getReadMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(company, null);
                String rowVal = (String) obj;
                if (!"id".equalsIgnoreCase(rowName)) {

                    sb.append(" [" + rowName + "] = '" + rowVal + "', ");
                } else {
                    whereStr = " where [" + rowName + "] = " + rowVal + " ";
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(whereStr);

        return sb.toString();

    }

    @Override
    protected String buildInsertSQL(Company company) {
//        insert into department ( [cname],[dname] )values ( 'K2','ZJC' )";
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = company.getClass().getName();
        clzName = company.getClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = company.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(company);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, company.getClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
                    Object obj = m.invoke(company, null);
                    String rowVal = (String) obj;
                    rowVal = rowVal == null ? "" : rowVal;
                    srowName.append(" [" + rowName + "], ");
                    srowVal.append("'" + rowVal + "', ");
                }

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }

    public static void main(String[] args) {
        CompanyService us = new CompanyService();
//        String sql = "select count(*) from company ";
//        int count = us.countByHql( sql );
//        System.out.println( count );
        String sql = "delete from company where company = 'z2'";
        AccessUtil.delete(sql);
        List<Company> companyList = us.list();
        System.out.println("aa");
////        Company company = us.get( "16" );
////        System.out.println(  "bb");
//        Company company = new Company();
//        company.setId("16");
////        company.setFlag("啊");
////        company.setName("哦");
////        company.setPassword("123456");
////        company.setRole("角色");
//        us.delete(company);
//        //        us.save(company);
////        company.setId("16");
////        company.setName("郑建");
////        us.update(company);
////        System.out.println( us.buildUpdateSQL(company) );
////        System.out.println(us.buildInsertSQL(company));
    }
}
