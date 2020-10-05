package io.github.hydos.proton.module.building;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.config.Configurable;
import io.github.hydos.proton.module.Module;
import io.github.hydos.proton.util.ProtonRegisterUtil;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class CompressedItemsModule extends Module {
    @Configurable
    public static float bluer_ice_slipperiness = 0.9998F;

    @Configurable
    public static float dark_blue_ice_slipperiness = 1.0F;

    public CompressedItemsModule() {
        super(Proton.identifier("compressed_items"));
    }

    @Override
    public void commonInit() {
        if (!enabled) return;
        ModuleBlocks.register();
    }

    public static class ModuleBlocks {
        public static Block COMPRESSED_NETHER_STAR;
        public static Block BLUER_ICE;
        public static Block DARK_BLUE_ICE;
        public static Block COMPRESSED_DIAMOND_BLOCK;

        public static void register() {
            COMPRESSED_NETHER_STAR = ProtonRegisterUtil.block(
                    "compressed_nether_star",
                    new Block(FabricBlockSettings.of(Material.STONE)
                                                 .breakByHand(false)
                                                 .requiresTool()
                                                 .breakByTool(FabricToolTags.PICKAXES, 2)
                                                 .strength(6.2F, 1200.0F)
                                                 .nonOpaque()
                                                 .blockVision(Blocks::never)),
                    new FabricItemSettings().group(ItemGroup.DECORATIONS).rarity(Rarity.RARE)
                                                             );

            BLUER_ICE = ProtonRegisterUtil.block(
                    "bluer_ice",
                    new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE).slipperiness(bluer_ice_slipperiness).breakByTool(FabricToolTags.PICKAXES)),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
                                                );

            DARK_BLUE_ICE = ProtonRegisterUtil.block(
                    "dark_blue_ice",
                    new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
                                                 .slipperiness(dark_blue_ice_slipperiness)
                                                 .breakByTool(FabricToolTags.PICKAXES)),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
                                                    );

            COMPRESSED_DIAMOND_BLOCK = ProtonRegisterUtil.block(
                    "compressed_diamond_block",
                    new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)
                                                 .strength(8.0F, 9.0F)
                                                 .requiresTool()
                                                 .breakByTool(FabricToolTags.PICKAXES, 2)),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
                                                               );
        }
    }
}
