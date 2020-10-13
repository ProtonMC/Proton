package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.compressed_items.BluerIceBlock;
import io.github.protonmc.proton.module.building.common.block.compressed_items.BluestIceBlock;
import io.github.protonmc.proton.module.building.common.block.compressed_items.CompressedDiamondBlock;
import io.github.protonmc.proton.module.building.common.block.compressed_items.CompressedNetherStarBlock;
import io.github.protonmc.proton.module.building.common.item.CompressedNetherStarItem;
import io.github.protonmc.tiny_config.Configurable;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
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
    public static double bluestIceSlipperiness = 1.01;

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
            COMPRESSED_NETHER_STAR = ProtonRegisterHandler.block("compressed_nether_star", new CompressedNetherStarBlock());

            BLUER_ICE = ProtonRegisterHandler.block("bluer_ice", new BluerIceBlock(), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

            BLUEST_ICE = ProtonRegisterHandler.block("bluest_ice", new BluestIceBlock(), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

            COMPRESSED_DIAMOND_BLOCK =
                    ProtonRegisterHandler.block("compressed_diamond_block",
                                                new CompressedDiamondBlock(),
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
