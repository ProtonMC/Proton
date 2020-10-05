package io.github.hydos.proton.config;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    private final Path path;
    public JsonObject config = new JsonObject();

    public ConfigManager(Path path) {
        this.path = path;
    }


    // config file handling
    public void load() throws IOException, SyntaxError {
        config = Jankson.builder().build().load(Files.newInputStream(path));
    }

    public void save(Iterable<? extends Saveable> objects) throws IOException {
        for (Saveable o : objects) {
            config.put(o.getSerializedId(), toJson(o));
        }
        BufferedWriter writer = Files.newBufferedWriter(path);
        writer.write(config.toJson(true, true));
        writer.close();
    }

    public void loadObject(Saveable obj) {
        loadObject(obj, obj.getSerializedId());
    }

    public void loadObject(Object obj, String id) {
        fromJson(obj, config.getObject(id));
    }


    // converting objects to JsonObjects and vice versa
    public static List<Field> getConfigurableFields(Class<?> cl) {
        return Arrays.stream(cl.getFields())
                .filter(f -> !Modifier.isTransient(f.getModifiers()))
                .filter(f -> f.isAnnotationPresent(Configurable.class))
                .collect(Collectors.toList());
    }

    public static void fromJson(Object obj, JsonObject json) {
        if (json == null) return;
        for (Field f : getConfigurableFields(obj.getClass())) {
            try {
                f.set(obj,
                        json.getMarshaller().marshall(f.getType(), json.get(f.getName())));
            } catch (Throwable ignored) {}
        }
    }

    public static JsonObject toJson(Object obj) {
        JsonObject json = new JsonObject();
        for (Field f : getConfigurableFields(obj.getClass())) {
            try {
                json.put(f.getName(), json.getMarshaller().serialize(f.get(obj)));
            } catch (Throwable ignored) {}
        }
        return json;
    }
}
