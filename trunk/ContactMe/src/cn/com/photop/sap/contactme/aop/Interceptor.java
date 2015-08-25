/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.aop;

import cn.com.photop.sap.contactme.common.CommonSession;
import cn.com.photop.sap.contactme.thread.UpdateDataThread;
import cn.com.photop.sap.contactme.thread.UploadDataThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 拦截器
 *
 * @author Administrator
 */
public class Interceptor {
    private Log logger = LogFactory.getLog(getClass());
    
    public void before() {
//        System.out.println("拦截器InterceptorClass方法调用:before()!");
    }

    public void before(String str) {
        
    }

    public void after() {
//        System.out.println("拦截器InterceptorClass方法调用:after()!");
    }

    public void after(String str) {
//        logger.info( str );
        if ( CommonSession.getLocal_flg() ) {
            new UpdateDataThread( str ).start();    
        }
        if ( CommonSession.getFtp_flg() ) {
            new UploadDataThread().start();
        }
        
    }
}
