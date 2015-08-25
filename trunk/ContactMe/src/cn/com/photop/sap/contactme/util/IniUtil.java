/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: IniUtil @Package cn.com.photop.sap.contactme.util
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-9 10:17:14
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @ClassName: IniUtil @Description: 读取指定INI文件 使用java读取配置文件(ini、properties)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-9 10:17:14
 * 
*
 */
public class IniUtil {

    public static Map<String, String> propMap = null;
    private static Boolean flag = false;

    /**
     * 初始化
     */
    public static void init(String path) {
        if (!flag) {
            propMap = new HashMap<String, String>();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path));
                String line = "";
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("=") != -1 && !line.trim().startsWith("#")
                            && !line.trim().startsWith("//")) {
                        String[] lineArr = line.split("=");
                        propMap.put(lineArr[0].trim(), lineArr[1].trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            flag = true;
        }

    }

    /**
     * 获取所有键值对组成的Map
     *
     * @param path 配置文件所在路径
     * @return
     */
    public static Map<String, String> getPropMap(String path) {
//      if(propMap == null || propMap.isEmpty()){   
        init(path);
//      }   

        return propMap;
    }

    /**
     * 获取属性值
     *
     * @param path 配置文件所在路径
     * @param key 属性名
     * @return
     */
    public static String getPropValue(String path, String key) {
//      if(propMap == null || propMap.isEmpty()){   
        init(path);
//      }   

        return propMap.get(key);
    }

    /**
     * 设置某个属性的值
     *
     * @param path
     * @param key
     * @param value
     */
    public static void setPropValue(String path, String key, String value) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(key, value);
        setPropValues(path, map);
    }

    /**
     * 根据Map设置属性
     *
     * @param path
     * @param map
     */
    public static void setPropValues(String path, Map<String, String> map) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(path));

            StringBuffer sb = new StringBuffer();
            String line = "";
            // 读取文件并对替换文件内容   
            while ((line = br.readLine()) != null) {
                if (line.indexOf("=") != -1 && !line.trim().startsWith("#")
                        && !line.trim().startsWith("//")) {
                    String[] lineArr = line.split("=");
                    String key = lineArr[0].trim();
                    String newValue = map.get(key);
                    if (null != newValue && !"".equals(newValue)) {
                        sb.append(key).append(" = ").append(newValue).append("\r\n");
                    } else {
                        sb.append(line).append("\r\n");
                    }
                } else {
                    sb.append(line).append("\r\n");
                }
            }
            br.close();
            bw = new BufferedWriter(new FileWriter(path));
            // 写入文件   
            bw.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

        Map<String, String> propMap1 = IniUtil.getPropMap(".\\res\\config.ini");
        Iterator it = propMap1.entrySet().iterator();
        while (it.hasNext()) {
            Entry e = (Entry) it.next();
            System.out.println((String) e.getKey() + ":" + (String) e.getValue());
        }
        
//        String str = "0.00";
//        float a = Float.valueOf( str );
//        System.out.println( a==0 );
//        System.out.println( a==0.00 );
    }
}
