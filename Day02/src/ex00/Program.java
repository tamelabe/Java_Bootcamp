package ex00;

import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws NoSuchFileException, FileNotFoundException {
        IOSignatures sig = new IOSignatures();
        Scanner sc = new Scanner(System.in);
        String line;
        while (!(line = sc.nextLine()).equals("42")) {
            sig.setFilepath(line);
            sig.writeToFile();
        }
        sc.close();
    }
}