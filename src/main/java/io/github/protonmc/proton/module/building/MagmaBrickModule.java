package io.github.protonmc.proton.module.building;

import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.block.MagmaBrickBlock;
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
		ProtonRegisterHandler.block("magma_bricks", magmaBrickBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
		VariantHandler.addSlabStairsWall(magmaBrickBlock);
	}

	@Override
	public void registerResources(ArtificeResourcePack.ClientResourcePackBuilder pack) {
		ResourceHandler.generateSimpleBlock(pack, "magma_bricks");
		ResourceHandler.generateSlabsStairs(pack, "magma_bricks");
		ResourceHandler.generateWalls(pack, "magma_bricks");
	}
}
