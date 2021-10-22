package com.zzy;

import com.zzy.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FluxApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    public static void main(String[] args) {
        SpringApplication.run(FluxApplication.class, args);
        LOGGER.info("flux 启动成功");
    }

}
