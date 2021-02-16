package com.jsainsburys.core.product.summary;

import com.jsainsburys.core.product.detail.Money;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Total {
    private Money gross;
    private Money vat;

    public Total(Money gross, Money vat) {
        this.gross = gross;
        this.vat = vat;
    }

    public Money getGross() {
        return gross;
    }

    public Money getVat() {
        return vat;
    }
}
