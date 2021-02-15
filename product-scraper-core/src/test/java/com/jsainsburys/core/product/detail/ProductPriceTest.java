package com.jsainsburys.core.product.detail;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductPriceTest {

    @Test
    public void priceIsRoundedUp() {
        ProductPrice expected = new ProductPrice(new BigDecimal("95.03"));
        ProductPrice result = new ProductPrice(new BigDecimal("95.0287"));

        assertEquals(expected.getPrice(), result.getPrice());
    }

    @Test
    public void priceIsRoundedDown() {
        ProductPrice expected = new ProductPrice(new BigDecimal("90.78"));
        ProductPrice result = new ProductPrice(new BigDecimal("90.784"));

        assertEquals(expected.getPrice(), result.getPrice());
    }
}