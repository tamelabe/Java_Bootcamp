package ex02;

import java.io.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please write current path (--current-folder=PATH)");
            System.exit(-1);
        }
        String[] arg = args[0].split("=");
        if (!arg[0].equals("--current-folder")) {
            System.err.println("Please write command correctly (--current-folder=PATH)");
            System.exit(-1);
        }
        FileManager fm = new FileManager(arg[1]);
        Scanner sc = new Scanner(System.in);
        String buffer = sc.nextLine();
        while (!buffer.equals("exit")) {
            fm.execute(buffer);
            buffer = sc.nextLine();
        }
    }
}