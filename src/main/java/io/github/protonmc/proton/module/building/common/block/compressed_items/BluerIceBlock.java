package io.github.protonmc.proton.module.building.common.block.compressed_items;

import io.github.protonmc.proton.base.config.ProtonConfig;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * @author YTG1234
 */
public class BluerIceBlock extends Block {
    public BluerIceBlock() {
        super(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
                                 .slipperiness((float) ProtonConfig.Building.CompressedItems.bluerIceSlipperiness)
                                 .breakByTool(FabricToolTags.PICKAXES));
    }

    @Override
    public float getSlipperiness() {
        return (float) ProtonConfig.Building.CompressedItems.bluerIceSlipperiness;
    }
}
