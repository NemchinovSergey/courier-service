package com.nsergey.courier.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@MapperScan("com.nsergey.db")
@Configuration
public class PersistenceConfig {

    @PostConstruct
    public void init() {
        log.info("Persistence config");
    }
}
