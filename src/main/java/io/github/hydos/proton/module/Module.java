package io.github.hydos.proton.module;

import io.github.hydos.proton.Proton;
import net.minecraft.util.Identifier;

public abstract class Module {

    protected final Identifier id;

    // note: @Configurable fields in normal modules MUST be static
    //       this is an exception, which CAN'T be static
    @Configurable
    public boolean enabled = true;

    public Module(Identifier id) {
        this.id = id;
        Proton.CONFIG.loadObject(this, id.toString());
    }

    public void clientInit() {}

    public void serverInit() {}

    public void commonInit() {}

    public final String getTranslationKey() {
        return "module." + id.getNamespace() + "." + id.getPath();
    }

    public final Identifier getId() {
        return id;
    }
}
