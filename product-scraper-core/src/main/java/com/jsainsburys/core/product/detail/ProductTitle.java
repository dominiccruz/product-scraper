package com.jsainsburys.core.product.detail;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class ProductTitle {
    private final String title;

    public ProductTitle(String title) {
        Assert.hasText(title, "Product Title cannot be NULL");
        this.title = title;
    }

}