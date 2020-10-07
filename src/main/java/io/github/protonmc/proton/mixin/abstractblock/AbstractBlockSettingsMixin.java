package io.github.protonmc.proton.mixin.abstractblock;

import io.github.protonmc.proton.base.annotation.FromModule;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// This Mixin is for VariantHandler.class, To copy over the emissive lightning.

@Mixin(AbstractBlock.Settings.class)
public class AbstractBlockSettingsMixin {

    @FromModule(FromModule.NullModule.class)
    @Inject(at = @At("RETURN"), method = "copy", locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void copy(AbstractBlock block, CallbackInfoReturnable ci, AbstractBlock.Settings settings) {
        AbstractBlockSettingsAccessorMixin blockSettings = (AbstractBlockSettingsAccessorMixin) ((AbstractBlockAccessor)block).getSettings();
        ((AbstractBlockSettingsAccessorMixin)settings).setEmissiveLightingPredicate(blockSettings.getEmissiveLightingPredicate());
    }

}
