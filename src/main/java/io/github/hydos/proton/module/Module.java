package io.github.hydos.proton.module;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.config.Configurable;
import io.github.hydos.proton.config.Saveable;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public abstract class Module implements Saveable {
    protected final Identifier id;

    // note: @Configurable fields in normal modules MUST be static
    //       this is an exception, which CAN'T be static
    @Configurable
    public boolean enabled = true;

    public Module(Identifier id) {
        this.id = id;
        Proton.CONFIG.loadObject(this);
    }

    @Environment(EnvType.CLIENT)
    public void clientInit() {};

    public void serverInit(MinecraftServer server) {};

    public void commonInit() {}

    public final String getTranslationKey() {
        return "module." + id.getNamespace() + "." + id.getPath();
    }

    public final Identifier getId() {
        return id;
    }

    @Override
    public String getSerializedId() {
        return id.toString();
    }
}
