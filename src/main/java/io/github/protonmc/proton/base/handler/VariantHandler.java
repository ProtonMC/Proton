package io.github.protonmc.proton.base.handler;

import io.github.protonmc.proton.base.block.ProtonSlabBlock;
import io.github.protonmc.proton.base.block.ProtonStairsBlock;
import io.github.protonmc.proton.base.block.ProtonWallBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.LinkedList;
import java.util.List;

public class VariantHandler {

    public static final List<ProtonSlabBlock> SLABS = new LinkedList<>();
    public static final List<ProtonStairsBlock> STAIRS = new LinkedList<>();
    public static final List<ProtonWallBlock> WALLS = new LinkedList<>();

    public static void addSlabStairsWall(Block block) {
        addSlabAndStairs(block);
        addWall(block);
    }

    public static void addSlabAndStairs(Block block) {
        addSlab(block);
        addStairs(block);
    }

    public static void addSlab(Block block) {
        SLABS.add(new ProtonSlabBlock(block));
    }

    public static void addStairs(Block block) {
        STAIRS.add(new ProtonStairsBlock(block));
    }

    public static void addWall(Block block) {
        WALLS.add(new ProtonWallBlock(block));
    }

    public static AbstractBlock.Settings realStateCopy(Block parent) {
        return AbstractBlock.Settings.copy(parent);
    }

}
