package com.jsainsburys.core.product;

import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductTitle;

public class Product {
    private final ProductTitle productTitle;
    private final Money money;
    private final ProductDescription productDescription;
    private final ProductNutrition productNutrition;

    public Product(ProductTitle productTitle,
                   Money money,
                   ProductDescription productDescription,
                   ProductNutrition productNutrition) {
        this.productTitle = productTitle;
        this.money = money;
        this.productDescription = productDescription;
        this.productNutrition = productNutrition;
    }

    public ProductTitle getProductTitle() {
        return productTitle;
    }

    public Money getProductPrice() {
        return money;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public ProductNutrition getProductNutrition() {
        return productNutrition;
    }
}