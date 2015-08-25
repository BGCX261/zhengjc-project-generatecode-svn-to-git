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
package cn.com.photop.sap.contactme.service.impl;

import cn.com.photop.sap.contactme.aop.DynamicProxyHandler;
import cn.com.photop.sap.contactme.dao.BaseDao;
import cn.com.photop.sap.contactme.dao.impl.IContactInfoDao;
import cn.com.photop.sap.contactme.dao.ContactInfoDao;
import cn.com.photop.sap.contactme.dao.impl.IBaseDao;
import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.pool.Client;
import cn.com.photop.sap.contactme.pool.ThreadConnection;
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
import java.sql.Statement;
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

    private ContactInfoDao contactInfoDao;

    public ContactInfoService() {
        super();
        contactInfoDao = new ContactInfoDao();
       
    }

    @Override
    public void save(ContactInfo contactInfo) {
        //组装ContactInfoSQL
        String sql = buildInsertSQL(contactInfo);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(ContactInfo contactInfo) {
        //组装ContactInfoSQL
        String sql = buildInsertSQL(contactInfo);
        baseDao.saveOrUpdate(sql);
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
        baseDao.update(sql);
    }
    
    public void update( String sql ) {
        //拼装sql数据
        baseDao.update(sql);
    }


    @Override
    public void delete(ContactInfo contactInfo) {
        String sql = "delete from contactInfo where id = "+ contactInfo.getId();
//        baseDao.delete( Integer.valueOf( contactInfo.getId() ) );
        baseDao.delete( sql );
    }
    
    @Override
    public void delete( String sql ) {
        baseDao.delete( sql );
    }
    
    @Override
    public void delete( int id) {
        String sql = "delete from contactInfo where id = "+ id;
        contactInfoDao.delete( sql );
//        contactInfoDao.delete( id );
    }
    
    @Override
    public void deleteAll(Collection<ContactInfo> entities) {
        for (ContactInfo contactInfo : entities) {
            delete(contactInfo);
        }
    }

    @Override
    public ContactInfo get(String id) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfoDao.get(id, contactInfo);
        return contactInfo;
    }

    @Override
    public List<ContactInfo> find(String queryString) {
        List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
        ResultSet rs = contactInfoDao.find(queryString);
        try {
            //组装ContactInfo
            while (rs.next()) {
                ContactInfo contactInfo = new ContactInfo();
                buildModel(rs, contactInfo);
                contactInfoList.add(contactInfo);
            }
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
        ResultSet rs = contactInfoDao.list();
        try {
            //组装ContactInfo
            while (rs.next()) {
                ContactInfo contactInfo = new ContactInfo();
                buildModel(rs, contactInfo);
                contactInfoList.add(contactInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contactInfoList;
    }

    @Override
    public List<ContactInfo> findByNamedQuery(String queryName) {
        List<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
        ResultSet rs = contactInfoDao.find(queryName);
        try {
            //组装ContactInfo
            while (rs.next()) {
//                contactInfoList.add(buildModel(rs));
                ContactInfo contactInfo = new ContactInfo();
                buildModel(rs, contactInfo);
                contactInfoList.add(contactInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactInfoService.class.getName()).log(Level.SEVERE, null, ex);
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
        return contactInfoDao.count(hql);
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
        return contactInfoDao.count(strHql + " where " + sb.toString());
    }
   
}
