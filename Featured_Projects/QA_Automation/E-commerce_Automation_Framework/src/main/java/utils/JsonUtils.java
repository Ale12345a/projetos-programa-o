package utils;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    private static final Gson gson = new Gson();

    public static <T> T readJson(String path, Class<T> clazz) {
        try {
            return gson.fromJson(new FileReader(path), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read JSON: " + path);
        }
    }
}