package five;

import java.util.concurrent.locks.ReentrantLock;

public class Client extends Thread{

    private final ReentrantLock lock;
    private final Reception reception;
    private final Hairdresser hairdresser;
    private final String name;


    public String getClientName() {
        return name;
    }

    public Client(ReentrantLock lock, Reception reception, Hairdresser hairdresser, String name) {
        this.lock = lock;
        this.reception = reception;
        this.hairdresser = hairdresser;
        this.name = name;
    }

    public boolean isSleepingHairdresser() {
        return hairdresser.isSleeping();
    }

    public void wakeUpHairdresser() throws InterruptedException {
        synchronized (hairdresser) {
            hairdresser.setSleeping(false);
            hairdresser.notifyAll();
            hairdresser.makeHaircut(this);
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            if (isSleepingHairdresser()) {
                System.out.println(getClientName() + " woke up the hairdresser");
                lock.unlock();
                wakeUpHairdresser();
            }
            else {
                System.out.println(getClientName() + " has been added to reception");
                lock.unlock();
                reception.add(this);
            }
        }
        catch (InterruptedException ignored) {}
    }
}
