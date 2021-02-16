package com.jsainsburys.service;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.summary.Total;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TotalServiceTest {

    TotalService totalService = new TotalService();

    @Test
    public void calculateTotal() {
        Money expectedVat = new Money(new BigDecimal("0.83"));
        Money expectedGross = new Money(new BigDecimal("5.00"));

        Total total = totalService.getTotal(getTestProducts());

        assertEquals(total.getGross(), expectedGross);
        assertEquals(total.getVat(), expectedVat);
    }

    private List<Product> getTestProducts() {
        BigDecimal price = BigDecimal.valueOf(2.5).setScale(2, RoundingMode.HALF_UP);
        Money productPrice = new Money(price);
        return Arrays.asList(
                Product.builder().productPrice(productPrice).build(),
                Product.builder().productPrice(productPrice).build());
    }

}