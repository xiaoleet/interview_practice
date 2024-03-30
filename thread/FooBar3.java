import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class FooBar3 {
    private static int n = 3;
    private BlockingQueue<Integer> blockingQueueBar = new LinkedBlockingQueue<>(1);
    private BlockingQueue<Integer> blockingQueueFoo = new LinkedBlockingQueue<>(1);

    public FooBar3(int n) {
    }
    public static void main(String[] args) {
        //a，b这两个runnable 实际上在foo，和bar里面执行的是原生的run方法。
        Runnable a = ()->{ System.out.print("foo");};//void 无参数，可隐式转换

        Runnable b = ()->{ System.out.print("bar");};

        FooBar3 fooBar3 = new FooBar3(n);
        new Thread(()->{
            try {
                fooBar3.foo(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                fooBar3.bar(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            //System.out.println("foo线程---"+Thread.currentThread().getName());
            //简单来说：等待bar take之后，通知foo可以执行了
            blockingQueueFoo.put(1);//相当于让下一轮的foo阻塞掉
            printFoo.run();
            //简单来说：通知bar 可以执行了，唤醒bar
            blockingQueueBar.put(1);//等printFoo执行完了，再去put blockingQueueBar，这样blockingQueueBar再foo执行完成之前，都是空的，take操作是阻塞的，保证foo执行前，bar都没法执行
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            //System.out.println("bar线程---"+Thread.currentThread().getName());
            //简单来说：等待fool put之后，通知bar可以执行了
            blockingQueueBar.take();//相当于让下一轮的bar阻塞掉
            printBar.run();
            //简单来说：通知foo 可以执行了，唤醒foo
            blockingQueueFoo.take();//等printBar执行完了，再去take blockingQueueFoo，这样blockingQueueFoo再bar执行完成之前，都是有1个元素的，put操作是阻塞的，保证bar执行前，foo都没法执行
        }
    }
}
