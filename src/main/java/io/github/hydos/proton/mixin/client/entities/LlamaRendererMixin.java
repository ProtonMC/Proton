package io.github.hydos.proton.mixin.client.entities;

import io.github.hydos.proton.module.ModuleManager;
import io.github.hydos.proton.module.tweaks.VariantAnimalTexturesModule;
import net.minecraft.client.render.entity.LlamaEntityRenderer;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LlamaEntityRenderer.class)
public class LlamaRendererMixin {

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTypeTexture(LlamaEntity llamaEntity, CallbackInfoReturnable<Identifier> cir){
        if(ModuleManager.getInstance().isModuleEnabled(VariantAnimalTexturesModule.class)){
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(llamaEntity, VariantAnimalTexturesModule.VariantTextureType.LLAMA, VariantAnimalTexturesModule.enableShinyLlama));
        }
    }
}
