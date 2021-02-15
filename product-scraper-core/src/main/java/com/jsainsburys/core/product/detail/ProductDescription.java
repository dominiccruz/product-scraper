package com.jsainsburys.core.product.detail;

import org.springframework.util.Assert;

public class ProductDescription {

    private final String description;

    /**
     * Creates a valid Product Description Object
     *
     * @param description
     */
    public ProductDescription(String description) {
        Assert.hasText(description, "Product Description cannot be NULL");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}