/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: GetUserService @Package
 * cn.com.photop.sap.contactme.service
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-12 10:49:58
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.model.User;
import cn.com.photop.sap.contactme.service.impl.UserService;
import cn.com.photop.sap.contactme.util.IniUtil;
import cn.com.photop.sap.contactme.util.MD5FileUtil;
import cn.com.photop.sap.contactme.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GetUserService 
 * @Description: 从两个配置文件中获取管理用户的信息
 *
 * @author Jiancheng.Zheng 
 * @email dahaha@189.cn 
 * @date 2014-5-12 10:49:58
 * 
 */
public class GetUserService {
    //获取所有的管理用户信息

    private static List<User> userList;
    //是否初始化的标志
    private static Boolean flag = false;

    /**
     * 初始化
     */
    private static void init() {
        if (!flag) {
            flag = true;
            userList = new ArrayList<User>();
            //获取common.properties的用户信息
            String adminInfo = CommonPropertiesService.getValue("AdminInfo");
            //解析用户结构
            //AdminInfo=zhengjc|passwords admin|passwords
            if (StringUtil.isNotNull(adminInfo)) {
                String users[] = adminInfo.split(" ");
                int len = users.length;
                for (int i = 0; i < len; i++) {
                    User user = new User();
                    String userDetail[] = users[i].split("\\|");
                    user.setName(userDetail[0].trim());
                    user.setPassword(userDetail[1].trim());
                    user.setRole("SUPERUSER");
                    user.setFlag("0");  //0标示从properties中获取到
                    userList.add(user);
                    
                }
            }

            //获取init的用户
            
            String path = CommonPropertiesService.getValue( "IniFilePath" );//".\\res\\config.ini";
            String userInfo = IniUtil.getPropValue(path, "userInfo");
            //AdminInfo=zhengjc|passwords admin|passwords
            if (StringUtil.isNotNull(userInfo)) {
                String users[] = userInfo.split(" ");
                int len = users.length;
                for (int i = 0; i < len; i++) {
                    User user = new User();
                    String userDetail[] = users[i].split("\\|", -1);
                    user.setName(userDetail[0].trim());
                    user.setPassword(userDetail[1].trim());
                    user.setRole("USER");
                    user.setFlag("2");  //0标示从ini中获取到
                    userList.add(user);
                    
                }
            }


            //数据库中获取的用户
            UserService userService = new UserService();
            List<User> userDBList = new ArrayList<User>();
            userDBList = userService.list();            
            if (userDBList != null) {
                userList.addAll(userDBList);
            }
            
        }
        
    }

    /**
     * 判断是否是里里面的用户
     *
     * @param user
     * @return
     */
    public static Boolean isContainsUser(User user) {
        init();
        return userList.contains(user);
    }

    /**
     * 判断登陆的用户用户名密码是否正确
     *
     * @param user
     * @return
     */
    public static Boolean isUser(User user) {
        Boolean flg = false;
        init();
        //判断用户名和密码
        for (User u : userList) {
            if (u.getName().equalsIgnoreCase(user.getName()) && u.getPassword().equals(user.getPassword())) {
                flg = true;
            }
        }
        return flg;
    }

    /**
     * 用户登陆的情况
     *
     * @param user
     * @return
     */
    public static Boolean LogonUser(User user) {
        Boolean flag = false;
        //获取登陆密码进行MD5加密处理
        String pwd_md5 = MD5FileUtil.getMD5String(user.getPassword());
        user.setPassword(pwd_md5);
        flag = isUser(user);
        if (flag) {
            //对Seeion User 进行设置
            for (User u : userList) {
                if (u.getName().equalsIgnoreCase(user.getName()) && u.getPassword().equals(user.getPassword())) {
                    CommonSession.user = u;
                    break;
                }
            }
            
        }
        return flag;
    }

    /**
     * @Title: main(这里用一句话描述这个方法的作用) @Description: TODO(这里用一句话描述这个方法的作用)
     *
     * @param args 设定文件
     * @return void 返回类型
     * @throws
     *
     */
    public static void main(String[] args) {
        init();
        for (User u : userList) {
            System.out.println("name:" + u.getName() + "|pwd:" + u.getPassword() + "|role:" + u.getRole());
        }
        
    }
}
