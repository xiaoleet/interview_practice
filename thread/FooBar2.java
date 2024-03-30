public class FooBar2 {
    private static int n = 2;
    private static boolean flagFoo = true;
    private static Object lock = new Object();
    public FooBar2(int n) {
    }

    public static void main(String[] args) {
        //a，b这两个runnable 实际上在foo，和bar里面执行的是原生的run方法。
        Runnable a = ()->{ System.out.print("foo");};//void 无参数，可隐式转换

        Runnable b = ()->{ System.out.print("bar");};

        FooBar2 fooBar2 = new FooBar2(n);

        new Thread(()->{
            try {
                fooBar2.bar(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                fooBar2.foo(a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }



    public void foo(Runnable printFoo) throws InterruptedException {
        synchronized (lock){
            for (int i = 0; i < n; i++) {
                while (!flagFoo){
                    lock.wait();
                }
                printFoo.run();
                flagFoo = false;
                lock.notify();
            }
        }

    }

    public void bar(Runnable printBar) throws InterruptedException {
        synchronized (lock){
            for (int i = 0; i < n; i++) {
                while (flagFoo){
                    lock.wait();
                }
                printBar.run();
                flagFoo = true;
                lock.notify();
            }
        }
    }
}
