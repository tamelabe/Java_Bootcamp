package ex03;

import ex02.MultiThread;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.err.println("Missed args (--threadsCount=?)");
            System.exit(-1);
        }
        int threadsCount = Integer.parseInt(args[0].split("=")[1]);
        if (threadsCount <= 0) {
            System.err.println("Incorrect input");
            System.exit(-1);
        }
        Parser parser = new Parser();
        Links links = new Links(parser.getLinks(), parser.getNumbers());
        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; ++i) {
            threads[i] = new Thread(new Downloader(links));
        }
//        for (int i = 0; i < threadCount; ++i) {
//            threads[i].start();
//        }
//        for (int i = 0; i < threadCount; ++i) {
//            threads[i].join();
//        }
    }
}

class Downloader implements Runnable {
    private Links links;
    public Downloader(Links links) {
        this.links = links;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DownloadFile downloader = new DownloadFile(Links);

            } catch (IOException e) {
                return;
            }
        }
    }
}

