package four;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Fork {

    private final Semaphore fork;

    Fork() {
        fork = new Semaphore(1);
    }

    public boolean pickUp(){
        try {
            return fork.tryAcquire(1, (long) (Math.random() * 400 + 100), TimeUnit.MILLISECONDS);
        } catch(InterruptedException ignored) {
            return false;
        }
    }

    public void putDown(){
        fork.release();
    }
}
