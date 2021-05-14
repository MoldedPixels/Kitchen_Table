package com.project2.kitchentable.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* An annotation that specifies that requested functionality should be restricted to Admins */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Admin {

}
