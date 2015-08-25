/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @project ContactMe @Title: CommonPropertiesService @Package
 * cn.com.photop.sap.contactme.service
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-12 10:28:42
 * @Copyright photop.cn.Co.,Ltd @All right reserved
 * @version V1.0 @Description: TODO(用一句话描述该文件做什么)
 * 
*/
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.util.Configuration;
import cn.com.photop.sap.contactme.util.FileUtil;
import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 * @ClassName: CommonPropertiesService @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng @email dahaha@189.cn @date 2014-5-12 10:28:42
 * 
*
 */
public class ImageService {

    private static Boolean initFlg = false;
    private static Map<String, ImageIcon> imageIconMap;

    /**
     * 初始化
     */
    private static void init() {
        if (!initFlg) {
            imageIconMap = new HashMap<String, ImageIcon>();
            String filePath = ImageService.class.getResource("/").getPath() + "cn/com/photop/sap/contactme/resource/images/";
            filePath = filePath.substring(1);
            String[] fileNames = FileUtil.getFileName(filePath);
            for (int i = 0; i < fileNames.length; i++) {
                String ext = fileNames[i].substring(fileNames[i].lastIndexOf(".") + 1);
                if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("gif") || ext.equalsIgnoreCase("jpg")) {
                    ImageIcon imageIcon = new ImageIcon(filePath + fileNames[i]);
                    String fileName = fileNames[i].substring(0, fileNames[i].lastIndexOf("."));
                    imageIconMap.put(fileName, imageIcon);
                }

            }
            initFlg = true;
        }

    }

    /**
     * 获取Key值
     *
     * @param key
     * @return
     */
    public static ImageIcon getImageIcon(String key) {
        init();
        ImageIcon imageIcon = imageIconMap.get(key);
        return imageIcon;
    }

    /**
     * 获取Key值
     *
     * @param key
     * @return
     */
    public static Image getImage(String key) {
        init();
        ImageIcon imageIcon = imageIconMap.get(key);
        Image image = imageIcon.getImage();
        return image;
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
        ImageService.getImage("flower");
        System.out.println("111");
        System.out.println(ImageService.class.getResource("/").getPath());


//        File dir = new File( "D:\\Workspaces\\ContactMe\\build\\classes\\cn\\com\\photop\\sap\\contactme\\resource\\images" );
//        File[] files =  dir.listFiles();
//        for (int i = 0; i < files.length; i++) {
//            System.out.println( files[i].getName() );
//        }
//        String[] filestr =  dir.list();
//        for (int i = 0; i < filestr.length; i++) {
//            System.out.println( filestr[i] );
//        }

    }
}
