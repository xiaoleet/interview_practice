import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FooBar6 {
    private static int n = 3;
    private static boolean fooFlag = true;
    public FooBar6(int n) {
    }
    public static void main(String args[]){
        FooBar6 fooBar6 = new FooBar6(n);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable a = ()->{System.out.print("foo");};
        Runnable b = ()->{System.out.print("bar");};
        executorService.submit(
                ()->{
                    try {
                        fooBar6.foo(a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        executorService.submit(
                ()->{
                    try {
                        fooBar6.bar(b);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!fooFlag){
                Thread.yield();
            }
            printFoo.run();
            fooFlag = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (fooFlag){
                Thread.yield();
            }
            printBar.run();
            fooFlag = true;
        }
    }
}