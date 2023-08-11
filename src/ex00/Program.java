package ex00;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("Missed arg (--count=??)");
            System.exit(-1);
        }
        int num = Integer.parseInt(args[0].split("-count=")[1]);
        Object object = new Object();
        Thread threadEgg = new Thread(new MyThread(object, "Egg", num));
        threadEgg.start();
        Thread threadHen = new Thread(new MyThread(object, "Hen", num));
        threadHen.start();
        threadHen.join();
        threadEgg.join();
        for (int i = 0; i < num; ++i) {
            System.out.println("Human");
        }
    }
}

class MyThread implements Runnable {
    private final String currMessage;
    private final int count;
    public MyThread(Object object, String message, int num) {
        currMessage = message;
        count = num;
    }
    @Override
    public void run() {
        for (int i = 0; i < count; ++i) {
            System.out.println(currMessage);
        }
    }
}

