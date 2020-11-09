package io.github.protonmc.proton;

import io.github.protonmc.proton.base.handler.DataHandler;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.server.ProtonServer;
import io.github.protonmc.tiny_config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Proton implements ModInitializer {
    public static Proton INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("Proton");
    public static final String MOD_ID = "proton";

    public static final ConfigManager CONFIG = new ConfigManager(
            FabricLoader.getInstance().getConfigDir().resolve("proton.json5")
    );

    public static final boolean DEBUG = true;

    public Proton() {
        INSTANCE = this;
    }

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Setting up modules...");
        ModuleManager.getInstance().setupCommonModules();
        DataHandler.registerData();
        LOGGER.log(Level.INFO, "Finished Setting Up Modules.");

        ServerLifecycleEvents.SERVER_STARTING.register(new ProtonServer());

        try {
            CONFIG.save(ModuleManager.getInstance().getModules());
        } catch (Throwable t) {
            LOGGER.error("Couldn't save the config", t);
        }
    }

    public static Identifier identifier(String path) {
        return new Identifier(MOD_ID, path);
    }
}
