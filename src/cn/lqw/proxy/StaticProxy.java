package cn.lqw.proxy;

/**
 * 静态代理模式
 * 2018/09/23
 * Author:lqw
 *
 * A:创建被代理和代理类的共同接口
 * B:被代理类和代理类分别实现该接口
 * C:代理类重写接口方法只是引用被代理类对象的方法
 */


public class StaticProxy {
    public static void main(String[] args) {
        Seller proxy = new Agency();
        proxy.sell();
    }
}

//定义一个共同的接口
interface Seller{
    void sell();
}

//实现被代理类
class HouseMaker implements Seller{

    @Override
    public void sell() {
        System.out.println("I sell my house!");
    }
}

//实现代理类
class Agency implements Seller{
    private Seller sr ;
    @Override
    public void sell() {
        if(sr == null){
            sr = new HouseMaker();
        }
        sr.sell();
    }
}