/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: IContactInfoService
* @Package cn.com.photop.sap.contactme.service 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* @Copyright photop.cn.Co.,Ltd
* @All right reserved
* @version V1.0   
* @Description: TODO(用一句话描述该文件做什么) 
*
*/
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.model.ContactInfo;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: IContactInfoService
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* 
*  
*/
public interface IContactInfoService {
// 增加、修改
    void save(ContactInfo contactInfo);

    void saveOrUpdate(ContactInfo contactInfo);

    void saveOrUpdateAll(Collection<ContactInfo> entities);

    void update(ContactInfo contactInfo);

    // 删除
    void delete(ContactInfo contactInfo);
    // 删除
    void delete(int id);
    // 删除
    void delete(String sql);
    
    void deleteAll(Collection<ContactInfo> entities);

    // 查询
    ContactInfo get(String id);

    List<ContactInfo> find(String queryString);

    List<ContactInfo> find(String queryString, Object value);

    List<ContactInfo> find(String queryString, Object[] values);

    List<ContactInfo> list();

    List<ContactInfo> findByNamedQuery(String queryName);

    List<ContactInfo> findByNamedQuery(String queryName, Object value);

    List<ContactInfo> findByNamedQuery(String queryName, Object[] values);

    // 分页查询
    public List<ContactInfo> getPagingObjects(final String hql, final int pageStart,final int maxResult);

    public List<ContactInfo> getLimitedObjects(final String hql, final int maxResult);

    public int countByHql(final String hql);

    public List<ContactInfo> getPagingByParams(final String hql, final Map params, final int pageStart, final int maxResult);

    public int findCountByParams(String strHql, Map params);
}
