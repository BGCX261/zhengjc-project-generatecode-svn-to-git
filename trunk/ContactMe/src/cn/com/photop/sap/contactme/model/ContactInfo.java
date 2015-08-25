/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: ContactInfo @Package
 * cn.com.photop.sap.contactme.model
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-4-30 13:00:08
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.model;

/**
 * @ClassName: ContactInfo @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-4-30 13:00:08
 * 
*
 */
public class ContactInfo extends BaseModel{
    //ID
    private String id;
    //公司
    private String company;
    //部门
    private String department;
    //姓名
    private String name;
    //电话
    private String tel;
    //内线
    private String inside;
    //传真
    private String fax;
    //移动电话
    private String mobile;
    //邮箱
    private String email;
    //备注
    private String remark;
//    //出自那个通讯文件
//    private String origin;
//    //行数
//    private int linesNum;
    //状态
    private String status;

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
        this.company = company.trim();
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department.trim();
    }

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
        this.name = name.trim();
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel.trim();
    }

    /**
     * @return the inside
     */
    public String getInside() {
        return inside;
    }

    /**
     * @param inside the inside to set
     */
    public void setInside(String inside) {
        this.inside = inside.trim();
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax.trim();
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile.trim();
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email.trim();
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark.trim();
    }

//    /**
//     * @return the origin
//     */
//    public String getOrigin() {
//        return origin;
//    }
//
//    /**
//     * @param origin the origin to set
//     */
//    public void setOrigin(String origin) {
//        this.origin = origin.trim();
//    }
//
//    /**
//     * @return the linesNum
//     */
//    public int getLinesNum() {
//        return linesNum;
//    }
//
//    /**
//     * @param linesNum the linesNum to set
//     */
//    public void setLinesNum(int linesNum) {
//        this.linesNum = linesNum;
//    }
//
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
        this.status = status.trim();
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
