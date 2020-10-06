package io.github.protonmc.proton.module;

import io.github.protonmc.proton.Proton;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public enum ModuleCategory {

    AUTOMATION(Proton.identifier("automation")),
    BUILDING(Proton.identifier("building")),
    MANAGEMENT(Proton.identifier("management")),
    TOOLS(Proton.identifier("tools")),
    TWEAKS(Proton.identifier("tweaks")),
    WORLD(Proton.identifier("world")),
    MOBS(Proton.identifier("mobs")),
    CLIENT(Proton.identifier("client")),
    ODDITIES(Proton.identifier("oddities")),
    EXPERIMENTAL(Proton.identifier("experimental"));

    public final Identifier id;
    public final boolean showInGui;

    public boolean enabled;

    private final List<ProtonModule> ownedProtonModules = new ArrayList<>();

    ModuleCategory(Identifier id) {
        this.id = id;
        this.showInGui = true;
        this.enabled = true;
    }

    public void addModule(ProtonModule protonModule) {
        ownedProtonModules.add(protonModule);
    }

    public String getTranslationKey() {
        return "proton.config.category." + id.getPath();
    }

    public boolean isEmpty() {
        return ownedProtonModules.isEmpty();
    }

    public List<ProtonModule> getOwnedModules() {
        return ownedProtonModules;
    }
}
