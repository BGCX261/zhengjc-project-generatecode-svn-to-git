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
package cn.com.photop.sap.contactme.service.impl;

import cn.com.photop.sap.contactme.dao.impl.IDepartmentDao;
import cn.com.photop.sap.contactme.dao.DepartmentDao;
import cn.com.photop.sap.contactme.model.Department;
import cn.com.photop.sap.contactme.pool.Client;
import cn.com.photop.sap.contactme.pool.ThreadConnection;
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
import java.sql.Statement;
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

    private DepartmentDao departmentDao;

    public DepartmentService() {
        super();
        departmentDao = new DepartmentDao();
    }

    @Override
    public void save(Department department) {
        //组装DepartmentSQL
        String sql = buildInsertSQL(department);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Department department) {
        //组装DepartmentSQL
        String sql = buildInsertSQL(department);
        baseDao.saveOrUpdate(sql);
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
        baseDao.update(sql);
    }
    
    public void update( String sql ) {
        baseDao.update( sql );
    }

    @Override
    public void delete(Department department) {
        String sql = "delete from department where id = "+ department.getId();
        baseDao.delete( sql );
//        baseDao.delete( Integer.valueOf( department.getId() ) );
    }

    @Override
    public void delete( String sql ) {
        baseDao.delete( sql );
    }
    
    @Override
    public void delete( int id) {
        String sql = "delete from department where id = "+ id;
        baseDao.delete( sql );
//        baseDao.delete( id );
    }
    
    @Override
    public void deleteAll(Collection<Department> entities) {
        for (Department department : entities) {
            delete(department);
        }
    }

    @Override
    public Department get(String id) {
        Department department = new Department();
        departmentDao.get(id, department);
        return department;
    }

    @Override
    public List<Department> find(String queryString) {
        List<Department> departmentList = new ArrayList<Department>();
        ResultSet rs = departmentDao.find(queryString);
        try {
            //组装Department
            while (rs.next()) {
                Department department = new Department();
                buildModel(rs, department);
                departmentList.add(department);
            }
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
        ResultSet rs = departmentDao.list();
        try {
            //组装Department
            while (rs.next()) {
                Department department = new Department();
                buildModel(rs, department);
                departmentList.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return departmentList;
    }

    @Override
    public List<Department> findByNamedQuery(String queryName) {
        List<Department> departmentList = new ArrayList<Department>();
        ResultSet rs = departmentDao.find(queryName);
        try {
            //组装Department
            while (rs.next()) {
//                departmentList.add(buildModel(rs));
                Department department = new Department();
                buildModel(rs, department);
                departmentList.add(department);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentService.class.getName()).log(Level.SEVERE, null, ex);
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
        return departmentDao.count(hql);
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
        return departmentDao.count(strHql + " where " + sb.toString());
    }
   
}
