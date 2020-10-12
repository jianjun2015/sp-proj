package com.sp.spproj.spring.source.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: wangjianjun
 * @date:2020-10-12 11:15
 */
public class DemoApplicationContext extends AnnotationConfigApplicationContext {

    @Override
    protected void initPropertySources() {
        getEnvironment().setRequiredProperties("demo");
    }

    public DemoApplicationContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }
}
