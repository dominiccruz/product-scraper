package com.jsainsburys.config;

import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import com.jsainsburys.parser.source.Source;
import com.jsainsburys.service.ScraperService;
import com.jsainsburys.service.TotalService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({WebSourceConfig.class, ProductDetailPageConfig.class})
@TestConfiguration
public class ScraperServiceConfig {

    @Value("${product.list.page.selector}")
    String listPageSelector;

    @Bean
    ProductListPageParser productListPageParser(Source source) {
        return new ProductListPageParser(source, listPageSelector);
    }

    @Bean
    ScraperService scraperService(ProductListPageParser productListPageParser,
                                  ProductDetailPageParser productDetailPageParser,
                                  TotalService totalService) {
        return new ScraperService(productListPageParser, productDetailPageParser, totalService);
    }

    @Bean
    TotalService totalService(){
        return new TotalService();
    }

}
