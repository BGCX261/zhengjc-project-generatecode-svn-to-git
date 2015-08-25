/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: ConfigService @Package
 * cn.com.photop.sap.contactme.service.impl
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.impl;

import cn.com.photop.sap.contactme.dao.impl.IConfigDao;
import cn.com.photop.sap.contactme.dao.ConfigDao;
import cn.com.photop.sap.contactme.model.Config;
import cn.com.photop.sap.contactme.pool.Client;
import cn.com.photop.sap.contactme.pool.ThreadConnection;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.IConfigService;
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
 * @ClassName: ConfigService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:34:20
 * 
*
 */
public class ConfigService extends BaseService<Config> implements IConfigService {

    private ConfigDao configDao;

    public ConfigService() {
        super();
        configDao = new ConfigDao();
    }

    @Override
    public void save(Config config) {
        //组装ConfigSQL
        String sql = buildInsertSQL(config);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdate(Config config) {
        //组装ConfigSQL
        String sql = buildInsertSQL(config);
        baseDao.saveOrUpdate(sql);
    }

    @Override
    public void saveOrUpdateAll(Collection<Config> entities) {
        for (Config config : entities) {
            saveOrUpdate(config);
        }
    }

    @Override
    public void update(Config config) {
        //拼装sql数据
        String sql = buildUpdateSQL(config);
        baseDao.update(sql);
    }
    
    
    public void update( String sql ) {
        baseDao.update(sql);
    }

    @Override
    public void delete(Config config) {
        String sql = "delete from config where id = "+ config.getId();
        baseDao.delete( sql );
//        baseDao.delete( Integer.valueOf( config.getId() ) );
    }
    
     @Override
    public void delete( String sql ) {
        baseDao.delete( sql );
    }
    
    @Override
    public void delete( int id) {
        String sql = "delete from config where id = "+ id;
        baseDao.delete( sql );        
//        baseDao.delete( id );
    }

    @Override
    public void deleteAll(Collection<Config> entities) {
        for (Config config : entities) {
            delete(config);
        }
    }

    @Override
    public Config get(String id) {
        Config config = new Config();
        configDao.get(id, config);
        return config;
    }

    @Override
    public List<Config> find(String queryString) {
        List<Config> configList = new ArrayList<Config>();
        ResultSet rs = configDao.find(queryString);
        try {
            //组装Config
            while (rs.next()) {
                Config config = new Config();
                buildModel(rs, config);
                configList.add(config);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configList;
    }

    @Override
    public List<Config> find(String queryString, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> find(String queryString, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> list() {
        List<Config> configList = new ArrayList<Config>();
        ResultSet rs = configDao.list();
        try {
            //组装Config
            while (rs.next()) {
                Config config = new Config();
                buildModel(rs, config);
                configList.add(config);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return configList;
    }

    @Override
    public List<Config> findByNamedQuery(String queryName) {
        List<Config> configList = new ArrayList<Config>();
        ResultSet rs = configDao.find(queryName);
        try {
            //组装Config
            while (rs.next()) {
//                configList.add(buildModel(rs));
                Config config = new Config();
                buildModel(rs, config);
                configList.add(config);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configList;
    }

    @Override
    public List<Config> findByNamedQuery(String queryName, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> findByNamedQuery(String queryName, Object[] values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> getPagingObjects(String hql, int pageStart, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Config> getLimitedObjects(String hql, int maxResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int countByHql(String hql) {
        return configDao.count(hql);
    }

    @Override
    public List<Config> getPagingByParams(String hql, Map params, int pageStart, int maxResult) {
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
        return configDao.count(strHql + " where " + sb.toString());
    }

    public Map<String, String> getConfigMap() {
        Map<String, String> configMap = new HashMap<String, String>();
        List<Config> configList = new ArrayList<Config>();
        ResultSet rs = configDao.list();
        try {
            //组装Config
            while (rs.next()) {
                Config config = new Config();
                configList.add(buildModel(rs, config));
            }
        } catch (SQLException ex) {
            Logger.getLogger(cn.com.photop.sap.contactme.service.impl.backup.ConfigService.class.getName()).log(Level.SEVERE, null, ex);
        }


        //把LIST转为MAP
        for (Config c : configList) {
            configMap.put(c.getKey(), c.getValue());
        }

        return configMap;
    }
    
    public static void main(String[] args) {
        ConfigService configService = new ConfigService();
        Config config = new Config();
        config.setKey("ftp_flg");
        config.setValue("true");
        configService.save(config);
        
        config.setKey("local_flg");
        config.setValue("true");
        configService.save(config);
    }
    
}
