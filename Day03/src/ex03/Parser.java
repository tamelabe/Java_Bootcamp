package ex03;

import java.io.IOException;
import java.nio.file.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class Parser {
    Path filepath;
    List<URL> links;
    int[] numbers;

    public Parser() throws IOException {
        this.filepath = Paths.get("src/ex03/files_urls.txt");
        links = new LinkedList<>();
        parseFile();
    }

    public List<URL> getLinks() {
        return links;
    }
    public int[] getNumbers() {
        return numbers;
    }

    private void parseFile() throws IOException {
        List<String> lines = Files.readAllLines(filepath);
        numbers = new int[lines.size()];
        for(int i = 0; i < lines.size(); ++i) {
            String line[] = lines.get(i).split(" ");
            if (line[1].startsWith("http")) {
                links.add(new URL(line[1]));
            }
            try {
                numbers[i] = Integer.parseInt(line[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid file format");
                System.exit(-1);
            }
        }
    }
}