package io.github.protonmc.proton.module.building.common.item;

import io.github.protonmc.proton.module.building.CompressedItemsModule;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

/**
 * Class for the {@link CompressedItemsModule.ModuleItems#COMPRESSED_NETHER_STAR} item.
 *
 * @author YTG1234
 */
public class CompressedNetherStarItem extends BlockItem {
    public CompressedNetherStarItem() {
        super(CompressedItemsModule.ModuleBlocks.COMPRESSED_NETHER_STAR, new FabricItemSettings().group(ItemGroup.DECORATIONS).rarity(Rarity.RARE));
    }

    /**
     * Calculates whether a {@link DamageSource} should damage the item.
     *
     * @param source The {@link DamageSource} for checking.
     */
    @Override
    public boolean damage(DamageSource source) {
        if (source.isExplosive()) return false;
        return super.damage(source);
    }
}
