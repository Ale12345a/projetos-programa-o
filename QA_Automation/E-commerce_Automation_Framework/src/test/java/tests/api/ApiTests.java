package tests.api;

import org.testng.annotations.Test;
import api.ApiClient;

public class ApiTests {

    @Test
    public void simpleGet() throws Exception {
        String response = ApiClient.get("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println(response);
    }
}