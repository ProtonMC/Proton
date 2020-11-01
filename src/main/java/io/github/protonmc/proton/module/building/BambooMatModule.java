package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.DataHandler;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.BambooMatBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import static io.github.protonmc.proton.Proton.identifier;

/**
 * Module that adds a bamboo mat.
 *
 * @author kara-b
 */
public class BambooMatModule extends ProtonModule {
    public BambooMatModule() {
        super(Proton.identifier("bamboo_mat"));
    }

    /**
     * @see ProtonModule#commonInit()
     */
    @Override
    public void commonInit() {
        if (!this.enabled) {
            return;
        }

        ProtonRegisterHandler.block("bamboo_mat", new BambooMatBlock(this), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    }

    @Override
    public void registerData(DataHandler dataHandler) {
        dataHandler.generateSimpleBlockLoot("bamboo_mat", false);

        // Generate recipes
        dataHandler.pack.addShapedRecipe(identifier("bamboo_mat"), shapedRecipeBuilder -> {
            shapedRecipeBuilder.pattern("SBS", "BBB", "SBS");
            shapedRecipeBuilder.ingredientItem('B', new Identifier("minecraft:bamboo"));
            shapedRecipeBuilder.ingredientItem('S', new Identifier("minecraft:stick"));
            shapedRecipeBuilder.result(identifier("bamboo_mat"), 2);
        });
    }
}
