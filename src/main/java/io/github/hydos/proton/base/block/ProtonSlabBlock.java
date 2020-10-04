package io.github.hydos.proton.base.block;

import io.github.hydos.proton.util.ProtonRegisterUtil;
import io.github.hydos.proton.util.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ProtonSlabBlock extends Block {

    public final Block parent;

    public ProtonSlabBlock(Block parent) {
        super(VariantHandler.realStateCopy(parent));

        this.parent = parent;
        System.out.println(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab").toString());
        ProtonRegisterUtil.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), this);
        ProtonRegisterUtil.item(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), new BlockItem(parent, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        // TODO: uhh
        //RenderLayerHandler.setInherited(this, parent.getBlock());
    }

}
