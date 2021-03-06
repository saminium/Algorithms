package exercise;

/**
 * Created by szeru on 3/18/2019
 */
public class DeadLockExample {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new ThreadDemo1();
        Thread t2 = new ThreadDemo2();
        t1.start();
        t2.start();

    }

    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }

                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (lock2) {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }


                }
            }

        }
    }

    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (lock2){
                System.out.println("Thread 2: Holding lock 2...");
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){}

                System.out.println("Thread 2: Holding lock 1...");

                synchronized (lock1){
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){

                    }
                }
            }
        }
    }
}

