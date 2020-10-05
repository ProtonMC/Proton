package io.github.hydos.proton.base.block;

import io.github.hydos.proton.util.ProtonRegisterUtil;
import io.github.hydos.proton.util.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ProtonSlabBlock extends SlabBlock {

    public final Block parent;

    public ProtonSlabBlock(Block parent) {
        super(VariantHandler.realStateCopy(parent));

        this.parent = parent;
        System.out.println(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab").toString());
        ProtonRegisterUtil.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), this);
        ProtonRegisterUtil.item(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), new BlockItem(this, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        // TODO: uhh
        //RenderLayerHandler.setInherited(this, parent.getBlock());
    }

}
