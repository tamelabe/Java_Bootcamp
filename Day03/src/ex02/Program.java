package ex02;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.err.println("Missed args (--arraySize=? --threadsCount=?)");
            System.exit(-1);
        }
        int size = Integer.parseInt(args[0].split("=")[1]);
        int threadCount = Integer.parseInt(args[1].split("=")[1]);
        if (threadCount <= 0 || size <= 0 || threadCount > size || size > 2e6) {
            System.err.println("Incorrect input");
            System.exit(-1);
        }
        SumArray array = new SumArray(size, threadCount);
        array.fillRandom();
        array.printUnthreadSum();
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; ++i) {
            threads[i] = new Thread(new MultiThread(array));
        }
        for (int i = 0; i < threadCount; ++i) {
            threads[i].start();
        }
        for (int i = 0; i < threadCount; ++i) {
            threads[i].join();
        }
        array.printTotalSum();
    }
}

class MultiThread implements Runnable {
    private SumArray array;
    private int count;
    public MultiThread(SumArray arrayOther) {
        array = arrayOther;
    }
    @Override
    public synchronized void run() {
        array.printSum();
    }
}