package com.jsainsburys.service;

import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;

import static org.mockito.Mockito.mock;

public class ScraperServiceTest {

    ProductListPageParser productListPageParser = mock(ProductListPageParser.class);

    ProductDetailPageParser productDetailPageParser = mock(ProductDetailPageParser.class);;

    public void scraperService() {
        //Arrange
        ScraperService scraperService = new ScraperService();

        //Act
        scraperService.scrape("url");

        //Assert
    }
}