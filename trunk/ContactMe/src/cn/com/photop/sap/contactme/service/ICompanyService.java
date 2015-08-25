/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: ICompanyService
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

import cn.com.photop.sap.contactme.model.Company;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: ICompanyService
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* 
*  
*/
public interface ICompanyService {
// 增加、修改
    void save(Company company);

    void saveOrUpdate(Company company);

    void saveOrUpdateAll(Collection<Company> entities);

    void update(Company company);

    // 删除
    void delete(Company company);
    // 删除
    void delete(int id);
    // 删除
    void delete(String sql);
    
    void deleteAll(Collection<Company> entities);

    // 查询
    Company get(String id);

    List<Company> find(String queryString);

    List<Company> find(String queryString, Object value);

    List<Company> find(String queryString, Object[] values);

    List<Company> list();

    List<Company> findByNamedQuery(String queryName);

    List<Company> findByNamedQuery(String queryName, Object value);

    List<Company> findByNamedQuery(String queryName, Object[] values);

    // 分页查询
    public List<Company> getPagingObjects(final String hql, final int pageStart,final int maxResult);

    public List<Company> getLimitedObjects(final String hql, final int maxResult);

    public int countByHql(final String hql);

    public List<Company> getPagingByParams(final String hql, final Map params, final int pageStart, final int maxResult);

    public int findCountByParams(String strHql, Map params);
}
