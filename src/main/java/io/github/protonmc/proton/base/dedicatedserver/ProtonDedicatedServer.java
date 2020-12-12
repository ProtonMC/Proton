package io.github.protonmc.proton.base.dedicatedserver;

import io.github.protonmc.proton.mixin.dedicatedserver.access.MinecraftServerAccess;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public class ProtonDedicatedServer implements DedicatedServerModInitializer {
    static {
        MinecraftServerAccess.getLogger()
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

    @Override
    public void onInitializeServer() {

    }
}
