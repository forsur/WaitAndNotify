package WaitAndNotify.Manager;

public class Manager {
    // 1 : consumer 执行
    // 0 : producer 执行

    public static int flag = 0;

    public static int count = 10;

    public static Object lock = new Object(); // Object 类实现了 wait() 方法
}
