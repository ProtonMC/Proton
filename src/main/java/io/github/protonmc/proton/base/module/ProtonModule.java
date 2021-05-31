package io.github.protonmc.proton.base.module;

import io.github.protonmc.proton.base.handler.DataHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

/**
 * An abstract class representing a proton module.
 *
 * @author kara-b
 * @author dzwdz
 * @author YTG1234
 * @author hydos
 * @author redcreeper14385
 */
public abstract class ProtonModule {
    /**
     * The {@linkplain Identifier ID} of this module used in translation keys and similar.
     */
    protected final Identifier id;

    /**
     * Reads config, sets up fields and constructs a ProtonModule.
     *
     * @param id The module ID to be used.
     */
    public ProtonModule(Identifier id) {
        this.id = id;
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

    public void registerData(DataHandler dataHandler) {
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
}
