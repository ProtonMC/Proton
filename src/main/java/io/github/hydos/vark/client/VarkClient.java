package io.github.hydos.vark.client;

import io.github.hydos.vark.module.ModuleHandler;
import net.fabricmc.api.ClientModInitializer;

import java.time.LocalDateTime;
import java.time.Month;

public class VarkClient implements ClientModInitializer {

    public static boolean doWeNeedJingleBells = false;

    @Override
    public void onInitializeClient() {
        LocalDateTime now = LocalDateTime.now();
        if(now.getMonth() == Month.DECEMBER && now.getDayOfMonth() >= 16 || now.getMonth() == Month.JANUARY && now.getDayOfMonth() <= 6)
            doWeNeedJingleBells = true;

        ModuleHandler.INSTANCE.setupClientModules();
    }
}
