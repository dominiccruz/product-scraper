package com.jsainsburys.schema.converter;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.schema.ProductDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductToProductDtoConverterTest {

    @Test
    public void convert() {
        //Arrange
        Product mockProduct = mock(Product.class);

        String title = "title";
        ProductTitle productTitle = new ProductTitle(title);
        when(mockProduct.getProductTitle()).thenReturn(productTitle);

        BigDecimal price = BigDecimal.valueOf(10.30).setScale(2, RoundingMode.HALF_UP);
        Money money = new Money(price);
        when(mockProduct.getProductPrice()).thenReturn(money);

        String description = "description";
        ProductDescription productDescription = new ProductDescription(description);
        when(mockProduct.getProductDescription()).thenReturn(productDescription);

        ProductToProductDtoConverter productToProductDtoConverter = new ProductToProductDtoConverter();

        //Act
        Optional<ProductDto> productResult = productToProductDtoConverter.convert(mockProduct);

        //Assert
        assertThat(productResult.isPresent(), is(true));
        ProductDto productDto = productResult.get();
        assertThat(productDto.getTitle(), is(title));
        assertThat(productDto.getUnitPrice(), is(money.getValue()));
        assertThat(productDto.getDescription(), is(description));
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
}