package com.jsainsburys.service;

import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

        ScraperService scraperService = new ScraperService(productListPageParser, productDetailPageParser, totalService);

        //Act
        scraperService.scrape("url");

        //Assert
        verify(productDetailPageParser, times(1)).parse(productUrls.get(0));
        verify(productDetailPageParser, times(1)).parse(productUrls.get(1));
    }

    private List<String> getProductUrls() {
        return Arrays.asList(
                "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html",
                "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html"
        );
    }
}