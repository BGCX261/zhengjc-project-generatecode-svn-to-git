/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.dao;

import cn.com.photop.sap.contactme.aop.DynamicProxyHandler;
import cn.com.photop.sap.contactme.dao.impl.IBaseDao;
import cn.com.photop.sap.contactme.dao.impl.ICompanyDao;
import cn.com.photop.sap.contactme.model.Company;
import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.pool.ConnectionPoolManager;
import cn.com.photop.sap.contactme.pool.IConnectionPool;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.CommonPropertiesService;
import cn.com.photop.sap.contactme.service.impl.UserService;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Administrator
 */
public class BaseDao<T> implements IBaseDao {

    private Log logger = LogFactory.getLog(getClass());
    protected IConnectionPool pool;
    protected Class<T> entityClass;

    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            logger.debug("T class = " + entityClass.getName());
        }
        return entityClass;
    }

    public BaseDao() {
        pool = initPool();
        while (pool == null || !pool.isActive()) {
            pool = initPool();
        }

    }

    /**
     *
     * @param sql
     * @return
     */
    public int update(String sql) {
        int i = 0;
        try {
            i = pool.getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    /**
     *
     * @param sql
     * @return
     */
    public ResultSet find(String sql) {
        ResultSet rs = null;
        try {
            rs = pool.getConnection().createStatement().executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * list
     *
     * @param sql
     * @return
     */
    public ResultSet list() {
        ResultSet rs = null;
        String sql = "select * from " + getModelName();

        try {
            rs = pool.getConnection().createStatement().executeQuery(sql);

        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * 统计总数
     *
     * @param sql
     * @return
     */
    public int count(String sql) {
        int count = 0;
        ResultSet rs = null;

        try {
            rs = pool.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs.getString(1));
//                count = Integer.valueOf(rs.getString(1));
                count = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    /**
     * 统计总数
     *
     * @param sql
     * @return
     */
    public int maxDBID() {
        int count = 0;
        ResultSet rs = null;
        String sql = "select max(id) from " + getModelName();
        try {
            rs = pool.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
//                count = Integer.valueOf(rs.getString(1));
                count = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    /**
     * 删除
     *
     * @param sql
     * @return
     */
    public boolean delete(String sql) {
        boolean result = false;
        ResultSet rs = null;
        try {
            result = pool.getConnection().createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * 删除
     *
     * @param sql
     * @return
     */
    public boolean delete(int i) {
        String sql = "delete from " + getModelName() + " where id = " + String.valueOf(i);
        boolean result = delete(sql);
        return result;
    }

    /**
     * 统计总数
     *
     * @param sql
     * @return
     */
    public boolean delete(T t) {
        String sql = "delete from " + getModelName() + " where id = " + getTIdVal(t);
        boolean result = delete(sql);
        return result;
    }

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    public T get(String id, T t) {
        User user = null;
        String sql = " select * from " + getModelName() + " where id = " + id + " ";
        ResultSet rs = null;
        try {
            rs = pool.getConnection().createStatement().executeQuery(sql);
            //组装User
            while (rs.next()) {
                buildModel(rs, t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    /**
     *
     * @param sql
     * @return
     */
    public void saveOrUpdate(String sql) {
        boolean result = false;
        ResultSet rs = null;
        try {
            result = pool.getConnection().createStatement().execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 批处理
     *
     * @param sql
     * @return
     */
    public int[] batch(List<String> sqlList) {
        Connection con = pool.getConnection();

        int[] count = null;
        ResultSet rs = null;
        try {
            Statement statement = (Statement) con.createStatement();
            con.setAutoCommit(false);//取消自动提交  
            for (String str : sqlList) {
                if (StringUtil.isNotNull(str)) {
                    try {
                        statement.addBatch(new String(str.getBytes("gbk"), "gb2312"));
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            count = pool.getConnection().createStatement().executeBatch();
            //如果没有异常，则执行此段代码  
            //提交事务，真正向数据库中提交数据  
            con.commit();


        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * 初始化线程池
     *
     * @return
     */
    public static IConnectionPool initPool() {
        return ConnectionPoolManager.getInstance().getPool(CommonPropertiesService.getValue("PoolName"));

    }

    /**
     * 获取ID 值
     *
     * @param t
     * @return
     */
    private String getTIdVal(T t) {
        String rowVal = "";
        try {
            PropertyDescriptor pd = new PropertyDescriptor("Id", getEntityClass());
            //获取PropertyDescriptor 类的写入方法
            Method m = pd.getReadMethod();
            //因为是读取属性中的方法，所以不需要传值进去
            Object obj = m.invoke(t, null);
            rowVal = (String) obj;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrospectionException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowVal;
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
     * 获取MODEL名称
     *
     * @return
     */
    protected String getModelName() {
        String ModelName = getEntityClass().getName();
        return ModelName.substring(ModelName.lastIndexOf(".") + 1);


    }

    public static void main(String[] args) {
        BaseDao bd = new BaseDao<User>();
        ContactInfoDao contactInfoDao = new ContactInfoDao();
//        bd.
        System.out.println(contactInfoDao.getModelName());



    }
}
