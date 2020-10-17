package io.github.protonmc.proton.module.building.common.block.compressed_items;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * @author YTG1234
 */
public class CompressedDiamondBlock extends Block {
    public CompressedDiamondBlock() {
        super(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).strength(8.0F, 9.0F).requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    }
}
