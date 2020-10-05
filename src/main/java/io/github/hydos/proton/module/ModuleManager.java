package io.github.hydos.proton.module;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.hydos.proton.Proton;
import io.github.hydos.proton.module.tweaks.VariantAnimalTexturesModule;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class ModuleManager {
    private static final ModuleManager INSTANCE = new ModuleManager();

    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    private final List<Module> modules = new ArrayList<>();

    @Environment(EnvType.CLIENT)
    public void setupClientModules() {
        for (Module module : modules) {
            module.clientInit();
        }
    }

    public void setupServerModules(MinecraftServer server) {
        for (Module module : modules) {
            module.serverInit(server);
        }
    }

    public void setupCommonModules() {
        for (Module module : modules) {
            module.commonInit();
        }
    }

    public void scanAndRegisterModules() {
        ScanResult scanResult = new ClassGraph().enableClassInfo().scan();
        List<String> classes = scanResult.getSubclasses(Module.class.getCanonicalName()).getNames();

        for (String classname : classes) {
            try {
                Class<?> clazz = Class.forName(classname);
                Module module = (Module) clazz.getDeclaredConstructor().newInstance();
                this.addModule(module);
            } catch (Exception e) {
                Proton.LOGGER.error("Couldn't register module with name " + classname);
            }
        }
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public List<Module> getModules() {
        return modules;
    }
  
    public boolean isModuleEnabled(Class<? extends Module> moduleClass) {
        for(Module module : modules){
            if(module.getClass() == moduleClass){
                return module.enabled;
            }
        }
        return false;
    }
}
