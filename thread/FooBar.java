class FooBar {
    public static int  n  = 2;
    public static Object status = new Object();
    public static boolean flagFoo = true;
    public static void main(String args[]) throws InterruptedException {

        Runnable runnable1 = () -> {
            try {
                for (int i = 0;i< n;i++){
                    // 在 Lambda 表达式中调用 fool 方法
                    FooBar.foo(FooBar::printFoo);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable runnable2 = () -> {
            try {
                for (int i = 0;i< n;i++){
                    // 在 Lambda 表达式中调用 bar 方法
                    FooBar.bar(FooBar::printBar);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start(); // 启动线程1
        thread2.start(); // 启动线程2
    }

    public FooBar(int n) {
        this.n = n;
    }


    public static void foo(Runnable printFoo) throws InterruptedException {
        synchronized (status) {
            while (!flagFoo) {
                status.wait(); // 等待 bar 方法通知
            }
            printFoo.run();
            flagFoo = false;
            status.notify(); // 通知 bar 方法
        }
    }

    public static void bar(Runnable printBar) throws InterruptedException {
        synchronized (status) {
            while (flagFoo) {
                status.wait(); // 等待 foo 方法通知
            }
            printBar.run();
            flagFoo = true;
            status.notify(); // 通知 foo 方法
        }
    }

    public static void printFoo(){
        System.out.print("foo");
    }
    public static void printBar(){
        System.out.print("bar");
    }
}
