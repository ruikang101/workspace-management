package tcss556.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import tcss556.constants.AppConstants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** This class is used help load JSON data into mocked repository. */
@Slf4j
public class MockedRepositoryUtils {
  /**
   * Load data from JSON file.
   *
   * @param fileName the name of JSON file to load.
   * @param target target class type to deserialize
   * @param <T> generic type
   * @return List of deserialized object.
   */
  public static <T> List<T> loadJsonData(String fileName, Class<T> target) {
    URL url = ClassLoader.getSystemClassLoader().getResource(".");
    if (url == null) {
      log.error("failed to find classpath ");
    } else {
      File file = new File(url.getFile(), fileName);
      if (file.exists()) {
        try {
          Type type = new TypeToken<ArrayList<T>>() {}.getType();
          return new Gson()
              .fromJson(org.apache.commons.io.FileUtils.readFileToString(file, "UTF-8"), type);
        } catch (IOException e) {
          log.error("failed to load {}", AppConstants.MOCKED_USER_FILE, e);
        }
      }
    }
    return Collections.emptyList();
  }
}
