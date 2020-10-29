package five;

import java.util.concurrent.locks.ReentrantLock;

public class Hairdresser extends Thread{

    private final Reception reception;
    private final ReentrantLock lock;
    private boolean sleeping;

    public Hairdresser(ReentrantLock lock, Reception reception) {
        this.lock = lock;
        this.reception = reception;
    }

    public boolean isEmptyReception() {
        return reception.isEmpty();
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public void sleep() throws InterruptedException {
        System.out.println("Hairdresser went to sleep");
        sleeping = true;
        lock.unlock();
        sleep(1000000);
    }

    public boolean isSleeping() {
        return sleeping;
    }

    public void makeHaircut(Client client) throws InterruptedException {
        System.out.println("making haircut for: " + client.getClientName());
        sleep(1000);
        System.out.println("finished making haircut for: " + client.getClientName());
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                if (isEmptyReception()) sleep();
                else {
                    lock.unlock();
                    makeHaircut(reception.remove());
                }
            }
            catch (InterruptedException ignored) {}
        }
    }
}