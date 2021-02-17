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

    @Test(expected = IllegalArgumentException.class)
    public void throwException_ifUrlNull() throws Exception {
        //Arrange
        Source source = mock(Source.class);
        ProductListPageParser productListPageParser = new ProductListPageParser(source, "selector");

        //Act
        productListPageParser.parse(null);

        //Assert
        verifyNoInteractions(source);
    }
}