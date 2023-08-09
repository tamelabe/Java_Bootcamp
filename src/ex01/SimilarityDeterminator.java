package ex01;

import java.io.*;
import java.util.*;

public class SimilarityDeterminator {
    private Vector<String> dictionary = new Vector<>(50);
    private Vector<String> firstFile = new Vector<>(50);
    private Vector<String> secondFile = new Vector<>(50);

    private final String dictionaryPath = "ex01/dictionary.txt";

    public void getSimilarity(String path1, String path2) {
        clearCollections();
        try {
            parseFiles(path1, path2);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        double res = Math.floor(findSimilarity() * 100) / 100;
        System.out.printf("Similarity = %.2f", res);
    }

    private void parseFiles(String filepath1, String filepath2) throws IOException {
        try (BufferedReader buffer1 = new BufferedReader(new FileReader(filepath1));
             BufferedReader buffer2 = new BufferedReader(new FileReader(filepath2))) {
            firstFile.addAll(Arrays.asList(parseBuffer(buffer1, 1)));
            secondFile.addAll(Arrays.asList(parseBuffer(buffer2, 2)));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("File not found");
        }
    }

    private String[] parseBuffer(BufferedReader buffer, int fileNumber) throws IOException {
        String line;
        String[] words = new String[1];
        while ((line = buffer.readLine()) != null) {
            line = line.replaceAll("[\\p{Punct}\\n]", "");
            words = line.split(" ");
            for (String word : words) {
                if (!word.isEmpty()) {
                    if (!dictionary.contains(word))
                        dictionary.add(word);
                }
            }
        }
        return words;
    }

    private void clearCollections() {
        dictionary.clear();
        firstFile.clear();
        secondFile.clear();
    }


    private double findSimilarity() {
        Vector<Integer> firstFreq = new Vector<>(dictionary.size());
        Vector<Integer> secondFreq = new Vector<>(dictionary.size());
        for (int i = 0; i < dictionary.size(); ++i) {
            int firstVal = 0, secVal = 0;
            for (String word : firstFile) {
                if (word.equals(dictionary.get(i)))
                    firstVal++;
            }
            for (String word : secondFile) {
                if (word.equals(dictionary.get(i)))
                    secVal++;
            }
            firstFreq.add(firstVal);
            secondFreq.add(secVal);
        }
        int numerator = 0;
        double preDen1 = 0, preDen2 = 0;
        for (int i = 0; i < firstFreq.size(); ++i) {
            numerator += firstFreq.get(i) * secondFreq.get(i);
            preDen1 += Math.pow(firstFreq.get(i), 2);
            preDen2 += Math.pow(secondFreq.get(i), 2);
        }
        preDen1 = Math.sqrt(preDen1);
        preDen2 = Math.sqrt(preDen2);
        return numerator / (preDen1 * preDen2);
    }
}