package cn.com.photop.sap.cmupdate.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * FileUtil. Simple file operation class.
 *
 *
 *
 * @author BeanSoft
 *
 *
 */
public class FileUtil {
//    public static Logger logger = Logger.getLogger( FileUtil.class );   

    public static Set<String> sets = new HashSet<String>();
    /**
     *
     * The buffer.
     */
    protected static byte buf[] = new byte[1024];

    /**
     *
     * Read content from local file. FIXME How to judge UTF-8 and GBK, the
     *
     * correct code should be: FileReader fr = new FileReader(new
     *
     * InputStreamReader(fileName, "ENCODING")); Might let the user select the
     *
     * encoding would be a better idea. While reading UTF-8 files, the content
     *
     * is bad when saved out.
     *
     *
     *
     * @param fileName -
     *
     * local file name to read
     *
     * @return
     *
     * @throws Exception
     */
    public static String readFileAsString(String fileName) throws Exception {

        String content = new String(readFileBinary(fileName));

        return content;

    }

    /**
     *
     * 读取文件并返回为给定字符集的字符串.
     *
     * @param fileName
     *
     * @param encoding
     *
     * @return
     *
     * @throws Exception
     */
    public static String readFileAsString(String fileName, String encoding)
            throws Exception {

        String content = new String(readFileBinary(fileName), encoding);

        return content;

    }

    /**
     *
     * 读取文件并返回为给定字符集的字符串.
     *
     * @param fileName
     *
     * @param encoding
     *
     * @return
     *
     * @throws Exception
     */
    public static String readFileAsString(InputStream in) throws Exception {

        String content = new String(readFileBinary(in));

        return content;

    }

    /**
     *
     * Read content from local file to binary byte array.
     *
     *
     *
     * @param fileName -
     *
     * local file name to read
     *
     * @return
     *
     * @throws Exception
     */
    public static byte[] readFileBinary(String fileName) throws Exception {

        FileInputStream fin = new FileInputStream(fileName);

        return readFileBinary(fin);

    }

    /**
     *
     * 从输入流读取数据为二进制字节数组.
     *
     * @param streamIn
     *
     * @return
     *
     * @throws IOException
     */
    public static byte[] readFileBinary(InputStream streamIn)
            throws IOException {

        BufferedInputStream in = new BufferedInputStream(streamIn);

        ByteArrayOutputStream out = new ByteArrayOutputStream(10240);

        int len;

        while ((len = in.read(buf)) >= 0) {
            out.write(buf, 0, len);
        }

        in.close();

        return out.toByteArray();

    }

    /**
     *
     * Write string content to local file.
     *
     *
     *
     * @param fileName -
     *
     * local file name will write to
     *
     * @param content
     *
     * String text
     *
     * @return true if success
     *
     * @throws IOException
     */
    public static boolean writeFileString(String fileName, String content)
            throws IOException {

        FileWriter fout = new FileWriter(fileName);

        fout.write(content);

        fout.close();

        return true;

    }

    /**
     *
     * Write string content to local file using given character encoding.
     *
     *
     *
     * @param fileName -
     *
     * local file name will write to
     *
     * @param content
     *
     * String text
     *
     * @param encoding
     *
     * the encoding
     *
     * @return true if success
     *
     * @throws IOException
     */
    public static boolean writeFileString(String fileName, String content,
            String encoding) throws IOException {

        OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(
                fileName), encoding);

        fout.write(content);

        fout.close();

