package io.github.protonmc.proton.mixin.abstractblock;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.Settings.class)
public interface AbstractBlockSettingsAccessorMixin {
    @Accessor("emissiveLightingPredicate")
    AbstractBlock.ContextPredicate getEmissiveLightingPredicate();

    @Accessor("emissiveLightingPredicate")
    void setEmissiveLightingPredicate(AbstractBlock.ContextPredicate value);
}
