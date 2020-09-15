package cn.magikarpll.k_eleven.callback;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class TestCallback {

}

//这里其实不是回调，是多线程调用，然后主动阻塞请求返回值
class ThreadCallback {
    //这里简单地使用future和callable实现了线程执行完后
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                TimeUnit.SECONDS.sleep(10);
                return "str";
            }
        });
        //手动阻塞调用get通过call方法获得返回值。
        System.out.println("future get:" + future.get());
        System.out.println("continue");
        //需要手动关闭，不然线程池的线程会继续执行。
        executor.shutdown();

        //使用futuretask同时作为线程执行单元和数据请求单元。
        FutureTask<Integer> futureTask = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("future task: dasds");
                return new Random().nextInt();
            }
        });
        new Thread(futureTask).start();
        //阻塞获取返回值
        System.out.println("futureTask");
        System.out.println("futureTask:" + futureTask.get());
    }

    @Test
    public void test () {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
        FutureTask futureTask = new FutureTask(callable);

    }
}

