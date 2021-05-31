package io.github.protonmc.proton.module.building;

import com.google.common.collect.ImmutableSet;
import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.base.handler.DataHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.protonmc.proton.Proton.identifier;

public class TurfModule extends ProtonModule {
    public TurfModule() {
        super(Proton.identifier("turf"));
    }

    public static Block turfBlock;

    @Override
    public void commonInit() {
        if (!ProtonConfig.Building.turf) { return; }

        turfBlock = new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK));
        ProtonRegisterHandler.block("turf", turfBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
        VariantHandler.addSlabAndStairs(turfBlock);
    }

    @Override
    public void clientInit() {
        if (!ProtonConfig.Building.turf) { return; }

        Block turf_slab = Registry.BLOCK.get(new Identifier(Registry.BLOCK.getId(turfBlock).toString() + "_slab"));
        Block turf_stairs = Registry.BLOCK.get(new Identifier(Registry.BLOCK.getId(turfBlock).toString() + "_stairs"));

        ColorProviderRegistry.BLOCK.register((blockState, renderView, blockPos, tintIndex) -> {
            return renderView != null ? BiomeColors.getGrassColor(renderView, blockPos) : GrassColors.getColor(0.5D, 1.0D);
        }, turfBlock, turf_slab, turf_stairs);
    }

    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        resourceHandler.pack.addBlockModel(identifier("turf"), model -> model.parent(identifier("block/cube_all_tinted"))
                .texture("all", new Identifier("block/grass_block_top")));

        resourceHandler.pack.addBlockState(identifier("turf"),
                state -> state.variant("", variant -> {
                    variant.model(identifier("block/turf"));
                    variant.model(identifier("block/turf")).rotationY(90);
                    variant.model(identifier("block/turf")).rotationY(180);
                    variant.model(identifier("block/turf")).rotationY(270);
                })
        );

        resourceHandler.generateBlockItems(ImmutableSet.of("turf"));
    }

    @Override
    public void registerData(DataHandler dataHandler) {
        dataHandler.generateSimpleBlockLoot("turf");
        dataHandler.generateSimpleBlockLoot("turf_stairs");

        // Generate recipes
        dataHandler.pack.addShapedRecipe(identifier("turf"), shapedRecipeBuilder -> {
            shapedRecipeBuilder.pattern("##", "##");
            shapedRecipeBuilder.ingredientItem("#".charAt(0), new Identifier("minecraft:grass_block"));
            shapedRecipeBuilder.result(identifier("turf"), 4);
        });
        dataHandler.generateSimpleStairsRecipe("turf", true);
        dataHandler.generateSimpleSlabRecipe("turf", true);
    }
}
