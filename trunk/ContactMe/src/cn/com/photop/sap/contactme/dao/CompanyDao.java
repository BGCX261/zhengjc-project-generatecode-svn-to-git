/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.dao;

import cn.com.photop.sap.contactme.aop.DynamicProxyHandler;
import cn.com.photop.sap.contactme.dao.impl.ICompanyDao;
import cn.com.photop.sap.contactme.model.Company;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CompanyDao extends BaseDao<Company> implements ICompanyDao {
    
    public CompanyDao(){
        super();
        
    }
    //http://tech.it168.com/jd/2008-07-03/200807031803835.shtml
     /**
     *
     * @param sql
     * @return
     */
    @Override
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
     * 统计总数
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
}
