package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageReader {
    private final Color white = new Color(255, 255, 255);
    private final Color black = new Color(0, 0, 0);
    private String firstElement = ".";
    private String secondElement = "#";
    private String pathFile = "/resources/it.bmp";

    public ImageReader(String whiteElement, String blackElement, String path) {
        firstElement = whiteElement;
        secondElement = blackElement;
        pathFile = path;
        if (path == null) System.exit(-1);
    }

    public void parseImage() throws IOException {
        BufferedImage image = ImageIO.read(ImageReader.class.getResource(pathFile));
        if (image == null) {
            System.err.println("Error on the read file");
            System.exit(-1);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = image.getRGB(j, i);
                if (rgb == white.getRGB()) {
                    System.out.print(firstElement);
                } else if (rgb == black.getRGB()) {
                    System.out.print(secondElement);
                }
            }
            System.out.println("");
        }

    }
}
