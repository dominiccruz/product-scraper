package com.jsainsburys.standalone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsainsburys.core.product.summary.ProductSummary;
import com.jsainsburys.schema.ProductListDto;
import com.jsainsburys.schema.converter.ProductSummaryToProductListDtoConverter;
import com.jsainsburys.service.ScraperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

@Slf4j
public class Scraper {

    ScraperService scraperService;
    ProductSummaryToProductListDtoConverter productSummaryToProductListDtoConverter;

    public Scraper(ScraperService scraperService, ProductSummaryToProductListDtoConverter productSummaryToProductListDtoConverter) {
        this.scraperService = scraperService;
        this.productSummaryToProductListDtoConverter = productSummaryToProductListDtoConverter;
    }

    public String scrape(String sourceUrl) {
        String productJson = "";
        try {
            ProductSummary productSummary = scraperService.scrape(sourceUrl);
            Optional<ProductListDto> productList = productSummaryToProductListDtoConverter.convert(productSummary);

            if (productList.isPresent()) {
                ObjectMapper objectMapper = new ObjectMapper();
                productJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(productList.get());
            } else {
                log.info("No Products Found");
            }
        } catch (MalformedURLException e) {
            log.error("Invalid url found during processing. Stopping application.", e);
            System.exit(1);
        } catch (IOException e) {
            log.error("Cannot retrieve product listings. Please check network connection", e);
            System.exit(1);
        } catch (Exception e) {
            log.error("Error occurred during processing", e);
            System.exit(1);
        }
        return productJson;
    }
}
