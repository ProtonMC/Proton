package io.github.hydos.proton.module;

import net.minecraft.util.Identifier;

public abstract class Module {

    public final Identifier id;

    public Module(Identifier id) {
        this.id = id;
    }

    public void clientInit() {}

    public void serverInit() {}

    public void commonInit() {}

    public final String getTranslationKey() {
        return "module." + id.getNamespace() + "." + id.getPath();
    }

    public final Identifier getId() {
        return id;
    }
}
