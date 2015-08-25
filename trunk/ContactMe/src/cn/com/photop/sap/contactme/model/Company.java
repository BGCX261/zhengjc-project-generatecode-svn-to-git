/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: Company
* @Package cn.com.photop.sap.contactme.model 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:22:43 
* @Copyright photop.cn.Co.,Ltd
* @All right reserved
* @version V1.0   
* @Description: TODO(用一句话描述该文件做什么) 
*
*/
package cn.com.photop.sap.contactme.model;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;


/** 
* @ClassName: Company
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-13 10:22:43 
* 
*  
*/
public class Company extends BaseModel{
    //id
    private String id;
    //公司
    private String company;
    //状态
    private String status;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public static void main(String[] args) {
        Company c = new Company();
        String clzName = c.getClass().getName();
        clzName = c.getClass().getName().substring( clzName.lastIndexOf(".")+1, clzName.length());
        System.out.println( clzName );
        Field f[] = c.getClass().getDeclaredFields();
        for (int i = 0; i < f.length; i++) {
            System.out.println( f[i].getName() );
            
        }
        try {
            System.out.println( f[0].get( c ) );
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
