package io.github.protonmc.proton.module;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.protonmc.proton.Proton;

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

	private final List<ProtonModule> protonModules = new ArrayList<>();

	@Environment(EnvType.CLIENT)
	public void setupClientModules() {
		for (ProtonModule protonModule : protonModules) {
			protonModule.clientInit();
		}
	}

	public void setupServerModules(MinecraftServer server) {
		for (ProtonModule protonModule : protonModules) {
			protonModule.serverInit(server);
		}
	}

	public void setupCommonModules() {
		for (ProtonModule protonModule : protonModules) {
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
		protonModules.add(protonModule);
	}

	public List<ProtonModule> getModules() {
		return protonModules;
	}
  
	public boolean isModuleEnabled(Class<? extends ProtonModule> moduleClass) {
		for(ProtonModule protonModule : protonModules){
			if(protonModule.getClass() == moduleClass){
				return protonModule.enabled;
			}
		}
		return false;
	}
}
