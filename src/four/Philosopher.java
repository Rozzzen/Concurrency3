package four;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private final String name;
    private final Semaphore semaphore;

    public Philosopher(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(name + " started eating");
            sleep(1000);
            System.out.println(name + " done eating");
            semaphore.release();
        } catch (InterruptedException ignored) {}
    }

}
