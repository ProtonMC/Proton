package io.github.hydos.proton.module;

import net.minecraft.util.Identifier;

public abstract class Module {

    protected final Identifier id;

    @Configurable
    public static boolean enabled = true;

    public Module(Identifier id) {
        this.id = id;
        ConfigManager.fromJson(this, ConfigManager.config.getObject(getId().toString()));
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
