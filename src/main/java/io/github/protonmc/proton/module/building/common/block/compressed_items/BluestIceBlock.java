package io.github.protonmc.proton.module.building.common.block.compressed_items;

import io.github.protonmc.proton.base.config.ProtonConfig;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * @author YTG1234
 */
public class BluestIceBlock extends Block {
    public BluestIceBlock() {
        super(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
                                 .slipperiness((float) ProtonConfig.Building.CompressedItems.bluestIceSlipperiness)
                                 .breakByTool(FabricToolTags.PICKAXES)
                                 .luminance(ProtonConfig.Building.CompressedItems.bluestIceLuminance)
                                 .emissiveLighting((state, world, pos) -> state.getLuminance() > 0));
    }

    @Override
    public float getSlipperiness() {
        return (float) ProtonConfig.Building.CompressedItems.bluestIceSlipperiness;
    }
}
