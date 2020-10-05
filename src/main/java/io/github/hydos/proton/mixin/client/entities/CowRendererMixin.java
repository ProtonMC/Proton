package io.github.hydos.proton.mixin.client.entities;

import io.github.hydos.proton.module.ModuleManager;
import io.github.hydos.proton.module.client.VariantAnimalTexturesModule;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(CowEntityRenderer.class)
public class CowRendererMixin {

    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    public void getTexture(CowEntity cowEntity, CallbackInfoReturnable<Identifier> cir){
        if(ModuleManager.getInstance().isModuleEnabled(VariantAnimalTexturesModule.class) && VariantAnimalTexturesModule.enableCow){
            cir.setReturnValue(VariantAnimalTexturesModule.getTextureOrShiny(cowEntity, VariantAnimalTexturesModule.VariantTextureType.COW, VariantAnimalTexturesModule.enableCow));
        }
    }

}
