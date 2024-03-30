import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class FooBar5 {
    private static int n = 3;
    private static boolean fooFlag = true;
    private static Object lock = new Object();
    public FooBar5(int n) {
    }
    public static void main(String args[]){
        FooBar5 fooBar5 = new FooBar5(n);
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        Runnable a = ()->{System.out.print("foo");};
        Runnable b = ()->{System.out.print("bar");};
        forkJoinPool.submit(
                ()->{
                    try {
                        fooBar5.foo(a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        forkJoinPool.submit(
                ()->{
                    try {
                        fooBar5.bar(b);
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
