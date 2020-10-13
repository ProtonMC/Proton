package io.github.protonmc.proton.base.module;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.annotation.DisabledByDefault;
import io.github.protonmc.proton.base.client.screen.ConfigScreenProvider;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.tiny_config.ConfigManager;
import io.github.protonmc.tiny_config.Configurable;
import io.github.protonmc.tiny_config.Saveable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

/**
 * An abstract class representing a proton module.
 *
 * @author kara-b
 * @author dzwdz
 * @author YTG1234
 * @author hydos
 * @author redcreeper14385
 */
public abstract class ProtonModule implements Saveable {
    /**
     * The {@linkplain Identifier ID} of this module used in translation keys and similar.
     */
    protected final Identifier id;

    // note: @Configurable fields in normal modules MUST be static
    //       this is an exception, which CAN'T be static
    @Configurable
    public boolean enabled = true;

    /**
     * Reads config, sets up fields and constructs a ProtonModule.
     *
     * @param id The module ID to be used.
     */
    public ProtonModule(Identifier id) {
        this.id = id;
        for (Field f : ConfigManager.getConfigurableFields(getClass())) {
            try {
                ConfigScreenProvider.DEFAULT_VALUES.put(f, f.get(this));
            } catch (Throwable ignored) {
            }
        }
        enabled = !getClass().isAnnotationPresent(DisabledByDefault.class);
        Proton.CONFIG.loadObject(this);
    }

    /**
     * Initializes the module on the client-side.
     */
    @Environment(EnvType.CLIENT)
    public void clientInit() {
    }

    /**
     * Ask boogie what this does :concern:
     *
     * @param server Ask boogie what this does :concern:
     */
    public void serverInit(MinecraftServer server) {
    }

    /**
     * Initializes the module both on server and client.
     */
    public void commonInit() {
    }

    /**
     * Registers resources for the module.
     *
     * @param resourceHandler The ResourceHandler that handles the resources.
     */
    public void registerResources(ResourceHandler resourceHandler) {
    }

    /**
     * Gets the module translation key. Can be used for translations.
     *
     * @return The module translation key.
     */
    public final String getTranslationKey() {
        return "proton.module." + id.getNamespace() + "." + id.getPath();
    }

    /**
     * Gets the {@linkplain Identifier module ID}, can be used in translation keys.
     *
     * @return The module's {@link Identifier}.
     */
    public final Identifier getId() {
        return id;
    }

    /**
     * Gets the config entry that this module is stored in.
     *
     * @return The config entry as a {@link String}
     *
     * @see Saveable#getSerializedId()
     */
    @Override
    public String getSerializedId() {
        return id.toString();
    }
}
