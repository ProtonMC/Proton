package io.github.protonmc.proton.mixin.tweaks;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.tweaks.TiltToDamage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("HEAD"), method = "takeKnockback")
    @FromModule(TiltToDamage.class)
    public void takeKnockback(float f, double d, double e, CallbackInfo ci) {
        if ((Object)this instanceof PlayerEntity && ProtonConfig.Tweaks.tiltToDamage) {
            TiltToDamage.takeKnockback((PlayerEntity)(Object)this, f, d, e);
        }
    }

}
