package com.sp.spproj.spring.source.ioc;

import com.sp.spproj.spring.source.base.FirstDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 *
 * @author: Jianjun
 * @date: 2020-10-09 15:30
 * @version:
 */
@Configuration
@ComponentScan(basePackages = "com.sp.spproj.spring.source.ioc")
public class MainConfig {

    @Bean
    public FirstDemo getFirstDemo(){
        return new FirstDemo(3, "张三");
    }
}
