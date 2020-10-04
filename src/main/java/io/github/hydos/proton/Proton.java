package io.github.hydos.proton;

import io.github.hydos.proton.module.ConfigManager;
import io.github.hydos.proton.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Proton implements ModInitializer {

    public static Proton INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger("Proton");
    public static final String MOD_ID = "proton";

    public static final ConfigManager CONFIG = new ConfigManager(
            new File(FabricLoader.getInstance().getConfigDir().toFile(), "proton.json5")
    );

    public static final boolean DEBUG = true;

    public Proton() {
        INSTANCE = this;
    }

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Setting up modules...");
        ModuleManager.getInstance().setupCommonModules();

        try {
            CONFIG.save();
        } catch (Throwable t) {
            LOGGER.error("Couldn't save the config", t);
        }
    }

    public static Identifier identifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    public void setup(){

    }

    public void loadTitleScreen(){
    }
}
