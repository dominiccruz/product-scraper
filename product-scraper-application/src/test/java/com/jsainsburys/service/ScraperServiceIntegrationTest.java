package com.jsainsburys.service;

import com.jsainsburys.config.ScraperServiceConfig;
import com.jsainsburys.core.product.summary.ProductSummary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Import({ScraperServiceConfig.class})
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-it.properties")
public class ScraperServiceIntegrationTest {

    private static final String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    @Autowired
    ScraperService scraperService;

    @Test
    public void parseProductPage() throws Exception {
        //Act
        ProductSummary productSummary = scraperService.scrape(url);

        //Assert
        assertThat(productSummary.getProducts().size(), is(17));

        assertThat(productSummary.getTotal().getGross().getValue(), is(BigDecimal.valueOf(39.50).setScale(2)));
        assertThat(productSummary.getTotal().getVat().getValue(), is(BigDecimal.valueOf(6.58).setScale(2)));
    }
}