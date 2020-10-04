package io.github.hydos.proton.client;

import io.github.hydos.proton.module.ModuleHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.time.LocalDateTime;
import java.time.Month;

@Environment(EnvType.CLIENT)
public class ProtonClient implements ClientModInitializer {

    public static boolean doWeNeedJingleBells = false;

    @Override
    public void onInitializeClient() {
        LocalDateTime now = LocalDateTime.now();
        if(now.getMonth() == Month.DECEMBER && now.getDayOfMonth() >= 16 || now.getMonth() == Month.JANUARY && now.getDayOfMonth() <= 6)
            doWeNeedJingleBells = true;

        ModuleHandler.INSTANCE.setupClientModules();
    }
}
