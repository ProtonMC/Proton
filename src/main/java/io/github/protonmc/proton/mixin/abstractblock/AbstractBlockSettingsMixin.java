package io.github.protonmc.proton.mixin.abstractblock;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// This Mixin is for VariantHandler.class, To copy over the emissive lightning.

/**
 * A Mixin essential for VariantHandler.
 *
 * @author kara-b
 */
@Mixin(AbstractBlock.Settings.class)
public class AbstractBlockSettingsMixin {
    /**
     * Makes the copy method also copy the emissiveLightingPredicate.
     *
     * @see AbstractBlock.Settings#copy(AbstractBlock)
     */
    @Inject(at = @At("RETURN"),
            method = "copy(Lnet/minecraft/block/AbstractBlock;)Lnet/minecraft/block/AbstractBlock$Settings;",
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    @FromModule(ProtonModule.class)
    private static void copy(AbstractBlock block, CallbackInfoReturnable<AbstractBlock.Settings> cir, AbstractBlock.Settings settings) {
        AbstractBlockSettingsAccessorMixin parentSettings = (AbstractBlockSettingsAccessorMixin) ((AbstractBlockAccessor) block).getSettings();
        AbstractBlockSettingsAccessorMixin childSettings = (AbstractBlockSettingsAccessorMixin) settings;

        childSettings.setEmissiveLightingPredicate(parentSettings.getEmissiveLightingPredicate());
    }
}
