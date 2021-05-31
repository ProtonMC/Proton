package io.github.protonmc.proton;

import io.github.protonmc.proton.base.config.*;
import io.github.protonmc.proton.base.handler.DataHandler;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.server.ProtonServer;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Proton implements ModInitializer {
    public static Proton INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger("Proton");
    public static final String MOD_ID = "proton";

    public static final boolean DEBUG = true;

    public Proton() {
        INSTANCE = this;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Setting up modules...");
        ModuleManager.getInstance().setupCommonModules();
        DataHandler.registerData();
        LOGGER.info("Finished Setting Up Modules.");

        LOGGER.info("Loding config...");
        AutoConfig.register(ProtonConfig.class, Toml4jConfigSerializer::new);
        LOGGER.info("Config Loaded!");

        ServerLifecycleEvents.SERVER_STARTING.register(new ProtonServer());
    }

    public static Identifier identifier(String path) {
        return new Identifier(MOD_ID, path);
    }
}
