package io.github.protonmc.proton.mixin.client.entities;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.client.VariantAnimalTexturesModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin essential for the VariantAnimalTexturesModule.
 *
 * @author hYdos
 */
@Environment(EnvType.CLIENT)
@Mixin(PigEntityRenderer.class)
public class PigRendererMixin {
    /**
     * Makes pig have variated textures.
     *
     * @see PigEntityRenderer#getTexture(PigEntity)
     */
    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/PigEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    @FromModule(VariantAnimalTexturesModule.class)
    public void getTexture(PigEntity pigEntity, CallbackInfoReturnable<Identifier> cir) {
        if (ProtonConfig.Client.VariantAnimalTextures.enabled) {
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(
                    pigEntity,
                    VariantAnimalTexturesModule.VariantTextureType.PIG,
                    ProtonConfig.Client.VariantAnimalTextures.enablePig
                                                                            ));
        }
    }
}
