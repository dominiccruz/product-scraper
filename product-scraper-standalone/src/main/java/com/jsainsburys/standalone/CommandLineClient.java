package com.jsainsburys.standalone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineClient implements ApplicationRunner {

    @Value("${source.url}")
    private String sourceUrl;

    @Override
    public void run(ApplicationArguments args) {

    }
}