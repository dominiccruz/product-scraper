package com.jsainsburys;

import com.jsainsburys.parser.productlist.ProductListPageParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CommandLineClient implements ApplicationRunner {

    @Value("${source.url}")
    private String sourceUrl;

    @Autowired
    ProductListPageParser productListPageParser;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> parse = productListPageParser.parse(sourceUrl);
        parse.forEach(System.out::println);
    }
}