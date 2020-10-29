package first;

public class Producer extends Thread{

    private int id;
    private final Buffer buffer;

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                createProduct(++id);
            }
            catch (InterruptedException ignored) {
                break;
            }
        }
    }

    public void createProduct(int id) throws InterruptedException{
        synchronized (buffer) {
            System.out.println("Producer created the product: " + "Product#" + id);
            buffer.add("Product#" + id);
            System.out.println("Waiting for consumer");
            buffer.wait();
        }
    }
}
