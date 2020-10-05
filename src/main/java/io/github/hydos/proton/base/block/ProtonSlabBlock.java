package io.github.hydos.proton.base.block;

import io.github.hydos.proton.util.ProtonRegisterUtil;
import io.github.hydos.proton.util.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ProtonSlabBlock extends SlabBlock {

    public final Block parent;

    public ProtonSlabBlock(Block parent) {
        super(VariantHandler.realStateCopy(parent));

        this.parent = parent;
        ProtonRegisterUtil.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), this);
        ProtonRegisterUtil.item(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), new BlockItem(this, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }

}
