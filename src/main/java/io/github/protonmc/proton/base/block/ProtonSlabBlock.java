package io.github.protonmc.proton.base.block;

import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A class representing a SlabBlock which is automatically registered using the ProtonRegisterHandler.
 * This class is used to automatically create and register a SlabBlock for an existing block.
 * @author kara-b
 */
public class ProtonSlabBlock extends SlabBlock {
    public final Block parent;

    /**
     * A constructor that... constructs a ProtonSlabBlock.
     * <p>
     *     This constructor takes in a Block, registers a slab block and returns it.
     * </p>
     * @param parent The block to register the slab from.
     */
    public ProtonSlabBlock(Block parent) {
        super(VariantHandler.realStateCopy(parent));

        this.parent = parent;
        ProtonRegisterHandler.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), this);
        ProtonRegisterHandler.item(new Identifier(Registry.BLOCK.getId(parent).toString() + "_slab"), new BlockItem(this, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
