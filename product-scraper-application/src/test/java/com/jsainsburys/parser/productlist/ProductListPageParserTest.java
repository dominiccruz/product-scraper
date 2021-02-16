package com.jsainsburys.parser.productlist;

import com.jsainsburys.parser.source.LocalSource;
import com.jsainsburys.parser.source.Source;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

public class ProductListPageParserTest {

    private static final String productListUrl = "testData/sainsbury/productList.html";
    private static final String productListPageSelector = "#main ul.productLister a[href]";

    @Test
    public void scrapeWebPage_valid() throws Exception {
        //Arrange
        Source source = new LocalSource();
        ProductListPageParser productListPageParser = new ProductListPageParser(source, productListPageSelector);

        //Act
        List<String> parse = productListPageParser.parse(productListUrl);

        //Assert
        assertThat(parse.size(), is(14));

        assertThat(parse.get(0), is("testData/sainsbury/html/valid/validProduct1.html"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwException_ifUrlNull() throws IOException {
        //Arrange
        Source source = mock(Source.class);
        ProductListPageParser productListPageParser = new ProductListPageParser(source, "selector");

        //Act
        productListPageParser.parse(null);

        //Assert
        verifyNoInteractions(source);
    }
}