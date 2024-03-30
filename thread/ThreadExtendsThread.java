public class ThreadExtendsThread extends Thread{
    public static void main(String args[]){
        Thread thread1  = new ThreadExtendsThread();
        //ThreadExtendsThread thread1  = new ThreadExtendsThread();
        thread1.start();

        Thread thread2  = new ThreadExtendsThread();
        //ThreadExtendsThread thread2  = new ThreadExtendsThread();
        thread2.start();

        int n = 100;
        while (n>0){
            System.out.println("这是主线程："+Thread.currentThread().getName());
            n--;
        }
    }
    @Override
    public void run (){
        int n = 100;
        while (n>0){
            System.out.println("这是子线程-----"+Thread.currentThread().getName()+"--n:"+n);
            n--;
        }
    }
}
