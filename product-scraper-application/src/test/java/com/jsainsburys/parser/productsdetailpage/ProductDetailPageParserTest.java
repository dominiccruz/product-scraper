package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.parser.source.Source;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ProductDetailPageParserTest {

    @Test
    public void titleAndPriceParserAreCalled() throws IOException {
        //Arrange
        Source source = mock(Source.class);
        when(source.load("url")).thenReturn(mock(Document.class));

        ProductDetailPageTitleParser productDetailPageTitleParser = mock(ProductDetailPageTitleParser.class);
        ProductDetailPagePriceParser productDetailPagePriceParser = mock(ProductDetailPagePriceParser.class);

        ProductDetailPageParser productDetailPageParser = new ProductDetailPageParser(source,
                productDetailPageTitleParser,
                productDetailPagePriceParser);

        //Act
        productDetailPageParser.parse("url");

        //Assert
        verify(productDetailPageTitleParser, times(1)).getProductTitle(any(Document.class));
        verify(productDetailPagePriceParser, times(1)).getProductPrice(any(Document.class));
    }
}