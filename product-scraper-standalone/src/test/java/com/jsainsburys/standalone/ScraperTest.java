package com.jsainsburys.standalone;

import com.jsainsburys.standalone.config.ProductScraperServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@Import(ProductScraperServiceConfiguration.class)
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ScraperTest {

    private static final String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    @Autowired
    Scraper scraper;

    @Test
    public void scrape() throws IOException {
        //Arrange
        //Act
        String scrape = scraper.scrape(url);

        //Assert
        assertEquals(scrape, getOutput());
    }

    private String getOutput() {
        return "{\n" +
                "  \"results\" : [ {\n" +
                "    \"title\" : \"Sainsbury's Strawberries 400g\",\n" +
                "    \"kcal_per_100g\" : 33,\n" +
                "    \"unit_price\" : 1.75,\n" +
                "    \"description\" : \"by Sainsbury's strawberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Blueberries 200g\",\n" +
                "    \"kcal_per_100g\" : 45,\n" +
                "    \"unit_price\" : 1.75,\n" +
                "    \"description\" : \"by Sainsbury's blueberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Raspberries 225g\",\n" +
                "    \"kcal_per_100g\" : 32,\n" +
                "    \"unit_price\" : 1.75,\n" +
                "    \"description\" : \"by Sainsbury's raspberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Blackberries, Sweet 150g\",\n" +
                "    \"kcal_per_100g\" : 32,\n" +
                "    \"unit_price\" : 1.50,\n" +
                "    \"description\" : \"by Sainsbury's blackberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Blueberries 400g\",\n" +
                "    \"kcal_per_100g\" : 45,\n" +
                "    \"unit_price\" : 3.25,\n" +
                "    \"description\" : \"by Sainsbury's blueberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Blueberries, SO Organic 150g\",\n" +
                "    \"kcal_per_100g\" : 45,\n" +
                "    \"unit_price\" : 2.00,\n" +
                "    \"description\" : \"So Organic blueberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Raspberries, Taste the Difference 150g\",\n" +
                "    \"kcal_per_100g\" : 32,\n" +
                "    \"unit_price\" : 2.50,\n" +
                "    \"description\" : \"Ttd raspberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Cherries 400g\",\n" +
                "    \"kcal_per_100g\" : 52,\n" +
                "    \"unit_price\" : 2.50,\n" +
                "    \"description\" : \"by Sainsbury's Family Cherry Punnet\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Blackberries, Tangy 150g\",\n" +
                "    \"kcal_per_100g\" : 32,\n" +
                "    \"unit_price\" : 1.50,\n" +
                "    \"description\" : \"by Sainsbury's blackberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Strawberries, Taste the Difference 300g\",\n" +
                "    \"kcal_per_100g\" : 33,\n" +
                "    \"unit_price\" : 2.50,\n" +
                "    \"description\" : \"Ttd strawberries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Cherry Punnet 200g\",\n" +
                "    \"kcal_per_100g\" : 52,\n" +
                "    \"unit_price\" : 1.50,\n" +
                "    \"description\" : \"Cherries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Mixed Berries 300g\",\n" +
                "    \"unit_price\" : 3.50,\n" +
                "    \"description\" : \"by Sainsbury's mixed berries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Mixed Berry Twin Pack 200g\",\n" +
                "    \"unit_price\" : 2.75,\n" +
                "    \"description\" : \"Mixed Berries\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Redcurrants 150g\",\n" +
                "    \"kcal_per_100g\" : 71,\n" +
                "    \"unit_price\" : 2.50,\n" +
                "    \"description\" : \"by Sainsbury's redcurrants\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Cherry Punnet, Taste the Difference 200g\",\n" +
                "    \"kcal_per_100g\" : 48,\n" +
                "    \"unit_price\" : 2.50,\n" +
                "    \"description\" : \"Cherry Punnet\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Blackcurrants 150g\",\n" +
                "    \"unit_price\" : 1.75,\n" +
                "    \"description\" : \"Union Flag \"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's British Cherry & Strawberry Pack 600g\",\n" +
                "    \"unit_price\" : 4.00,\n" +
                "    \"description\" : \"British Cherry & Strawberry Mixed Pack\"\n" +
                "  } ],\n" +
                "  \"total\" : {\n" +
                "    \"gross\" : 39.50,\n" +
                "    \"vat\" : 6.58\n" +
                "  }\n" +
                "}";
    }
}
