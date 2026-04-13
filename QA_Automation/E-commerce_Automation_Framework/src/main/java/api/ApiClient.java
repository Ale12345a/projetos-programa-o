package api;

import org.apache.hc.client5.http.fluent.Request;

public class ApiClient {

    public static String get(String url) {
        try {
            return Request.get(url)
                    .execute()
                    .returnContent()
                    .asString();
        } catch (Exception e) {
            throw new RuntimeException("API GET failed");
        }
    }
}