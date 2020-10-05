package io.github.hydos.proton.client.modmenu;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.client.screen.ProtonConfigScreen;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuCompat implements ModMenuApi {
    @Override
    public String getModId() {
        return Proton.MOD_ID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ProtonConfigScreen();
    }
}
