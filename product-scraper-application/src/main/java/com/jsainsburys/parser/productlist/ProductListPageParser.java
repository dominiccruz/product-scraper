package com.jsainsburys.parser.productlist;

import com.jsainsburys.parser.JSoupParser;
import com.jsainsburys.parser.source.Source;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ProductListPageParser extends JSoupParser {

    private final String productListPageSelector;

    public ProductListPageParser(Source source,
                                 String productListPageSelector) {
        super(source);
        this.productListPageSelector = productListPageSelector;
    }

    public List<String> parse(String url) throws IOException {
        Assert.notNull(url, "Product List Page Url Cannot be NULL");
        Document productListPage = getDocument(url);
        Elements productUrlElements = productListPage.select(productListPageSelector);

        return productUrlElements
                .stream()
                .map(productUrlElement -> productUrlElement.attr("href"))
                .collect(Collectors.toList());
    }
}