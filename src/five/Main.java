package five;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Reception reception = new Reception();
        Hairdresser hairdresser = new Hairdresser(lock, reception);
        hairdresser.start();
        for (int i = 0; i < 7; i++) {
            sleep((long) (Math.random() * 1000) + 100);
            new Client(lock, reception, hairdresser, "Client#" + i).start();
        }
    }
}
