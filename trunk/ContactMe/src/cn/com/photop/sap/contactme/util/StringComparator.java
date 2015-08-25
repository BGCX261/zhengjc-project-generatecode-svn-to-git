/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**   
* @project ContactMe
* @Title: StringComparator
* @Package cn.com.photop.sap.contactme.util 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-19 16:17:15 
* @Copyright photop.cn.Co.,Ltd
* @All right reserved
* @version V1.0   
* @Description: TODO(用一句话描述该文件做什么) 
*
*/
package cn.com.photop.sap.contactme.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** 
* @ClassName: StringComparator
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author Jiancheng.Zheng
* @email dahaha@189.cn
* @date 2014-5-19 16:17:15 
* 
*  
*/
public class StringComparator implements Comparator<String>{

    /** 
    * @Title: main(这里用一句话描述这个方法的作用) 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param args    设定文件 
    * @return void    返回类型 
    * @throws 
    *
    */
    public static void main(String[] args) {
        List<String> a = new ArrayList<String>();
        a.add("Setup_V2.00Beta");
        a.add("Setup_V1.00Beta");
        a.add("Setup_V1.02Beta");
        a.add("Setup_V1.01Beta");
        a.add("Setup_V2.01Beta");
        StringComparator comparator = new StringComparator();
        Collections.sort( a,comparator );
        for ( String str:a ) {
            System.out.println( str );
        }
        
    }

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2) * -1;
    }

}

