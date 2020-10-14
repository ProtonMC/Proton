package io.github.protonmc.proton.base.handler;

import com.google.common.collect.ImmutableSet;
import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.util.Identifier;

import static io.github.protonmc.proton.Proton.identifier;

public class DataHandler {
    private static ArtificeResourcePack DATA_PACK;

    public ArtificeResourcePack.ServerResourcePackBuilder pack;

    public DataHandler(ArtificeResourcePack.ServerResourcePackBuilder pack) {
        this.pack = pack;
    }

    public static void registerAssets() {
        DATA_PACK = Artifice.registerData(identifier("data"), pack -> {
            pack.setDisplayName("Proton's Data Pack");
            DataHandler dataHandler = new DataHandler(pack);
            for (ProtonModule m : ModuleManager.getInstance().getModules())
                m.registerData(dataHandler);
        });
    }

    public void generateSimpleBlockLoot(String base) {
        generateSimpleBlockLoot(base, false);
    }

    public void generateSimpleBlockLoot(String base, boolean silkTouch) {
        pack.addLootTable(identifier("blocks/" + base), lootTableBuilder -> {
            lootTableBuilder.type(new Identifier("minecraft:block"));
            lootTableBuilder.pool(pool -> {
                pool.rolls(1);
                pool.entry(entry -> entry.type(new Identifier("minecraft:item")).name(identifier(base)));
                // Absolutely cursed
                if (!silkTouch) {
                    pool.condition(new Identifier("minecraft:survives_explosion"), jsonObjectBuilder -> {});
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
