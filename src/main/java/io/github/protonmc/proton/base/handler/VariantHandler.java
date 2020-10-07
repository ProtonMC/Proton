package io.github.protonmc.proton.base.handler;

import io.github.protonmc.proton.base.block.ProtonSlabBlock;
import io.github.protonmc.proton.base.block.ProtonStairsBlock;
import io.github.protonmc.proton.base.block.ProtonWallBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class VariantHandler {

    public static final List<ProtonSlabBlock> SLABS = new LinkedList<>();
    public static final List<ProtonStairsBlock> STAIRS = new LinkedList<>();
    public static final List<ProtonWallBlock> WALLS = new LinkedList<>();

    public static void addSlabStairsWall(Block... blocks) {
        addSlabAndStairs(blocks);
        addWall(blocks);
    }

    public static void addSlabAndStairs(Block... blocks) {
        addSlab(blocks);
        addStairs(blocks);
    }

    public static void addSlab(Block... blocks) {
        Arrays.stream(blocks).forEach(block -> SLABS.add(new ProtonSlabBlock(block)));
    }

    public static void addStairs(Block... blocks) {
        Arrays.stream(blocks).forEach(block -> STAIRS.add(new ProtonStairsBlock(block)));
    }

    public static void addWall(Block... blocks) {
        Arrays.stream(blocks).forEach(block -> WALLS.add(new ProtonWallBlock(block)));
    }

    public static AbstractBlock.Settings realStateCopy(Block parent) {
        return AbstractBlock.Settings.copy(parent);
    }

}
