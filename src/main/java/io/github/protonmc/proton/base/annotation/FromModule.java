package io.github.protonmc.proton.base.annotation;

import io.github.protonmc.proton.module.ProtonModule;

public @interface FromModule {
    Class<? extends ProtonModule>[] value();
}
