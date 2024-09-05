package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpImageStatusCli {
    private final HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
    public void askStatus() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter HTTP status code: ");
        try {
            String input = reader.readLine();
            int code = Integer.parseInt(input);
            try {
                downloader.downloadStatusImage(code);
            } catch (IOException e) {
                System.out.println("There is no image for HTTP status " + code);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}
