package four;

public class Main {
    public static void main(String[] args) {
        Fork []fork = new Fork[5];

        for (int i = 0; i < 5; i++) fork[i] = new Fork();

        new Philosopher(fork[0], fork[1], "Socrates").start();
        new Philosopher(fork[1], fork[2], "Platon").start();
        new Philosopher(fork[2], fork[3], "Aristotle").start();
        new Philosopher(fork[3], fork[4], "Homer").start();
        new Philosopher(fork[4], fork[0], "Pythagoras").start();
    }
}
