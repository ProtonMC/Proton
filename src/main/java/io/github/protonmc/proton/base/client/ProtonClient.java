package io.github.protonmc.proton.base.client;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.apache.logging.log4j.Level;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Proton's client side stuff
 *
 * @author hYdos
 * @author YTG1234
 * @author kara-b
 */
@Environment(EnvType.CLIENT)
public class ProtonClient implements ClientModInitializer {
    public static boolean doWeNeedJingleBells = false;

    /**
     * Sets up client side modules, registers the Artifice resource pack and populates {@link ProtonClient#doWeNeedJingleBells}.
     *
     * @see ClientModInitializer#onInitializeClient()
     */
    @Override
    public void onInitializeClient() {
        LocalDateTime now = LocalDateTime.now();
        if (now.getMonth() == Month.DECEMBER && now.getDayOfMonth() >= 16 || now.getMonth() == Month.JANUARY && now.getDayOfMonth() <= 6) {
            doWeNeedJingleBells = true;
        }

        Proton.LOGGER.log(Level.INFO, "Setting up clientside modules...");
        ModuleManager.getInstance().setupClientModules();
        ResourceHandler.registerAssets();
    }
}
