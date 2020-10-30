package three;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Reader extends Thread {

    private final ReentrantReadWriteLock lock;
    private final ReentrantLock synchLock;
    private final Resource recourse;
    private final String monitor;

    public Reader(ReentrantReadWriteLock lock, ReentrantLock synchLock, Resource recourse, String monitor) {
        this.lock = lock;
        this.recourse = recourse;
        this.monitor = monitor;
        this.synchLock = synchLock;
    }

    @Override
    public void run() {
        lock.readLock().lock();
        try {
            System.out.println("READING: " + this + "started reading: " + recourse);
            sleep(500);
            lock.readLock().unlock();
            System.out.println("READING: " + this + "finished reading: " + recourse);
        }
        catch (InterruptedException ignored) {}
    }
}
