package io.github.protonmc.proton.mixin.client.entities;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.module.client.VariantAnimalTexturesModule;
import net.minecraft.client.render.entity.LlamaEntityRenderer;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin essential for the VariantAnimalTexturesModule.
 * @author hYdos
 */
@Mixin(LlamaEntityRenderer.class)
public class LlamaRendererMixin {
    @Shadow
    @Final
    @FromModule(VariantAnimalTexturesModule.class)
    private static Identifier[] TEXTURES;

    /**
     * Makes llamas have variated textures.
     * @see LlamaEntityRenderer#getTexture(LlamaEntity)
     */
    @Inject(method = "getTexture(Lnet/minecraft/entity/passive/LlamaEntity;)Lnet/minecraft/util/Identifier;", at = @At("HEAD"), cancellable = true)
    @FromModule(VariantAnimalTexturesModule.class)
    public void getTypeTexture(LlamaEntity llamaEntity, CallbackInfoReturnable<Identifier> cir) {
        if (ModuleManager.getInstance().isModuleEnabled(VariantAnimalTexturesModule.class)) {
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(
                    llamaEntity,
                    VariantAnimalTexturesModule.VariantTextureType.RABBIT,
                    () -> getOldTexture(llamaEntity)
                                                                            ));
        }
    }

    /**
     * Gets the original texture of a LlamaEntity.
     * @param llamaEntity The entity.
     * @return Identifier of the original texture.
     */
    @FromModule(VariantAnimalTexturesModule.class)
    @Unique
    private Identifier getOldTexture(LlamaEntity llamaEntity) {
        return TEXTURES[llamaEntity.getVariant()];
    }
}
