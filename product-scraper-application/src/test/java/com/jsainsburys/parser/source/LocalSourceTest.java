package com.jsainsburys.parser.source;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class LocalSourceTest {

    private static final String productListUrl = "testData/sainsbury/productList.html";

    @Test
    void get() throws IOException {
        //Arrange
        LocalSource localSource = new LocalSource();

        //Act
        Document document = localSource.get(productListUrl);

        //Assert
        assertThat(document.title(), is("Product List Page 1"));
    }

}