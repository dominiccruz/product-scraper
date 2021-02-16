package com.jsainsburys.parser.source;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Source {

  Document get(String url) throws IOException;
}
