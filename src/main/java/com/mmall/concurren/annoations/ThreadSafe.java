package com.mmall.concurren.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//用来标记线程安全类
@Target(ElementType.TYPE) //注解需要
@Retention(RetentionPolicy.SOURCE) //注解作用范围
public @interface ThreadSafe {
    String value() default "";
}
