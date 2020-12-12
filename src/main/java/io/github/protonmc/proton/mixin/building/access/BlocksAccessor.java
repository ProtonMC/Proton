package io.github.protonmc.proton.mixin.building.access;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.module.building.CompressedItemsModule;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Blocks.class)
public interface BlocksAccessor {
    @Invoker("never")
    @FromModule(CompressedItemsModule.class)
    static boolean invokeNever(BlockState state, BlockView world, BlockPos pos) { throw null; }
}
