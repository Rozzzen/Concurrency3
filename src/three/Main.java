package three;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Resource resource = new Resource("Resource");

        final Writer[] writer = new Writer[1];
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 7; i++) {
                    writer[0] = new Writer(lock, resource);
                    writer[0].start();
                    System.out.println("Writer has been created");
                    Thread.sleep((long) (Math.random() * 1000) + 500);
                }
            } catch (InterruptedException exception) {}
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    if(lock.hasQueuedThread(writer[0])) {
                        System.out.println("---------Waiting for write thread to leave queue---------");
                        writer[0].join();
                        System.out.println("Stopped waiting");
                    }
                    new Reader(lock, resource).start();
                    Thread.sleep(300);
                }
            } catch (InterruptedException ignored) {}
        });
        thread2.start();

    }
}
