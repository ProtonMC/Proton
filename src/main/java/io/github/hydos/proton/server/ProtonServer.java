package io.github.hydos.proton.server;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.module.ModuleManager;
import org.apache.logging.log4j.Level;

import net.minecraft.server.MinecraftServer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ProtonServer implements ServerLifecycleEvents.ServerStarting {
    @Override
    public void onServerStarting(MinecraftServer server) {
        Proton.LOGGER.log(Level.INFO, "Setting Up Server-Side Modules...");
        ModuleManager.getInstance().setupServerModules(server);
        Proton.LOGGER.log(Level.INFO, "Finished Setting Up Server-Side Modules.");
    }
}
