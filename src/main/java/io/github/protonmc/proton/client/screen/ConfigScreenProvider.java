package io.github.protonmc.proton.client.screen;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ModuleCategory;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.tiny_config.ConfigManager;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.FieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.lang.reflect.Field;

public class ConfigScreenProvider {
    protected static AbstractConfigListEntry<?> createEntry(ConfigEntryBuilder builder, Field f, Object o) {
        try {
            FieldBuilder<?, ?> fieldBuilder = null;
            Text text = new LiteralText("todo");
            if (f.getType().isAssignableFrom(Boolean.class)) {
                fieldBuilder = builder.startBooleanToggle(text, f.getBoolean(o));
            }
            return fieldBuilder.build(); // intentional npe
        } catch (Throwable t) {
            Proton.LOGGER.error(t);
            return null;
        }
    }

    public static Screen getScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("proton.config.title"));

        builder.setSavingRunnable(() -> {
            // Serialise the config into the config file. This will be called last after all variables are updated.
        });

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        for (ModuleCategory moduleCategory : ModuleCategory.values()) {
            if (moduleCategory.showInGui) {
                ConfigCategory configCategory = builder.getOrCreateCategory(new TranslatableText(moduleCategory.getTranslationKey()));
                for (ProtonModule module : moduleCategory.getOwnedModules()) {
                    SubCategoryBuilder subCategoryBuilder = entryBuilder.startSubCategory(new TranslatableText(module.getTranslationKey()));
                    for (Field f : ConfigManager.getConfigurableFields(module.getClass())) {
                        if (f.getType().isAssignableFrom(Boolean.class)) {
                            subCategoryBuilder.add(createEntry(entryBuilder, f, module));
                        }
                    }
                    configCategory.addEntry(subCategoryBuilder.build());
                }
            }
        }

        return builder.build();
    }
}
