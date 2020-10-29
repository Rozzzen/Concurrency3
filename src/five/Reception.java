package five;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Reception {

    Queue<Client> queue = new ConcurrentLinkedQueue<>();

    private static final int MAX_SIZE = 3;

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void add(Client client) {
        if (queue == null || client == null) {
            throw new IllegalArgumentException();
        }
        if (queue.size() == MAX_SIZE) {
            System.out.println("No more space in reception");
            System.out.println(client.getClientName() + " has left");
            return;
        }
        queue.add(client);
    }

    public Client remove() {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        return queue.remove();
    }
}
