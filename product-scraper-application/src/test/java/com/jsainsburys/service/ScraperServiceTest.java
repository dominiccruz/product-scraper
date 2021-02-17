package com.jsainsburys.service;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.summary.ProductSummary;
import com.jsainsburys.core.product.summary.Total;
import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ScraperServiceTest {

    ProductListPageParser productListPageParser = mock(ProductListPageParser.class);

    ProductDetailPageParser productDetailPageParser = mock(ProductDetailPageParser.class);

    TotalService totalService = mock(TotalService.class);

    @Test
    public void scraperService() throws Exception {
        //Arrange
        List<String> productUrls = getProductUrls();
        when(productListPageParser.parse("url")).thenReturn(productUrls);

        List<Product> parsedProducts = getParsedProducts();
        when(productDetailPageParser.parse(productUrls.get(0))).thenReturn(Optional.of(parsedProducts.get(0)));
        when(productDetailPageParser.parse(productUrls.get(1))).thenReturn(Optional.of(parsedProducts.get(1)));

        Total total = mock(Total.class);
        when(totalService.calculateTotal(parsedProducts)).thenReturn(total);

        ScraperService scraperService = new ScraperService(productListPageParser, productDetailPageParser, totalService);

        //Act
        ProductSummary productSummary = scraperService.scrape("url");

        //Assert
        assertThat(productSummary.getProducts().size(), is(2));
        assertEquals(productSummary.getProducts(), parsedProducts);

        Total parsedTotal = productSummary.getTotal();
        assertThat(parsedTotal, is(total));

        verify(productDetailPageParser, times(1)).parse(productUrls.get(0));
        verify(productDetailPageParser, times(1)).parse(productUrls.get(1));
        verify(totalService, times(1)).calculateTotal(parsedProducts);
    }

    private List<String> getProductUrls() {
        return Arrays.asList(
                "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html",
                "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html"
        );
    }

    private List<Product> getParsedProducts(){
        return Arrays.asList(
                mock(Product.class),
                mock(Product.class)
        );
    }
}