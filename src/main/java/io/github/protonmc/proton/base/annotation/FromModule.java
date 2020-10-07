package io.github.protonmc.proton.base.annotation;

import io.github.protonmc.proton.module.ProtonModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface FromModule {
    Class<? extends ProtonModule>[] value();
}
