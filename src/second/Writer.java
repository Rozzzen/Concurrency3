package second;

import java.util.concurrent.Semaphore;

public class Writer extends Thread {

    private final Semaphore readLock;
    private final Semaphore writeLock;
    private final Resource recourse;
    private final String monitor;

    public Writer(Semaphore readLock, Semaphore writeLock, Resource recourse, String monitor) {
        this.readLock = readLock;
        this.writeLock = writeLock;
        this.recourse = recourse;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            synchronized (monitor) { if(readLock.availablePermits() != 10) monitor.wait(); }
            writeLock.acquire();
            readLock.acquire(10);
            System.out.println("WRITING: " + this + "started writing: " + recourse);
            sleep(1000);
            recourse.setResource(recourse.getResource() + "*");
            writeLock.release();
            readLock.release(10);
            System.out.println("WRITING: " + this + "finished writing: " + recourse);
        }
        catch(InterruptedException ignored) {}
    }
}
