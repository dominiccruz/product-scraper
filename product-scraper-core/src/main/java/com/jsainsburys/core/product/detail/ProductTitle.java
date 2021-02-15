package com.jsainsburys.core.product.detail;

import org.springframework.util.Assert;

public class ProductTitle {
    private final String title;

    public ProductTitle(String title) {
        Assert.hasText(title, "Product Title cannot be NULL");
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}