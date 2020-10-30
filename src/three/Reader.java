package three;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Reader extends Thread {

    private final ReentrantReadWriteLock lock;
    private final Resource recourse;

    public Reader(ReentrantReadWriteLock lock, Resource recourse) {
        this.lock = lock;
        this.recourse = recourse;
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
