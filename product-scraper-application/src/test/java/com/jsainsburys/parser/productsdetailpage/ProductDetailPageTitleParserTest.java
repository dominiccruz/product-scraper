package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.ProductTitle;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDetailPageTitleParserTest {

    @Test
    public void getProductTitle() {
        //Arrange
        String productDetailPageTitleSelector = "productDetailPageTitleSelector";

        String productTitleValue = "productTitleValue";
        Document mockDocument = mock(Document.class);
        Elements titleElements = mock(Elements.class);
        when(titleElements.text()).thenReturn(productTitleValue);
        when(mockDocument.select(productDetailPageTitleSelector)).thenReturn(titleElements);

        //Act
        ProductDetailPageTitleParser productDetailPageTitleParser = new ProductDetailPageTitleParser(productDetailPageTitleSelector);
        Optional<ProductTitle> productTitle = productDetailPageTitleParser.getProductTitle(mockDocument);

        //Assert
        assertThat(productTitle.isPresent(), is(true));
        assertThat(productTitle.get().getTitle(), is(productTitleValue));
    }
}