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
package cn.com.photop.sap.contactme.service.impl;

import cn.com.photop.sap.contactme.aop.DynamicProxyHandler;
import cn.com.photop.sap.contactme.dao.BaseDao;
import cn.com.photop.sap.contactme.dao.impl.ICompanyDao;
import cn.com.photop.sap.contactme.dao.CompanyDao;
import cn.com.photop.sap.contactme.dao.impl.IBaseDao;
import cn.com.photop.sap.contactme.model.Company;
import cn.com.photop.sap.contactme.pool.Client;
import cn.com.photop.sap.contactme.pool.ThreadConnection;
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
import java.sql.Statement;
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

    private CompanyDao companyDao;

    public CompanyService() {
        super();
        companyDao = new CompanyDao();
    }

    @Override
    public void save(Company company) {
        //组装CompanySQL
        String sql = buildInsertSQL(company);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Company company) {
        //组装CompanySQL
        String sql = buildInsertSQL(company);
        baseDao.saveOrUpdate(sql);
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
        baseDao.update(sql);
    }

    @Override
    public void delete(Company company) {
        String sql = "delete from company where id = "+ company.getId();
        baseDao.delete( sql );
//        baseDao.delete( Integer.valueOf( company.getId() ) );
    }
    
     @Override
    public void delete( String sql ) {
        baseDao.delete( sql );
    }
    
    @Override
    public void delete( int id) {
        String sql = "delete from company where id = "+ id;
        baseDao.delete( sql );
//        baseDao.delete( id );
    }

    @Override
    public void deleteAll(Collection<Company> entities) {
        for (Company company : entities) {
            delete(company);
        }
    }

    @Override
    public Company get(String id) {
        Company company = new Company();
        companyDao.get(id, company);
        return company;
    }

    @Override
    public List<Company> find(String queryString) {
        List<Company> companyList = new ArrayList<Company>();
        ResultSet rs = companyDao.find(queryString);
        try {
            //组装Company
            while (rs.next()) {
                Company company = new Company();
                buildModel(rs, company);
                companyList.add(company);
            }
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
        ResultSet rs = companyDao.list();
        try {
            //组装Company
            while (rs.next()) {
                Company company = new Company();
                buildModel(rs, company);
                companyList.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return companyList;
    }

    @Override
    public List<Company> findByNamedQuery(String queryName) {
        List<Company> companyList = new ArrayList<Company>();
        ResultSet rs = companyDao.find(queryName);
        try {
            //组装Company
            while (rs.next()) {
//                companyList.add(buildModel(rs));
                Company company = new Company();
                buildModel(rs, company);
                companyList.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
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
        return companyDao.count(hql);
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
        return companyDao.count(strHql + " where " + sb.toString());
    }
}
