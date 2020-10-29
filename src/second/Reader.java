package second;

import java.util.concurrent.Semaphore;

public class Reader extends Thread {

    private final Semaphore readLock;
    private final Semaphore writeLock;
    private final Resource recourse;
    private final String monitor;

    public Reader(Semaphore readLock, Semaphore writeLock, Resource recourse, String monitor) {
        this.readLock = readLock;
        this.writeLock = writeLock;
        this.recourse = recourse;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            readLock.acquire();
            System.out.println("READING: " + this + "started reading: " + recourse);
            sleep(500);
            readLock.release();
            synchronized (monitor) { if(readLock.availablePermits() == 10) monitor.notifyAll(); }
            System.out.println("READING: " + this + "finished reading: " + recourse);
        }
        catch (InterruptedException ignored) {}
    }
}
