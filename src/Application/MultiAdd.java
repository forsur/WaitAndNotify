package Application;


// 100 个线程，每个线程执行100次累加，最后得到 10000

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiAdd {
    private static AtomicInteger result = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(100);

        for(int i=0;i<100;i++)
        {
            threadPool.submit(new Runnable() {
                @Override
                public void run()
                {
                    for(int j=0;j<100;j++)
                    {
                        result.incrementAndGet();
                    }
                }
            });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(result.get());
    }
}
