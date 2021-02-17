package com.jsainsburys.config;

import com.jsainsburys.parser.source.LocalSource;
import com.jsainsburys.parser.source.Source;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class LocalSourceConfig {

    @Bean
    public Source source(){
        return new LocalSource();
    }

}
