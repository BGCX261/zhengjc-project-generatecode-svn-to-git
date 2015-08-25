/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *BASE64加密解密
 * @author Administrator
 */
public class BASE64 {
    /**    
     * BASE64解密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }               
                  
    /**         
     * BASE64加密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(key);               
    }       
         
    public static void main(String[] args) throws Exception     
    {     
         String sql = "insert into company ( [company],[status] ) values ( 'z1','1' )";
         
//        String data = BASE64.encryptBASE64("http://aub.iteye.com/".getBytes());     
        String data = BASE64.encryptBASE64(sql.getBytes());     
        System.out.println("加密前："+data);     
        data = "aW5zZXJ0IGludG8gQ29tcGFueSAoICBbY29tcGFueV0sICBbc3RhdHVzXSApIHZhbHVlcyAoICfpgJrorq8nLCAnMScgKSA=";
        byte[] byteArray = BASE64.decryptBASE64(data);     
        System.out.println("解密后："+new String(byteArray));     
    } 
}
