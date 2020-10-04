package io.github.hydos.proton.module;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import io.github.hydos.proton.Proton;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    private final String filename;
    public JsonObject config = null;

    public ConfigManager(String filename) {
        this.filename = filename;
    }

    // config file handling
    public File getConfigFile() {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), filename);
    }

    public void load() {
        config = new JsonObject();
        try {
            config = Jankson.builder().build().load(getConfigFile());
        } catch (SyntaxError syntaxError) {
            // todo: don't save the config when it can't load it due to a syntax error
            Proton.LOGGER.error("Couldn't load the config.");
            Proton.LOGGER.error(syntaxError);
        } catch (Exception ignored) {}
    }

    public void save() {
        for (Module m : ModuleManager.getInstance().getModules()) {
            config.put(m.getId().toString(), toJson(m));
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(getConfigFile().toPath());
            writer.write(config.toJson(true, true));
            writer.close();
        } catch (Throwable t) {
            Proton.LOGGER.error("Couldn't save Proton's config");
            Proton.LOGGER.error(t);
        }
    }

    public void loadObject(Object obj, String id) {
        fromJson(obj, config.getObject(id));
    }

    // converting objects to JsonObjects and vice versa
    public static List<Field> getConfigurableFields(Class cl) {
        return Arrays.stream(cl.getFields())
                .filter(f -> f.isAnnotationPresent(Configurable.class))
                .collect(Collectors.toList());
    }

    public static void fromJson(Object obj, JsonObject json) {
        if (json == null) return;
        for (Field f : getConfigurableFields(obj.getClass())) {
            try {
                f.set(obj,
                        json.getMarshaller().marshall(f.getType(), json.get(f.getName())));
            } catch (Throwable ignored) {
                System.out.println(ignored);
            }
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
