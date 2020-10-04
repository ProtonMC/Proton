package io.github.hydos.proton.module;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public enum ModuleCategory {

    AUTOMATION("automation", true, Items.REDSTONE),
    BUILDING("building", true, Items.BRICKS),
    MANAGEMENT("management", true, Items.CHEST),
    TOOLS("tools", true, Items.IRON_PICKAXE),
    TWEAKS("tweaks", true, Items.NAUTILUS_SHELL),
    WORLD("world", true, Items.GRASS_BLOCK),
    MOBS("mobs", true, Items.PIG_SPAWN_EGG),
    CLIENT("client", true, Items.ENDER_EYE),
    ODDITIES("oddities", true, Items.CHORUS_FRUIT),
    EXPERIMENTAL("experimental", true, Items.TNT);

    public final String name;
    public final boolean showInGui;
    public final Item item;

    public boolean enabled;

    private final List<Module> ownedModules = new ArrayList<>();

    ModuleCategory(String name, boolean showInGui, Item item) {
        this.name = name;
        this.showInGui = showInGui;
        this.item = item;
        this.enabled = true;
    }

    public void addModule(Module module) {
        ownedModules.add(module);
    }

    public List<Module> getOwnedModules() {
        return ownedModules;
    }
}
