package WaitAndNotify.Producer;

import WaitAndNotify.Manager.Manager;

public class Producer extends Thread{
    @Override
    public void run()
    {
        while(true)
        {
            synchronized (Manager.lock)
            {
                if(Manager.count == 0){
                    System.out.println("Finish producing");
                    break;
                }
                if(Manager.flag == 1){
                    try {
                        Manager.lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    Manager.flag = 1;
                    Manager.lock.notifyAll();
                    System.out.println("WaitAndNotify.Producer's turn");
                }
            }
        }
    }
}
