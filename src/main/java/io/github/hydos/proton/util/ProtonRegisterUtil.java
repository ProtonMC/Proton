package io.github.hydos.proton.util;

import io.github.hydos.proton.Proton;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ProtonRegisterUtil {

    /**
     * @param name
     *        Name of item instance to be registered
     * @param item
     *        Item instance to be registered
     * @return Item instanced registered
     */
    public static <T extends Item> T registerItems(String name, T item) {
        return registerItems(Proton.identifier(name), item);
    }

    /**
     * @param name
     *        Identifier of item instance to be registered
     * @param item
     *        Item instance to be registered
     * @return Item instance registered
     */
    public static <T extends Item> T registerItems(Identifier name, T item) {
        return Registry.register(Registry.ITEM, name, item);
    }

    /**
     * @param name
     *        Name of block instance to be registered
     * @param block
     *        Block instance to be registered
     * @param settings
     *        Item.Settings of BlockItem of Block instance to be registered
     * @return Block instance registered
     */
    public static <T extends Block> T registerBlocks(String name, T block, Item.Settings settings) {
        return registerBlocks(name, block, new BlockItem(block, settings));
    }

    /**
     * @param name
     *        Name of block instance to be registered
     * @param block
     *        Block instance to be registered
     * @param item
     *        BlockItem instance of Block to be registered
     * @return Block instance registered
     */
    public static <T extends Block> T registerBlocks(String name, T block, BlockItem item) {
        T b = registerBlocks(Proton.identifier(name), block);
        if (item != null) {
            registerItems(name, item);
        }
        return b;
    }

    /**
     * @param name
     *        Name of block instance to be registered
     * @param block
     *        Block instance to be registered
     * @return Block instance registered
     */
    public static <T extends Block> T registerBlocks(String name, T block) {
        return registerBlocks(Proton.identifier(name), block);
    }

    /**
     * @param name
     *        Identifier of block instance to be registered
     * @param block
     *        Block instance to be registered
     * @return Block instance registered
     */
    public static <T extends Block> T registerBlocks(Identifier name, T block) {
        return Registry.register(Registry.BLOCK, name, block);
    }

}
