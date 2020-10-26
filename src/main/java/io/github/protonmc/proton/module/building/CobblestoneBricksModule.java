package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;

public class CobblestoneBricksModule extends ProtonModule {
    public CobblestoneBricksModule() {
        super(Proton.identifier("cobblestone_bricks"));
    }

    @Override
    public void commonInit() {
        if (!this.enabled) {return;}
        ModuleBlocks.register();
    }

    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        resourceHandler.generateSimpleBlock("cobblestone_bricks");
        resourceHandler.generateSimpleBlock("mossy_cobblestone_bricks");
        resourceHandler.generateSlabsStairs("cobblestone_bricks");
        resourceHandler.generateSlabsStairs("mossy_cobblestone_bricks");
    }

    public static class ModuleBlocks {
        public static Block COBBLESTONE_BRICKS;
        public static Block MOSSY_COBBLESTONE_BRICKS;

        public static void register() {
            COBBLESTONE_BRICKS = ProtonRegisterHandler.block("cobblestone_bricks",
                    new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            MOSSY_COBBLESTONE_BRICKS = ProtonRegisterHandler.block("mossy_cobblestone_bricks",
                    new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

            VariantHandler.addSlabAndStairs(COBBLESTONE_BRICKS, MOSSY_COBBLESTONE_BRICKS);
        }
    }
}
