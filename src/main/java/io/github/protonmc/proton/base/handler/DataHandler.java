package io.github.protonmc.proton.base.handler;

import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.util.Identifier;

import static io.github.protonmc.proton.Proton.identifier;

/**
 * A class that handles generating data and server-side resources using Artifice.
 *
 * @author kara-b
 */
public class DataHandler {
    /**
     * The current active Proton {@linkplain ArtificeResourcePack data pack}.
     */
    private static ArtificeResourcePack DATA_PACK;

    /**
     * A {@link ArtificeResourcePack.ServerResourcePackBuilder} that builds {@link DataHandler#DATA_PACK}.
     */
    public ArtificeResourcePack.ServerResourcePackBuilder pack;

    /**
     * A simple constructor which sets {@link DataHandler#pack}.
     *
     * @param pack The resource pack builder to use for this instance.
     */
    public DataHandler(ArtificeResourcePack.ServerResourcePackBuilder pack) {
        this.pack = pack;
    }

    /**
     * Registers data as a data pack.
     */
    public static void registerData() {
        DATA_PACK = Artifice.registerData(identifier("data"), pack -> {
            pack.setDisplayName("Proton's Data Pack");
            DataHandler dataHandler = new DataHandler(pack);
            for (ProtonModule m : ModuleManager.getInstance().getModules())
                m.registerData(dataHandler);
        });
    }

    public void generateSimpleStairsRecipe(String base) {
        pack.addShapedRecipe(identifier(base + "_stairs"), shapedRecipeBuilder -> {
            shapedRecipeBuilder.pattern("#  ", "## ", "###");
            shapedRecipeBuilder.ingredientItem("#".charAt(0), identifier(base));
            // 6 because why the hell would 6 of base turn into 4 stairs
            shapedRecipeBuilder.result(identifier(base + "_stairs"), 6);
        });
    }

    public void generateSimpleSlabRecipe(String base) {
        pack.addShapedRecipe(identifier(base + "_slab"), shapedRecipeBuilder -> {
            shapedRecipeBuilder.pattern("###");
            shapedRecipeBuilder.ingredientItem("#".charAt(0), identifier(base));
            shapedRecipeBuilder.result(identifier(base + "_slab"), 6);
        });
    }

    /**
     * Generates a simple block loot table for a given block path.
     *
     * @param base The block path to generate the loot table from.
     */
    public void generateSimpleBlockLoot(String base) {
        generateSimpleBlockLoot(base, false);
    }

    /**
     * Generates a simple block loot table for a given block path that can optionally only drop when mined with silk touch.
     *
     * @param base      The block path to generate the loot table from.
     * @param silkTouch Wether the block requires Silk Touch to be given the item.
     */
    public void generateSimpleBlockLoot(String base, boolean silkTouch) {
        pack.addLootTable(identifier("blocks/" + base), lootTableBuilder -> {
            lootTableBuilder.type(new Identifier("minecraft:block"));
            lootTableBuilder.pool(pool -> {
                pool.rolls(1);
                pool.entry(entry -> entry.type(new Identifier("minecraft:item")).name(identifier(base)));
                // Absolutely cursed
                if (!silkTouch) {
                    pool.condition(new Identifier("minecraft:survives_explosion"), jsonObjectBuilder -> {
                    });
                } else {
                    pool.condition(new Identifier("minecraft:match_tool"), jsonObjectBuilder -> {
                        jsonObjectBuilder.addObject("predicate", predicateObjectBuilder -> {
                            predicateObjectBuilder.addArray("enchantments", enchantmentArrayBuilder -> {
                                enchantmentArrayBuilder.addObject(enchantmentObjectBuilder -> {
                                    enchantmentObjectBuilder.add("enchantment", "minecraft:silk_touch");
                                    enchantmentObjectBuilder.addObject("levels", levelsObjectBuilder -> levelsObjectBuilder.add("min", 1));
                                });
                            });
                        });
                    });
                }
            });
        });
    }
}
