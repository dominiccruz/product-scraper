package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.config.LocalSourceConfig;
import com.jsainsburys.config.ProductDetailPageConfig;
import com.jsainsburys.core.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@Import({ProductDetailPageConfig.class, LocalSourceConfig.class})
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-it.properties")
public class ProductDetailPageParserIntegrationTest {

    private static final String mixedBerriesMissingNutritionPage = "testData/sainsbury/products/MixedBerriesMissingNutrition.html";
    private static final String blueberriesProductPage = "testData/sainsbury/products/BlueberriesProductPage.html";

    @Autowired
    ProductDetailPageParser productDetailPageParser;

    @Test
    public void parseProductPage() throws Exception {
        //Act
        Optional<Product> parsedProduct = productDetailPageParser.parse(blueberriesProductPage);

        //Assert
        assertEquals(parsedProduct.isPresent(), true);

        Product product = parsedProduct.get();
        assertThat(product.getProductTitle().getTitle(), is("Sainsbury's Blueberries 200g"));
        assertThat(product.getProductPrice().getValue(), is(BigDecimal.valueOf(1.75)));
        assertThat(product.getProductDescription().getDescription(), is("by Sainsbury's blueberries"));
        assertThat(product.getProductNutrition().getNutrition(), is(45));
    }

    @Test
    public void parseProductPageWithNutritionMissing() throws Exception {
        //Act
        Optional<Product> parsedProduct = productDetailPageParser.parse(mixedBerriesMissingNutritionPage);

        //Assert
        assertEquals(parsedProduct.isPresent(), true);

        Product product = parsedProduct.get();
        assertThat(product.getProductTitle().getTitle(), is("Sainsbury's Mixed Berries 300g"));
        assertThat(product.getProductPrice().getValue(), is(BigDecimal.valueOf(3.50).setScale(2)));
        assertThat(product.getProductDescription().getDescription(), is("by Sainsbury's mixed berries"));
        assertThat(product.getProductNutrition(), nullValue());
    }

}