package com.jsainsburys.schema.converter;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.schema.ProductDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProductToProductDtoConverterTest {

    @Test
    public void convert() {
        //Arrange
        Product testProduct = getTestProduct();
        ProductToProductDtoConverter productToProductDtoConverter = new ProductToProductDtoConverter();

        //Act
        Optional<ProductDto> productResult = productToProductDtoConverter.convert(testProduct);

        //Assert
        assertThat(productResult.isPresent(), is(true));
        ProductDto productDto = productResult.get();
        assertThat(productDto.getTitle(), is(testProduct.getProductTitle().getTitle()));
        assertThat(productDto.getUnitPrice(), is(testProduct.getProductPrice().getValue()));
        assertThat(productDto.getDescription(), is(testProduct.getProductDescription().getDescription()));
        assertThat(productDto.getKcalPer100g(), is(testProduct.getProductNutrition().getNutrition()));
    }

    @Test
    public void convert_returnEmpty_ifProductIsNUll() {
        //Arrange
        ProductToProductDtoConverter productToProductDtoConverter = new ProductToProductDtoConverter();

        //Act
        Optional<ProductDto> productResult = productToProductDtoConverter.convert(null);

        //Assert
        assertThat(productResult.isPresent(), is(false));
    }

    private Product getTestProduct() {
        ProductTitle productTitle = new ProductTitle("title");

        BigDecimal price = BigDecimal.valueOf(10.30).setScale(2, RoundingMode.HALF_UP);
        Money productPrice = new Money(price);

        ProductDescription productDescription = new ProductDescription("description");

        ProductNutrition productNutrition = new ProductNutrition("33");

        return new Product(productTitle, productPrice, productDescription, productNutrition);
    }
}