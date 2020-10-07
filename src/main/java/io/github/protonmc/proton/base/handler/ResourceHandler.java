package io.github.protonmc.proton.base.handler;

import com.google.common.collect.ImmutableSet;
import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.ArtificeResourcePack.ClientResourcePackBuilder;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.util.Identifier;

import static io.github.protonmc.proton.Proton.identifier;

public class ResourceHandler {
    private static ArtificeResourcePack RESOURCE_PACK;

    public static void registerAssets() {
        RESOURCE_PACK = Artifice.registerAssets(identifier("resources"), pack -> {
            pack.setDisplayName("Proton's Resource Pack");
            for (ProtonModule m : ModuleManager.getInstance().getModules())
                m.registerResources(pack);
        });
    }

    public static void generateBlockItems(ClientResourcePackBuilder pack, Iterable<String> ids) {
        for (String s : ids)
            pack.addItemModel(identifier(s), model -> model.parent(identifier("block/" + s)));
    }

    public static void generateSimpleBlock(ClientResourcePackBuilder pack, String base) {
        pack.addBlockModel(identifier(base), model -> model.parent(new Identifier("block/cube_all"))
                                                            .texture("all", identifier("block/" + base)));

        pack.addBlockState(identifier(base),
                state -> state.variant("", variant -> variant.model(identifier("block/" + base)))
        );

        generateBlockItems(pack, ImmutableSet.of(base));
    }

    public static void generateSlabsStairs(ClientResourcePackBuilder pack, String base) {
        Identifier tex = identifier("block/" + base);
        for (String s : ImmutableSet.of("slab", "slab_top", "stairs", "inner_stairs", "outer_stairs")) {
            pack.addBlockModel(
                    identifier(base + "_" + s),
                    model -> model.parent(new Identifier("block/" + s))
                                .texture("bottom", tex)
                                .texture("top", tex)
                                .texture("side", tex)
            );
        }

        pack.addBlockState(identifier(base+"_slab"),
                state -> state  .variant("type=bottom", variant -> variant
                                    .model(identifier("block/"+base+"_slab")))
                                .variant("type=top", variant -> variant
                                    .model(identifier("block/"+base+"_slab_top")))
                                .variant("type=double", variant -> variant
                                    .model(identifier("block/"+base)))
        );

        // todo stair blockstates

        generateBlockItems(pack, ImmutableSet.of(base+"_slab", base+"_stairs"));
    }

    public static void generateWalls(ClientResourcePackBuilder pack, String base) {
        Identifier tex = identifier("block/" + base);
        for (String s : ImmutableSet.of("wall_post", "wall_side", "wall_side_tall")) {
            pack.addBlockModel(
                    identifier(base + "_" + s),
                    model -> model.parent(new Identifier("block/template_" + s))
                            .texture("wall", tex)
            );
        }
        pack.addBlockModel(
                identifier(base + "_wall_inventory"),
                model -> model.parent(new Identifier("block/wall_inventory"))
                        .texture("wall", tex)
        );

        pack.addItemModel(identifier(base+"_wall"), model -> model.parent(identifier("block/" + base + "_wall_inventory")));
    }
}
