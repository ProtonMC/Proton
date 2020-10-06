package io.github.protonmc.proton.client.screen;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.module.ModuleCategory;
import io.github.protonmc.proton.module.ProtonModule;
import io.github.protonmc.tiny_config.ConfigManager;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.lang.reflect.Field;

public class ConfigScreenProvider {
    protected static AbstractConfigListEntry<?> createEntry(ConfigEntryBuilder builder, Field f, ProtonModule o) { // todo add saveConsumers, handle default values
        try {
            Text text = new TranslatableText(o.getTranslationKey() + "." + f.getName());
            if (f.getType() == boolean.class) {
                return builder.startBooleanToggle(text, f.getBoolean(o))
                    .build();
            } else if (f.getType().isAssignableFrom(double.class)) {
                return builder.startDoubleField(text, (double)f.get(o)).build();
            }
        } catch (Throwable t) {
            Proton.LOGGER.error(t);
        }
        return null;
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
