/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: IConfigService
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

import cn.com.photop.sap.contactme.model.Config;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: IConfigService
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* 
*  
*/
public interface IConfigService {
// 增加、修改
    void save(Config config);

    void saveOrUpdate(Config config);

    void saveOrUpdateAll(Collection<Config> entities);

    void update(Config config);

    // 删除
    void delete(Config config);
       // 删除
    void delete(int id);
    // 删除
    void delete(String sql);
    
    void deleteAll(Collection<Config> entities);

    // 查询
    Config get(String id);

    List<Config> find(String queryString);

    List<Config> find(String queryString, Object value);

    List<Config> find(String queryString, Object[] values);

    List<Config> list();

    List<Config> findByNamedQuery(String queryName);

    List<Config> findByNamedQuery(String queryName, Object value);

    List<Config> findByNamedQuery(String queryName, Object[] values);

    // 分页查询
    public List<Config> getPagingObjects(final String hql, final int pageStart,final int maxResult);

    public List<Config> getLimitedObjects(final String hql, final int maxResult);

    public int countByHql(final String hql);

    public List<Config> getPagingByParams(final String hql, final Map params, final int pageStart, final int maxResult);

    public int findCountByParams(String strHql, Map params);
}
