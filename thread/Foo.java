//leetcode 1114题，保证顺序打印first second thrid
class Foo {
    private   int flag = 1;
    private static Object status = new Object();
    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (status){
            while (flag != 1) status.wait();
            printFirst.run();
            flag = 2;
            status.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (status){
            while (flag != 2) status.wait();
            printSecond.run();
            flag = 3;
            status.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (status){
            while (flag != 3) status.wait();
            printThird.run();
            status.notifyAll();
        }
    }
}