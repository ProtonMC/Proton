package io.github.hydos.proton.module.building;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.common.block.BambooMatBlock;
import io.github.hydos.proton.module.Module;
import io.github.hydos.proton.util.ProtonRegisterUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BambooMatModule extends Module {
    public BambooMatModule() {
        super(Proton.identifier("general_mod_blocks"));
    }

    @Override
    public void commonInit() {
        ProtonRegisterUtil.block("bamboo_mat", new BambooMatBlock(this), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    }
}
