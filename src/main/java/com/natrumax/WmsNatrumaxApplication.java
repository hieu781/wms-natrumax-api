package com.natrumax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WmsNatrumaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmsNatrumaxApplication.class, args);
    }

}
