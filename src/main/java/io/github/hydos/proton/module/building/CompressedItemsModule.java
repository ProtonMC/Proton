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
    public static boolean compressed_nether_star = true;

    public CompressedItemsModule() {
        super(Proton.identifier("compressed_items"));
    }

    @Override
    public void commonInit() {
        if (enabled) ModuleBlocks.register();
    }

    public static class ModuleBlocks {
        public static Block COMPRESSED_NETHER_STAR;

        public static void register() {
            if (compressed_nether_star) {
                COMPRESSED_NETHER_STAR = ProtonRegisterUtil.block(
                        "compressed_items/compressed_nether_star",
                        new Block(FabricBlockSettings.of(Material.STONE)
                                                     .breakByHand(false)
                                                     .requiresTool()
                                                     .breakByTool(FabricToolTags.PICKAXES, 2)
                                                     .strength(3.0F, 1200.0F)
                                                     .nonOpaque()
                                                     .blockVision(Blocks::never)),
                        new FabricItemSettings().group(ItemGroup.DECORATIONS).maxCount(64).rarity(Rarity.RARE)
                                                                 );
            }
        }
    }
}
