package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.Identity;
import edu.school21.printer.logic.ImageReader;

import java.io.IOException;
public class Program {
    public static void main(String[] args) throws IOException {
        Identity arg = new Identity();
        JCommander jCommander = new JCommander(arg);
        jCommander.parse(args);

        ImageReader img = new ImageReader(arg.getWhileColor(), arg.getBlackColor(), "/resources/it.bmp");
        img.parseImage();
    }
}



