package io.github.protonmc.proton.mixin.dedicatedserver.access;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftServer.class)
@Environment(EnvType.SERVER)
public interface MinecraftServerAccess {
    @Accessor("LOGGER")
    static Logger getLogger() {
        throw null;
    }
}
