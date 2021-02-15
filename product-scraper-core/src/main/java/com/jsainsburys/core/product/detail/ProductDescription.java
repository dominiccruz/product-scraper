package com.jsainsburys.core.product.detail;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
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
}