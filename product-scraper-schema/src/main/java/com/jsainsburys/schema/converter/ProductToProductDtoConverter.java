package com.jsainsburys.schema.converter;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.schema.ProductDto;

import java.util.Optional;

public class ProductToProductDtoConverter {

    public Optional<ProductDto> convert(Product product) {
        Optional<ProductDto> productResult = Optional.empty();
        if (product != null) {
            String title = product.getProductTitle().getTitle();
            String description = product.getProductDescription().getDescription();
            Integer nutrition = null;
            if (product.getProductNutrition() != null) {
                nutrition = product.getProductNutrition().getNutrition();
            }
            productResult = Optional.of(new ProductDto(title, nutrition, product.getProductPrice().getPrice(), description));
        }
        return productResult;
    }
}