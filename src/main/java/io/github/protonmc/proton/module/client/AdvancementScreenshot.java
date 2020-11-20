package io.github.protonmc.proton.module.client;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class AdvancementScreenshot extends ProtonModule {
    public AdvancementScreenshot() {
        super(Proton.identifier("advancement_screenshot"));
    }
}
