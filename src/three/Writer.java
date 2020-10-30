package three;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Writer extends Thread {

    private final ReentrantReadWriteLock lock;
    private final Resource recourse;

    public Writer(ReentrantReadWriteLock lock, Resource recourse) {
        this.lock = lock;
        this.recourse = recourse;
    }

    @Override
    public void run() {
        lock.writeLock().lock();
        try {
            System.out.println("WRITING: " + this + "started writing: " + recourse);
            sleep(1000);
            recourse.setResource(recourse.getResource() + "*");
            lock.writeLock().unlock();
            System.out.println("WRITING: " + this + "finished writing: " + recourse);
        }
        catch(InterruptedException ignored) {}
    }
}
