package io.github.hydos.proton.module;

import io.github.hydos.proton.Proton;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public enum ModuleCategory {

    AUTOMATION(Proton.identifier("automation"), true, Items.REDSTONE),
    BUILDING(Proton.identifier("building"), true, Items.BRICKS),
    MANAGEMENT(Proton.identifier("management"), true, Items.CHEST),
    TOOLS(Proton.identifier("tools"), true, Items.IRON_PICKAXE),
    TWEAKS(Proton.identifier("tweaks"), true, Items.NAUTILUS_SHELL),
    WORLD(Proton.identifier("world"), true, Items.GRASS_BLOCK),
    MOBS(Proton.identifier("mobs"), true, Items.PIG_SPAWN_EGG),
    CLIENT(Proton.identifier("client"), true, Items.ENDER_EYE),
    ODDITIES(Proton.identifier("oddities"), true, Items.CHORUS_FRUIT),
    EXPERIMENTAL(Proton.identifier("experimental"), true, Items.TNT);

    public final Identifier id;
    public final boolean showInGui;
    public final Item item;

    public boolean enabled;

    private final List<ProtonModule> ownedProtonModules = new ArrayList<>();

    ModuleCategory(Identifier id, boolean showInGui, Item item) {
        this.id = id;
        this.showInGui = showInGui;
        this.item = item;
        this.enabled = true;
    }

    public void addModule(ProtonModule protonModule) {
        ownedProtonModules.add(protonModule);
    }

    public String getTranslationKey() {
        return "modulecategory." + id.getNamespace() + "." + id.getPath();
    }

    public List<ProtonModule> getOwnedModules() {
        return ownedProtonModules;
    }
}
