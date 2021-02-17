package com.jsainsburys.parser.productsdetailpage;

import com.jsainsburys.core.product.Product;
import com.jsainsburys.core.product.detail.Money;
import com.jsainsburys.core.product.detail.ProductDescription;
import com.jsainsburys.core.product.detail.ProductNutrition;
import com.jsainsburys.core.product.detail.ProductTitle;
import com.jsainsburys.core.product.factory.ProductFactory;
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
    private ProductFactory productFactory;

    public ProductDetailPageParser(Source source,
                                   ProductDetailPageTitleParser productDetailPageTitleParser,
                                   ProductDetailPagePriceParser productDetailPagePriceParser,
                                   ProductDetailPageDescriptionParser productDetailPageDescriptionParser,
                                   ProductDetailPageNutritionParser productDetailPageNutritionParser,
                                   ProductFactory productFactory) {
        super(source);
        this.productDetailPageTitleParser = productDetailPageTitleParser;
        this.productDetailPagePriceParser = productDetailPagePriceParser;
        this.productDetailPageDescriptionParser = productDetailPageDescriptionParser;
        this.productDetailPageNutritionParser = productDetailPageNutritionParser;
        this.productFactory = productFactory;
    }

    public Optional<Product> parse(String url) {
        Optional<Product> parsedProduct = Optional.empty();
        Document document = null;
        try {
            document = getDocument(url);
        } catch (IOException e) {
            log.info("Returning empty as failed to get product on url:" + url);
        }

        if (document != null) {
            Optional<ProductTitle> productTitle = productDetailPageTitleParser.getProductTitle(document);
            Optional<Money> productPrice = productDetailPagePriceParser.getProductPrice(document);
            Optional<ProductDescription> productDescription = productDetailPageDescriptionParser.getProductDescription(document);
            Optional<ProductNutrition> productNutrition = productDetailPageNutritionParser.getProductNutrition(document);

            if (productTitle.isEmpty()) {
                log.info("Product Title is not available on url: " + url);
            }
            if (productPrice.isEmpty()) {
                log.info("Product Price is not available on url: " + url);
            }
            if (productDescription.isEmpty()) {
                log.info("Product Description is not available on url: " + url);
            }
            if (productNutrition.isEmpty()) {
                log.info("Product Nutrition is not available on url: " + url);
            }

            parsedProduct = productFactory.createProduct(productTitle.orElse(null), productPrice.orElse(null), productDescription.orElse(null), productNutrition.orElse(null));
        }

        return parsedProduct;
    }

}