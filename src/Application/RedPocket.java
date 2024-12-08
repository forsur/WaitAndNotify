package Application;

import java.util.concurrent.SynchronousQueue;

public class RedPocket {

    public static void main(String[] args)
    {
        Grab runnable = new Grab();

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        Thread t3 = new Thread(runnable, "t3");
        Thread t4 = new Thread(runnable, "t4");
        Thread t5 = new Thread(runnable, "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    public static class Grab implements Runnable{
        private static int tot = 3;
        private static int money = 100;

        private static Object lock = new Object();

        @Override
        public void run()
        {
            synchronized (lock)
            {
                if(tot > 0){
                    tot--;
                    System.out.println(Thread.currentThread().getName() + "：我抢到红包了！");
                }else{
                    System.out.println(Thread.currentThread().getName() + "：我没抢到");
                }
            }
        }
    }
}
