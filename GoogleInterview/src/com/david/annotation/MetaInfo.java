package com.david.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MetaInfo {
	
	public enum Priority {
		LOW, MEDIUM, HIGH
	}
	
	Priority priority() default Priority.MEDIUM;
	 
	String[] tags() default "";
	
	int difficulty() default 3;
 
	String createdBy() default "David Ou";
 
	String lastModified() default "03/09/2014";
}
