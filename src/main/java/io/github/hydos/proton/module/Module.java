package io.github.hydos.proton.module;

public abstract class Module {
    public final String name;
    public final String id;

    public Module(String name, String id){
        this.name = name;
        this.id = id;
    }

    public abstract void clientInit();

    public abstract void serverInit();

    public abstract void commonInit();
}
