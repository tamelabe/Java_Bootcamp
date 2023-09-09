package ex02;

import java.io.*;
import java.nio.file.*;


public class FileManager {
    private Path current;
    FileManager(String path) {
        current = Paths.get(path).normalize();
    }
    public void execute(String command) {
        String[] args = command.split(" ");
        if (args[0].equals("mv") && args.length == 3) {
            move(args[1], args[2]);
        } else if (args[0].equals("ls") && args.length == 1) {
            list();
        } else if (args[0].equals("cd") && args.length == 2) {
            changeDirectory(args[1]);
        } else {
            System.err.println("Invalid command");
        }
    }

    private void move(String fileOrigin, String destination) {
        Path file = current.resolve(fileOrigin);
        Path dest = current.resolve(destination);
        if (Files.isDirectory(dest))
            dest = dest.resolve(file.getFileName());
        try {
            Files.move(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void list() {
        try {
            Files.list(current).forEach(path -> {
                if (!path.toFile().isHidden()) {
                    System.out.print(path.getFileName() + " ");
                    if (Files.isDirectory(path)) {
                        System.out.println(getDirSize(path) / 1024 + " KB");
                    } else if (Files.isRegularFile(path)) {
                        System.out.println(path.toFile().length() / 1024 + " KB");
                    }
                }
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void changeDirectory(String newPath) {
        Path temp = current.resolve(newPath).normalize();
        if (!Files.isDirectory(temp)) {
            System.err.println("cd: " + newPath + " is not directory");
            return;
        }
        current = temp;
        System.out.println(current);
    }

    private long getDirSize(Path path) {
        long size = 0;
        try (DirectoryStream<Path> list = Files.newDirectoryStream(path)) {
            for (Path file : list) {
                if (Files.isRegularFile(file)) {
                    size += file.toFile().length();
                } else if (Files.isDirectory(path)) {
                    size += getDirSize(path);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return size;
    }
}