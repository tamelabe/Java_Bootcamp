package ex03;

import javax.xml.ws.http.HTTPException;
import java.util.List;
import java.net.URL;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Queue;

public class DownloadFile {
    private final Links links;
    private final String saveToPath;
    private int count;
    public DownloadFile(Links links) throws IOException {
        this.links = links;
        saveToPath = "/Users/tamelabe/Projects/java/Java_Bootcamp.Day03-1/src/ex03/downloads";
        count = 0;
        download();
    }

    private void download() throws IOException {
        URL link = links.getLink();
        count++;
        startMessage();
        finalMessage();
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        int response = connection.getResponseCode();
        if (response != 200) {
            System.err.println("Server is not response (URL: " + link.toString() + ")");
            return;
        }
        String contentLengthHeader = connection.getHeaderField("Content-Length");
        int fileSize = Integer.parseInt(contentLengthHeader);
        InputStream inputStream = connection.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        FileOutputStream fos = new FileOutputStream(saveToPath);
        System.out.println("Hello");
        byte[] buffer = new byte[4096];
        int bytes = 0, totalBytesRead = 0;;
        while ((bytes = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, bytes);
            totalBytesRead += bytes;
            if (totalBytesRead >= fileSize) {
                break;
            }
        }

        bis.close();
        fos.close();
    }

    private void startMessage() {
        System.out.println(" start download file number " + (count + 1));
    }
    private void finalMessage() {
        System.out.println("Thread- + count + finish download file number " + (count));
    }
}