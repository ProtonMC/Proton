package io.github.hydos.proton.mixin.client.entities;

import io.github.hydos.proton.module.ModuleManager;
import io.github.hydos.proton.module.tweaks.VariantAnimalTexturesModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PigEntityRenderer.class)
public class PigRendererMixin {

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTexture(PigEntity pigEntity, CallbackInfoReturnable<Identifier> cir){
        if(ModuleManager.getInstance().isModuleEnabled(VariantAnimalTexturesModule.class)){
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(pigEntity, VariantAnimalTexturesModule.VariantTextureType.PIG, VariantAnimalTexturesModule.enablePig));
        }
    }

}