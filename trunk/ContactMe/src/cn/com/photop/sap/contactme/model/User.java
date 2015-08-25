/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: User
* @Package cn.com.photop.sap.contactme.model 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-12 10:45:16 
* @Copyright photop.cn.Co.,Ltd
* @All right reserved
* @version V1.0   
* @Description: TODO(用一句话描述该文件做什么) 
*
*/
package cn.com.photop.sap.contactme.model;


/** 
* @ClassName: User
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-12 10:45:16 
* 
*  
*/
public class User extends BaseModel {
    //ID
    private String id;
    //用户名
    private String name;
    //密码
    private String password;
    //角色
    private String role;
    //状态
    private String flag;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

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
}
