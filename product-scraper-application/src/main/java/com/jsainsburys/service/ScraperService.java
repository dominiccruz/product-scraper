package com.jsainsburys.service;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.summary.ProductSummary;
import com.jsainsburys.core.product.summary.Total;
import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class ScraperService {

    private ProductListPageParser productListPageParser;
    private ProductDetailPageParser productDetailPageParser;
    private TotalService totalService;

    public ScraperService(ProductListPageParser productListPageParser,
                          ProductDetailPageParser productDetailPageParser,
                          TotalService totalService) {

        this.productListPageParser = productListPageParser;
        this.productDetailPageParser = productDetailPageParser;
        this.totalService = totalService;
    }

    public ProductSummary scrape(String url) throws Exception {
        List<String> productUrls = productListPageParser.parse(url);

        List<Product> products = productUrls.stream()
                .map(productDetailPageParser::parse)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        Total total = totalService.calculateTotal(products);

        return new ProductSummary(products, total);
    }
}
