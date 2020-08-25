package cn.magikarpll.f_six.abstract_inter;

@FunctionalInterface
public interface TestInterface {

    //接口中的属性会被隐式指定为public final static
    Integer i = 1;

    //接口中的方法会被隐式指定为public abstract
    void test2();

    //接口中的方法会被隐式指定为public abstract
    default void test(){
        System.out.println("ddd");
    }

    //jdk1.8后，接口中的静态方法可以有方法体，且必须有方法体
    static void testStatic(){

    }

}


