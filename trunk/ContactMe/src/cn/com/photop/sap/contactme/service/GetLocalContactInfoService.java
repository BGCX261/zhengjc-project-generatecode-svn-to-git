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
import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.util.BASE64;
import cn.com.photop.sap.contactme.util.Configuration;
import cn.com.photop.sap.contactme.util.FileUtil;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * @ClassName: GetLocalContactInfoService 
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author Jiancheng.Zheng 
 * @email dahaha@189.cn 
 * @date 2014-5-7 10:08:09
 * 
 */
public class GetLocalContactInfoService {

    public static Map< String, Map<String, List<ContactInfo>>> mapContactInfo;
    public static List<ContactInfo> listContactInfo;
    private String resourcePath;
    //公司的集合
    public List<String> listCompany;
    //部门的集合 key 是公司
    public Map<String, List<String>> mapDepartment;
    public Map<String, Set<String>> mapSet = new HashMap<String, Set<String>>();

    /**
     * 构造函数
     */
    public GetLocalContactInfoService() throws FileNotFoundException, IOException, Exception {
        listContactInfo = new ArrayList<ContactInfo>();
        //设置通讯录对象内容
        mapContactInfo = new HashMap();
        //公司的集合
        listCompany = new ArrayList<String>();

        //部门的集合 key 是公司
        mapDepartment = new HashMap<String, List<String>>();

        //获取配置文件
//        InputStream is = GetLocalContactInfoService.class.getResourceAsStream("/cn/com/photop/sap/contactme/config/common.properties");
//        Configuration rc = new Configuration(is);// 相对路径
//        //获取本地文件的路径
//        resourcePath = rc.getValue("ResourcePath");
        resourcePath = CommonPropertiesService.getValue("ResourcePath");
        //huoqu 
//        String fileName[] = FileUtil.getFileName(resourcePath);
//        for (int i = 0; i < fileName.length; i++) {
//            //获取每个文件的数据
//            String singleFileName = fileName[i];
//            String reportStr = FileUtil.fileToString(GetLocalContactInfoService.class.getResourceAsStream(singleFileName));
//
//        }
        listContactInfo = new ArrayList<ContactInfo>();
        List<ContactInfo> listCISingleCompany;
        File folder = new File(resourcePath);
        File[] files = folder.listFiles();
        Set<String> setDepartment = null;
        Map<String, List<ContactInfo>> mapciTmp = new HashMap<String, List<ContactInfo>>();
        for (File file : files) {
            if (file.isFile()) {
                if (CommonConstant.CONSTANT_BASE64_DATA) {
                    //是否为加密后的文件
                    if (!CommonConstant.CONSTANT_BASE64_FILE_EXT.equalsIgnoreCase(FileUtil.ext(file))) {
                        continue;
                    }
                } else {
                    if (!"TXT".equalsIgnoreCase(FileUtil.ext(file))) {
                        continue;
                    }
                }

                String fileName = file.getName().substring(0, file.getName().length() - 4);

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                int linesNum = 0;
                String companyName = "";
                for (String line = br.readLine(); line != null; line = br.readLine()) {

                    if (0 != line.length() && !line.startsWith("#")) {
                        //获取每行数据
                        ContactInfo ci = new ContactInfo();
                        byte[] byteArray = BASE64.decryptBASE64(line);
                        line = new String(byteArray);
                        String items[] = line.split("\\|");

                        ci.setCompany(items[0]);
                        ci.setDepartment(items[1]);

                        if (!"".equals(items[0])) {
                            companyName = items[0].trim();
                            setDepartment = (HashSet<String>) mapSet.get(companyName);
                            if (setDepartment == null) {
                                setDepartment = new HashSet<String>();
                            }
                            setDepartment.add(ci.getDepartment());
                            mapSet.put(companyName, setDepartment);
                        }
                        ci.setName(items[2]);
                        ci.setTel(items[3]);
                        ci.setInside(items[4]);
                        ci.setFax(items[5]);
                        ci.setMobile(items[6]);
                        ci.setEmail(items[7]);
                        ci.setRemark(items[8]);
                        ci.setStatus(items[9]);
//                        ci.setOrigin(fileName);
//                        ci.setLinesNum(linesNum);
                        listContactInfo.add(ci);

                    }
                    linesNum++;
                }
                br.close();

//                File generate_File = new File(".\\" + fileName);
//                if (!generate_File.exists()) {
//                    generate_File.mkdirs();
//                }
            }
        }


        //遍历部门的SET 获取相关的LIST
        Iterator m = mapSet.entrySet().iterator();
        while (m.hasNext()) {
            Entry e = (Entry) m.next();
            setDepartment = (HashSet<String>) e.getValue();
            String company = (String) e.getKey();
            mapciTmp = new HashMap<String, List<ContactInfo>>();
            for (Iterator it = setDepartment.iterator(); it.hasNext();) {
                List<ContactInfo> ciListTemp = new ArrayList<ContactInfo>();
                String setValue = it.next().toString();
                for (int j = 0; j < listContactInfo.size(); j++) {
                    if (listContactInfo.get(j).getCompany().equals(company)
                            && listContactInfo.get(j).getDepartment().equals(setValue)) {
                        ciListTemp.add(listContactInfo.get(j));
                    }

                }
                mapciTmp.put(setValue, ciListTemp);

            }
            //部门的集合 key 是公司
            mapDepartment.put(company, new ArrayList<String>(setDepartment));


            mapContactInfo.put(company, mapciTmp);
            //公司集合
            listCompany.add(company);
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
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // TODO code application logic here
        GetLocalContactInfoService glcis = new GetLocalContactInfoService();
    }
}
