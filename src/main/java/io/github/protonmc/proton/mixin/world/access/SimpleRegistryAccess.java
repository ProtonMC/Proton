package io.github.protonmc.proton.mixin.world.access;

import com.google.common.collect.BiMap;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SimpleRegistry.class)
public interface SimpleRegistryAccess<T> {
    @Accessor
    BiMap<Identifier, T> getIdToEntry();
}
