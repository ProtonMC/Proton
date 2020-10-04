package io.github.hydos.proton.module;

import net.minecraft.util.Identifier;

public abstract class Module {

    protected final Identifier id;

    public Module(Identifier id) {
        this.id = id;
    }

    public abstract void clientInit();

    public abstract void serverInit();

    public abstract void commonInit();

    public final String getTranslationKey() {
        return "module." + id.getNamespace() + "." + id.getPath();
    }

    public final Identifier getId() {
        return id;
    }
}
