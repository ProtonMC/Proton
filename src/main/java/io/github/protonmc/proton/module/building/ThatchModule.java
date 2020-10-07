package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.tiny_config.Configurable;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.ThatchBlock;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ThatchModule extends ProtonModule {
    @Configurable
    public static double fallDamageMultiplier = 0.5;

    public static ThatchBlock thatchBlock;

    public ThatchModule() {
        super(Proton.identifier("thatch"));
    }

    @Override
    public void commonInit() {
        if (!this.enabled) { return; }

        thatchBlock = new ThatchBlock();
        ProtonRegisterHandler.block("thatch", thatchBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
        FlammableBlockRegistry.getDefaultInstance().add(thatchBlock, 300, 20);
        CompostingChanceRegistry.INSTANCE.add(thatchBlock, 0.65F); // Make it compostable
        VariantHandler.addSlabAndStairs(thatchBlock);
    }

    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        resourceHandler.generateSimpleBlock("thatch");
        resourceHandler.generateSlabsStairs("thatch");
    }
}
