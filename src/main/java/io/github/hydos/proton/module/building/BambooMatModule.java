package io.github.hydos.proton.module.building;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.module.ProtonModule;
import io.github.hydos.proton.module.building.common.block.BambooMatBlock;
import io.github.hydos.proton.util.ProtonRegisterUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BambooMatModule extends ProtonModule {
    public BambooMatModule() {
        super(Proton.identifier("bamboo_mat"));
    }

    @Override
    public void commonInit() {
        if (!this.enabled) { return; }

        ProtonRegisterUtil.block("bamboo_mat", new BambooMatBlock(this), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    }
}
