package com.jsainsburys.parser.source;

import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LocalSourceTest {

    private static final String productListUrl = "testData/sainsbury/productList.html";

    @Test
    public void get() throws IOException {
        //Arrange
        LocalSource localSource = new LocalSource();

        //Act
        Document document = localSource.load(productListUrl);

        //Assert
        assertThat(document.title(), is("Product List Page 1"));
    }

}