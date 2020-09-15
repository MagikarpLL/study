package cn.magikarpll.j_ten.test_exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestException {
}


//错误即error一般指jvm无法处理的错误
//异常是Java定义的用于简化错误处理流程和定位错误的一种工具。
class ExceptionType {
    Error error = new Error();

    public static void main(String[] args) {
        throw new Error();
    }

    //下面这四个异常或者错误有着不同的处理方法
    public void error1 (){
        //编译期要求必须处理，因为这个异常是最顶层异常，包括了检查异常，必须要处理
        try {
            throw new Throwable();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    //Exception也必须处理。否则报错，因为检查异常都继承自exception，所以默认需要捕捉。
    public void error2 (){
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //非检查异常，不用处理
        throw new ArithmeticException();
    }

    //error可以不处理，编译不报错,原因是虚拟机根本无法处理，所以啥都不用做
    public void error3 (){
        throw new Error();
    }

    //runtimeexception众所周知编译不会报错
    public void error4 (){
        throw new RuntimeException();
    }
//    Exception in thread "main" java.lang.Error
//    at com.javase.异常.错误.main(错误.java:11)

}


//try catch finallt
class ExceptionHadle {
    Error error = new Error();

    public static void main(String[] args) {
        throw new Error();
    }

    //测试异常匹配机制， FileNotFound io
    /**
     * 如果把父类异常放在前面，则子类异常一定会报错： 该异常已经被处理。
     * 因此异常的顺序一定是从小到大，一但匹配处理后，如果不抛出新的异常，则停止异常链的执行
     *
     * 同时如果代码未进入try，则finally中的代码也不会被执行；只要进入到try，则不管抛没抛出异常，finally一定会被执行,除非执行了System.exit()
     * 同时一个try最少要跟一个catch或者一个finally
     */
    public void testSubException(){
        try{
            throw new FileNotFoundException();
        }catch (FileNotFoundException fileNotFoundException){

        }catch (IOException ioException){

        }
//        catch (FileNotFoundException fileNotFoundException){
//
//        }
        finally {

        }

        try{
            System.out.println("tt");
        }finally {
            System.out.println("tt");
        }

        try{
            System.out.println("tt");
        }catch (Exception e){
            System.out.println("tt");
        }
    }

    public void testReturn(){




    }

}

class TestFinally {
    public static void main(String[] args) throws Throwable {
        try {
            System.out.println("try");
            throw new IllegalAccessException();
        }catch (IllegalAccessException e) {
            System.out.println("catch");
            //throw new Throwable();
            //此时如果再抛异常，catch无法执行，只能报错。
            //finally无论何时都会执行
            //除非我显示调用下面的代码。此时finally才不会执行
            System.exit(0);

        }finally {
            System.out.println("算你狠");
        }

    }
}

/**
 * 这个完美解决了我开发时的问题，最近开发就感觉异常处理很乱。顶层异常会吃掉底层异常，最后导致异常信息根本不准
 */
class ExceptionChain{
    public static void main(String[] args) throws Exception {
        C();
    }

    public static void A()throws Exception{
        try{
            throw new Exception("this is origin exception");
        }catch (Exception e){
            System.out.println("catch a");
            throw new Exception("a exception", e);
        }finally {
            System.out.println("a finally");
        }
    }

    public static void B()throws Exception{
        try{
            A();
        }catch (Exception e){
            System.out.println("catch b");
            throw new Exception("b exception", e);
        }finally {
            System.out.println("b finally");
        }
    }

    public static void C()throws Exception{
        try{
            B();
        }catch (Exception e){
            System.out.println("catch c");
            throw new Exception("c exception", e);
        }finally {
            System.out.println("c finally");
        }
    }


}

//同时java中的异常是线程独立的，某个线程的异常仅仅会影响那条线程，而不会影响其他线程
class ThreadException {

    public static void main(String[] args) {
        go();
    }

    public static void go () {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0;i <= 2;i ++) {
            int finalI = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                //每个线程抛出异常时并不会影响其他线程的继续执行
                public void run() {
                    try {
                        System.out.println("start thread" + finalI);
                        throw new Exception();
                    }catch (Exception e) {
                        System.out.println("thread" + finalI + " go wrong");
                    }
                }
            });
        }
//        结果：
//        start thread0
//        thread0 go wrong
//        start thread1
//        thread1 go wrong
//        start thread2
//        thread2 go wrong
    }
}

/**
 * try…catch…finally中的return 只要能执行，就都执行了，
 * 他们共同向同一个内存地址（假设地址是0×80）写入返回值，
 * 后执行的将覆盖先执行的数据，而真正被调用者取的返回值就是最后一次写入的。
 *
 * 同理finally中抛出的异常也会覆盖前面的，只要能抛出，以最后一次抛出为值
 */

//finally中的return 会覆盖 try 或者catch中的返回值。
class finallyReturnValue{
    public static void main(String[] args)
    {
        int result;

        result  =  foo();
        System.out.println(result);     /////////2

        result = bar();
        System.out.println(result);    /////////2
    }

    @SuppressWarnings("finally")
    public static int foo()
    {
        try{
        int a = 5 / 0;
    } catch (Exception e){
        return 1;
    } finally{
        return 2;
    }

    }

    @SuppressWarnings("finally")
    public static int bar()
    {
        try {
            return 1;
        }finally {
            return 2;
        }
    }
}

//finally中的return会抑制（消灭）前面try或者catch块中的异常
class TestFinallyReturn {
    public static void main(String[] args)
    {
        int result;
        try{
            result = foo();
            System.out.println(result);           //输出100
        } catch (Exception e){
            System.out.println(e.getMessage());    //没有捕获到异常
        }

        try{
            result  = bar();
            System.out.println(result);           //输出100
        } catch (Exception e){
            System.out.println(e.getMessage());    //没有捕获到异常
        }
    }

    //catch中的异常被抑制
    @SuppressWarnings("finally")
    public static int foo() throws Exception
    {
        try {
            int a = 5/0;
            return 1;
        }catch(ArithmeticException amExp) {
            throw new Exception("我将被忽略，因为下面的finally中使用了return");
        }finally {
            return 100;
        }
    }

    //try中的异常被抑制
    @SuppressWarnings("finally")
    public static int bar() throws Exception
    {
        try {
            int a = 5/0;
            return 1;
        }finally {
            return 100;
        }
    }
}

//finally中的异常会覆盖（消灭）前面try或者catch中的异常
class TestFinanllyException {
    public static void main(String[] args)
    {
        int result;
        try{
            result = foo();
        } catch (Exception e){
            System.out.println(e.getMessage());    //输出：我是finaly中的Exception
        }

        try{
            result  = bar();
        } catch (Exception e){
            System.out.println(e.getMessage());    //输出：我是finaly中的Exception
        }
    }

    //catch中的异常被抑制
    @SuppressWarnings("finally")
    public static int foo() throws Exception
    {
        try {
            int a = 5/0;
            return 1;
        }catch(ArithmeticException amExp) {
            throw new Exception("我将被忽略，因为下面的finally中抛出了新的异常");
        }finally {
            throw new Exception("我是finaly中的Exception");
        }
    }

    //try中的异常被抑制
    @SuppressWarnings("finally")
    public static int bar() throws Exception
    {
        try {
            int a = 5/0;
            return 1;
        }finally {
            throw new Exception("我是finaly中的Exception");
        }

    }
}

class JVM7Exception{

    public static void main(String[] args)  {
        try(InputStream inputStream = new FileInputStream("test");
            InputStream inputStream2 = new FileInputStream("test2");){

        }catch (IOException | NullPointerException | ArithmeticException exception){

        }
    }

}