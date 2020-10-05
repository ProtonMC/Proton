package io.github.protonmc.proton.module.building;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.proton.module.building.common.item.CompressedNetherStarItem;
import io.github.protonmc.proton.util.ProtonRegisterUtil;
import io.github.protonmc.tiny_config.Configurable;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.ItemGroup;
import org.apache.logging.log4j.Level;

public class CompressedItemsModule extends ProtonModule {
	@Configurable
	public static float bluer_ice_slipperiness = 0.9998F;

	@Configurable
	public static float bluest_ice_slipperiness = 1.0F;

	@Configurable
	public static int bluest_ice_luminance = 3;

	public CompressedItemsModule() {
		super(Proton.identifier("compressed_items"));
	}

	@Override
	public void commonInit() {
		if (!enabled) return;
		Proton.LOGGER.log(Level.INFO, "Initializing Compressed Item module!");
		ModuleBlocks.register();
		ModuleItems.register();
	}

	public static class ModuleBlocks {
		public static Block COMPRESSED_NETHER_STAR;
		public static Block BLUER_ICE;
		public static Block BLUEST_ICE;
		public static Block COMPRESSED_DIAMOND_BLOCK;

		public static void register() {
			COMPRESSED_NETHER_STAR = ProtonRegisterUtil.block(
					"compressed_nether_star",
					new Block(FabricBlockSettings.of(Material.STONE)
												 .breakByHand(false)
												 .requiresTool()
												 .breakByTool(FabricToolTags.PICKAXES, 2)
												 .strength(6.2F, 1200.0F)
												 .nonOpaque()
												 .blockVision(Blocks::never))
															 );

			BLUER_ICE = ProtonRegisterUtil.block(
					"bluer_ice",
					new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE).slipperiness(bluer_ice_slipperiness).breakByTool(FabricToolTags.PICKAXES)),
					new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
												);

			BLUEST_ICE = ProtonRegisterUtil.block(
					"bluest_ice",
					new Block(FabricBlockSettings.copyOf(Blocks.BLUE_ICE)
												 .slipperiness(bluest_ice_slipperiness)
												 .breakByTool(FabricToolTags.PICKAXES)
												 .lightLevel(bluest_ice_luminance)),
					new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
												 );

			COMPRESSED_DIAMOND_BLOCK = ProtonRegisterUtil.block(
					"compressed_diamond_block",
					new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)
												 .strength(8.0F, 9.0F)
												 .requiresTool()
												 .breakByTool(FabricToolTags.PICKAXES, 2)),
					new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)
															   );
		}
	}

	public static class ModuleItems {
		public static CompressedNetherStarItem COMPRESSED_NETHER_STAR;

		public static void register() {
			COMPRESSED_NETHER_STAR = ProtonRegisterUtil.item("compressed_nether_star", new CompressedNetherStarItem());
		}
	}
}
