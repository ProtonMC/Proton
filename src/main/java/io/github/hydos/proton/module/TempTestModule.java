package io.github.hydos.proton.module;

import io.github.hydos.proton.Proton;

public class TempTestModule extends Module {
    public TempTestModule() {
        super("temp", "tempid");
    }

    @Override
    public void clientInit() {
        Proton.LOGGER.info("Hello from Test Module client init!");
    }

    @Override
    public void serverInit() {
        Proton.LOGGER.info("Hello from Test Module server init!");
    }

    @Override
    public void commonInit() {
        Proton.LOGGER.info("Hello from Test Module common init!");
    }
}
