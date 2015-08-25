/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.dao.impl;

/**
 *
 * @author Administrator
 */
public interface ICompanyDao {
    /**
     *
     * @param sql
     * @return
     */
    public int update(String sql);

    /**
     * 统计总数
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
}
