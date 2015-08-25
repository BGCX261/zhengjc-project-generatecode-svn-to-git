/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: BaseService @Package
 * cn.com.photop.sap.contactme.service
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:14:58
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.aop.DynamicProxyHandler;
import cn.com.photop.sap.contactme.dao.BaseDao;
import cn.com.photop.sap.contactme.dao.impl.IBaseDao;
import cn.com.photop.sap.contactme.model.BaseModel;
import cn.com.photop.sap.contactme.model.Company;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.impl.UserService;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: BaseService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:14:58
 * 
*
 */
public abstract class BaseService<T extends BaseModel> {

    private Log logger = LogFactory.getLog(getClass());
    protected Class<T> entityClass;
    protected DynamicProxyHandler handler;
    protected IBaseDao baseDao;

    public BaseService() {
        baseDao = new BaseDao<T>();
        handler = new DynamicProxyHandler();
        baseDao = (IBaseDao) handler.bind(baseDao);

    }

    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            logger.debug("T class = " + entityClass.getName());
        }
        return entityClass;
    }

    /**
     * 组装成MODEL
     *
     * @return
     */
    protected T buildModel(ResultSet rs, T t) {
        //处理ResultSet
        Field f[] = getEntityClass().getDeclaredFields();
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
                PropertyDescriptor pd = new PropertyDescriptor(methodName, getEntityClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getWriteMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(t, rowVal);

            } catch (IllegalAccessException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return t;
    }

    /**
     * 把对象转为Update的SQL
     *
     * @param t
     * @return
     */
    protected String buildUpdateSQL(T t) {
        StringBuilder sb = new StringBuilder("update ");
        String whereStr = new String();
        String clzName = getEntityClass().getName();
        clzName = getEntityClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" set ");
        Field f[] = getEntityClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();
//                String rowVal = (String) f[i].get(user);
                //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                PropertyDescriptor pd = new PropertyDescriptor(methodName, getEntityClass());
                //获取PropertyDescriptor 类的写入方法
                Method m = pd.getReadMethod();
                //因为是读取属性中的方法，所以不需要传值进去
                Object obj = m.invoke(t, null);
                String rowVal = (String) obj;
                if (!"id".equalsIgnoreCase(rowName)) {
                    if (StringUtil.isNotNull(rowVal)) {
                        sb.append(" [" + rowName + "] = '" + rowVal + "', ");
                    }
                } else {
                    whereStr = " where [" + rowName + "] = " + rowVal + " ";
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(whereStr);

        return sb.toString();
    }

    /**
     * 把对象转为Insert的SQL
     *
     * @param t
     * @return
     */
    protected String buildInsertSQL(T t) {
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = getEntityClass().getName();
        clzName = getEntityClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = getEntityClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(user);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, getEntityClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
//                    Object o = new Object();
                    Object obj = m.invoke(t, null);
                    String rowVal = (String) obj;
//                    rowVal = (rowVal == null) ? "" : rowVal;
                    if (StringUtil.isNotNull(rowVal)) {
                        srowName.append(" [" + rowName + "], ");
                        srowVal.append("'" + rowVal + "', ");
                    }

                }

            } catch (IllegalAccessException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }
}
