package io.github.protonmc.proton.mixin.tweaks;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.tweaks.FeatherFallingFarmland;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(FarmlandBlock.class)
public class FarmlandMixin {

    @FromModule(FeatherFallingFarmland.class)
    @Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance, CallbackInfo ci) {
        if (ProtonConfig.Tweaks.featherFallingFarmland) {
            AtomicBoolean hasFeatherFalling = new AtomicBoolean(false);
            entity.getArmorItems().forEach(itemStack -> {
                if (!hasFeatherFalling.get()) {
                    hasFeatherFalling.set(EnchantmentHelper.getLevel(Enchantments.FEATHER_FALLING, itemStack) != 0);
                }
            });

            if (hasFeatherFalling.get()) {
                entity.handleFallDamage(distance, 1.0F);
                ci.cancel();
            }
        }
    }

}
