package four;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        new Philosopher("Socrates", semaphore).start();
        new Philosopher("Platon", semaphore).start();
        new Philosopher("Aristotle", semaphore).start();
        new Philosopher("Homer", semaphore).start();
        new Philosopher("Pythagoras", semaphore).start();
    }
}
