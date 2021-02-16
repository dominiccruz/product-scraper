package com.jsainsburys.config;

import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageNutritionParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPagePriceParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageTitleParser;
import com.jsainsburys.parser.source.Source;
import com.jsainsburys.parser.source.WebSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {

    @Value("${product.list.page.selector}")
    String listPageSelector;

    @Value("${product.detail.page.price.selector}")
    String priceSelector;

    @Value("${product.detail.page.title.selector}")
    String titleSelector;

    @Value("${product.detail.page.nutrition.selector}")
    String nutritionSelector;

    @Bean
    public Source source(@Value("${timeout.value}") int timeOut){
        return new WebSource(timeOut);
    }

    @Bean
    ProductListPageParser productListPageParser(Source source){
        return new ProductListPageParser(source, listPageSelector);
    }

    @Bean
    ProductDetailPageTitleParser productDetailPageTitleParser(Source source){
        return new ProductDetailPageTitleParser(titleSelector);
    }

    @Bean
    ProductDetailPagePriceParser productDetailPagePriceParser(){
        return new ProductDetailPagePriceParser(priceSelector);
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
    public ProductDetailPageNutritionParser productDetailPageNutritionParser(List<String> nutritionSubSelectors){
        return new ProductDetailPageNutritionParser(nutritionSelector, nutritionSubSelectors);
    }

    @Bean
    ProductDetailPageParser productDetailPageParser(Source source,
                                                    ProductDetailPageTitleParser productDetailPageTitleParser,
                                                    ProductDetailPagePriceParser productDetailPagePriceParser,
                                                    ProductDetailPageNutritionParser productDetailPageNutritionParser) {
        return new ProductDetailPageParser(source, productDetailPageTitleParser, productDetailPagePriceParser,
                productDetailPageNutritionParser);
    }
}