        return true;

    }

    /**
     *
     * Write binary byte array to local file.
     *
     *
     *
     * @param fileName -
     *
     * local file name will write to
     *
     * @param content
     *
     * binary byte array
     *
     * @return true if success
     *
     * @throws IOException
     */
    public static boolean writeFileBinary(String fileName, byte[] content)
            throws IOException {

        FileOutputStream fout = new FileOutputStream(fileName);

        fout.write(content);

        fout.close();

        return true;

    }

    /**
     *
     * 检查文件名是否合法.文件名字不能包含字符\/:*?"<>|
     *
     *
     *
     * @param fileName文件名 ,不包含路径
     *
     * @return boolean is valid file name
     */
    public static boolean isValidFileName(String fileName) {

        boolean isValid = true;

        String errChar = "\\/:*?\"<>|"; //

        if (fileName == null || fileName.length() == 0) {

            isValid = false;

        } else {

            for (int i = 0; i < errChar.length(); i++) {

                if (fileName.indexOf(errChar.charAt(i)) != -1) {

                    isValid = false;

                    break;

                }

            }

        }

        return isValid;

    }

    /**
     *
     * 把非法文件名转换为合法文件名.
     *
     *
     *
     * @param fileName
     *
     * @return
     */
    public static String replaceInvalidFileChars(String fileName) {

        StringBuffer out = new StringBuffer();

        for (int i = 0; i < fileName.length(); i++) {

            char ch = fileName.charAt(i);

            // Replace invlid chars: \\/:*?\"<>|

            switch (ch) {

                case '\\':

                case '/':

                case ':':

                case '*':

                case '?':

                case '\"':

                case '<':

                case '>':

                case '|':

                    out.append('_');

                    break;

                default:

                    out.append(ch);

            }

        }

        return out.toString();

    }

    /**
     *
     * Convert a given file name to a URL(URI) string.
     *
     *
     *
     * @param fileName -
     *
     * the file to parse
     *
     * @return - URL string
     */
    public static String filePathToURL(String fileName) {

        String fileUrl = new File(fileName).toURI().toString();

        return fileUrl;

    }

    /**
     *
     * Write string content to local file.
     *
     *
     *
     * @param fileName -
     *
     * local file name will write to
     *
     * @param content
     *
     * String text
     *
     * @return true if success
     *
     * @throws IOException
     */
    public static boolean appendFileString(String fileName, String content)
            throws IOException {

        OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(
                fileName, true), "GBK");

        fout.write(content);
