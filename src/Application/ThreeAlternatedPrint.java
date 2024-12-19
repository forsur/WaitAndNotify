package Application;

public class ThreeAlternatedPrint {

    public static int count = 0;

    public static final Object LOCK = new Object();

    public static int turn = 0;


    public static void main(String[] args)
    {
        Task a = new Task(0);
        Task b = new Task(1);
        Task c = new Task(2);

        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        Thread t3 = new Thread(c);

        t1.start();
        t2.start();
        t3.start();
    }



    public static class Task implements Runnable {

        private int myTurn;

        public Task(int myTurn) {
            this.myTurn = myTurn;
        }

        @Override
        public void run() {
            while(true)
            {
                synchronized (LOCK) {
                    if(count == 101){
                        LOCK.notifyAll(); // 不要忘了
                        break;
                    }
                    if(myTurn == turn){
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                        turn = (turn + 1) % 3;
                        LOCK.notifyAll();
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
