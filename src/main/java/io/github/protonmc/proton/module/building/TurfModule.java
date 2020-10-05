package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.proton.util.ProtonRegisterUtil;
import io.github.protonmc.proton.util.VariantHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TurfModule extends ProtonModule {
	public TurfModule() {
		super(Proton.identifier("turf"));
	}

	public static Block turfBlock;

	@Override
	public void commonInit() {
		if (!this.enabled) { return; }

		turfBlock = new Block(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK));
		ProtonRegisterUtil.block("turf", turfBlock, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
		VariantHandler.addSlabAndStairs(turfBlock);
	}

	@Override
	public void clientInit() {
		if (!this.enabled) { return; }

		Block turf_slab = Registry.BLOCK.get(new Identifier(Registry.BLOCK.getId(turfBlock).toString() + "_slab"));
		Block turf_stairs = Registry.BLOCK.get(new Identifier(Registry.BLOCK.getId(turfBlock).toString() + "_stairs"));

		ColorProviderRegistry.BLOCK.register((blockState, renderView, blockPos, tintIndex) -> {
			return renderView != null ? BiomeColors.getGrassColor(renderView, blockPos) : GrassColors.getColor(0.5D, 1.0D);
		}, turfBlock, turf_slab, turf_stairs);
	}
}
