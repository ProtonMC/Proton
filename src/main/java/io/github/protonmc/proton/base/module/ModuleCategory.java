package io.github.protonmc.proton.base.module;

import io.github.protonmc.proton.Proton;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * An enum representing categories of ProtonModules.
 *
 * @author hYdos
 * @author dzwdz
 * @author TechJS
 */
public enum ModuleCategory {

    UNASSIGNED(Proton.identifier("unassigned")), // used as the default
    AUTOMATION(Proton.identifier("automation")),
    BUILDING(Proton.identifier("building")),
    MANAGEMENT(Proton.identifier("management")),
    TOOLS(Proton.identifier("tools")),
    TWEAKS(Proton.identifier("tweaks")),
    WORLD(Proton.identifier("world")),
    MOBS(Proton.identifier("mobs")),
    CLIENT(Proton.identifier("client")),
    ODDITIES(Proton.identifier("oddities")),
    EXPERIMENTAL(Proton.identifier("experimental")),
    DECORATION(Proton.identifier("decoration"));

    /**
     * Category ID, used in translation.
     */
    public final Identifier id;

    /**
     * A {@link List} of all modules owned by the category.
     */
    private final List<ProtonModule> ownedProtonModules = new ArrayList<>();
    public boolean enabled;

    /**
     * Responsible for construction of a ModuleCategory.
     *
     * @param id A unique Identifier for the category.
     */
    ModuleCategory(Identifier id) {
        this.id = id;
        this.enabled = true;
    }

    /**
     * Adds a ProtonModule to a category.
     *
     * @param protonModule The ProtonModule being added.
     */
    public void addModule(ProtonModule protonModule) {
        ownedProtonModules.add(protonModule);
    }

    /**
     * Gets the translation key of the category. Used in the proton config screen.
     *
     * @return The translation key as a String.
     */
    public String getTranslationKey() {
        return "proton.config.category." + id.getPath();
    }

    /**
     * Checks if the category is empty.
     *
     * @return Is the category empty?
     */
    public boolean isEmpty() {
        return ownedProtonModules.isEmpty();
    }

    /**
     * Gets a list of modules in the category.
     *
     * @return A list of modules in the category.
     */
    public List<ProtonModule> getOwnedModules() {
        return ownedProtonModules;
    }
}
