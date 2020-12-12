package io.github.protonmc.proton;

import io.github.protonmc.proton.base.module.ModuleManager;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.Level;

public class ProtonPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        Proton.LOGGER.info("Loading the config...");
        try {
            Proton.CONFIG.load();
        } catch (Throwable t) {
            Proton.LOGGER.error("Couldn't load the config", t);
        }

        Proton.LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

}
