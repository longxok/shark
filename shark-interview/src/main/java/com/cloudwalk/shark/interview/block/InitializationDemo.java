package com.cloudwalk.shark.interview.block;

/**
 * @author: yuanhao
 * @version: v1.0
 * @description: com.cloudwalk.shark.interview.block
 * @date:2019/12/9
 */
class Grandpa
{
    static
    {
        System.out.println("爷爷在静态代码块");
    }

    public Grandpa() {
        System.out.println("我是爷爷~");
    }
}
class Father extends Grandpa
{
    static
    {
        System.out.println("爸爸在静态代码块");
    }

    public Father()
    {
        System.out.println("我是爸爸~");
    }
}
class Son extends Father
{
    static
    {
        System.out.println("儿子在静态代码块");
    }

    public Son()
    {
        System.out.println("我是儿子~");
    }
}
public class InitializationDemo
{
    public static void main(String[] args)
    {
        new Son();  //入口
    }
}