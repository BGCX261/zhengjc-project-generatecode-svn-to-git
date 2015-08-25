/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: IUserService
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

import cn.com.photop.sap.contactme.model.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: IUserService
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* 
*  
*/
public interface IUserService {
// 增加、修改
    void save(User user);

    void saveOrUpdate(User user);

    void saveOrUpdateAll(Collection<User> entities);

    void update(User user);

    // 删除
    void delete(User user);
        // 删除
    void delete(int id);
    // 删除
    void delete(String sql);
    
    void deleteAll(Collection<User> entities);

    // 查询
    User get(String id);

    List<User> find(String queryString);

    List<User> find(String queryString, Object value);

    List<User> find(String queryString, Object[] values);

    List<User> list();

    List<User> findByNamedQuery(String queryName);

    List<User> findByNamedQuery(String queryName, Object value);

    List<User> findByNamedQuery(String queryName, Object[] values);

    // 分页查询
    public List<User> getPagingObjects(final String hql, final int pageStart,final int maxResult);

    public List<User> getLimitedObjects(final String hql, final int maxResult);

    public int countByHql(final String hql);

    public List<User> getPagingByParams(final String hql, final Map params, final int pageStart, final int maxResult);

    public int findCountByParams(String strHql, Map params);
}
