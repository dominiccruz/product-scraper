package com.jsainsburys;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.parser.productlist.ProductListPageParser;
import com.jsainsburys.parser.productsdetailpage.ProductDetailPageParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CommandLineClient implements ApplicationRunner {

    @Value("${source.url}")
    private String sourceUrl;

    @Autowired
    ProductListPageParser productListPageParser;

    @Autowired
    ProductDetailPageParser productDetailPageParser;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> productUrls = productListPageParser.parse(sourceUrl);
        List<Product> products = productUrls
                .stream()
                .map(productUrl -> {
                    try {
                        return productDetailPageParser.parse(productUrl);
                    } catch (IOException e) {
                        System.exit(1);
                    }
                    return null;
                })
                .collect(Collectors.toList());

        System.out.println(products
                .stream()
                .map(p -> p.getProductPrice().getValue())
                .collect(Collectors.toList()));
    }
}