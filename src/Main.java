// 存在 while(true) 循环，不会出现早期通知的问题

import Consumer.Consumer;
import Producer.Producer;

public class Main {
    public static void main(String[] args) {
        Producer pr = new Producer();
        Consumer co = new Consumer();

        pr.setName("producer");
        co.setName("consumer");

        pr.start();
        co.start();
    }
}