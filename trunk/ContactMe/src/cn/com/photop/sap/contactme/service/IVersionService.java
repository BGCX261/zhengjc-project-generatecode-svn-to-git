/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: IVersionService
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

import cn.com.photop.sap.contactme.model.Version;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: IVersionService
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* 
*  
*/
public interface IVersionService {
// 增加、修改
    void save(Version version);

    void saveOrUpdate(Version version);

    void saveOrUpdateAll(Collection<Version> entities);

    void update(Version version);

    // 删除
    void delete(Version version);
        // 删除
    void delete(int id);
    // 删除
    void delete(String sql);
    
    void deleteAll(Collection<Version> entities);

    // 查询
    Version get(String id);

    List<Version> find(String queryString);

    List<Version> find(String queryString, Object value);

    List<Version> find(String queryString, Object[] values);

    List<Version> list();

    List<Version> findByNamedQuery(String queryName);

    List<Version> findByNamedQuery(String queryName, Object value);

    List<Version> findByNamedQuery(String queryName, Object[] values);

    // 分页查询
    public List<Version> getPagingObjects(final String hql, final int pageStart,final int maxResult);

    public List<Version> getLimitedObjects(final String hql, final int maxResult);

    public int countByHql(final String hql);

    public List<Version> getPagingByParams(final String hql, final Map params, final int pageStart, final int maxResult);

    public int findCountByParams(String strHql, Map params);
}
