package io.github.protonmc.proton;

import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.base.module.ModuleManager;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class ProtonPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        Proton.LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

}
