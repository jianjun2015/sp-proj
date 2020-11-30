package com.sp.spproj.excel;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelCell {
    /**
     * 当值为null时要显示的值 default StringUtils.EMPTY
     *
     * @return
     */
    String defaultValue() default StringUtils.EMPTY;
}
