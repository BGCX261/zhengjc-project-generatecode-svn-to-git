/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: Java2XML @Package cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-7 12:37:57
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.util;

import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.service.GetLocalContactInfoService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jdom.CDATA;

import org.jdom.Document;

import org.jdom.Element;

import org.jdom.JDOMException;

import org.jdom.output.XMLOutputter;

/**
 * @ClassName: Java2XML @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-7 12:37:57
 * 
*
 */
public class Java2XML {

    public void BuildXMLDoc() throws IOException, JDOMException, FileNotFoundException, Exception {

        // 创建根节点 list;    

        Element root = new Element("list");

        // 根节点添加到文档中；    

        Document Doc = new Document(root);

        GetLocalContactInfoService glcis = new GetLocalContactInfoService();

        Iterator it = glcis.mapContactInfo.entrySet().iterator();
        XMLOutputter XMLOut = new XMLOutputter();
        while (it.hasNext()) {
            Entry e = (Entry) it.next();
            Map<String, ArrayList<ContactInfo>> m = (Map<String, ArrayList<ContactInfo>>) e.getValue();
            Iterator it1 = m.entrySet().iterator();
            while (it1.hasNext()) {
                Entry e1 = (Entry) it1.next();
                ArrayList<ContactInfo> ciList = (ArrayList<ContactInfo>) e1.getValue();

                // 此处 for 循环可替换成 遍历 数据库表的结果集操作;    

                for (int i = 0; i < ciList.size(); i++) {

                    // 创建节点 user;    

                    Element elements = new Element("user");

                    // 给 user 节点添加属性 id;    

                    elements.setAttribute("id", "" + i);

                    // 给 user 节点添加子节点并赋值；    

                    // new Element("name")中的 "name" 替换成表中相应字段，setText("xuehui")中 "xuehui 替换成表中记录值；    
//                elements.addContent(new Element("company").setText(StringUtil.toUTF8(ciList.get(i).getCompany())));

                    elements.addContent(new Element("company").setText(ciList.get(i).getCompany()));

                    elements.addContent(new Element("department").setText(ciList.get(i).getDepartment()));

                    elements.addContent(new Element("tel").setText(ciList.get(i).getTel()));

                    elements.addContent(new Element("inside").setText(ciList.get(i).getInside()));

                    elements.addContent(new Element("fax").setText(ciList.get(i).getFax()));

                    elements.addContent(new Element("mobile").setText(ciList.get(i).getMobile()));

                    elements.addContent(new Element("email").setText(ciList.get(i).getEmail()));

                    elements.addContent(new Element("remark").setText(ciList.get(i).getRemark()));

                    elements.addContent(new Element("status").setText(ciList.get(i).getStatus()));

                    //      生成XML的时候，处理特殊字符
//                elements.addContent(new CDATA("<xml> content"));
                    // 给父节点list添加user子节点;   

                    root.addContent(elements);

                }
            }
        }



        // 输出 user.xml 文件；   
        XMLOut.output(Doc, new FileOutputStream("user.xml"));


////生成XML的时候，设置编码
//
//XMLOut.setEncoding("gb2312");
//
//XMLOut.output(Doc, new FileOutputStream("user.xml"));



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
        // TODO code application logic here
        try {


            Java2XML j2x = new Java2XML();

            System.out.println("生成 mxl 文件...");

            j2x.BuildXMLDoc();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
