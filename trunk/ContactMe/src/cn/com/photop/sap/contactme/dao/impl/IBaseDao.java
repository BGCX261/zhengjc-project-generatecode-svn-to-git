/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.dao.impl;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface IBaseDao {
    /**
     *
     * @param sql
     * @return
     */
    public int update(String sql);

    /**
     * 删除
     *
     * @param sql
     * @return
     */
    public boolean delete(String sql);
    
    /**
     *
     * @param sql
     * @return
     */
    public void saveOrUpdate(String sql) ;
    
      /**
     * 删除
     *
     * @param T t
     * @return
     */
    public boolean delete(int i);
    
}
