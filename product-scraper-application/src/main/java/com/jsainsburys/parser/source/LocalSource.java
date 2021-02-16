package com.jsainsburys.parser.source;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class LocalSource implements Source {

  public Document get(String url) throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(url).getFile());
    return Jsoup.parse(file, UTF_8.toString());
  }

}
