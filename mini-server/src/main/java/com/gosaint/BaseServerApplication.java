package com.gosaint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gosaint.dao")
public class BaseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServerApplication.class, args);
    }

}
