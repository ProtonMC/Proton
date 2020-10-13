package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.item.CompressedNetherStarItem;
import io.github.protonmc.tiny_config.Configurable;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;

/**
 * Module that adds compressed blocks for some items.
 *
 * @author YTG1234
 */
public class CompressedItemsModule extends ProtonModule {
    @Configurable
    public static double bluerIceSlipperiness = 0.9998;

    @Configurable
    public static double bluestIceSlipperiness = 1.0;

    @Configurable
    public static int bluestIceLuminance = 3;

    public CompressedItemsModule() {
        super(Proton.identifier("compressed_items"));
    }

    /**
     * Initializes the mod both on the server and the client.
     *
     * @see ProtonModule#commonInit()
     */
    @Override
    public void commonInit() {
        if (!enabled) return;
        Proton.LOGGER.log(Level.INFO, "Initializing Compressed Item module!");
        ModuleBlocks.register();
        ModuleItems.register();
    }

    /**
     * Registers all models for blocks and items.
     *
     * @see ProtonModule#registerResources(ResourceHandler)
     */
    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        resourceHandler.generateSimpleBlock("compressed_nether_star", new Identifier("item/nether_star"));
        resourceHandler.generateSimpleBlock("bluer_ice");
        resourceHandler.generateSimpleBlock("bluest_ice");
        resourceHandler.generateSimpleBlock("compressed_diamond_block");
    }

    /**
     * Static inner-class containing all the blocks in the module.
     *
     * @author YTG1234
     */
    public static class ModuleBlocks {
        public static Block COMPRESSED_NETHER_STAR;
        public static Block BLUER_ICE;
        public static Block BLUEST_ICE;
        public static Block COMPRESSED_DIAMOND_BLOCK;

        /**
         * Registers all the blocks in the module.
         */
        public static void register() {
            COMPRESSED_NETHER_STAR = ProtonRegisterHandler.block(
                    "compressed_nether_star",
                    new Block(FabricBlockSettings.of(Material.STONE)
                                                 .breakByHand(false)
                                                 .requiresTool()
                                                 .breakByTool(FabricToolTags.PICKAXES, 2)
                                                 .strength(6.2F, 1200.0F)
                                                 .nonOpaque()
                                                 .blockVision(Blocks::never))
                                                                );

            BLUER_ICE = ProtonRegisterHandler.block(
                    "bluer_ice",
                    new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
                                                 .slipperiness((float) bluerIceSlipperiness)
                                                 .breakByTool(FabricToolTags.PICKAXES)),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
                                                   );

            BLUEST_ICE = ProtonRegisterHandler.block(
                    "bluest_ice",
                    new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
                                                 .slipperiness((float) bluestIceSlipperiness)
                                                 .breakByTool(FabricToolTags.PICKAXES)
                                                 .lightLevel(bluestIceLuminance)
                                                 .emissiveLighting((state, world, pos) -> state.getLuminance() > 0)),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
                                                    );

            COMPRESSED_DIAMOND_BLOCK = ProtonRegisterHandler.block(
                    "compressed_diamond_block",
                    new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)
                                                 .strength(8.0F, 9.0F)
                                                 .requiresTool()
                                                 .breakByTool(FabricToolTags.PICKAXES, 2)),
                    new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
                                                                  );
        }
    }

    /**
     * Static inner-class containing all of the module's items.
     *
     * @author YTG1234
     */
    public static class ModuleItems {
        public static CompressedNetherStarItem COMPRESSED_NETHER_STAR;

        /**
         * Registers all of the module's items.
         */
        public static void register() {
            COMPRESSED_NETHER_STAR = ProtonRegisterHandler.item("compressed_nether_star", new CompressedNetherStarItem());
        }
    }
}
