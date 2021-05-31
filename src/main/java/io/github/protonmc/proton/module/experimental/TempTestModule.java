package io.github.protonmc.proton.module.experimental;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.util.Identifier;

import net.minecraft.server.MinecraftServer;

public class TempTestModule extends ProtonModule {
    public TempTestModule() {
        super(new Identifier("temp", "tempid"));
    }

    @Override
    public void clientInit() {
        Proton.LOGGER.info("Hello from Test ProtonModule client init!");
    }

    @Override
    public void serverInit(MinecraftServer server) {
        Proton.LOGGER.info("Hello from Test ProtonModule server init!");
    }

    @Override
    public void commonInit() {
        Proton.LOGGER.info("Hello from Test ProtonModule common init!");
        if (ProtonConfig.Experimental.testModule)
            Proton.LOGGER.info("im enable");
        else
            Proton.LOGGER.info("im disable");
    }
}
