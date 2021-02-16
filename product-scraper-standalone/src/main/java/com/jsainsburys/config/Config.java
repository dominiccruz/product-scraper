package com.jsainsburys.config;

import com.jsainsburys.parser.source.Source;
import com.jsainsburys.parser.source.WebSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Source source(@Value("${timeout.value}") int timeOut){
        return new WebSource(timeOut);
    }
}
