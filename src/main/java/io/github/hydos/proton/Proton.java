package io.github.hydos.proton;

import io.github.hydos.proton.module.ModuleHandler;
import net.fabricmc.api.ModInitializer;
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
        //TODO recipies

        //TODO sounds

        ModuleHandler.INSTANCE.setupCommonModules();

        //TODO events

        //TODO christmas
    }

    public void setup(){

    }

    public void loadTitleScreen(){

    }

    //TODO: configs
}
