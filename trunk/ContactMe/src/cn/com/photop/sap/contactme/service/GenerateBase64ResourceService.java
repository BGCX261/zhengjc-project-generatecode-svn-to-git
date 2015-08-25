/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.service;

import cn.com.photop.sap.contactme.common.CommonConstant;
import cn.com.photop.sap.contactme.model.ContactInfo;
import cn.com.photop.sap.contactme.util.BASE64;
import cn.com.photop.sap.contactme.util.FileUtil;
import java.io.*;
import java.util.*;

/**
 * 生存Base64位的资源文件
 *
 * @author Administrator
 */
public class GenerateBase64ResourceService {

    public static void createBase64File(File objectFile, String content) {
        try {
            FileWriter fileWriter = new FileWriter(objectFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        
        List<ContactInfo> listCISingleCompany;
        File folder = new File("./res/");
        File[] files = folder.listFiles();
        for (File file : files) {
            StringBuffer sb = new StringBuffer();
            if (file.isFile()) {
                if (!"TXT".equalsIgnoreCase(FileUtil.ext(file))) {
                    continue;
                }

                String fileName = file.getName().substring(0, file.getName().length() - 4);

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                int linesNum = 0;
                String companyName = "";
                listCISingleCompany = new ArrayList<ContactInfo>();
                Map<String, List<ContactInfo>> mapciTmp = new HashMap<String, List<ContactInfo>>();
                Set<String> setDepartment = new HashSet<String>();
                for (String line = br.readLine(); line != null; line = br.readLine()) {

                    if (0 != line.length() && !line.startsWith("#")) {
                        //获取每行数据  并且 进行加密处理
                        String newLine = BASE64.encryptBASE64(line.getBytes());
                        newLine = newLine.replaceAll("\r\n", "").replaceAll("77u/", "");
                        System.out.println( newLine );
                        sb.append( newLine );
                        sb.append( "\r\n" );

                    }

                    linesNum++;
                }

                br.close();

                File generate_File = new File(".\\res");
                if (!generate_File.exists()) {
                    generate_File.mkdirs();
                }

                // 创建BASE64文件
                File generateFile = new File(".\\res\\" + fileName + "." + CommonConstant.CONSTANT_BASE64_FILE_EXT );
                createBase64File(generateFile,sb.toString());
            }
        }
    }
}
