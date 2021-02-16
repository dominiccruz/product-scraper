package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.parser.JSoupParser;
import com.jsainsburys.parser.source.Source;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class ProductDetailPageParser extends JSoupParser {

    private final ProductDetailPageTitleParser productDetailPageTitleParser;
    private final ProductDetailPagePriceParser productDetailPagePriceParser;
    private final ProductDetailPageNutritionParser productDetailPageNutritionParser;

    public ProductDetailPageParser(Source source,
                                   ProductDetailPageTitleParser productDetailPageTitleParser,
                                   ProductDetailPagePriceParser productDetailPagePriceParser,
                                   ProductDetailPageNutritionParser productDetailPageNutritionParser) {
        super(source);
        this.productDetailPageTitleParser = productDetailPageTitleParser;
        this.productDetailPagePriceParser = productDetailPagePriceParser;
        this.productDetailPageNutritionParser = productDetailPageNutritionParser;
    }

    public Product parse(String url) throws IOException {
        Document document = getDocument(url);
        Optional<ProductTitle> productTitle = productDetailPageTitleParser.getProductTitle(document);
        Optional<Money> productPrice = productDetailPagePriceParser.getProductPrice(document);
        Optional<ProductNutrition> productNutrition = productDetailPageNutritionParser.getProductNutrition(document);

        return new Product(productTitle.orElse(null), productPrice.orElse(null), null, productNutrition.orElse(null));
    }

}