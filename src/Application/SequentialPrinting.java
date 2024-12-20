package Application;

public class SequentialPrinting {
    private static int count = 0;

    private static int turn = 0;

    public static Object LOCK = new Object();


    public static void main(String[] args)
    {
        Thread t1 = new Thread(() -> printer('a', 0));
        Thread t2 = new Thread(() -> printer('b', 1));
        Thread t3 = new Thread(() -> printer('c', 2));

        t1.start();
        t2.start();
        t3.start();
    }

    // norm
    public static void printer(char c, int t) {
        while(true)
        {
            synchronized (LOCK) {
                if(count == 30){
                    LOCK.notifyAll();
                    break;
                }

                if(turn != t){
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{ // 此处需要 else，防止被唤醒后继续执行
                    System.out.println(Thread.currentThread().getName() + " " + c);
                    turn = (turn + 1) % 3;
                    count++;
                    LOCK.notifyAll();
                }
            }
        }
    }

}
