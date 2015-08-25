/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: GetLocalContactInfoService @Package
 * cn.com.photop.sap.contactme.service
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-7 10:08:09
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.common.CommonConstant;
import cn.com.photop.sap.contactme.model.Company;
import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.model.Department;
import cn.com.photop.sap.contactme.service.impl.CompanyService;
import cn.com.photop.sap.contactme.service.impl.ContactInfoService;
import cn.com.photop.sap.contactme.service.impl.DepartmentService;
import java.io.*;
import java.util.*;

/**
 * @ClassName: GetLocalContactInfoService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-7 10:08:09
 * 
*
 */
public class GetMDBContactInfoService {

    private Map< String, Map<String, List<ContactInfo>>> mapContactInfo;
    private List<ContactInfo> listContactInfo;
    //公司的集合
    private List<Company> listCompany;
    //部门的集合 key 是公司
    private Map<String, List<Department>> mapDepartment;
    private Map<String, Set<String>> mapSet = new HashMap<String, Set<String>>();
    //相关数据库服务
    private CompanyService companyService;
    private DepartmentService departmentService;
    private ContactInfoService contactInfoService;

    /**
     * 构造函数
     */
    public GetMDBContactInfoService() throws FileNotFoundException, IOException, Exception {
        //获取联系人的信息
        listContactInfo = new ArrayList<ContactInfo>();
        contactInfoService = new ContactInfoService();
        listContactInfo = contactInfoService.list();
        //公司的集合
        listCompany = new ArrayList<Company>();
        companyService = new CompanyService();
        List<Company> companyList = companyService.list();

        departmentService = new DepartmentService();
        contactInfoService = new ContactInfoService();
        //部门的集合 key 是公司
        mapDepartment = new HashMap<String, List<Department>>();
        for (Company c : companyList) {
            listCompany.add(c);
            //获取所有部门的列表 
            String sql = "select * from department where company = '" + c.getCompany() + "'";
//            String sql = "select * from department where company = '"+ c.getId() + "'";
            List<Department> departmentList = departmentService.find(sql);
            mapDepartment.put(c.getCompany(), departmentList);

        }

        //设置通讯录对象内容
        mapContactInfo = new HashMap< String, Map<String, List<ContactInfo>>>();
        for (Company c : listCompany) {
            List<Department> departmentList = mapDepartment.get(c.getCompany());
            Map<String, List<ContactInfo>> dciMap = new HashMap<String, List<ContactInfo>>();
            for (Department d : departmentList) {
                List<ContactInfo> ciList = new ArrayList<ContactInfo>();
                for (int i = 0; i < listContactInfo.size(); i++) {
                    if (c.getCompany().equals(listContactInfo.get(i).getCompany())
                            && d.getDepartment().equals(listContactInfo.get(i).getDepartment())) {
                        ciList.add(listContactInfo.get(i));
                    }
//                    if ( c.getId().equals( listContactInfo.get(i).getCompany() )
//                         && d.getId().equals( listContactInfo.get(i).getDepartment() ) ) {
//                        ciList.add( listContactInfo.get(i) );
//                    }
                }
                dciMap.put(d.getDepartment(), ciList);
            }

            mapContactInfo.put(c.getCompany(), dciMap);
        }




    }

    public Map<String, List<Department>> getMapDepartment() {
        return mapDepartment;
    }

    public List<Company> getListCompany() {
        return listCompany;
    }

    public Map< String, Map<String, List<ContactInfo>>> getMapContactInfo() {
        return mapContactInfo;
    }

    public List<ContactInfo> getListContactInfo() {
        return listContactInfo;
    }

    /**
     * @Title: main(这里用一句话描述这个方法的作用) @Description: TODO(这里用一句话描述这个方法的作用)
     *
     * @param args 设定文件
     * @return void 返回类型
     * @throws
     *
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here
        GetMDBContactInfoService glcis = new GetMDBContactInfoService();
    }
}
