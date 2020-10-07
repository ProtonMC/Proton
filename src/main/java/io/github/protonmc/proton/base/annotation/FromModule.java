package io.github.protonmc.proton.base.annotation;

import io.github.protonmc.proton.module.ProtonModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
public @interface FromModule {
    Class<? extends ProtonModule>[] value();
}
