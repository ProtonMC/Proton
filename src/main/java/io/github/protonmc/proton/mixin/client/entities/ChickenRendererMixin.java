package io.github.protonmc.proton.mixin.client.entities;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.client.VariantAnimalTexturesModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.entity.passive.ChickenEntity;
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
@Mixin(ChickenEntityRenderer.class)
public class ChickenRendererMixin {
    /**
     * Makes the chicken have a random texture?
     *
     * @see ChickenEntityRenderer#getTexture(ChickenEntity)
     */
    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/ChickenEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    @FromModule(VariantAnimalTexturesModule.class)
    public void getTexture(ChickenEntity chickenEntity, CallbackInfoReturnable<Identifier> cir) {
        if (ProtonConfig.Client.VariantAnimalTextures.enabled) {
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(
                    chickenEntity,
                    VariantAnimalTexturesModule.VariantTextureType.CHICKEN,
                    ProtonConfig.Client.VariantAnimalTextures.enableChicken
                                                                            ));
        }
    }
}
