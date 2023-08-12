package edu.school21.printer.app;

import edu.school21.printer.logic.ImageReader;

import java.io.IOException;
public class Program {
    public static void main(String[] args) throws IOException {

        String firstSymbol = args[0];
        String secondSymbol = args[1];
        ImageReader img = new ImageReader(firstSymbol, secondSymbol, "/resources/it.bmp");
        img.parseImage();
    }
}



