package io.github.protonmc.proton.base.client.modmenu;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import io.github.protonmc.proton.base.client.screen.ConfigScreenProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Used to add a configuration button to the Mod Menu screen.
 *
 * @author YTG1234
 */
@Environment(EnvType.CLIENT)
public class ModMenuCompat implements ModMenuApi {
    /**
     * @see ModMenuApi#getModConfigScreenFactory()
     */
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ConfigScreenProvider::getScreen;
    }
}
