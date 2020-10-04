package io.github.hydos.proton;

import io.github.hydos.proton.module.ModuleManager;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.Level;

public class ProtonPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        Proton.LOGGER.log(Level.INFO, "Loading the config...");
        Proton.CONFIG.load();

        Proton.LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

}
