package io.github.hydos.vark;

import io.github.hydos.vark.module.ModuleHandler;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Vark implements ModInitializer {

    public static Vark INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger("Vark");
    public static final String MOD_ID = "vark";

    public static final boolean DEBUG = true;

    public Vark() {
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
