package WaitAndNotify.Consumer;

import WaitAndNotify.Manager.Manager;

public class Consumer extends Thread{

    @Override
    public void run()
    {
        while(true)
        {
            synchronized (Manager.lock)
            {
                if(Manager.count == 0){
                    System.out.println("Finish consuming");
                    break;
                }
                if(Manager.flag == 0) {
                    try {
                        // 使当前线程进入等待状态，直到其他线程调用同一个对象的 notify 或 notifyAll 方法来唤醒它。
                        Manager.lock.wait(); // 放锁
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    Manager.count--;
                    System.out.println("WaitAndNotify.Consumer's turn " + Manager.count + " left ");
                    Manager.flag = 0;
                    // 通知 producer 进入就绪状态，等待锁和 cpu
                    Manager.lock.notifyAll(); // 退出同步代码块后才会放锁
                }
            }
            // 一个线程在调用 notifyAll() 之后，仍然有可能再次抢到执行权。
        }
    }
}
