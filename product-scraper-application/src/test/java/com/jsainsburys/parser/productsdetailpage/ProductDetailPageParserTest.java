package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.parser.source.Source;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductDetailPageParserTest {

    @Test
    public void titleAndPriceParserAreCalled() throws IOException {
        //Arrange
        Document document = mock(Document.class);
        Source source = mock(Source.class);
        when(source.load("url")).thenReturn(document);

        ProductDetailPageTitleParser productDetailPageTitleParser = mock(ProductDetailPageTitleParser.class);
        when(productDetailPageTitleParser.getProductTitle(document)).thenReturn(Optional.of(mock(ProductTitle.class)));

        ProductDetailPagePriceParser productDetailPagePriceParser = mock(ProductDetailPagePriceParser.class);
        when(productDetailPagePriceParser.getProductPrice(document)).thenReturn(Optional.of(mock(Money.class)));

        ProductDetailPageNutritionParser productDetailPageNutritionParser = mock(ProductDetailPageNutritionParser.class);
        when(productDetailPageNutritionParser.getProductNutrition(document)).thenReturn(Optional.of(mock(ProductNutrition.class)));

        ProductDetailPageParser productDetailPageParser = new ProductDetailPageParser(source,
                productDetailPageTitleParser,
                productDetailPagePriceParser,
                productDetailPageNutritionParser);

        //Act
        productDetailPageParser.parse("url");

        //Assert
        verify(productDetailPageTitleParser, times(1)).getProductTitle(any(Document.class));
        verify(productDetailPagePriceParser, times(1)).getProductPrice(any(Document.class));
        verify(productDetailPageNutritionParser, times(1)).getProductNutrition(any(Document.class));
    }
}