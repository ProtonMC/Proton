package io.github.hydos.vark.module;

public abstract class Module {

    public final String id;
    public final String name;

    public Module(String name, String id){
        this.name = name;
        this.id = id;
    }

    public void clientInit() {
    }

    public void serverInit() {

    }

    public void commonInit() {

    }
}
