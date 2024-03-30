import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FooBar4 {
    private static int n = 3;
    private static boolean fooFlag = true;
    private static Object lock = new Object();
    public FooBar4(int n) {
    }
    public static void main(String args[]){
        FooBar4 fooBar4 = new FooBar4(n);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable a = ()->{System.out.print("foo");};
        Runnable b = ()->{System.out.print("bar");};
        //ExecutorService 是一个线程池，它负责管理一组线程并执行任务。当你调用 ExecutorService 的 submit() 方法时，你提交的是一个任务（通常是实现了 Runnable 或 Callable 接口的对象），而不是一个线程对象。ExecutorService 会负责在内部管理线程池中的线程，并将任务分配给这些线程来执行。
        //因此，通常情况下，你不需要显式地创建线程对象，然后将它们提交给 ExecutorService。相反，你只需创建实现了 Runnable 接口的任务对象，并直接将它们提交给 ExecutorService。ExecutorService 会根据需要创建线程，并在线程池中重用它们。
//        Thread thread1 = new Thread(()->{
//            try {
//                fooBar4.foo(a);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread thread2 = new Thread(()->{
//            try {
//                fooBar4.bar(b);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        executorService.submit(thread1);
//        executorService.submit(thread2);


        executorService.submit(
                ()->{
                    try {
                        fooBar4.foo(a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        executorService.submit(
                ()->{
                    try {
                        fooBar4.bar(b);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

    }
    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (lock){
            for (int i = 0; i < n; i++) {
                while (!fooFlag){
                    lock.wait();
                }
                printFoo.run();
                fooFlag = false;
                lock.notify();

            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (lock){
            for (int i = 0; i < n; i++) {
                while (fooFlag){
                    lock.wait();
                }
                printBar.run();
                fooFlag = true;
                lock.notify();
            }
        }

    }
}