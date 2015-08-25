/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: IDepartmentService
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

import cn.com.photop.sap.contactme.model.Department;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/** 
* @ClassName: IDepartmentService
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:28:57 
* 
*  
*/
public interface IDepartmentService {
// 增加、修改
    void save(Department department);

    void saveOrUpdate(Department department);

    void saveOrUpdateAll(Collection<Department> entities);

    void update(Department department);

    // 删除
    void delete(Department department);
        // 删除
    void delete(int id);
    // 删除
    void delete(String sql);
    
    void deleteAll(Collection<Department> entities);

    // 查询
    Department get(String id);

    List<Department> find(String queryString);

    List<Department> find(String queryString, Object value);

    List<Department> find(String queryString, Object[] values);

    List<Department> list();

    List<Department> findByNamedQuery(String queryName);

    List<Department> findByNamedQuery(String queryName, Object value);

    List<Department> findByNamedQuery(String queryName, Object[] values);

    // 分页查询
    public List<Department> getPagingObjects(final String hql, final int pageStart,final int maxResult);

    public List<Department> getLimitedObjects(final String hql, final int maxResult);

    public int countByHql(final String hql);

    public List<Department> getPagingByParams(final String hql, final Map params, final int pageStart, final int maxResult);

    public int findCountByParams(String strHql, Map params);
}
