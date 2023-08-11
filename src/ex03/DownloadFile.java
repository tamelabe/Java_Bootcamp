package ex03;

import javax.xml.ws.http.HTTPException;
import java.util.List;
import java.net.URL;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Queue;

public class DownloadFile {
    private Links links;
    private String saveToPath;
    private int count;
    public DownloadFile(Links links) throws IOException {
        this.links = links;
        saveToPath = "ex03/downloads";
        count = 0;
        download();
    }

    private void download() throws IOException {
        startMessage();
        URL link = links.getLink();
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        int response = connection.getResponseCode();
        if (response != 200) {
            System.err.println("Server is not response (URL: " + link.toString() + ")");
            return;
        }
        InputStream inputStream = connection.getInputStream();
        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
            FileOutputStream fos = new FileOutputStream(saveToPath)) {
            byte[] buffer = new byte[4096];
            int bytes = 0;
            while ((bytes = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytes);
            }
        }
        count++;
        finalMessage();
    }

    private void startMessage() {
        System.out.println(" start download file number " + (count + 1));
    }
    private void finalMessage() {
        System.out.println(" finish download file number " + (count));
    }
}