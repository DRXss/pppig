package com.bawei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.bawei.mapper")
public class P2pApplication {
    public static void main(String[] args) {
        SpringApplication.run(P2pApplication.class,args);
    }
}
