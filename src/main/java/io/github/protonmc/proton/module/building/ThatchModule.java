package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.DataHandler;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.ThatchBlock;
import io.github.protonmc.tiny_config.Configurable;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import static io.github.protonmc.proton.Proton.identifier;

/**
 * Adds a funni thatch block.
 *
 * @author kara-b
 */
public class ThatchModule extends ProtonModule {
    @Configurable
    public static double fallDamageMultiplier = 0.5;

    public static ThatchBlock thatchBlock;

    public ThatchModule() {
        super(Proton.identifier("thatch"));
    }

    /**
     * @see ProtonModule#commonInit()
     */
    @Override
    public void commonInit() {
        if (!this.enabled) {
            return;
        }

        thatchBlock = new ThatchBlock();
        ProtonRegisterHandler.block("thatch", thatchBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
        FlammableBlockRegistry.getDefaultInstance().add(thatchBlock, 300, 20);
        CompostingChanceRegistry.INSTANCE.add(thatchBlock, 0.65F); // Make it compostable
        VariantHandler.addSlabAndStairs(thatchBlock);
    }

    /**
     * @see ProtonModule#registerResources(ResourceHandler)
     */
    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        resourceHandler.generateSimpleBlock("thatch");
        resourceHandler.generateSlabsStairs("thatch");
    }

    @Override
    public void registerData(DataHandler dataHandler) {
        dataHandler.generateSimpleBlockLoot("thatch");
        dataHandler.generateSimpleBlockLoot("thatch_stairs");

        // Generate recipes
        dataHandler.pack.addShapedRecipe(identifier("thatch"), shapedRecipeBuilder -> {
            shapedRecipeBuilder.pattern("##", "##");
            shapedRecipeBuilder.ingredientItem('#', new Identifier("minecraft:wheat"));
            shapedRecipeBuilder.result(identifier("thatch"), 4);
        });
        dataHandler.pack.addShapedRecipe(identifier("thatch_revert"), shapedRecipeBuilder -> {
            shapedRecipeBuilder.pattern("#");
            shapedRecipeBuilder.ingredientItem('#', identifier("thatch"));
            shapedRecipeBuilder.result(new Identifier("minecraft:wheat"), 1);
        });
        dataHandler.generateSimpleStairsRecipe("thatch", true);
        dataHandler.generateSimpleSlabRecipe("thatch", true);
    }
}
