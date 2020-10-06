package io.github.protonmc.proton.module;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.protonmc.proton.Proton;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleManager {
    private static final ModuleManager INSTANCE = new ModuleManager();

    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    private final Map<Class<? extends ProtonModule>, ProtonModule> modules = new HashMap<>();

    @Environment(EnvType.CLIENT)
    public void setupClientModules() {
        for (ProtonModule protonModule : modules.values()) {
            protonModule.clientInit();
        }
    }

    public void setupServerModules(MinecraftServer server) {
        for (ProtonModule protonModule : modules.values()) {
            protonModule.serverInit(server);
        }
    }

    public void setupCommonModules() {
        for (ProtonModule protonModule : modules.values()) {
            protonModule.commonInit();
        }
    }

    public void scanAndRegisterModules() {
        ScanResult scanResult = new ClassGraph().enableClassInfo().scan();
        List<String> classes = scanResult.getSubclasses(ProtonModule.class.getCanonicalName()).getNames();

        for (String classname : classes) {
            try {
                Class<?> clazz = Class.forName(classname);
                ProtonModule protonModule = (ProtonModule) clazz.getDeclaredConstructor().newInstance();
                this.addModule(protonModule);
            } catch (Exception e) {
                Proton.LOGGER.error("Couldn't register module with name " + classname);
            }
        }
    }

    public void addModule(ProtonModule protonModule) {
        modules.put(protonModule.getClass(), protonModule);
    }

    public Iterable<ProtonModule> getModules() {
        return modules.values();
    }
  
    public boolean isModuleEnabled(Class<? extends ProtonModule> moduleClass) {
        ProtonModule module = modules.get(moduleClass);
        if (module != null) return module.enabled;
        return false;
    }
}
