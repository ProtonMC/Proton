package io.github.hydos.proton;

import io.github.hydos.proton.module.ModuleManager;
import io.github.hydos.proton.sound.Sound;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Proton implements ModInitializer, PreLaunchEntrypoint {

    public static Proton INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger("Proton");
    public static final String MOD_ID = "proton";

    public static final boolean DEBUG = true;

    public Proton() {
        INSTANCE = this;
    }

    @Override
    public void onInitialize() {
        LOGGER.log(Level.INFO, "Initialising Recipes...");
        //TODO recipes
        LOGGER.log(Level.INFO, "Recipe Initialisation Done.");

        LOGGER.log(Level.INFO, "Initialising Sounds...");
        Sound.registerSounds();
        LOGGER.log(Level.INFO, "Sound Initialisation Done.");

        LOGGER.log(Level.INFO, "Setting Up Modules...");
        ModuleManager.getInstance().setupCommonModules();
        LOGGER.log(Level.INFO, "Finished Setting Up Modules.");

        //TODO events

        //TODO christmas
    }

    public void setup(){

    }

    public void loadTitleScreen(){
    }

    @Override
    public void onPreLaunch() {
        LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

    //TODO: configs
}
