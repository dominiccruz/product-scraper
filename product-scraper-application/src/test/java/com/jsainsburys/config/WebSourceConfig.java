package com.jsainsburys.config;

import com.jsainsburys.parser.source.Source;
import com.jsainsburys.parser.source.WebSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WebSourceConfig {

    @Bean
    public Source source(){
        return new WebSource(10000);
    }

}
