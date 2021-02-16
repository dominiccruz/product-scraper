package com.jsainsburys.core.product;

import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductTitle;
import lombok.Builder;

@Builder
public class Product {
    private final ProductTitle productTitle;
    private final Money productPrice;
    private final ProductDescription productDescription;
    private final ProductNutrition productNutrition;

    public Product(ProductTitle productTitle,
                   Money productPrice,
                   ProductDescription productDescription,
                   ProductNutrition productNutrition) {
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productNutrition = productNutrition;
    }

    public ProductTitle getProductTitle() {
        return productTitle;
    }

    public Money getProductPrice() {
        return productPrice;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public ProductNutrition getProductNutrition() {
        return productNutrition;
    }
}