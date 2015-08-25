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
package cn.com.photop.sap.contactme.service.impl;

import cn.com.photop.sap.contactme.dao.impl.IVersionDao;
import cn.com.photop.sap.contactme.dao.VersionDao;
import cn.com.photop.sap.contactme.model.Version;
import cn.com.photop.sap.contactme.pool.Client;
import cn.com.photop.sap.contactme.pool.ThreadConnection;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IVersionService;
import cn.com.photop.sap.contactme.thread.UpdateDataThread;
import cn.com.photop.sap.contactme.thread.UploadDataThread;
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
 * @ClassName: VersionService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class VersionService extends BaseService<Version> implements IVersionService {

    private VersionDao versionDao;

    public VersionService() {
        versionDao = new VersionDao();
    }

    @Override
    public void save(Version version) {
        //组装VersionSQL
        String sql = buildInsertSQL(version);
        versionDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Version version) {
        //组装VersionSQL
        String sql = buildInsertSQL(version);
        versionDao.saveOrUpdate(sql);

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
        versionDao.update(sql);

    }

    @Override
    public void delete(Version version) {
        String sql = "delete from version where id = " + version.getId();        
          versionDao.delete( sql );
//        versionDao.delete( version );

    }
    
    @Override
    public void delete(String sql) {
        versionDao.delete( sql );
    }

     @Override
    public void delete( int id ) {
        String sql = "delete from version where id = " + id;         
         versionDao.delete( sql );
//        versionDao.delete( id );
    }

    
    @Override
    public void deleteAll(Collection<Version> entities) {
        for (Version version : entities) {
            delete(version);
        }
    }

    @Override
    public Version get(String id) {
        Version version = new Version();
        versionDao.get(id, version);
        return version;
    }

    @Override
    public List<Version> find(String queryString) {
        List<Version> versionList = new ArrayList<Version>();
        ResultSet rs = versionDao.find(queryString);
        try {
            //组装Version
            while (rs.next()) {
                Version version = new Version();
                buildModel(rs, version);
                versionList.add(version);
            }
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
        ResultSet rs = versionDao.list();
        try {
            //组装Version
            while (rs.next()) {
                Version version = new Version();
                buildModel(rs, version);
                versionList.add(version);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.VersionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return versionList;
    }

    @Override
    public List<Version> findByNamedQuery(String queryName) {
        List<Version> versionList = new ArrayList<Version>();
        ResultSet rs = versionDao.find(queryName);
        try {
            //组装Version
            while (rs.next()) {
//                versionList.add(buildModel(rs));
                Version version = new Version();
                buildModel(rs, version);
                versionList.add(version);
            }
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
        return versionDao.count(hql);
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
        return versionDao.count(strHql + " where " + sb.toString());
    }
}
