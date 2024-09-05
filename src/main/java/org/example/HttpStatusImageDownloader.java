package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpStatusImageDownloader {
    private final OkHttpClient client = new OkHttpClient();
    private final HttpStatusChecker checker = new HttpStatusChecker();
    public void downloadStatusImage(int code) throws IOException {
        String url = checker.getStatusImage(code);
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                try (InputStream inputStream = response.body().byteStream();
                     FileOutputStream fileOutputStream = new FileOutputStream(code + ".jpg")) {
                    byte[] buffer = new byte[2048];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("Image downloaded successfully for HTTP status code: " + code);
            } else {
                throw new IOException("Image not found for HTTP status code: " + code);
            }
        }
    }

    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        try {
            downloader.downloadStatusImage(200);
            downloader.downloadStatusImage(10000);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
