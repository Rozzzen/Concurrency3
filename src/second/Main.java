package second;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore readLock = new Semaphore(10);
        Semaphore writeLock = new Semaphore(1);
        Resource resource = new Resource("Resource");
        String monitor = "Monitor";

        for (int i = 0; i < 10; i++) {
            new Reader(readLock, writeLock, resource, monitor).start();
            if(i == 5) new Writer(readLock, writeLock, resource, monitor).start();
        }
        Thread.sleep(10000);
        System.out.println("new age of writers");
        new Writer(readLock, writeLock, resource, monitor).start();
        new Reader(readLock, writeLock, resource, monitor).start();
    }
}
