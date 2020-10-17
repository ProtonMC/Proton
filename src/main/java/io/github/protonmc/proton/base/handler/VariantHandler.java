package io.github.protonmc.proton.base.handler;

import io.github.protonmc.proton.base.block.ProtonSlabBlock;
import io.github.protonmc.proton.base.block.ProtonStairsBlock;
import io.github.protonmc.proton.base.block.ProtonWallBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility for registering walls, slabs and stairs for existing blocks.
 *
 * @author kara-b
 */
public class VariantHandler {

    /**
     * A {@link List} of all slabs registered using this class.
     */
    public static final List<ProtonSlabBlock> SLABS = new LinkedList<>();

    /**
     * A {@link List} of all stair blocks registered using this class.
     */
    public static final List<ProtonStairsBlock> STAIRS = new LinkedList<>();
    /**
     * A {@link List} of all walls registered using this class.
     */
    public static final List<ProtonWallBlock> WALLS = new LinkedList<>();

    /**
     * Adds slab, stair and wall variants to multiple blocks.
     *
     * @param blocks The blocks to add the variants to.
     */
    public static void addSlabStairsWall(Block... blocks) {
        addSlabAndStairs(blocks);
        addWall(blocks);
    }

    /**
     * Adds slab and stair variants to multiple blocks.
     *
     * @param blocks The blocks to add the variants to.
     */
    public static void addSlabAndStairs(Block... blocks) {
        addSlab(blocks);
        addStairs(blocks);
    }

    /**
     * Adds a wall variant to multiple blocks.
     *
     * @param blocks The blocks to add the variant to.
     */
    public static void addWall(Block... blocks) {
        Arrays.stream(blocks).forEach(block -> WALLS.add(new ProtonWallBlock(block)));
    }

    /**
     * Adds a slab variants to multiple blocks.
     *
     * @param blocks The blocks to add the variant to.
     */
    public static void addSlab(Block... blocks) {
        Arrays.stream(blocks).forEach(block -> SLABS.add(new ProtonSlabBlock(block)));
    }

    /**
     * Adds a stair variant to multiple blocks.
     *
     * @param blocks The blocks to add the variant to.
     */
    public static void addStairs(Block... blocks) {
        Arrays.stream(blocks).forEach(block -> STAIRS.add(new ProtonStairsBlock(block)));
    }

    /**
     * Copies the settings of a block.
     *
     * @param parent The block to copy the settings from.
     *
     * @return The copied settings.
     */
    public static AbstractBlock.Settings realStateCopy(Block parent) {
        return AbstractBlock.Settings.copy(parent);
    }

}
