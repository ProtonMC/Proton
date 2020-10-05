package io.github.hydos.proton.module.building;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.config.Configurable;
import io.github.hydos.proton.module.Module;
import io.github.hydos.proton.module.building.common.block.ThatchBlock;
import io.github.hydos.proton.util.ProtonRegisterUtil;
import io.github.hydos.proton.util.VariantHandler;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ThatchModule extends Module {
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
        ProtonRegisterUtil.block("thatch", thatchBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
        FlammableBlockRegistry.getDefaultInstance().add(thatchBlock, 300, 20);
        CompostingChanceRegistry.INSTANCE.add(thatchBlock, 0.65F); // Make it compostable
        VariantHandler.addSlabAndStairs(thatchBlock);
    }
}
