package io.github.protonmc.proton.module.building.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

/**
 * @author TehcJS
 */
public class MagmaBrickBlock extends Block {
    public MagmaBrickBlock() {
        super(FabricBlockSettings.copyOf(Blocks.MAGMA_BLOCK)
                .emissiveLighting((s, v, p) -> true)
                .hardness(1.5F)
                .resistance(10F)
                .luminance((state) -> 3)
                .allowsSpawning((state, view, pos, type) -> type.isFireImmune()));
    }
}
