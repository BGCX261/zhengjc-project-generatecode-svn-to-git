/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.dao;

import cn.com.photop.sap.contactme.dao.impl.IUserDao;
import cn.com.photop.sap.contactme.model.User;

/**
 *
 * @author Administrator
 */
public class UserDao extends BaseDao<User> implements IUserDao {
    
    public UserDao(){
        super();
    }
    
}
