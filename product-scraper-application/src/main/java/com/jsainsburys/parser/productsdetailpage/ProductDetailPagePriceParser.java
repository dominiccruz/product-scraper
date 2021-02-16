package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.Money;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDetailPagePriceParser {

    private final String selector;
    private final Pattern pricePattern = Pattern.compile("([0-9]+.[0-9]+)");

    public ProductDetailPagePriceParser(String selector) {
        this.selector = selector;
    }

    public Optional<Money> getProductPrice(Document document){
        Optional<Money> productPrice = Optional.empty();

        Elements productPriceElement = document.select(selector);
        String data = productPriceElement.text();

        if(data != null && !data.isEmpty()) {
            Matcher matcher = pricePattern.matcher(data);

            if (matcher.find()) {
                String priceValue = matcher.group(0);
                if (priceValue != null && !priceValue.isEmpty()) {
                    BigDecimal price = new BigDecimal(priceValue);
                    productPrice = Optional.of(new Money(price));
                }
            }
        }
        return productPrice;
    }
}