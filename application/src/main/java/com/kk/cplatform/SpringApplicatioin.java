package com.kk.cplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 * @author kkorkk
 * */

@SpringBootApplication
@MapperScan("com.kk.cplatform.mapper")
public class SpringApplicatioin{

    public static void main(String[] args){
        SpringApplication.run(SpringApplicatioin.class,args);
    }

}
