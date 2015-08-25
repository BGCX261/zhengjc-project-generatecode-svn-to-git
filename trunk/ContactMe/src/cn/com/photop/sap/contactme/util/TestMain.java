/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: TestMain @Package cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-6-12 15:31:47
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.util;

import cn.com.photop.sap.contactme.model.BaseModel;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.BaseService;
import cn.com.photop.sap.contactme.service.impl.CompanyService;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestMain @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-6-12 15:31:47
 * 
*
 */
public class TestMain {

    /**
     * @Title: main(这里用一句话描述这个方法的作用) @Description: TODO(这里用一句话描述这个方法的作用)
     *
     * @param args 设定文件
     * @return void 返回类型
     * @throws
     *
     */
    public static void main(String[] args) {
//        BaseService[] baseService = new BaseService[100];
//        CompanyService
        List<User> csList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("" + i);
            csList.add(user);
        }
        final int size = csList.size();
        BaseModel[] baseModel = csList.toArray( new User[size] );
                
        for (int i = 0; i < baseModel.length; i++) {
            System.out.println(((User)baseModel[i]).getName());
        }



    }
}
