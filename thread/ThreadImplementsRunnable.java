public class ThreadImplementsRunnable implements Runnable{
    public static void main(String args[]){
        Thread thread1 = new ThreadExtendsThread();
        new Thread(thread1).start();
        Thread thread2 = new ThreadExtendsThread();
        new Thread(thread2).start();
        int n = 100;
        while (n>0){
            System.out.println("这是主线程："+Thread.currentThread().getName());
            n--;
        }
    }

    public void run() {
        int n = 100;
        while (n>0){
            System.out.println("这是子线程------"+Thread.currentThread().getName()+"--n:"+n);
            n--;
        }
    }

}
