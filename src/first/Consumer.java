package first;

public class Consumer extends Thread {

    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep((long)(Math.random() * 1000));
                consumeProduct();
            } catch (InterruptedException ignored) {
                break;
            }
        }
    }

    public void consumeProduct() {
        synchronized (buffer) {
            System.out.println("Consumer received the product: " + buffer.remove());
            buffer.notifyAll();
        }
    }
}
