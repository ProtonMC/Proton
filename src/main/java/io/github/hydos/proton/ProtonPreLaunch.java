package io.github.hydos.proton;

import io.github.hydos.proton.module.ModuleManager;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.Level;

import java.io.File;

public class ProtonPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        Proton.LOGGER.log(Level.INFO, "Loading the config...");
        try {
            Proton.CONFIG.load();
        } catch (Throwable t) {
            Proton.LOGGER.error("Couldn't load the config", t);
        }

        Proton.LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

}
