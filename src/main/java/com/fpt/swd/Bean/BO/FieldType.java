package com.fpt.swd.Bean.BO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldType {
    int exportIndex() default -1;

    boolean exportExtendField() default false;

    boolean isGroup() default false;

    boolean isSum() default false;

    boolean isCount() default false;

    int importIndex() default -1;

    boolean importExtendField() default false;
}