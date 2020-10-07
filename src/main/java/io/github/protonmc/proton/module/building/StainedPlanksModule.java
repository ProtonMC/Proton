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

import java.lang.reflect.Field;

public class StainedPlanksModule extends ProtonModule {
    public StainedPlanksModule() {
        super(Proton.identifier("stained_planks"));
    }

    @Override
    public void commonInit() {
        if (!this.enabled) { return; }

        ModuleBlocks.register();
    }

    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        // i'm sorry
        for (Field f : ModuleBlocks.class.getFields()) {
            resourceHandler.generateSimpleBlock(f.getName().toLowerCase());
            resourceHandler.generateSlabsStairs(f.getName().toLowerCase());
        }
    }

    public static class ModuleBlocks {
        public static Block WHITE_STAINED_PLANKS;
        public static Block ORANGE_STAINED_PLANKS;
        public static Block MAGENTA_STAINED_PLANKS;
        public static Block LIGHT_BLUE_STAINED_PLANKS;
        public static Block YELLOW_STAINED_PLANKS;
        public static Block LIME_STAINED_PLANKS;
        public static Block PINK_STAINED_PLANKS;
        public static Block GRAY_STAINED_PLANKS;
        public static Block LIGHT_GRAY_STAINED_PLANKS;
        public static Block CYAN_STAINED_PLANKS;
        public static Block PURPLE_STAINED_PLANKS;
        public static Block BLUE_STAINED_PLANKS;
        public static Block BROWN_STAINED_PLANKS;
        public static Block GREEN_STAINED_PLANKS;
        public static Block RED_STAINED_PLANKS;
        public static Block BLACK_STAINED_PLANKS;

        public static void register() {
            WHITE_STAINED_PLANKS = ProtonRegisterHandler.block("white_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            ORANGE_STAINED_PLANKS = ProtonRegisterHandler.block("orange_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            MAGENTA_STAINED_PLANKS = ProtonRegisterHandler.block("magenta_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            LIGHT_BLUE_STAINED_PLANKS = ProtonRegisterHandler.block("light_blue_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            YELLOW_STAINED_PLANKS = ProtonRegisterHandler.block("yellow_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            LIME_STAINED_PLANKS = ProtonRegisterHandler.block("lime_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            PINK_STAINED_PLANKS = ProtonRegisterHandler.block("pink_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            GRAY_STAINED_PLANKS = ProtonRegisterHandler.block("gray_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            LIGHT_GRAY_STAINED_PLANKS = ProtonRegisterHandler.block("light_gray_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            CYAN_STAINED_PLANKS = ProtonRegisterHandler.block("cyan_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            PURPLE_STAINED_PLANKS = ProtonRegisterHandler.block("purple_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            BLUE_STAINED_PLANKS = ProtonRegisterHandler.block("blue_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            BROWN_STAINED_PLANKS = ProtonRegisterHandler.block("brown_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            GREEN_STAINED_PLANKS = ProtonRegisterHandler.block("green_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            RED_STAINED_PLANKS = ProtonRegisterHandler.block("red_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            BLACK_STAINED_PLANKS = ProtonRegisterHandler.block("black_stained_planks",
                    new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)), new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

            VariantHandler.addSlabAndStairs(WHITE_STAINED_PLANKS, ORANGE_STAINED_PLANKS, MAGENTA_STAINED_PLANKS, LIGHT_BLUE_STAINED_PLANKS, YELLOW_STAINED_PLANKS,
                    LIME_STAINED_PLANKS, PINK_STAINED_PLANKS, GRAY_STAINED_PLANKS, LIGHT_GRAY_STAINED_PLANKS, CYAN_STAINED_PLANKS, PURPLE_STAINED_PLANKS,
                    BLUE_STAINED_PLANKS, BROWN_STAINED_PLANKS, GREEN_STAINED_PLANKS, RED_STAINED_PLANKS, BLACK_STAINED_PLANKS);
        }
    }
}
