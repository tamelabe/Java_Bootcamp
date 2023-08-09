package ex00;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IOSignatures {

    IOSignatures() {
        fillMap();
    }
    private final String setSigns = "src/ex00/signatures.txt";
    private Map<String, String> setsMap  = new HashMap<>();
    private String filepath;
    private String magicString;

    public void setFilepath(String fpath) throws FileNotFoundException {
        filepath = fpath;
        readSignature();
    }

    public void writeToFile() {
        String type = setsMap.get(magicString);
        if (type != null) { type += "\n"; }
        try (FileOutputStream fos = new FileOutputStream("src/ex00/result.txt", true)) {
            byte[] contentBytes = type.getBytes();
            fos.write(contentBytes);
            System.out.println("PROCESSED");
        } catch (IOException | NullPointerException e) {
            System.out.println("UNDEFINED");
        }
        magicString = "";
    }

    private void readSignature() {
        try (FileInputStream inputStream = new FileInputStream(filepath)) {
            StringBuilder buffer = new StringBuilder();
            int stream = 0;
            for (int i = 0; (stream = inputStream.read()) != -1 && i < 8; ++i) {
                buffer.append(String.format("%02X ", stream));
            }
            magicString = buffer.toString().trim();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void fillMap() {
        try (FileInputStream inputStream = new FileInputStream(setSigns)) {
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(", ");
                setsMap.put(parts[1], parts[0]);
            }
        } catch (IOException e) {
            System.out.println("Set not found");
        }
    }
}