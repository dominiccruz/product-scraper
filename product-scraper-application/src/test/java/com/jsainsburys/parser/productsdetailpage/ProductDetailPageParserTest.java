package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.core.product.factory.ProductFactory;
import com.jsainsburys.parser.source.Source;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ProductDetailPageParserTest {

    @Test
    public void parsersAndFactoryAreCalled() throws Exception {
        //Arrange
        Document document = mock(Document.class);
        Source source = mock(Source.class);
        when(source.load("url")).thenReturn(document);

        ProductTitle productTitle = mock(ProductTitle.class);
        Money money = mock(Money.class);
        ProductDescription productDescription = mock(ProductDescription.class);
        ProductNutrition productNutrition = mock(ProductNutrition.class);

        ProductDetailPageTitleParser productDetailPageTitleParser = mock(ProductDetailPageTitleParser.class);
        when(productDetailPageTitleParser.getProductTitle(document)).thenReturn(Optional.of(productTitle));

        ProductDetailPagePriceParser productDetailPagePriceParser = mock(ProductDetailPagePriceParser.class);
        when(productDetailPagePriceParser.getProductPrice(document)).thenReturn(Optional.of(money));

        ProductDetailPageDescriptionParser productDetailPageDescriptionParser = mock(ProductDetailPageDescriptionParser.class);
        when(productDetailPageDescriptionParser.getProductDescription(document)).thenReturn(Optional.of(productDescription));

        ProductDetailPageNutritionParser productDetailPageNutritionParser = mock(ProductDetailPageNutritionParser.class);
        when(productDetailPageNutritionParser.getProductNutrition(document)).thenReturn(Optional.of(productNutrition));

        ProductFactory productFactory = mock(ProductFactory.class);
        when(productFactory.createProduct(productTitle, money, productDescription, productNutrition)).thenReturn(Optional.of(mock(Product.class)));

        ProductDetailPageParser productDetailPageParser = new ProductDetailPageParser(source,
                productDetailPageTitleParser,
                productDetailPagePriceParser,
                productDetailPageDescriptionParser,
                productDetailPageNutritionParser,
                productFactory
                );

        //Act
        Optional<Product> product = productDetailPageParser.parse("url");

        //Assert
        assertThat(product.isPresent(), is(true));

        verify(productDetailPageTitleParser, times(1)).getProductTitle(any(Document.class));
        verify(productDetailPagePriceParser, times(1)).getProductPrice(any(Document.class));
        verify(productDetailPageDescriptionParser, times(1)).getProductDescription(any(Document.class));
        verify(productDetailPageNutritionParser, times(1)).getProductNutrition(any(Document.class));
        verify(productFactory, times(1)).createProduct(productTitle, money, productDescription, productNutrition);
    }
}