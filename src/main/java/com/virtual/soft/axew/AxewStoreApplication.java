package com.virtual.soft.axew;

import com.virtual.soft.axew.utils.PropertiesUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxewStoreApplication {

    public static void main (String[] args) {
        PropertiesUtils.loadByDotEnv();
        SpringApplication.run(AxewStoreApplication.class, args);
    }

}
