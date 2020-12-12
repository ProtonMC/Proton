package io.github.protonmc.proton.base.server;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ModuleManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Level;

/**
 * idk ask boogie
 *
 * @author BoogieMonster101
 * @author YTG1234
 */
public class ProtonServer implements ServerLifecycleEvents.ServerStarting {
    /**
     * @see ServerLifecycleEvents.ServerStarting#onServerStarting(MinecraftServer)
     */
    @Override
    public void onServerStarting(MinecraftServer server) {
        Proton.LOGGER.info("Setting Up Server-Side Modules...");
        ModuleManager.getInstance().setupServerModules(server);
        Proton.LOGGER.info("Finished Setting Up Server-Side Modules.");
    }
}
