package io.github.protonmc.proton.mixin.world;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.module.world.ClayModule;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
    @Inject(method = "addMineables(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("HEAD"))
    @FromModule(ClayModule.class)
    private static void addMineables(GenerationSettings.Builder builder, CallbackInfo ci) {
        if (!ModuleManager.getInstance().isModuleEnabled(ClayModule.class)) { return; }

        ClayModule.mixinInit();
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ClayModule.ORE_CLAY_OVERWORLD);
    }
}
