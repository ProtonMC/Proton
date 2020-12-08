package io.github.protonmc.proton.base.client;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.mixin.client.access.MinecraftClientAccess;
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

    static {
        MinecraftClientAccess.getLogger()
                             .trace("\n" +
                                   " ___            _                _                                 _   _         _     ___ \n" +
                                   "|_ _|   ___ ___| |___ _ __  _ _ | |_  _   ____ __ _____ __ _ _ _  | |_| |_  __ _| |_  |_ _|\n" +
                                   " | |   (_-</ _ \\ / -_) '  \\| ' \\| | || | (_-< V  V / -_) _` | '_| |  _| ' \\/ _` |  _|  | | \n" +
                                   "|___|  /__/\\___/_\\___|_|_|_|_||_|_|\\_, | /__/\\_/\\_/\\___\\__,_|_|    \\__|_||_\\__,_|\\__| |___|\n" +
                                   "                                   |__/                                                    \n" +
                                   "                          _                                      _   \n" +
                                   " __ _ _ __    _  _ _ __  | |_ ___   _ _  ___   __ _ ___  ___  __| |  \n" +
                                   "/ _` | '  \\  | || | '_ \\ |  _/ _ \\ | ' \\/ _ \\ / _` / _ \\/ _ \\/ _` |_ \n" +
                                   "\\__,_|_|_|_|  \\_,_| .__/  \\__\\___/ |_||_\\___/ \\__, \\___/\\___/\\__,_(_)\n" +
                                   "                  |_|                         |___/                  \n");
    }

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

        Proton.LOGGER.info("Setting up clientside modules...");
        ModuleManager.getInstance().setupClientModules();
        ResourceHandler.registerAssets();
    }
}
