package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductDescription;
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
    private final ProductDetailPageDescriptionParser productDetailPageDescriptionParser;
    private final ProductDetailPageNutritionParser productDetailPageNutritionParser;

    public ProductDetailPageParser(Source source,
                                   ProductDetailPageTitleParser productDetailPageTitleParser,
                                   ProductDetailPagePriceParser productDetailPagePriceParser,
                                   ProductDetailPageDescriptionParser productDetailPageDescriptionParser,
                                   ProductDetailPageNutritionParser productDetailPageNutritionParser) {
        super(source);
        this.productDetailPageTitleParser = productDetailPageTitleParser;
        this.productDetailPagePriceParser = productDetailPagePriceParser;
        this.productDetailPageDescriptionParser = productDetailPageDescriptionParser;
        this.productDetailPageNutritionParser = productDetailPageNutritionParser;
    }

    public Product parse(String url) throws Exception {
        Document document = getDocument(url);
        Optional<ProductTitle> productTitle = productDetailPageTitleParser.getProductTitle(document);
        Optional<Money> productPrice = productDetailPagePriceParser.getProductPrice(document);
        Optional<ProductDescription> productDescription = productDetailPageDescriptionParser.getProductDescription(document);
        Optional<ProductNutrition> productNutrition = productDetailPageNutritionParser.getProductNutrition(document);

        if(!productTitle.isPresent()) {
            log.info("Product Title is not available on url: " + url.toString());
        }
        if(!productPrice.isPresent()) {
            log.info("Product Price is not available on url: " + url.toString());
        }
        if(!productDescription.isPresent()) {
            log.info("Product Description is not available on url: " + url.toString());
        }
        if(!productNutrition.isPresent()) {
            log.info("Product Nutrition is not available on url: " + url.toString());
        }

        return new Product(productTitle.orElse(null), productPrice.orElse(null), productDescription.orElse(null), productNutrition.orElse(null));
    }

}