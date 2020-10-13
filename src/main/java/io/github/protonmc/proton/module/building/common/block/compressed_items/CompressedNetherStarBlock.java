package io.github.protonmc.proton.module.building.common.block.compressed_items;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;

/**
 * @author YTG1234
 */
public class CompressedNetherStarBlock extends Block {
    public CompressedNetherStarBlock() {
        super(FabricBlockSettings.of(Material.STONE)
                                 .breakByHand(false)
                                 .requiresTool()
                                 .breakByTool(FabricToolTags.PICKAXES, 2)
                                 .strength(6.2F, 1200.0F)
                                 .nonOpaque()
                                 .blockVision(Blocks::never));
    }
}
