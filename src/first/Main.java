package first;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer);
        producer.start();
        consumer.start();
        Thread.sleep(10000);
        producer.interrupt();
        consumer.interrupt();
    }
}
