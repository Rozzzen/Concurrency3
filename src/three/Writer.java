package three;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Writer extends Thread {

    private final ReentrantReadWriteLock lock;
    private final Resource recourse;
    private final String monitor;

    public Writer(ReentrantReadWriteLock lock, Resource recourse, String monitor) {
        this.lock = lock;
        this.recourse = recourse;
        this.monitor = monitor;
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
            synchronized (monitor) { monitor.notifyAll(); }
        }
        catch(InterruptedException ignored) {}
    }
}
