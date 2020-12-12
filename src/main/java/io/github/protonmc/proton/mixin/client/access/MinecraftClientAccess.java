package io.github.protonmc.proton.mixin.client.access;

import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAccess {
    @Accessor("LOGGER")
    static Logger getLogger() {
        throw null;
    }
}
