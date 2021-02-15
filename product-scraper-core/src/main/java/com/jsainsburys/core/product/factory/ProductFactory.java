package com.jsainsburys.core.product.factory;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductPrice;
import com.jsainsburys.core.product.detail.ProductTitle;

import java.util.Optional;

/**
 * Factory to create a Product Object
 */
public class ProductFactory {


    /**
     * Creates a new Product Object if valid data is provided.
     * Requires Product Title and Product and Product Description
     *
     * @return a new Optional Product instance.
     * Empty if Product Title or Product Price or Product Description are NULL
     */
    public Optional<Product> createProduct(ProductTitle productTitle,
                                           ProductPrice productPrice,
                                           ProductDescription productDescription,
                                           ProductNutrition productNutrition) {
        Optional<Product> product = Optional.empty();
        if(productTitle != null && productPrice != null && productDescription !=null) {
            product = Optional.of(new Product(productTitle, productPrice, productDescription, productNutrition));
        }
        return product;
    }
}