package io.github.hydos.proton.module;

import net.minecraft.util.Identifier;

public abstract class Module {

    public final Identifier id;
    public final String name;

    public Module(String name, Identifier id){
        this.name = name;
        this.id = id;
    }

    public void clientInit() {}

    public void serverInit() {}

    public void commonInit() {}
}
