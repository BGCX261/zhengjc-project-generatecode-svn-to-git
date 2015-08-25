/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.util;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * ftp上传，下载
 *
 * @author Administrator
 */
public class FtpUtil {

    private static final Log log = LogFactory.getLog(FtpUtil.class);
    private String ip = "";
    private String username = "";
    private String password = "";
    private int port = -1;
    private String path = "";
    FtpClient ftpClient = null;
    OutputStream os = null;
    FileInputStream is = null;

    public FtpUtil(String serverIP, String username, String password) {
        this.ip = serverIP;
        this.username = username;
        this.password = password;
    }

    public FtpUtil(String serverIP, int port, String username, String password) {
        this.ip = serverIP;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * 连接ftp服务器
     *
     * @throws IOException
     */
    public boolean connectServer() {
        ftpClient = new FtpClient();
        try {
            if (this.port != -1) {
                ftpClient.openServer(this.ip, this.port);
            } else {
                ftpClient.openServer(this.ip);
            }
            ftpClient.login(this.username, this.password);
            if (this.path.length() != 0) {
                ftpClient.cd(this.path);// path是ftp服务下主目录的子目录
            }
            ftpClient.binary();// 用2进制上传、下载
//            System.out.println("已登录到\"" + ftpClient.pwd() + "\"目录");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 断开与ftp服务器连接
     *
     * @throws IOException
     */
    public boolean closeServer() {
        try {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            if (ftpClient != null) {
                ftpClient.closeServer();
            }
//            System.out.println("已从服务器断开");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 检查文件夹在当前目录下是否存在
     *
     * @param dir
     * @return
     */
    private boolean isDirExist(String dir) {
        String pwd = "";
        try {
            pwd = ftpClient.pwd();
            ftpClient.cd(dir);
            ftpClient.cd(pwd);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 在当前目录下创建文件夹
     *
     * @param dir
     * @return
     * @throws Exception
     */
    private boolean createDir(String dir) {
        try {
            ftpClient.ascii();
            StringTokenizer s = new StringTokenizer(dir, "/"); //sign
            s.countTokens();
            String pathName = ftpClient.pwd();
            while (s.hasMoreElements()) {
                pathName = pathName + "/" + (String) s.nextElement();
                try {
                    ftpClient.sendServer("MKD " + pathName + "\r\n");
                } catch (Exception e) {
                    e = null;
                    return false;
                }
                ftpClient.readServerResponse();
            }
            ftpClient.binary();
            return true;
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    /**
     * ftp上传 如果服务器段已存在名为filename的文件夹，
     * 该文件夹中与要上传的文件夹中同名的文件将被替换
     *
     * @param filename 要上传的文件（或文件夹）名
     * @return
     * @throws Exception
     */
    public boolean upload(String filename) {
        String newname = "";
        if (filename.indexOf("/") > -1) {
            newname = filename.substring(filename.lastIndexOf("/") + 1);
        } else {
            newname = filename;
        }
        return upload(filename, newname);
    }

    /**
     * ftp上传 如果服务器段已存在名为newName的文件夹，
     * 该文件夹中与要上传的文件夹中同名的文件将被替换
     *
     * @param fileName 要上传的文件（或文件夹）名
     * @param newName 服务器段要生成的文件（或文件夹）名
     * @return
     */
    public boolean upload(String fileName, String newName) {
        try {
//            String savefilename = new String(fileName.getBytes("ISO-8859-1"), "GBK");
            String savefilename = fileName;
            File file_in = new File(savefilename);//打开本地待长传的文件
            if (!file_in.exists()) {
                throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
            }
            if (file_in.isDirectory()) {
                upload(file_in.getPath(), newName, ftpClient.pwd());
            } else {
                uploadFile(file_in.getPath(), newName);
            }

            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception e in Ftp upload(): " + e.toString());
            return false;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 真正用于上传的方法
     *
     * @param fileName
     * @param newName
     * @param path
     * @throws Exception
     */
    private void upload(String fileName, String newName, String path) throws Exception {
        String savefilename = new String(fileName.getBytes("ISO-8859-1"), "GBK");
        File file_in = new File(savefilename);//打开本地待长传的文件
        if (!file_in.exists()) {
            throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
        }
        if (file_in.isDirectory()) {
            if (!isDirExist(newName)) {
                createDir(newName);
            }
            ftpClient.cd(newName);
            File sourceFile[] = file_in.listFiles();
            for (int i = 0; i < sourceFile.length; i++) {
                if (!sourceFile[i].exists()) {
                    continue;
                }
                if (sourceFile[i].isDirectory()) {
                    this.upload(sourceFile[i].getPath(), sourceFile[i].getName(), path + "/" + newName);
                } else {
                    this.uploadFile(sourceFile[i].getPath(), sourceFile[i].getName());
                }
            }
        } else {
            uploadFile(file_in.getPath(), newName);
        }
        ftpClient.cd(path);
    }

    /**
     * upload 上传文件
     *
     * @param filename 要上传的文件名
     * @param newname 上传后的新文件名
     * @return -1 文件不存在 >=0 成功上传，返回文件的大小
     * @throws Exception
     */
    public long uploadFile(String filename, String newname) throws Exception {
        long result = 0;
        TelnetOutputStream os = null;
        FileInputStream is = null;
        try {
            java.io.File file_in = new java.io.File(filename);
            if (!file_in.exists()) {
                return -1;
            }
            os = ftpClient.put(newname);
            result = file_in.length();
            is = new FileInputStream(file_in);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
            }
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
        return result;
    }

    /**
     * 从ftp下载文件到本地
     *
     * @param filename 服务器上的文件名
     * @param newfilename 本地生成的文件名
     * @return
     * @throws Exception
     */
    public long downloadFile(String filename, String newfilename) {
        long result = 0;
        TelnetInputStream is = null;
        FileOutputStream os = null;
        try {
            is = ftpClient.get(filename);
            java.io.File outfile = new java.io.File(newfilename);
            os = new FileOutputStream(outfile);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
                result = result + c;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 取得相对于当前连接目录的某个目录下所有文件列表
     *
     * @param path
     * @return
     */
    public List getFileList(String path) {
        List list = new ArrayList();
        DataInputStream dis;
        try {
            dis = new DataInputStream(ftpClient.nameList(this.path + path));
            String filename = "";
            while ((filename = dis.readLine()) != null) {
                list.add(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FtpUtil ftp = new FtpUtil("133.224.202.2", "tstbill", "tstbill");
        ftp.connectServer();
        boolean result = ftp.upload("C:/test_why", "test_why/test");
        System.out.println(result ? "上传成功！" : "上传失败！");
        List list = ftp.getFileList("test_why/test");
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).toString();
            System.out.println(name);
        }
        ftp.closeServer();
        /**
         * FTP远程命令列表 USER PORT RETR ALLO DELE SITE XMKD CDUP FEAT PASS PASV STOR
         * REST CWD STAT RMD XCUP OPTS ACCT TYPE APPE RNFR XCWD HELP XRMD STOU
         * AUTH REIN STRU SMNT RNTO LIST NOOP PWD SIZE PBSZ QUIT MODE SYST ABOR
         * NLST MKD XPWD MDTM PROT
         * 在服务器上执行命令,如果用sendServer来执行远程命令(不能执行本地FTP命令)的话，所有FTP命令都要加上\r\n
         * ftpclient.sendServer("XMKD /test/bb\r\n"); //执行服务器上的FTP命令
         * ftpclient.readServerResponse一定要在sendServer后调用
         * nameList("/test")获取指目录下的文件列表 XMKD建立目录，当目录存在的情况下再次创建目录时报错 XRMD删除目录
         * DELE删除文件
         */
    }

    public static int download(String ip, int port, String userName, String password, FileFilter filter, String localFilePath) throws SocketException, IOException {
        return download(ip, port, userName, password, null, filter, localFilePath);
    }

    public static int download(String ip, int port, String userName, String password, String ftpPath, FileFilter filter, String localFilePath) throws SocketException, IOException {
        FTPClient ftp = new FTPClient();
        int i = 0;
        try {
            // 不为空连接 通过IP 和端口
            ftp.connect(ip, port);
            // 登陆
            if (ftp.login(userName, password)) {
                ftp.enterLocalPassiveMode();
                // 获取FTP登陆目录下的所有文件
                if (ftpPath != null) {
                    if (!ftp.changeWorkingDirectory(ftpPath)) {
                        throw new RuntimeException("找不到该目录:" + ftpPath);
                    }
                }
                FTPFile[] files = ftp.listFiles();
                for (FTPFile file : files) {
                    String fileName = file.getName();
                    File file1 = new File(fileName);
                    if (filter.accept(file1)) {
                        BufferedOutputStream out = null;
                        try {
                            // IO流下载文件到本地
                            out = new BufferedOutputStream(
                                    new FileOutputStream(new File(localFilePath, fileName)));
                            // 开始下载
                            ftp.retrieveFile(file.getName(), out);
                            log.info("下载文件:" + file.getName() + "到本地路径:"
                                    + localFilePath);
                            i++;
                        } finally {
                            try {
                                if (out != null) {
                                    out.close();
                                }
                            } catch (Exception e) {
                                log.error("", e);
                            }
                        }
                    }
                }
            } else {
                i = -1;
            }
        } finally {
            if (ftp != null && ftp.isConnected()) {
                try {
                    log.info("关闭ftp连接");
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return i;
    }

    public static int download(String ip, int port, String userName, String password, String ftpPath, final String fileName, String localFilePath) throws SocketException, IOException {


        return download(ip, port, userName, password, ftpPath, new FileFilter() {

            public boolean accept(String fileName1) {
                
                return fileName.equalsIgnoreCase(fileName1);
            }

            @Override
            public boolean accept(File pathname) {
//                String fileName1 = pathname.getName();
//                return fileName.equalsIgnoreCase(fileName1);
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }, localFilePath);

    }

    public static int download(String ip, int port, String userName, String password, final String fileName, String localFilePath) throws SocketException, IOException {
        return download(ip, port, userName, password, null, fileName, localFilePath);
    }

    /**
     * ftp上传文件至服务器
     *
     * @throws SocketException
     * @throws IOException
     */
    public static void uploadFile(String ip, int port, String userName, String password, String localFile)
            throws SocketException, IOException {
        log.info("上传本地文件: " + localFile);
        File file = new File(localFile);
        InputStream in = null;
        FTPClient ftpClient = null;
        if (file != null) {
            try {
                ftpClient = new FTPClient();
                in = new FileInputStream(file);
                ftpClient.connect(ip, port);
                if (ftpClient.login(userName, password)) {
                    ftpClient.enterLocalPassiveMode();
                    boolean flag = ftpClient.appendFile(file.getName(), in);
                    log.info("上传文件成功:" + flag);
                }
            } catch (SocketException e) {
                log.error("ftp上传文件失败:", e);
                throw e;
            } catch (IOException e) {
                log.error("ftp上传文件失败:", e);
                throw e;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ftpClient != null) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void upload(String ip, int port, String userName, String password, String path, String remotePath)
            throws SocketException, IOException {
        upload(ip, port, userName, password, new File(path), remotePath);
    }

    /**
     * ftp上传文件至服务器
     *
     * @throws SocketException
     * @throws IOException
     */
    public static void upload(String ip, int port, String userName, String password, File localFile, String remotePath)
            throws SocketException, IOException {
        log.info("上传本地文件: " + localFile.getName());
        InputStream in = null;
        FTPClient ftpClient = null;
        if (localFile != null) {
            try {
                ftpClient = new FTPClient();
                in = new FileInputStream(localFile);
                ftpClient.connect(ip, port);
                if (ftpClient.login(userName, password)) {
                    ftpClient.enterLocalPassiveMode();
                    if (remotePath != null) {
                        if (!ftpClient.changeWorkingDirectory(remotePath)) {
                            ftpClient.makeDirectory(remotePath);
                            ftpClient.changeWorkingDirectory(remotePath);
                        }
                    }
                    boolean flag = ftpClient.storeFile(localFile.getName(), in);
                    log.info("上传文件成功:" + flag);
                }
            } catch (SocketException e) {
                log.error("ftp上传文件失败:", e);
                throw e;
            } catch (IOException e) {
                log.error("ftp上传文件失败:", e);
                throw e;
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (ftpClient != null) {
                    try {
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
