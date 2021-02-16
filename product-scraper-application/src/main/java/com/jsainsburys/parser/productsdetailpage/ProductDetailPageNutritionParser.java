package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.detail.ProductNutrition;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductDetailPageNutritionParser {

    private final String selector;
    private final List<String> subSelectors;

    public ProductDetailPageNutritionParser(String selector,
                                            List<String> nutritionSubSelectors) {
        this.selector = selector;
        this.subSelectors = nutritionSubSelectors;
    }

    public Optional<ProductNutrition> getProductNutrition(Document document) {
        Optional<ProductNutrition> productNutrition = Optional.empty();

        Elements productNutritionElement = document.select(selector);

        for (String subSelector : subSelectors) {
            Elements nutritionElement = productNutritionElement.select(subSelector);
            try {
                String nutrition = nutritionElement.text().replace("kcal", "");
                productNutrition = Optional.of(new ProductNutrition(nutrition));
            } catch (NumberFormatException e) {
                log.debug("Cannot create Product Nutrition for product using sub selector: " + subSelector, e);
            }

            if (productNutrition.isPresent())
                break;
        }

        return productNutrition;
    }
}