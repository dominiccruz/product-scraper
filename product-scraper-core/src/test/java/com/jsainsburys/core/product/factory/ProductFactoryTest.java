package com.jsainsburys.core.product.factory;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.*;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ProductFactoryTest {

    @Test
    public void createProduct() {
        //Arrange
        ProductFactory productFactory = new ProductFactory();
        ProductTitle mockProductTitle = mock(ProductTitle.class);
        Money mockMoney = mock(Money.class);
        ProductDescription mockProductDescription = mock(ProductDescription.class);
        ProductNutrition mockProductNutrition = mock(ProductNutrition.class);

        //Act
        Optional<Product> newProduct = productFactory.createProduct(mockProductTitle, mockMoney, mockProductDescription, mockProductNutrition);

        //Assert
        assertThat(newProduct.isPresent(), is(true));
        Product product = newProduct.get();
        assertThat(product.getProductTitle(), is(mockProductTitle));
        assertThat(product.getProductPrice(), is(mockMoney));
        assertThat(product.getProductDescription(), is(mockProductDescription));
        assertThat(product.getProductNutrition(), is(mockProductNutrition));
    }

    @Test
    public void createProduct_returnEmpty_ifTitleIsNull() {
        //Arrange
        ProductFactory productFactory = new ProductFactory();
        ProductTitle mockProductTitle = null;
        Money mockMoney = mock(Money.class);
        ProductDescription mockProductDescription = mock(ProductDescription.class);
        ProductNutrition mockProductNutrition = mock(ProductNutrition.class);

        //Act
        Optional<Product> newProduct = productFactory.createProduct(mockProductTitle, mockMoney, mockProductDescription, mockProductNutrition);

        //Assert
        assertThat(newProduct.isPresent(), is(false));
    }

    @Test
    public void createProduct_returnEmpty_ifPriceIsNull() {
        //Arrange
        ProductFactory productFactory = new ProductFactory();
        ProductTitle mockProductTitle = mock(ProductTitle.class);
        Money mockMoney = null;
        ProductDescription mockProductDescription = mock(ProductDescription.class);
        ProductNutrition mockProductNutrition = mock(ProductNutrition.class);

        //Act
        Optional<Product> newProduct = productFactory.createProduct(mockProductTitle, mockMoney, mockProductDescription, mockProductNutrition);

        //Assert
        assertThat(newProduct.isPresent(), is(false));
    }
}