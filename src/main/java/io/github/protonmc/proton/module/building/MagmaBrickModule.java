package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.MagmaBrickBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * Module for magma brick block.
 * @author TehcJS
 */
public class MagmaBrickModule extends ProtonModule {
	public static MagmaBrickBlock magmaBrickBlock;

	public MagmaBrickModule() {
		super(Proton.identifier("magma_bricks"));
	}

	/**
	 * @see ProtonModule#commonInit()
	 */
	@Override
	public void commonInit() {
		if (!this.enabled) { return; }

		magmaBrickBlock = new MagmaBrickBlock();
		ProtonRegisterHandler.block("magma_bricks", magmaBrickBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
		VariantHandler.addSlabStairsWall(magmaBrickBlock);
	}

	/**
	 * @see ProtonModule#registerResources(ResourceHandler)
	 */
	@Override
	public void registerResources(ResourceHandler resourceHandler) {
		resourceHandler.generateSimpleBlock("magma_bricks");
		resourceHandler.generateSlabsStairs("magma_bricks");
		resourceHandler.generateWalls("magma_bricks");
	}
}
