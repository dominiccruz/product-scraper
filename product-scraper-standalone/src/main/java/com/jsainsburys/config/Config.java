package com.jsainsburys.config;

import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPagePriceParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageTitleParser;
import com.jsainsburys.parser.source.Source;
import com.jsainsburys.parser.source.WebSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${product.list.page.selector}")
    String listPageSelector;

    @Value("${product.detail.page.price.selector}")
    String priceSelector;

    @Value("${product.detail.page.title.selector}")
    String titleSelector;

    @Bean
    public Source source(@Value("${timeout.value}") int timeOut){
        return new WebSource(timeOut);
    }

    @Bean
    ProductListPageParser productListPageParser(Source source){
        return new ProductListPageParser(source, listPageSelector);
    }

    @Bean
    ProductDetailPageParser productDetailPageParser(Source source,
                                                    ProductDetailPageTitleParser productDetailPageTitleParser,
                                                    ProductDetailPagePriceParser productDetailPagePriceParser) {
        return new ProductDetailPageParser(source, productDetailPageTitleParser, productDetailPagePriceParser);
    }

    @Bean
    ProductDetailPageTitleParser productDetailPageTitleParser(Source source){
        return new ProductDetailPageTitleParser(titleSelector);
    }

    @Bean
    ProductDetailPagePriceParser productDetailPagePriceParser(){
        return new ProductDetailPagePriceParser(priceSelector);
    }

}
