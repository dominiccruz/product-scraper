package com.jsainsburys.core.product;

import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductPrice;
import com.jsainsburys.core.product.detail.ProductTitle;

public class Product {
    private final ProductTitle productTitle;
    private final ProductPrice productPrice;
    private final ProductDescription productDescription;
    private final ProductNutrition productNutrition;

    public Product(ProductTitle productTitle,
                   ProductPrice productPrice,
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

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public ProductNutrition getProductNutrition() {
        return productNutrition;
    }
}