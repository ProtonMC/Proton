package io.github.hydos.proton.module;

import blue.endless.jankson.JsonObject;
import io.github.hydos.proton.Proton;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Module {

    protected final Identifier id;

    @Configurable
    public static boolean enabled = true;

    public Module(Identifier id) {
        this.id = id;
        fromJson(Proton.config.getObject(getId().toString()));
    }

    public List<Field> getConfigurableFields() {
        return Arrays.stream(getClass().getFields())
                .filter(f -> f.isAnnotationPresent(Configurable.class))
                .collect(Collectors.toList());
    }

    public void fromJson(JsonObject object) {
        if (object == null) return;
        for (Field f : getConfigurableFields()) {
            try {
                f.set(this,
                        object.getMarshaller().marshall(f.getType(), object.get(f.getName())));
            } catch (Throwable ignored) {}
        }
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        for (Field f : getConfigurableFields()) {
            try {
                object.put(f.getName(), object.getMarshaller().serialize(f.get(this)));
            } catch (Throwable ignored) {}
        }
        return object;
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