//        fout.write("\r\n");

        fout.close();

        return true;

    }

    public static final void readF1(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath)));

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            System.out.println(line);
        }
        br.close();

    }

    public static final void readF2(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader bufferedreader = new BufferedReader(fr);
        String instring;
        while ((instring = bufferedreader.readLine().trim()) != null) {
            if (0 != instring.length()) {
                System.out.println(instring);
            }
        }
        fr.close();
    }

    /**
     * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━� ? @description 获取文件扩展,大写
     *
     * @param file(File类型) 目标文件
     *
     * @return String 文件扩展�?
     *
     * @date 2009-3-23 -------------------------------------------------------
     */
    public static String ext(File file) {
        String[] t = file.getName().split("\\.");
        int l = t.length;
        if (l > 0) {
            return t[l - 1].toUpperCase();
        }
        return null;
    }

    /**
     * 列出文件名,仅为文件名:
     */
    public static void listFile(String sDir) {
        File file = new File(sDir);
        String test[];
        test = file.list();

        String subDir;
        for (int i = 0; i < test.length; i++) {
            if (sDir.endsWith(File.separator)) {
                subDir = sDir + test[i];
            } else {
                subDir = sDir + File.separator + test[i];
            }

            File subfile = new File(subDir);

            if (subfile.isDirectory()) {
                listFile(subDir);
            }

//            System.out.println(test[i]);
        }
    }

    /**
     * 过滤MP3文件
     *
     * @param strPath
     */
    public static void refreshFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                refreshFileList(files[i].getAbsolutePath());
            } else {
                String strFilePath = files[i].getAbsolutePath().toLowerCase();
                String strName = files[i].getName();
                if (strName.endsWith(".mp3")) {
                    boolean bFlag = sets.add(strName);
                    if (bFlag == Boolean.FALSE) {
                        // 删除重复文件   
                        removeFile(strFilePath);
                    }
                }
                // System.out.println("FILE_PATH:" + strFilePath + "|strName:" +   
                // strName);   
            }
        }
    }

    /**
     * 创建文件夹
     *
     * @param strFilePath 文件夹路径
     */
    public static boolean mkdirFolder(String strFilePath) {
        boolean bFlag = false;
        try {
            File file = new File(strFilePath.toString());
            if (!file.exists()) {
                bFlag = file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFlag;
    }

    public static boolean createFile(String strFilePath, String strFileContent) {
        boolean bFlag = false;
        try {
            File file = new File(strFilePath.toString());
            if (!file.exists()) {
                bFlag = file.createNewFile();
            }
            if (bFlag == Boolean.TRUE) {
                FileWriter fw = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(strFileContent.toString());
                pw.println("\r\n");
                pw.close();
            }
        } catch (Exception e) {
            // logger.error("新建文件操作出错" + e.getLocalizedMessage());
            e.printStackTrace();
        }
        return bFlag;
    }

    /**
     * 删除文件
     *
     * @param strFilePath
     * @return
     */
    public static boolean removeFile(String strFilePath) {
        boolean result = false;
        if (strFilePath == null || "".equals(strFilePath)) {
            return result;
        }
        File file = new File(strFilePath);
        if (file.isFile() && file.exists()) {
            result = file.delete();
            if (result == Boolean.TRUE) {
                //   logger.debug("[REMOE_FILE:" + strFilePath + "删除成功!]");
            } else {
                // logger.debug("[REMOE_FILE:" + strFilePath + "删除失败]");
            }
        }
        return result;
    }

    /**
     * 删除文件夹(包括文件夹中的文件内容，文件夹)
     *
     * @param strFolderPath
     * @return
     */
    public static boolean removeFolder(String strFolderPath) {
        boolean bFlag = false;
        try {
            if (strFolderPath == null || "".equals(strFolderPath)) {
                return bFlag;
            }
            File file = new File(strFolderPath.toString());
            bFlag = file.delete();
            if (bFlag == Boolean.TRUE) {
                //  logger.debug("[REMOE_FOLDER:" + file.getPath() + "删除成功!]");
            } else {
                //logger.debug("[REMOE_FOLDER:" + file.getPath() + "删除失败]");
            }
        } catch (Exception e) {
            //logger.error("FLOADER_PATH:" + strFolderPath + "删除文件夹失败!");
            e.printStackTrace();
        }
        return bFlag;
    }

    /**
     * 移除所有文件
     *
     * @param strPath
     */
    public static void removeAllFile(String strPath) {
        File file = new File(strPath);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] fileList = file.list();
        File tempFile = null;
        for (int i = 0; i < fileList.length; i++) {
            if (strPath.endsWith(File.separator)) {
                tempFile = new File(strPath + fileList[i]);
            } else {
                tempFile = new File(strPath + File.separator + fileList[i]);
            }
            if (tempFile.isFile()) {
                tempFile.delete();
            }
            if (tempFile.isDirectory()) {
                removeAllFile(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件   
                removeFolder(strPath + "/" + fileList[i]);// 删除文件夹   
            }
        }
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时   
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件   
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小   
//                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                // logger.debug("[COPY_FILE:" + oldfile.getPath() + "复制文件成功!]");
            }
        } catch (Exception e) {
//            System.out.println("复制单个文件操作出错 ");
            e.printStackTrace();
        }
    }

    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹   
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath
                            + "/ " + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                    //   logger.debug("[COPY_FILE:" + temp.getPath() + "复制文件成功!]");
                }
                if (temp.isDirectory()) {// 如果是子文件夹   
                    copyFolder(oldPath + "/ " + file[i], newPath + "/ "
                            + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错 ");
            e.printStackTrace();
        }
    }

    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        //removeFile(oldPath);   
    }

    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        //removeFolder(oldPath);   
    }

    /**
     * 获取文件名称
     *
     * @param strPath
     * @return
     */
    public static String[] getFileName(String strPath) {
        File file = new File(strPath);
        String[] fileList = null;
        if (!file.exists()) {
            return fileList;
        }
        if (!file.isDirectory()) {
            return fileList;
        }
        fileList = file.list();
        return fileList;
    }

    /**
     * 文件转为String
     *
     * @param in
     * @return
     */
    public static String fileToString(InputStream in) {
        BufferedInputStream br = null;
        byte[] buffer = null;
        try {
            br = new BufferedInputStream(in);
            buffer = new byte[br.available()];
            br.read(buffer, 0, buffer.length);
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return new String(buffer);

    }

    /**
     * 写内容到文件中去，如果没有文件就创建文件，没有文件夹就创建文件夹
     *
     * @param path
     * @param fileName
     * @param content
     */
    public synchronized static void writeStringToFile(String path, String fileName, String content) {
        try {
            File fileDirs = new File(path);
            if (!fileDirs.exists()) {
                fileDirs.mkdirs();
            }
            String filePath = path + "\\" + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            appendFileString(filePath, content);
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<String> readFileContent( String filePath ) {
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {

        //System.out.println(replaceInvalidFileChars("http://www.abc.com/"));


//        listFile("/res");
        String path = "D:\\Workspaces\\ContactMe\\res\\update\\aaa";
        String fileName = "dish_20140518.sql";
//        appendFileString( path,"content" );
        String content = "content";
        writeStringToFile(path, fileName, content);
        writeStringToFile(path, fileName, content);
        writeStringToFile(path, fileName, content);
        writeStringToFile(path, fileName, content);


    }
}
