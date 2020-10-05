package io.github.protonmc.proton.module;

import io.github.protonmc.proton.Proton;

import io.github.protonmc.tiny_config.Configurable;
import io.github.protonmc.tiny_config.Saveable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public abstract class ProtonModule implements Saveable {
	protected final Identifier id;

	// note: @Configurable fields in normal modules MUST be static
	//	   this is an exception, which CAN'T be static
	@Configurable
	public boolean enabled = true;

	public ProtonModule(Identifier id) {
		this.id = id;
		Proton.CONFIG.loadObject(this);
	}

	@Environment(EnvType.CLIENT)
	public void clientInit() {};

	public void serverInit(MinecraftServer server) {};

	public void commonInit() {}

	public final String getTranslationKey() {
		return "module." + id.getNamespace() + "." + id.getPath();
	}

	public final Identifier getId() {
		return id;
	}

	@Override
	public String getSerializedId() {
		return id.toString();
	}
}
