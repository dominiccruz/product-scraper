package com.jsainsburys.core.product.detail;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPrice {
    private final BigDecimal price;

    /**
     * Creates a valid Product Price Object
     *
     * @param price product price
     */
    public ProductPrice(BigDecimal price) {
        Assert.notNull(price, "Product Price cannot be null");
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getPrice() {
        return price;
    }

}