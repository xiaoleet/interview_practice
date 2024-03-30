public class ThreadImplementsRunnable2 {
    private static int n = 100;
    private static Object status = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(ThreadImplementsRunnable2::print);
        Thread thread2 = new Thread(ThreadImplementsRunnable2::print);
        thread1.start();
        thread2.start();
    }

    public static void print(){
        synchronized (status){
            while (n>50){
                System.out.println("我是子线程1---"+Thread.currentThread().getName()+"--n:"+n);
                n--;
            }
            status.notify();
        }
        synchronized (status){
            while (n>0){
                System.out.println("我是子线程2---"+Thread.currentThread().getName()+"--n:"+n);
                n--;
            }
        }

    }
}
