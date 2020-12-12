package io.github.protonmc.proton.mixin.dedicatedserver;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
 * You are probably wondering, why is this in the "dedicatedserver" package when MinecraftServer is present on both sides?
 * The reason is that, the client-side equivalent of this Mixin can be found at MinecraftClientMixin.
 */
@Mixin(MinecraftServer.class)
@Environment(EnvType.SERVER)
public abstract class MinecraftServerMixin {
    @Shadow
    @Final
    private static Logger LOGGER;

    @Inject(method = "stop(Z)V", at = @At("HEAD"))
    @FromModule(ProtonModule.class)
    private void printEndLine(boolean bl, CallbackInfo ci) {
        LOGGER.trace("\n" +
                    " __  __ _        _    _      __                                    _   \n" +
                    "|  \\/  (_)___ __| |_ (_)___ / _|  _ __  __ _ _ _  __ _ __ _ ___ __| |  \n" +
                    "| |\\/| | (_-</ _| ' \\| / -_)  _| | '  \\/ _` | ' \\/ _` / _` / -_) _` |_ \n" +
                    "|_|  |_|_/__/\\__|_||_|_\\___|_|   |_|_|_\\__,_|_||_\\__,_\\__, \\___\\__,_(_)\n" +
                    "                                                      |___/            \n");
    }
}
