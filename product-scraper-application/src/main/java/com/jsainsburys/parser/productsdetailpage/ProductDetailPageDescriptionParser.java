package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.ProductDescription;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductDetailPageDescriptionParser {

    private final String selector;
    private final List<String> subSelectors;

    public ProductDetailPageDescriptionParser(String selector,
                                              List<String> descriptionSubSelectors) {
        this.selector = selector;
        this.subSelectors = descriptionSubSelectors;
    }

    public Optional<ProductDescription> getProductDescription(Document document) {
        Optional<ProductDescription> productDescription = Optional.empty();

        Elements productDescriptionElement = document.select(selector);

        for (String subSelector : subSelectors) {
            Elements descriptionElement = productDescriptionElement.select(subSelector);
            try {
                ProductDescription description = new ProductDescription(descriptionElement.text());
                productDescription = Optional.of(description);
            } catch (IllegalArgumentException e) {
                log.debug("Cannot create Product Description for product using sub selector: " + subSelector);
            }

            if(productDescription.isPresent())
                break;
        }

        return productDescription;
    }
}