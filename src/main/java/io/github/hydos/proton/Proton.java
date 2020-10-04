package io.github.hydos.proton;

import blue.endless.jankson.JsonObject;
import io.github.hydos.proton.module.Module;
import io.github.hydos.proton.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;

public class Proton implements ModInitializer {

    public static Proton INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger("Proton");
    public static final String MOD_ID = "proton";

    public static JsonObject config;

    public static final boolean DEBUG = true;

    public Proton() {
        INSTANCE = this;
    }

    public static File getConfigFile() {
        return new File(FabricLoader.INSTANCE.getConfigDirectory(), "proton.json5");
    }

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Setting up modules...");
        ModuleManager.getInstance().setupCommonModules();

        saveConfig();
    }

    public static Identifier identifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    public void setup(){

    }

    public void loadTitleScreen(){
    }

    public void saveConfig() {
        JsonObject object = new JsonObject();
        for (Module m : ModuleManager.getInstance().getModules()) {
            object.put(m.getId().toString(), m.toJson());
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(getConfigFile().toPath());
            writer.write(object.toJson(true, true));
            writer.close();
        } catch (Throwable t) {
            LOGGER.error("Couldn't save Proton's config");
            LOGGER.error(t);
        }
    }
}
