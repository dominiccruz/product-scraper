package com.jsainsburys.core.product.detail;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MoneyTest {

    @Test
    public void priceIsRoundedUp() {
        //Arrange
        //Act
        Money expected = new Money(new BigDecimal("95.03"));
        Money result = new Money(new BigDecimal("95.0287"));

        //Assert
        assertEquals(expected.getValue(), result.getValue());
    }

    @Test
    public void priceIsRoundedDown() {
        //Arrange
        //Act
        Money expected = new Money(new BigDecimal("90.78"));
        Money result = new Money(new BigDecimal("90.784"));

        //Assert
        assertEquals(expected.getValue(), result.getValue());
    }
}