package first;

public class Consumer extends Thread {

    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                consumeProduct();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consumeProduct() throws InterruptedException {
        synchronized (buffer) {
            System.out.println("Consumer received the product: " + buffer.remove());
            if(buffer.isEmpty()) {
                System.out.println("Producing continues");
                buffer.notifyAll();
                buffer.wait();
            }
        }
    }
}
