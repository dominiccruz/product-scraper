package com.jsainsburys.parser.source;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Source {

    Document load(String url) throws IOException;
}
