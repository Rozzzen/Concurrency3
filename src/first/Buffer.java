package first;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Buffer {

    private Queue<String> queue = new ConcurrentLinkedQueue<>();
    private final int MAX_SIZE = 4;

    public synchronized void add(String string) {
        if (queue == null || string == null) {
            throw new IllegalArgumentException();
        }
        queue.add(string);
    }

    public synchronized String remove() {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return queue.remove();
    }

    public boolean isOversized() {
        return queue.size() >= MAX_SIZE;
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
