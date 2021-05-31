package io.github.protonmc.proton.base.module;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.protonmc.proton.Proton;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages {@link ProtonModule}s.
 *
 * @author hydos
 * @author kara-b
 * @author dzwdz
 * @author YTG1234
 * @author BoogieMonster101
 */
public class ModuleManager {
    /**
     * The one and only instance of this class.
     */
    private static final ModuleManager INSTANCE = new ModuleManager();
    /**
     * All modules stored in the manager.
     */
    private final Map<Class<? extends ProtonModule>, ProtonModule> modules = new HashMap<>();

    /**
     * Public instance of the class.
     *
     * @return The public instance of this class, for use by everyone.
     */
    public static ModuleManager getInstance() {
        return INSTANCE;
    }

    /**
     * Sets up the client-side part of all modules.
     *
     * @see ProtonModule#clientInit()
     */
    @Environment(EnvType.CLIENT)
    public void setupClientModules() {
        for (ProtonModule protonModule : modules.values()) {
            protonModule.clientInit();
        }
    }

    /**
     * Sets up the server start init of all modules.
     *
     * @param server The server the modules are running on.
     *
     * @see ProtonModule#serverInit(MinecraftServer)
     */
    public void setupServerModules(MinecraftServer server) {
        for (ProtonModule protonModule : modules.values()) {
            protonModule.serverInit(server);
        }
    }

    /**
     * Sets up module parts that are common to the server and the client.
     *
     * @see ProtonModule#commonInit()
     */
    public void setupCommonModules() {
        for (ProtonModule protonModule : modules.values()) {
            protonModule.commonInit();
        }
    }

    /**
     * Scans all class for ProtonModules are registers them.
     */
    public void scanAndRegisterModules() {
        ScanResult scanResult = new ClassGraph().enableClassInfo().scan();
        List<String> classes = scanResult.getSubclasses(ProtonModule.class.getCanonicalName()).getNames();

        for (String classname : classes) {
            try {
                Class<?> clazz = Class.forName(classname);
                ProtonModule protonModule = (ProtonModule) clazz.getDeclaredConstructor().newInstance();
                this.addModule(protonModule);
                String[] packageParts = clazz.getPackage().getName().split("\\.");
                try {
                    ModuleCategory.valueOf(packageParts[packageParts.length - 1].toUpperCase()).addModule(protonModule);
                } catch (IllegalArgumentException e) {
                    ModuleCategory.UNASSIGNED.addModule(protonModule);
                }
            } catch (Exception e) {
                Proton.LOGGER.error("Couldn't register module with name " + classname);
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds a module to the module list.
     *
     * @param protonModule The module to add.
     */
    public void addModule(ProtonModule protonModule) {
        modules.put(protonModule.getClass(), protonModule);
    }

    /**
     * Returns the module list.
     *
     * @return The module list as an Iterable.
     */
    public Iterable<ProtonModule> getModules() {
        return modules.values();
    }
}
