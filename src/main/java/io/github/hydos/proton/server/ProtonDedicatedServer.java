package io.github.hydos.proton.server;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.module.ModuleHandler;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;

@Environment(EnvType.SERVER)
public class ProtonDedicatedServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Proton.LOGGER.log(Level.INFO, "Setting Up Server-Side Modules...");
        ModuleHandler.INSTANCE.setupServerModules();
        Proton.LOGGER.log(Level.INFO, "Finished Setting Up Server-Side Modules.");
    }
}
