package io.github.protonmc.proton.mixin.abstractblock;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * A Mixin essential for the VariantHandler.
 * @author kara-b
 */
@Mixin(AbstractBlock.Settings.class)
public interface AbstractBlockSettingsAccessorMixin {
    /**
     * @return The emissiveLightingPredicate of the Settings object.
     */
    @Accessor("emissiveLightingPredicate")
    AbstractBlock.ContextPredicate getEmissiveLightingPredicate();

    /**
     * Sets the emissiveLightingPredicate of the Settings object.
     * @param value The new emissiveLightingPredicate.
     */
    @Accessor("emissiveLightingPredicate")
    void setEmissiveLightingPredicate(AbstractBlock.ContextPredicate value);
}
