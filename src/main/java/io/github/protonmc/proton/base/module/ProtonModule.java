package io.github.protonmc.proton.base.module;

import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.annotation.DisabledByDefault;
import io.github.protonmc.proton.base.client.screen.ConfigScreenProvider;
import io.github.protonmc.tiny_config.ConfigManager;
import io.github.protonmc.tiny_config.Configurable;
import io.github.protonmc.tiny_config.Saveable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

public abstract class ProtonModule implements Saveable {
    protected final Identifier id;

    // note: @Configurable fields in normal modules MUST be static
    //       this is an exception, which CAN'T be static
    @Configurable
    public boolean enabled = true;

    public ProtonModule(Identifier id) {
        this.id = id;
        for (Field f : ConfigManager.getConfigurableFields(getClass())) {
            try {
                ConfigScreenProvider.DEFAULT_VALUES.put(f, f.get(this));
            } catch (Throwable ignored) {}
        }
        enabled = !getClass().isAnnotationPresent(DisabledByDefault.class);
        Proton.CONFIG.loadObject(this);
    }

    @Environment(EnvType.CLIENT)
    public void clientInit() {};

    public void serverInit(MinecraftServer server) {};

    public void commonInit() {}

    public void registerResources(ArtificeResourcePack.ClientResourcePackBuilder pack) {}

    public final String getTranslationKey() {
        return "proton.module." + id.getNamespace() + "." + id.getPath();
    }

    public final Identifier getId() {
        return id;
    }

    @Override
    public String getSerializedId() {
        return id.toString();
    }
}
