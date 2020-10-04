package io.github.hydos.proton.server;

import io.github.hydos.proton.module.ModuleHandler;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public class ProtonDedicatedServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ModuleHandler.INSTANCE.setupServerModules();
    }
}
