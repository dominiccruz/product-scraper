package com.jsainsburys.parser.productlist;

import com.jsainsburys.parser.source.LocalSource;
import com.jsainsburys.parser.source.Source;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

public class ProductListPageIntegrationTest {

    private static final String emptyProductListPage = "testData/sainsbury/productList.html";
    private static final String sainsburysProductListPage = "testData/sainsbury/Berries.html";
    private static final String productListPageSelector = "#main #productsContainer #productLister ul.productLister .productNameAndPromotions a[href]";

    @Test
    public void parseListPage_valid() throws Exception {
        //Arrange
        Source source = new LocalSource();
        ProductListPageParser productListPageParser = new ProductListPageParser(source, productListPageSelector);

        //Act
        List<String> productUrls = productListPageParser.parse(sainsburysProductListPage);

        //Assert
        assertThat(productUrls.size(), is(17));

        assertEquals(
                "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html",
                productUrls.get(0));

        assertEquals(
                "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html",
                productUrls.get(1));
    }

    @Test
    public void parseListPage_empty() throws Exception {
        //Arrange
        Source source = new LocalSource();
        ProductListPageParser productListPageParser = new ProductListPageParser(source, productListPageSelector);

        //Act
        List<String> parse = productListPageParser.parse(emptyProductListPage);

        //Assert
        assertThat(parse.size(), is(0));
    }

}