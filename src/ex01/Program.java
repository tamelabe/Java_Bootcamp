package ex01;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("Missed arg (--count=??)");
            System.exit(-1);
        }
        int num = 0;
        try {
            num = Integer.parseInt(args[0].split("-count=")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid argument");
        }
        Object object = new Object();
        Thread threadEgg = new Thread(new PCThread(object, "Egg", num));
        Thread threadHen = new Thread(new PCThread(object, "Hen", num));
        threadEgg.start();
        threadHen.start();
    }
}

class PCThread implements Runnable {
    private final Object monitor;
    private final String currMessage;
    private int count;
    public PCThread(Object object, String message, int num) {
        monitor = object;
        currMessage = message;
        count = num;
    }
    @Override
    public void run() {
        synchronized (monitor) {
            for (int i = 0; i < count; ++i) {
                System.out.println(currMessage);
                monitor.notifyAll();
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            monitor.notifyAll();
        }
    }
}