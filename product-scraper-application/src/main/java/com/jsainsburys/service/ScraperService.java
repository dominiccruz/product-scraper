package com.jsainsburys.service;

import com.jsainsburys.core.product.summary.ProductSummary;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScraperService {

    public ProductSummary scrape(String url) {
        try {

        } catch (Exception e){
            log.error("Error occurred during processing", e);
            throw e;
        }
        return null;
    }
}
