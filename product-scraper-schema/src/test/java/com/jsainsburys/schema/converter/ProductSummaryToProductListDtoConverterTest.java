package com.jsainsburys.schema.converter;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.core.product.summary.ProductSummary;
import com.jsainsburys.core.product.summary.Totals;
import com.jsainsburys.schema.ProductDto;
import com.jsainsburys.schema.ProductListDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class ProductSummaryToProductListDtoConverterTest {

    @Test
    public void convert() {
        //Arrange
        ProductSummary testProductSummary = getTestProductSummary();

        ProductToProductDtoConverter mockProductToProductDtoConverter = mock(ProductToProductDtoConverter.class);
        ProductDto mockProductDto1 = mock(ProductDto.class);
        ProductDto mockProductDto2 = mock(ProductDto.class);
        when(mockProductToProductDtoConverter.convert(any(Product.class)))
                .thenReturn(Optional.of(mockProductDto1))
                .thenReturn(Optional.of(mockProductDto2));

        ProductSummaryToProductListDtoConverter productSummaryToProductListDtoConverter = new ProductSummaryToProductListDtoConverter(mockProductToProductDtoConverter);

        //Act
        Optional<ProductListDto> productListValues = productSummaryToProductListDtoConverter.convert(testProductSummary);

        //Assert
        assertThat(productListValues.isPresent(), is(true));

        verify(mockProductToProductDtoConverter, times(2)).convert(any(Product.class));

        ProductListDto productList = productListValues.get();
        assertThat(productList.getTotal().getGross(), is(testProductSummary.getTotals().getGross().getValue()));

        List<ProductDto> results = productList.getResults();
        assertThat(results.size(), is(2));
        assertThat(results.contains(mockProductDto1), is(true));
        assertThat(results.contains(mockProductDto2), is(true));
    }

    @Test
    public void convert_returnEmpty_ifProductsListEmpty() {
        //Arrange
        List<Product> products = new ArrayList<>();
        ProductSummary productSummary = ProductSummary.builder().products(products).build();
        ProductToProductDtoConverter mockProductToProductDtoConverter = mock(ProductToProductDtoConverter.class);

        ProductSummaryToProductListDtoConverter productToProductListConverter = new ProductSummaryToProductListDtoConverter(mockProductToProductDtoConverter);

        //Act
        Optional<ProductListDto> productListValues = productToProductListConverter.convert(productSummary);

        //Assert
        assertThat(productListValues.isPresent(), is(false));
    }

    @Test
    public void convert_returnEmpty_ifProductsListNull() {
        //Arrange
        ProductSummary productSummary = ProductSummary.builder().products(null).build();
        ProductToProductDtoConverter mockProductToProductDtoConverter = mock(ProductToProductDtoConverter.class);

        ProductSummaryToProductListDtoConverter productToProductListConverter = new ProductSummaryToProductListDtoConverter(mockProductToProductDtoConverter);

        //Act
        Optional<ProductListDto> productListValues = productToProductListConverter.convert(productSummary);

        //Assert
        assertThat(productListValues.isPresent(), is(false));
    }

    private Product getTestProduct() {
        ProductTitle productTitle = new ProductTitle("title");

        BigDecimal price = BigDecimal.valueOf(10.30).setScale(2, RoundingMode.HALF_UP);
        Money productPrice = new Money(price);

        ProductDescription productDescription = new ProductDescription("description");

        ProductNutrition productNutrition = new ProductNutrition("33");

        return new Product(productTitle, productPrice, productDescription, productNutrition);
    }

    private ProductSummary getTestProductSummary() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(getTestProduct());
        productList.add(getTestProduct());

        return ProductSummary
                .builder()
                .products(productList)
                .totals(Totals
                        .builder()
                        .gross(new Money(new BigDecimal("5.00")))
                        .vat(new Money(new BigDecimal("0.83")))
                        .build())
                .build();
    }

}