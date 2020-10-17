package io.github.protonmc.proton.base.block;

import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A class representing a WallBlock which is automatically registered using the ProtonRegisterHandler.
 * This class is used to automatically create and register a WallBlock for an existing block.
 *
 * @author kara-b
 */
public class ProtonWallBlock extends WallBlock {
    public final Block parent;

    /**
     * A constructor that... constructs a ProtonWallBlock.
     * <p>
     * This constructor takes in a Block, registers a wall block and returns it.
     * </p>
     *
     * @param parent The block to register the wall from.
     */
    public ProtonWallBlock(Block parent) {
        super(VariantHandler.realStateCopy(parent));

        this.parent = parent;
        ProtonRegisterHandler.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_wall"), this);
        ProtonRegisterHandler.item(
                new Identifier(Registry.BLOCK.getId(parent).toString() + "_wall"),
                new BlockItem(this, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
