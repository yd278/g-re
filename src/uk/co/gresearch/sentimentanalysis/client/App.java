package uk.co.gresearch.sentimentanalysis.client;

import java.io.IOException;

public class App {
    private static final String ServerUrl = "https://devrecruitmentchallenge.com";
    private static final String ApiKey = "94e3d148-caea-4de0-a567-e34544252da7";

    public static void main(String[] args) throws IOException {
        WebHandler webHandler = new WebHandler(ServerUrl, ApiKey);
        Client client = new Client(webHandler);
        client.run();
    }
}
