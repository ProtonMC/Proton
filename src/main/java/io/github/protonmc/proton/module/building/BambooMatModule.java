package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.BambooMatBlock;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * Module that adds a bamboo mat.
 * @author kata-b
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
        if (!this.enabled) { return; }

        ProtonRegisterHandler.block("bamboo_mat", new BambooMatBlock(this), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    }
}
