package Application;

public class PrintOdd {

    private static int numToPrint = 1;

    private static Object lock = new Object();

    public static void main(String[] args)
    {
        POThread t1 = new POThread();
        POThread t2 = new POThread();

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();
    }

    public static class POThread extends Thread{
        @Override
        public void run()
        {
            while(true)
            {
                synchronized (lock)
                {
                    if(numToPrint > 100){
                        lock.notifyAll(); // 注意唤醒另一个线程
                        break;
                    }
                    System.out.println(getName() + " " + numToPrint);
                    numToPrint += 2;

                    lock.notifyAll();

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
