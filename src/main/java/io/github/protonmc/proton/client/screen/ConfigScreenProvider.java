package io.github.protonmc.proton.client.screen;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ModuleCategory;
import io.github.protonmc.proton.module.ModuleManager;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.tiny_config.ConfigManager;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// everything about this is horrible
public class ConfigScreenProvider {
    public static Map<Field, Object> DEFAULT_VALUES = new HashMap<>();

    private static <T> Consumer<T> saveConsumer(Field f, ProtonModule o) {
        return v -> {
            try {
                f.set(o, v);
            } catch (Throwable t) {
                Proton.LOGGER.error("Couldn't change config property");
                t.printStackTrace();
            }
        };
    }

    protected static AbstractConfigListEntry<?> createEntry(ConfigEntryBuilder builder, Field f, ProtonModule o) { // todo add saveConsumers, handle default values
        try {
            FieldBuilder<?,?> fieldBuilder = null;
            Text text = new TranslatableText(o.getTranslationKey() + "." + f.getName());
            if (f.getType() == boolean.class) {
                fieldBuilder = builder.startBooleanToggle(text, f.getBoolean(o))
                        .setDefaultValue((boolean)DEFAULT_VALUES.get(f))
                        .setSaveConsumer(saveConsumer(f, o));
            } else if (f.getType().isAssignableFrom(double.class)) {
                fieldBuilder = builder.startDoubleField(text, (double)f.get(o))
                        .setDefaultValue((double)DEFAULT_VALUES.get(f))
                        .setSaveConsumer(saveConsumer(f, o));
            }
            assert fieldBuilder != null;
            return fieldBuilder.build();
        } catch (Throwable t) {
            Proton.LOGGER.error("Couldn't create a config entry");
            t.printStackTrace();
        }
        return null;
    }

    public static Screen getScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("proton.config.title"));

        builder.setSavingRunnable(() -> {
            try {
                Proton.CONFIG.save(ModuleManager.getInstance().getModules());
            } catch (Throwable t) {
                Proton.LOGGER.error("Couldn't save the config", t);
            }
        });

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        for (ModuleCategory moduleCategory : ModuleCategory.values()) {
            if (moduleCategory.showInGui) {
                ConfigCategory configCategory = builder.getOrCreateCategory(new TranslatableText(moduleCategory.getTranslationKey()));
                for (ProtonModule module : moduleCategory.getOwnedModules()) {
                    SubCategoryBuilder subCategoryBuilder = entryBuilder.startSubCategory(new TranslatableText(module.getTranslationKey()));
                    for (Field f : ConfigManager.getConfigurableFields(module.getClass())) {
                        AbstractConfigListEntry<?> entry = createEntry(entryBuilder, f, module);
                        if (entry != null)
                            subCategoryBuilder.add(entry);
                    }
                    configCategory.addEntry(subCategoryBuilder.build());
                }
            }
        }

        return builder.build();
    }
}
