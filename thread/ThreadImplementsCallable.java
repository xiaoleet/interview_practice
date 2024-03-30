import java.util.concurrent.*;

public class ThreadImplementsCallable implements Callable<String> {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        Callable<String> callable1 = new ThreadImplementsCallable();
        Callable<String> callable2 = new ThreadImplementsCallable();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future1 = executorService.submit(callable1);
        Future<String> future2 = executorService.submit(callable2);
        int n = 100;
        while (n>0){
            System.out.println("这是主线程："+Thread.currentThread().getName());
            n--;
        }
        System.out.println("future1:"+future1.get());
        System.out.println("future2:"+future2.get());
        executorService.shutdown();

    }

    public String call(){
        int n = 100;
        while (n>0){
            System.out.println("这是子线程------"+Thread.currentThread().getName()+"--n:"+n);
            n--;
        }
        return null;
    }
}
