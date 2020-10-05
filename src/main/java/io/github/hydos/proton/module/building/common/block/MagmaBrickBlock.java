package io.github.hydos.proton.module.building.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

// todo: allow mobs that are immune to fire to spawn on magma bricks
/**
 * @author TehcJS
 */
public class MagmaBrickBlock extends Block {
	public MagmaBrickBlock() {
		super(FabricBlockSettings.copyOf(Blocks.MAGMA_BLOCK)
				.emissiveLighting((s, v, p) -> true)
				.hardness(1.5F)
				.resistance(10F)
				.luminance((state) -> 3));
	}
}
