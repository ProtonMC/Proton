package io.github.hydos.proton.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();

    private final List<Module> modules = new ArrayList<>();

    public void setupClientModules() {
        for (Module module : modules) {
            module.clientInit();
        }
    }

    public void setupServerModules() {
        for (Module module : modules) {
            module.serverInit();
        }
    }

    public void setupCommonModules() {
        for (Module module : modules) {
            module.commonInit();
        }
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }

}
