/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.photop.sap.contactme.aop;

import cn.com.photop.sap.contactme.util.StringUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理处理器工具
 *
 * @author Administrator
 */
public class DynamicProxyHandler implements InvocationHandler {

    private Object business; //被代理对象
    private Interceptor interceptor = new Interceptor(); //拦截器

    /**
     * 动态生成一个代理类对象,并绑定被代理类和代理处理器
     *
     * @param business
     * @return 代理类对象
     */
    public Object bind(Object business) {
        this.business = business;
        //参数1 被代理类的ClassLoader,
        //参数2 要被代理的接口,本方法返回对象会自动声称实现了这些接口
        //参数3 代理处理器对象
        Object object = Proxy.newProxyInstance(business.getClass().getClassLoader(), business.getClass().getInterfaces(), this);
        return object;
    }

    /**
     * 代理要调用的方法,并在方法调用前后调用连接器的方法.
     *
     * @param proxy 代理类对象
     * @param method 被代理的接口方法
     * @param args 被代理接口方法的参数
     * @return 方法调用返回的结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        
        interceptor.before();

        result = method.invoke(business, args);
        
        interceptor.after();
        
        if (args != null && args.length > 0) {
            String str = (String) args[0];
            if (StringUtil.isNotNull(str)) {
                interceptor.after(str);
            }
        }
        return result; //To change body of implemented methods use File | Settings | File Templates.
    }
}
