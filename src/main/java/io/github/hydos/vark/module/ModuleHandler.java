package io.github.hydos.vark.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleHandler {

    public static final ModuleHandler INSTANCE = new ModuleHandler();

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
