package io.github.protonmc.proton.base.handler;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.Files;
import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.ArtificeResourcePack.ClientResourcePackBuilder;
import com.swordglowsblue.artifice.api.resource.StringResource;
import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
            for (ProtonModule m : ModuleManager.getInstance().getModules())
                m.registerResources(r);
        });
    }

    public ClientResourcePackBuilder pack;
    public ResourceHandler(ClientResourcePackBuilder pack) {
        this.pack = pack;
    }

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

        try {
            File template_stairs_file = FabricLoader.getInstance().getModContainer(Proton.MOD_ID).get()
                    .getPath("assets/" + Proton.MOD_ID + "/templates/stairs_blockstate_template.json").toFile();
            String template_stairs_string =  Files.toString(template_stairs_file, StandardCharsets.UTF_8);
            template_stairs_string = template_stairs_string.replaceAll("model_normal", identifier("block/" + base + "_stairs").toString())
                    .replaceAll("model_inner", identifier("block/" + base + "_inner_stairs").toString())
                    .replaceAll("model_outer", identifier("block/" + base + "_outer_stairs").toString());

            pack.add(identifier("blockstates/" + base + "_stairs.json"), new StringResource(template_stairs_string));
        } catch (Exception exception) {
            Proton.LOGGER.error("Exception when loading template for stairs", exception);
        }

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

        try {
            File template_walls_file = FabricLoader.getInstance().getModContainer(Proton.MOD_ID).get()
                    .getPath("assets/" + Proton.MOD_ID + "/templates/walls_blockstate_template.json").toFile();
            String template_walls_string =  Files.toString(template_walls_file, StandardCharsets.UTF_8);
            template_walls_string = template_walls_string.replaceAll("model_post", identifier("block/" + base + "_wall_post").toString())
                    .replaceAll("model_side", identifier("block/" + base + "_wall_side").toString())
                    .replaceAll("model_side_tall", identifier("block/" + base + "_wall_side_tall").toString());

            pack.add(identifier("blockstates/" + base + "_wall.json"), new StringResource(template_walls_string));
        } catch (Exception exception) {
            Proton.LOGGER.error("Exception when loading template for walls", exception);
        }

        pack.addItemModel(identifier(base+"_wall"), model -> model.parent(identifier("block/" + base + "_wall_inventory")));
    }
    //endregion
}
