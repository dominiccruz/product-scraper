package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.Money;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductDetailPagePriceParserTest {

    @Test
    public void getProductPrice() {
        //Arrange
        String productDetailPagePriceSelector = "selector";

        String productPriceValue = "£1.40/unit";
        Document mockDocument = mock(Document.class);
        Elements priceElements = mock(Elements.class);
        when(priceElements.text()).thenReturn(productPriceValue);
        when(mockDocument.select(productDetailPagePriceSelector)).thenReturn(priceElements);

        //Act
        ProductDetailPagePriceParser productDetailPagePriceParser = new ProductDetailPagePriceParser(productDetailPagePriceSelector);
        Optional<Money> productPrice = productDetailPagePriceParser.getProductPrice(mockDocument);

        //Assert
        assertThat(productPrice.isPresent(), is(true));
        Money price = productPrice.get();
        assertThat(price.getValue().toString(), is("1.40"));
    }

    @Test
    public void getProductPrice_returnEmptyIf_ifProductPriceNull() {
        //Arrange
        String productDetailPagePriceSelector = "selector";

        String productPriceValue = null;
        Document mockDocument = mock(Document.class);
        Elements priceElements = mock(Elements.class);
        when(priceElements.text()).thenReturn(productPriceValue);
        when(mockDocument.select(productDetailPagePriceSelector)).thenReturn(priceElements);

        //Act
        ProductDetailPagePriceParser productDetailPagePriceParser = new ProductDetailPagePriceParser(productDetailPagePriceSelector);
        Optional<Money> productPrice = productDetailPagePriceParser.getProductPrice(mockDocument);

        //Assert
        assertThat(productPrice.isPresent(), is(false));
    }

    @Test
    public void getProductPrice_returnEmptyIf_ifProductPriceInvalid() {
        //Arrange
        String productDetailPagePriceSelector = "selector";

        String productPriceValue = "sometext";
        Document mockDocument = mock(Document.class);
        Elements priceElements = mock(Elements.class);
        when(priceElements.text()).thenReturn(productPriceValue);
        when(mockDocument.select(productDetailPagePriceSelector)).thenReturn(priceElements);

        //Act
        ProductDetailPagePriceParser productDetailPagePriceParser = new ProductDetailPagePriceParser(productDetailPagePriceSelector);
        Optional<Money> productPrice = productDetailPagePriceParser.getProductPrice(mockDocument);

        //Assert
        assertThat(productPrice.isPresent(), is(false));
    }
}