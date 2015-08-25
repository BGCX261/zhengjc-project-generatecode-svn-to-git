/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.aop;

/**
 *
 * @author Administrator
 */
public class Client {

    public static void main(String args[]) {
        DynamicProxyHandler handler = new DynamicProxyHandler();
        IBusiness business = new Business();
        business = (IBusiness) handler.bind(business);
        business.doSomething();
//        System.out.println( business.getClass().getClassLoader() );
//        System.out.println( business.getClass().getInterfaces() );
        
        
        
    }
}
