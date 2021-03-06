package com.jsainsburys.core.product.detail;

import lombok.Getter;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Money {

    private final BigDecimal value;

    public Money(BigDecimal value) {
        Assert.notNull(value, "Product Price cannot be null");
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

}