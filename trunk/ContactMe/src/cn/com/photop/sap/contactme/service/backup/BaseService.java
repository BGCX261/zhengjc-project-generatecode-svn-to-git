/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: BaseService @Package
 * cn.com.photop.sap.contactme.service
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:14:58
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service.backup;

import cn.com.photop.sap.contactme.service.*;
import cn.com.photop.sap.contactme.model.BaseModel;
import cn.com.photop.sap.contactme.service.impl.UserService;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: BaseService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-13 10:14:58
 * 
*
 */
public abstract class BaseService<T extends BaseModel> {

    private Log logger = LogFactory.getLog(getClass());
    
    protected Class<T> entityClass;

    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            logger.debug("T class = " + entityClass.getName());
        }
        return entityClass;
    }

    /**
     * 组装成MODEL
     *
     * @return
     */
    protected abstract T buildModel(ResultSet rs);

    /**
     * 把对象转为Update的SQL
     *
     * @param t
     * @return
     */
    protected abstract String buildUpdateSQL(T t);

    /**
     * 把对象转为Insert的SQL
     *
     * @param t
     * @return
     */
    protected abstract String buildInsertSQL(T t);
    
    protected String buildInsertSQL1( T t ) {
        StringBuilder sb = new StringBuilder("insert into ");
        StringBuilder srowName = new StringBuilder();
        StringBuilder srowVal = new StringBuilder();
        String clzName = getEntityClass().getName();
        clzName = getEntityClass().getName().substring(clzName.lastIndexOf(".") + 1, clzName.length());
        sb.append(clzName);
        sb.append(" ( ");
        Field f[] = getEntityClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
//            System.out.println( f[i].getName() );
            try {
                String rowName = f[i].getName();

//                String rowVal = (String) f[i].get(user);
                if (!"id".equalsIgnoreCase(rowName)) {

                    //参数1为person类中属性的名（首字母大写），参数2为person类转化的Class
                    String methodName = rowName.substring(0, 1).toUpperCase() + rowName.substring(1, rowName.length());
                    PropertyDescriptor pd = new PropertyDescriptor(methodName, getEntityClass());
                    //获取PropertyDescriptor 类的写入方法
                    Method m = pd.getReadMethod();
                    //因为是读取属性中的方法，所以不需要传值进去
//                    Object o = new Object();
                    Object obj = m.invoke( t, null );
                    String rowVal = (String) obj;
                    rowVal = rowVal == null ? "" : rowVal;
                    srowName.append(" [" + rowName + "], ");
                    srowVal.append("'" + rowVal + "', ");
                }

            } catch (IllegalAccessException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BaseService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IntrospectionException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        sb.append(srowName.substring(0, srowName.lastIndexOf(",")));
        sb.append(" ) values ( ");
        sb.append(srowVal.substring(0, srowVal.lastIndexOf(",")));
        sb.append(" ) ");

        return sb.toString();
    }
}
