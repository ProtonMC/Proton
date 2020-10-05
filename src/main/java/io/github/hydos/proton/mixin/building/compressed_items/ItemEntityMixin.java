package io.github.hydos.proton.mixin.building.compressed_items;

import io.github.hydos.proton.module.building.CompressedItemsModule;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getStack();

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("RETURN"), cancellable = true)
    private void noDestroyCompressedNetherStars(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!getStack().isEmpty() && getStack().getItem() == CompressedItemsModule.ModuleBlocks.COMPRESSED_NETHER_STAR.asItem() && source.isExplosive()) {
            cir.setReturnValue(false);
        }
    }
}
