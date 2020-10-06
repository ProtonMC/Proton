package io.github.protonmc.proton.module.decoration.tater;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

/**
 * @author TehcJS
 */
public class FatPotatoBlock extends HorizontalFacingBlock {
    public FatPotatoBlock() {
        super(Settings.of(Material.UNUSED_PLANT)
                .emissiveLighting((s, v, p) -> true)
                .breakInstantly()
                .luminance((state) -> 7));
        setDefaultState(getStateManager().getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
}
