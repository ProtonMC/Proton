package io.github.protonmc.proton.module.building.common.block;

import io.github.protonmc.proton.module.ProtonModule;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class BambooMatBlock extends Block {
    public static final EnumProperty<Direction> FACING = Properties.HOPPER_FACING;

    public BambooMatBlock(ProtonModule protonModule) {
        super(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).hardness(0.5F));
//        super("bamboo_mat", protonModule, ItemGroup.BUILDING_BLOCKS,
//                Block.Properties.create(Material.WOOD, MaterialColor.YELLOW)
//                        .hardnessAndResistance(0.5F)
//                        .sound(SoundType.WOOD));

        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction dir = ctx.getPlayer().getHorizontalFacing();
        if(ctx.getPlayer().pitch > 70)
            dir = Direction.DOWN;

        if(dir != Direction.DOWN) {
            Direction opposite = dir.getOpposite();
            BlockPos target = ctx.getBlockPos().offset(opposite);
            BlockState state = ctx.getWorld().getBlockState(target);

            if(state.getBlock() != this || state.get(FACING) != opposite) {
                target = ctx.getBlockPos().offset(dir);
                state = ctx.getWorld().getBlockState(target);

                if(state.getBlock() == this && state.get(FACING) == dir)
                    dir = opposite;
            }
        }

        return getDefaultState().with(FACING, dir);
    }



//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
//        Direction dir = ctx.getPlacementHorizontalFacing();
//        if(ctx.getPlayer().rotationPitch > 70)
//            dir = Direction.DOWN;
//
//        if(dir != Direction.DOWN) {
//            Direction opposite = dir.getOpposite();
//            BlockPos target = ctx.getPos().offset(opposite);
//            BlockState state = ctx.getWorld().getBlockState(target);
//
//            if(state.getBlock() != this || state.get(FACING) != opposite) {
//                target = ctx.getPos().offset(dir);
//                state = ctx.getWorld().getBlockState(target);
//
//                if(state.getBlock() == this && state.get(FACING) == dir)
//                    dir = opposite;
//            }
//        }
//
//        return getDefaultState().with(FACING, dir);
//    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//        builder.add(FACING);
//    }

}
