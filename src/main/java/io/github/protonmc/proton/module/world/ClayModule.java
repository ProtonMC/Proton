package io.github.protonmc.proton.module.world;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.mixin.world.access.SimpleRegistryAccess;
import io.github.protonmc.tiny_config.Configurable;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ClayModule extends ProtonModule {

    @Configurable public static int vein_size = 15;
    @Configurable public static int veins_per_chunk = 10;
    @Configurable public static int max_y_level = 256;

    public ClayModule() {
        super(Proton.identifier("clay"));
    }

    public static ConfiguredFeature<?, ?> ORE_CLAY_OVERWORLD;

    public static void mixinInit() {
        boolean enabled = ModuleManager.getInstance().isModuleEnabled(ClayModule.class);
        if (!FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
            if (BuiltinRegistries.CONFIGURED_FEATURE instanceof SimpleRegistry) {
                // Used because containsId is client-only, if you have a better solution do it.
                if (!enabled || ((SimpleRegistryAccess<ConfiguredFeature<?, ?>>) BuiltinRegistries.CONFIGURED_FEATURE).getIdToEntry().containsKey(Proton.identifier("ore_clay_overworld"))) {
                    return;
                }
            }
        } else if (!enabled || BuiltinRegistries.CONFIGURED_FEATURE.containsId(Proton.identifier("ore_clay_overworld"))) { return; }

        ORE_CLAY_OVERWORLD = Feature.ORE
                .configure(new OreFeatureConfig(
                        OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                        Blocks.CLAY.getDefaultState(),
                        vein_size)) // vein size
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                        0, // bottom offset
                        0, // min y level
                        max_y_level))) // max y level
                .spreadHorizontally()
                .repeat(veins_per_chunk); // number of veins per chunk

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Proton.identifier("ore_clay_overworld"), ORE_CLAY_OVERWORLD);
    }
}
