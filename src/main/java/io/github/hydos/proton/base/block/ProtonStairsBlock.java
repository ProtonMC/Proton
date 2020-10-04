package io.github.hydos.proton.base.block;

import io.github.hydos.proton.util.ProtonRegisterUtil;
import io.github.hydos.proton.util.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ProtonStairsBlock extends Block {

    public final Block parent;

    public ProtonStairsBlock(Block parent) {
        super(VariantHandler.realStateCopy(parent));

        this.parent = parent;
        ProtonRegisterUtil.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_stairs"), this);
        ProtonRegisterUtil.item(new Identifier(Registry.BLOCK.getId(parent).toString() + "_stairs"), new BlockItem(parent, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        // TODO: uhh
        //RenderLayerHandler.setInherited(this, parent.getBlock());
    }

}
