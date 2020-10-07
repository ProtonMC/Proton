package io.github.protonmc.proton.base.annotation;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.util.Identifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Signifies that a method or a field (usually inside a Mixin) comes from a module.
 * @author kara-b
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface FromModule {
    Class<? extends ProtonModule>[] value();

    /**
     * A module used for signifying that the annotation target doesn't belong to any module.
     * @author YTG1234
     */
    @ForceNotLoad
    class NullModule extends ProtonModule {
        public NullModule() {
            super(Proton.identifier("null_module"));
        }

        @Override
        public void commonInit() {
            System.out.println("You shouldn't see this! If you're seeing this report a bug!");
        }
    }
}
