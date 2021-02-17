package com.jsainsburys.service;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.summary.Total;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
public class TotalService {

    private BigDecimal vat = BigDecimal.valueOf(6);

    public Total getTotal(List<Product> products) {
        Money grossTotal = getGrossTotal(products);
        Money vat = getVat(grossTotal);

        return Total.builder().gross(grossTotal).vat(vat).build();
    }

    private Money getGrossTotal(List<Product> products) {
        return new Money(products
                .stream()
                .filter(product -> product.getProductPrice() != null)
                .map(product -> product.getProductPrice().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    private Money getVat(Money money) {
        return new Money(money.getValue()
                        .divide(vat, 2, RoundingMode.HALF_UP));
    }
}
