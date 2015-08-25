/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.dao;

import cn.com.photop.sap.contactme.dao.impl.IContactInfoDao;
import cn.com.photop.sap.contactme.model.ContactInfo;

/**
 *
 * @author Administrator
 */
public class ContactInfoDao extends BaseDao<ContactInfo> implements IContactInfoDao {
    
    public ContactInfoDao(){
        super();
    }
    
}
