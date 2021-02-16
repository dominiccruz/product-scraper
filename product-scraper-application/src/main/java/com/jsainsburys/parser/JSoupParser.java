package com.jsainsburys.parser;

import com.jsainsburys.parser.source.Source;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;

import java.io.IOException;

@AllArgsConstructor
public abstract class JSoupParser {

    private Source source;

    public Document getDocument(String url) throws IOException {
        return source.load(url);
    }

}
