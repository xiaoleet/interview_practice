public class ThreadStatus extends Thread{
    /**
     * thread状态：
     * 1.就绪（ready）。创建好的线程，调用start()之前，处于这个状态
     * 2.运行（running）。调用start()后，子线程就已进入这个状态
     * 3.阻塞（blocked）。对象锁被另外一个线程持有，等待另外一个线程释放锁，当前线程才能拿到锁，此时当前线程处于阻塞状态
     * 4.等待（waiting）。一个线程需要另外一个线程唤醒（调用notify，或者notifyAll唤醒时），则这个线程处于等待状态。
     * 5.TIMED_WAITING (指定休眠时间)：跟waiting状态多了一个时间，这个状态要等到超时，或者等待被唤醒，才会跳出状态到运行态
     * 6.结束（terminal）。线程shutdown了，执行完成了
     * @param args
     */
    public int n = 100;
    public static void main(String args[]) throws InterruptedException {
        Thread status = new ThreadStatus();//进入reday状态
        //thread1，thread2共享同一个对象锁，这个对象锁时主线程的
        Thread thread1 = new Thread(status);
        Thread thread2 = new Thread(status);
        thread1.start();//启动线程1
        synchronized (status) {
            try {
                status.wait();// 主线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread2.start();//启动线程2
    }
    @Override
    public void run() {
        synchronized (this){
            while (n>50){
                System.out.println("这是子线程1------"+Thread.currentThread().getName()+"--n:"+n);
                n--;
            }
            this.notify();//唤醒主线程
        }
        synchronized (this){
            while (n>0){
                System.out.println("这是子线程2------"+Thread.currentThread().getName()+"--n:"+n);
                n--;
            }
        }
    }
}
