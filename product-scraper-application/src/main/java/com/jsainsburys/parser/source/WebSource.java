package com.jsainsburys.parser.source;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class WebSource implements Source {

    private final int timeout;

    public WebSource(int timeout) {
        this.timeout = timeout;
    }

    public Document load(String url) throws IOException {
        return Jsoup.parse(new URL(url), timeout);
    }

}
