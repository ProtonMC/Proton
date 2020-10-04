package io.github.hydos.proton;

import io.github.hydos.proton.module.ModuleManager;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class ProtonPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        Proton.LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

}
