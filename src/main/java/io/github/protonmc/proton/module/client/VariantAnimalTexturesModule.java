package io.github.protonmc.proton.module.client;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.function.Supplier;

public class VariantAnimalTexturesModule extends ProtonModule {

    private static final int COW_COUNT = 4;
    private static final int PIG_COUNT = 3;
    private static final int CHICKEN_COUNT = 6;

    private static final ListMultimap<VariantTextureType, Identifier> textures = Multimaps.newListMultimap(new EnumMap<>(VariantTextureType.class), ArrayList::new);

    private static final Map<VariantTextureType, Identifier> shinyTextures = new HashMap<>();

    @Override
    public void clientInit() {
        registerTextures(VariantTextureType.COW, COW_COUNT, new Identifier("textures/entity/cow/cow.png"));
        registerTextures(VariantTextureType.PIG, PIG_COUNT, new Identifier("textures/entity/pig/pig.png"));
        registerTextures(VariantTextureType.CHICKEN, CHICKEN_COUNT, new Identifier("textures/entity/chicken.png"));
        registerShiny(VariantTextureType.RABBIT);
        registerShiny(VariantTextureType.LLAMA);
    }


    private static void registerTextures(VariantTextureType type, int count, Identifier vanilla) {
        String name = type.name().toLowerCase(Locale.ROOT);
        for (int i = 1; i < count + 1; i++)
            textures.put(type, new Identifier(Proton.MOD_ID, String.format("textures/model/entity/variants/%s%d.png", name, i)));

        if (vanilla != null)
            textures.put(type, vanilla);
        registerShiny(type);
    }

    private static void registerShiny(VariantTextureType type) {
        shinyTextures.put(type, new Identifier(Proton.MOD_ID, String.format("textures/model/entity/variants/%s_shiny.png", type.name().toLowerCase(Locale.ROOT))));
    }

    public static Identifier getTextureOrShiny(Entity e, VariantTextureType type, boolean enabled) {
        return getTextureOrShiny(e, type, () -> getRandomTexture(e, type, enabled));
    }

    public static Identifier getTextureOrShiny(Entity e, VariantTextureType type, Supplier<Identifier> nonShiny) {
        UUID id = e.getUuid();
        long most = id.getMostSignificantBits();
        if(ProtonConfig.Client.VariantAnimalTextures.shinyAnimalChance > 0 && (most % ProtonConfig.Client.VariantAnimalTextures.shinyAnimalChance) == 0)
            return shinyTextures.get(type);

        return nonShiny.get();
    }

    private static Identifier getRandomTexture(Entity e, VariantTextureType type, boolean enabled) {
        List<Identifier> styles = textures.get(type);
        if (!enabled)
            return styles.get(styles.size() - 1);

        UUID id = e.getUuid();
        long most = id.getMostSignificantBits();
        int choice = Math.abs((int) (most % styles.size()));
        return styles.get(choice);
    }

    public VariantAnimalTexturesModule() {
        super(new Identifier(Proton.MOD_ID, "variantanimaltextures"));
    }

    public enum VariantTextureType {
        COW, PIG, CHICKEN, LLAMA, RABBIT
    }
}
