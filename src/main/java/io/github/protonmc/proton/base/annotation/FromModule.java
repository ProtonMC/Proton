package io.github.protonmc.proton.base.annotation;

import io.github.protonmc.proton.base.module.ProtonModule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Signifies that a method or a field (usually inside a {@link org.spongepowered.asm.mixin.Mixin Mixin class}) is needed in order for a {@link ProtonModule} to function correctly.
 *
 * @author kara-b
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface FromModule {
    Class<? extends ProtonModule>[] value();
}
