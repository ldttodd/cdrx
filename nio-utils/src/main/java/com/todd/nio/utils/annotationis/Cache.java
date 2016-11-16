package com.todd.nio.utils.annotationis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
	/**
	 * 单位 秒
	 * @return
	 */
	public long time() default 60;
}
