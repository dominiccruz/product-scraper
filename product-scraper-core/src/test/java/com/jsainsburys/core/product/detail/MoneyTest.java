package com.jsainsburys.core.product.detail;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MoneyTest {

    @Test
    public void priceIsRoundedUp() {
        Money expected = new Money(new BigDecimal("95.03"));
        Money result = new Money(new BigDecimal("95.0287"));

        assertEquals(expected.getValue(), result.getValue());
    }

    @Test
    public void priceIsRoundedDown() {
        Money expected = new Money(new BigDecimal("90.78"));
        Money result = new Money(new BigDecimal("90.784"));

        assertEquals(expected.getValue(), result.getValue());
    }
}