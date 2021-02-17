package com.jsainsburys.config;

import com.jsainsburys.core.product.factory.ProductFactory;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageDescriptionParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageNutritionParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPagePriceParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageTitleParser;
import com.jsainsburys.parser.source.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
public class ProductDetailPageConfig {

    @Value("${product.detail.page.price.selector}")
    String priceSelector;

    @Value("${product.detail.page.title.selector}")
    String titleSelector;

    @Value("${product.detail.page.nutrition.selector}")
    String nutritionSelector;

    @Value("${product.detail.page.description.selector}")
    String descriptionSelector;

    @Bean
    ProductDetailPageTitleParser productDetailPageTitleParser(){
        return new ProductDetailPageTitleParser(titleSelector);
    }

    @Bean
    ProductDetailPagePriceParser productDetailPagePriceParser(){
        return new ProductDetailPagePriceParser(priceSelector);
    }

    @Bean
    ProductDetailPageDescriptionParser productDetailPageDescriptionParser(List<String> descriptionSubSelectors) {
        return new ProductDetailPageDescriptionParser(descriptionSelector, descriptionSubSelectors);
    }

    @Bean
    public List<String> descriptionSubSelectors(@Value("${product.detail.page.description.sub.selector1}") String descriptionSubSelector1,
                                                @Value("${product.detail.page.description.sub.selector2}") String descriptionSubSelector2,
                                                @Value("${product.detail.page.description.sub.selector3}") String descriptionSubSelector3,
                                                @Value("${product.detail.page.description.sub.selector4}") String descriptionSubSelector4) {
        List<String> descriptionSubSelectors = new ArrayList<>();
        descriptionSubSelectors.add(descriptionSubSelector1);
        descriptionSubSelectors.add(descriptionSubSelector2);
        descriptionSubSelectors.add(descriptionSubSelector3);
        descriptionSubSelectors.add(descriptionSubSelector4);
        return descriptionSubSelectors;
    }

    @Bean
    public List<String> nutritionSubSelectors(@Value("${product.detail.page.nutrition.sub.selector1}") String nutritionSubSelector1,
                                              @Value("${product.detail.page.nutrition.sub.selector2}") String nutritionSubSelector2) {
        List<String> nutritionSubSelectors = new ArrayList<>();
        nutritionSubSelectors.add(nutritionSubSelector1);
        nutritionSubSelectors.add(nutritionSubSelector2);
        return nutritionSubSelectors;
    }

    @Bean
    public ProductDetailPageNutritionParser productDetailPageNutritionParser(List<String> nutritionSubSelectors) {
        return new ProductDetailPageNutritionParser(nutritionSelector, nutritionSubSelectors);
    }

    @Bean
    ProductFactory productFactory(){
        return new ProductFactory();
    }

    @Bean
    ProductDetailPageParser productDetailPageParser(Source source,
                                                    ProductDetailPageTitleParser productDetailPageTitleParser,
                                                    ProductDetailPagePriceParser productDetailPagePriceParser,
                                                    ProductDetailPageDescriptionParser productDetailPageDescriptionParser,
                                                    ProductDetailPageNutritionParser productDetailPageNutritionParser,
                                                    ProductFactory productFactory) {
        return new ProductDetailPageParser(source,
                productDetailPageTitleParser,
                productDetailPagePriceParser,
                productDetailPageDescriptionParser,
                productDetailPageNutritionParser,
                productFactory);
    }
}
