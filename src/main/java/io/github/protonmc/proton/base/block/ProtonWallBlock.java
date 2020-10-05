package io.github.protonmc.proton.base.block;

import io.github.protonmc.proton.util.ProtonRegisterUtil;
import io.github.protonmc.proton.util.VariantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ProtonWallBlock extends WallBlock {

	public final Block parent;

	public ProtonWallBlock(Block parent) {
		super(VariantHandler.realStateCopy(parent));

		this.parent = parent;
		ProtonRegisterUtil.block(new Identifier(Registry.BLOCK.getId(parent).toString() + "_wall"), this);
		ProtonRegisterUtil.item(new Identifier(Registry.BLOCK.getId(parent).toString() + "_wall"), new BlockItem(this, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	}

}
