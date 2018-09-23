package cn.lqw.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 2018/09/23
 * Author:lqw
 *
 * A:先实现InvocationHandler接口创建动态代理类
 * B:重写invoke()方法
 * C:构造方法传递被代理类对象
 * D:method.invoke()参数为被代理类对象
 * E:调用Proxy类的newProxyInstance方法
 * F:参数为(被代理类的类加载器，被代理类的接口，动态代理类)
 */


public class Dynamic {
    public static void main(String[] args) {
        DSeller ds = new DHouseMaker();
        InvocationHandler handler = new DynamicProxy(ds);
        Class dsc = ds.getClass();
        DSeller dp = (DSeller) Proxy.newProxyInstance(dsc.getClassLoader(),dsc.getInterfaces(),handler);
        dp.sell();

        Person star = new Star();
        InvocationHandler handler1 = new DynamicProxy(star);
        Class cls = star.getClass();
        Person agency = (Person)Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),handler1);
        agency.sign();
    }
}

//动态代理类
class DynamicProxy implements InvocationHandler {
    Object obj;
    DynamicProxy(){

    }
    DynamicProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //the object the underlying method is invoked from
        method.invoke(obj,args);
        return null;
    }
}

//定义被代理对象接口
interface DSeller{
    void sell();
}

//实现该接口的被代理类
class DHouseMaker implements DSeller{
    @Override
    public void sell() {
        System.out.println("I sell my house!");
    }
}

interface Person{
    void sign();
}

class Star implements Person{
    @Override
    public void sign() {
        System.out.println("I sign this contract");
    }
}

