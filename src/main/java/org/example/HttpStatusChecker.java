package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class HttpStatusChecker {
    private final OkHttpClient client = new OkHttpClient();
    public String getStatusImage(int code) throws IOException {
        String url = "https://http.cat/" + code + ".jpg";
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return url;
            } else {
                throw new IOException("Image not found for HTTP status code: " + code);
            }
        }
    }

    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            System.out.println(checker.getStatusImage(200));
            System.out.println(checker.getStatusImage(10000));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
