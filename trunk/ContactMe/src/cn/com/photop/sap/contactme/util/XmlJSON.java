/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: XmlJSON @Package cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-7 12:34:22
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.util;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * @ClassName: XmlJSON @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-7 12:34:22
 * 
*
 */
public class XmlJSON {

    private static final String STR_JSON = "{\"name\":\"Michael\",\"address\":{\"city\":\"Suzou\",\"street\":\" Changjiang Road \",\"postcode\":100025},\"blog\":\"http://www.ij2ee.com\"}";

    public static String xml2JSON(String xml) {

        return new XMLSerializer().read(xml).toString();

    }

    public static String json2XML(String json) {

        JSONObject jobj = JSONObject.fromObject(json);

        String xml = new XMLSerializer().write(jobj);

        return xml;

    }

    public static void main(String[] args) {

        String xml = json2XML(STR_JSON);

        System.out.println("xml = " + xml);

        String json = xml2JSON(xml);

        System.out.println("json=" + json);

    }
}
