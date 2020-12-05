package io.github.protonmc.proton.mixin.client;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    @Final
    private static Logger LOGGER;

    @FromModule(ProtonModule.class)
    @Inject(method = "stop()V", at = @At("HEAD"))
    private void printEndLine(CallbackInfo ci) {
        LOGGER.info("\n" +
                    " __  __ _        _    _      __                                    _   \n" +
                    "|  \\/  (_)___ __| |_ (_)___ / _|  _ __  __ _ _ _  __ _ __ _ ___ __| |  \n" +
                    "| |\\/| | (_-</ _| ' \\| / -_)  _| | '  \\/ _` | ' \\/ _` / _` / -_) _` |_ \n" +
                    "|_|  |_|_/__/\\__|_||_|_\\___|_|   |_|_|_\\__,_|_||_\\__,_\\__, \\___\\__,_(_)\n" +
                    "                                                      |___/            \n");
    }
}
