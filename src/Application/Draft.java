package Application;

import java.util.*;

public class Draft {
    public static void main(String[] args) throws InterruptedException {
        DraftRun runnable1 = new DraftRun();
        DraftRun runnable2 = new DraftRun();

        Thread t1 = new Thread(runnable1, "t1");
        Thread t2 = new Thread(runnable2, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        if(runnable1.maxPrize > runnable2.maxPrize){
            System.out.println(t1.getName() + " win!");
        }else{
            System.out.println(t2.getName() + " win!");
        }

    }


    public static class DraftRun implements Runnable{
        static List<Integer> prizes = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 2, 80, 300, 700, 800));
        static Object lock = new Object();

        List<Integer> curPrizes = new ArrayList<>();
        int maxPrize = 0;

        @Override
        public void run()
        {
            while(true)
            {
                synchronized (lock)
                {
                    if(!prizes.isEmpty()){
                        Collections.shuffle(prizes);
                        int x = prizes.remove(prizes.size() - 1);
                        curPrizes.add(x);
                        maxPrize = Math.max(x, maxPrize);
                        System.out.println(Thread.currentThread().getName() + ": " + x);
                    }else{
                        break;
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "'s prizes = " + curPrizes);
        }
    }
}


