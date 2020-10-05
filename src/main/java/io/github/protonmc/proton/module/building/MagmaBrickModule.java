package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.MagmaBrickBlock;
import io.github.protonmc.proton.util.ProtonRegisterUtil;
import io.github.protonmc.proton.util.VariantHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * @author TehcJS
 */
public class MagmaBrickModule extends ProtonModule {
	public static MagmaBrickBlock magmaBrickBlock;

	public MagmaBrickModule() {
		super(Proton.identifier("magma_bricks"));
	}

	@Override
	public void commonInit() {
		if (!this.enabled) { return; }

		magmaBrickBlock = new MagmaBrickBlock();
		ProtonRegisterUtil.block("magma_bricks", magmaBrickBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
		VariantHandler.addSlabStairsWall(magmaBrickBlock);
	}
}
