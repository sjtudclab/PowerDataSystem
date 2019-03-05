package com.dclab.datasystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dclab.datasystem.mapper")
public class DatasyetemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasyetemApplication.class, args);
    }

}
