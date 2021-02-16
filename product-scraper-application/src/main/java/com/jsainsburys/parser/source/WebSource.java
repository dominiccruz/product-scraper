package com.jsainsburys.parser.source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class WebSource implements Source {

  private final int timeout;

  @Autowired
  public WebSource(@Value("${timeout.value}") int timeout) {
    this.timeout = timeout;
  }

  public Document get(String url) throws IOException {
    return Jsoup.parse(new URL(url), timeout);
  }

}
