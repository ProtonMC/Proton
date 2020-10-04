package io.github.hydos.proton.module.building.common.block;

import io.github.hydos.proton.module.building.ThatchModule;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThatchBlock extends Block {
    public ThatchBlock() {
        super(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).sounds(BlockSoundGroup.CROP)
                .hardness(0.5F).resistance(0.5F)
                .breakByTool(FabricToolTags.HOES).materialColor(MaterialColor.YELLOW));
    }

    @Override
    public void onLandedUpon(World world, BlockPos pos, Entity entity, float distance) {
        entity.handleFallDamage(distance, (float) ThatchModule.fallDamageMultiplier);
    }
}
