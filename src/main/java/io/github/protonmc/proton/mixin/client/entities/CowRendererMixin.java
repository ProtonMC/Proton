package io.github.protonmc.proton.mixin.client.entities;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.client.VariantAnimalTexturesModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.entity.passive.CowEntity;
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
@Mixin(CowEntityRenderer.class)
public class CowRendererMixin {
    /**
     * Makes cows have a random texture?
     *
     * @see CowEntityRenderer#getTexture(CowEntity)
     */
    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/CowEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    @FromModule(VariantAnimalTexturesModule.class)
    public void getTexture(CowEntity cowEntity, CallbackInfoReturnable<Identifier> cir) {
        if (ProtonConfig.Client.VariantAnimalTextures.enabled && ProtonConfig.Client.VariantAnimalTextures.enableCow) {
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(
                    cowEntity,
                    VariantAnimalTexturesModule.VariantTextureType.COW,
                    ProtonConfig.Client.VariantAnimalTextures.enableCow
                                                                            ));
        }
    }
}
