package first;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Buffer {
    Queue<String> queue = new ConcurrentLinkedQueue<>();

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
}
