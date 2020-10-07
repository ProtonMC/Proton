package io.github.protonmc.proton.base.handler;

import com.google.common.collect.ImmutableSet;
import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.ArtificeResourcePack.ClientResourcePackBuilder;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static io.github.protonmc.proton.Proton.identifier;

public class ResourceHandler {
    private static ArtificeResourcePack RESOURCE_PACK;

    public static void registerAssets() {
        RESOURCE_PACK = Artifice.registerAssets(identifier("resources"), pack -> {
            pack.setDisplayName("Proton's Resource Pack");
            ResourceHandler r = new ResourceHandler(pack);
            BASIC_BLOCKS.forEach(r::generateSimpleBlock);
            BASIC_STAIRS.forEach(r::generateStairs);
            BASIC_SLABS.forEach(r::generateSlabs);
            BASIC_WALLS.forEach(r::generateWalls);
            for (ProtonModule m : ModuleManager.getInstance().getModules())
                m.registerResources(r);
        });
    }

    public ClientResourcePackBuilder pack;
    public ResourceHandler(ClientResourcePackBuilder pack) {
        this.pack = pack;
    }

    //region basic registry
    public static final List<String> BASIC_BLOCKS = new LinkedList<>();
    public static final List<String> BASIC_STAIRS = new LinkedList<>();
    public static final List<String> BASIC_SLABS = new LinkedList<>();
    public static final List<String> BASIC_WALLS = new LinkedList<>();

    public static void addBasicBlocks(String... bases) {
        BASIC_BLOCKS.addAll(Arrays.asList(bases));
    }
    public static void addBasicStairs(String... bases) {
        BASIC_STAIRS.addAll(Arrays.asList(bases));
    }
    public static void addBasicSlabs(String... bases) {
        BASIC_SLABS.addAll(Arrays.asList(bases));
    }
    public static void addBasicWalls(String... bases) {
        BASIC_WALLS.addAll(Arrays.asList(bases));
    }
    public static void addBasicSlabStairs(String... bases) {
        addBasicSlabs(bases); addBasicStairs(bases);
    }
    public static void addBasicSlabStairsWall(String... bases) {
        addBasicSlabStairs(bases); addBasicWalls(bases);
    }
    //endregion

    // region generate
    public void generateBlockItems(Iterable<String> ids) {
        for (String s : ids)
            pack.addItemModel(identifier(s), model -> model.parent(identifier("block/" + s)));
    }

    public void generateSimpleBlock(String base) {
        generateSimpleBlock(base, identifier("block/" + base));
    }

    public void generateSimpleBlock(String base, Identifier texture) {
        pack.addBlockModel(identifier(base), model -> model.parent(new Identifier("block/cube_all"))
                .texture("all", texture));

        pack.addBlockState(identifier(base),
                state -> state.variant("", variant -> variant.model(identifier("block/" + base)))
        );

        generateBlockItems(ImmutableSet.of(base));
    }


    public void generateSlabsStairs(String base) {
        generateSlabs(base); generateStairs(base);
    }

    public void generateSlabs(String base) {
        Identifier tex = identifier("block/" + base);
        for (String s : ImmutableSet.of("slab", "slab_top")) {
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

        generateBlockItems(ImmutableSet.of(base+"_slab"));
    }

    public void generateStairs(String base) {
        Identifier tex = identifier("block/" + base);
        for (String s : ImmutableSet.of("stairs", "inner_stairs", "outer_stairs")) {
            pack.addBlockModel(
                    identifier(base + "_" + s),
                    model -> model.parent(new Identifier("block/" + s))
                            .texture("bottom", tex)
                            .texture("top", tex)
                            .texture("side", tex)
            );
        }

        // todo stair blockstates

        generateBlockItems(ImmutableSet.of(base+"_stairs"));
    }

    public void generateWalls(String base) {
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

        // todo wall blockstates

        pack.addItemModel(identifier(base+"_wall"), model -> model.parent(identifier("block/" + base + "_wall_inventory")));
    }
    //endregion
}
