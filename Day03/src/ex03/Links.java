package ex03;

import java.net.URL;
import java.util.List;

public class Links {
    List<URL> links;
    int[] numbers;
    int count;
    int capacity;
    public Links(List<URL> links, int[] numbers) {
        count = 0;
        capacity = numbers.length;
        this.numbers = numbers;
        this.links = links;
    }
    public int getNumber() {
        return numbers[count];
    }
    public URL getLink() {
        capacity--;
        count++;
        return links.get(count - 1);
    }
}