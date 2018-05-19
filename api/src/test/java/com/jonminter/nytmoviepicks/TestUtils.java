package com.jonminter.nytmoviepicks;

import java.io.IOException;
import java.net.URL;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TestUtils {
  public static String getResourceContents(String resourcePath) throws IOException {
    URL url = Resources.getResource(resourcePath);
    return Resources.toString(url, Charsets.UTF_8);
  }
}
