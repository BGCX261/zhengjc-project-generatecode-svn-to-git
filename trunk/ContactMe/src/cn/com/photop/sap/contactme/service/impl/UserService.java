/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: UserService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl;

import cn.com.photop.sap.contactme.dao.impl.IUserDao;
import cn.com.photop.sap.contactme.dao.UserDao;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.pool.Client;
import cn.com.photop.sap.contactme.pool.ThreadConnection;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IUserService;
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
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: UserService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class UserService extends BaseService<User> implements IUserService {

    private UserDao userDao;

    public UserService() {
        super();
        userDao = new UserDao();
    }

    @Override
    public void save(User user) {
        //组装UserSQL
        String sql = buildInsertSQL(user);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(User user) {
        //组装UserSQL
        String sql = buildInsertSQL(user);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdateAll(Collection<User> entities) {
        for (User user : entities) {
            saveOrUpdate(user);
        }
    }

    @Override
    public void update(User user) {
        //拼装sql数据
        String sql = buildUpdateSQL(user);
        baseDao.update(sql);
    }

    @Override
    public void delete(User user) {
        String sql = "delete from user where id = "+ user.getId();
        baseDao.delete( sql );
//        baseDao.delete( Integer.valueOf( user.getId() ) );
    }

     @Override
    public void delete( String sql ) {
        baseDao.delete( sql );
    }
    
    @Override
    public void delete( int id) {
        String sql = "delete from user where id = "+ id;
        baseDao.delete( sql );
//        baseDao.delete( id );
    }
    
    @Override
    public void deleteAll(Collection<User> entities) {
        for (User user : entities) {
            delete(user);
        }
    }

    @Override
    public User get(String id) {
        User user = new User();
        userDao.get(id, user);
        return user;
    }

    @Override
    public List<User> find(String queryString) {
        List<User> userList = new ArrayList<User>();
        ResultSet rs = userDao.find(queryString);
        try {
            //组装User
            while (rs.next()) {
                User user = new User();
                buildModel(rs, user);
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    @Override
    public List<User> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> list() {
        List<User> userList = new ArrayList<User>();
        ResultSet rs = userDao.list();
        try {
            //组装User
            while (rs.next()) {
                User user = new User();
                buildModel(rs, user);
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userList;
    }

    @Override
    public List<User> findByNamedQuery(String queryName) {
        List<User> userList = new ArrayList<User>();
        ResultSet rs = userDao.find(queryName);
        try {
            //组装User
            while (rs.next()) {
//                userList.add(buildModel(rs));
                User user = new User();
                buildModel(rs, user);
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    @Override
    public List<User> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return userDao.count(hql);
    }

    @Override
    public List<User> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
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
        return userDao.count(strHql + " where " + sb.toString());
    }
    
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User();
    }
   
}
