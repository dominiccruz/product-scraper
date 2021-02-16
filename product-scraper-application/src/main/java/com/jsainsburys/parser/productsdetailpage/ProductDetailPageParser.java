package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.parser.JSoupParser;
import com.jsainsburys.parser.source.Source;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ProductDetailPageParser extends JSoupParser {

    private ProductDetailPageTitleParser productDetailPageTitleParser;
    private ProductDetailPagePriceParser productDetailPagePriceParser;

    public ProductDetailPageParser(Source source,
                                   ProductDetailPageTitleParser productDetailPageTitleParser,
                                   ProductDetailPagePriceParser productDetailPagePriceParser) {
        super(source);
        this.productDetailPageTitleParser = productDetailPageTitleParser;
        this.productDetailPagePriceParser = productDetailPagePriceParser;
    }

    public Product parse(String url) throws IOException {
        return null;
    }
}