package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.ProductTitle;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

@Slf4j
public class ProductDetailPageTitleParser {

    private final String selector;

    public ProductDetailPageTitleParser(String selector) {
        this.selector = selector;
    }

    public Optional<ProductTitle> getProductTitle(Document document){
        Elements productTitleElement = document.select(selector);
        Optional<ProductTitle> productTitle;

        try {
            ProductTitle title = new ProductTitle(productTitleElement.text());
            productTitle = Optional.of(title);
        } catch (IllegalArgumentException e) {
            log.debug("Cannot create Product Title for product" , e);
            throw e;
        }

        return productTitle;
    }
}