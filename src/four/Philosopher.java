package four;

public class Philosopher extends Thread {

    private final String name;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(Fork leftFork, Fork rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            think();
            doEat();
        }
    }

    private void think() {
        System.out.println(name + " is thinking");
        try {
            sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    private void doEat() {
        if(leftFork.pickUp()) {
            if (rightFork.pickUp()) {
                eat();
                leftFork.putDown();
                rightFork.putDown();
            } else {
                leftFork.putDown();
                doEat();
            }
        }
        else {
            doEat();
        }
    }

    public void eat() {
        System.out.println(name + " is eating");
        try {
            sleep(1000);
        } catch (InterruptedException ignored) {}
    }
}
