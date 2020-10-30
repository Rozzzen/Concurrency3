package first;

public class Producer extends Thread{

    private int id;
    private final Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                createProduct(++id);
            }
        } catch (InterruptedException ignored) {}
    }

    public void createProduct(int id) throws InterruptedException{
        synchronized (buffer) {
            System.out.println("Producer created the product: " + "Product#" + id);
            buffer.add("Product#" + id);
            if(buffer.isOversized()) {
                System.out.println("Waiting for consumer");
                buffer.notifyAll();
                buffer.wait();
            }
        }
    }
}
