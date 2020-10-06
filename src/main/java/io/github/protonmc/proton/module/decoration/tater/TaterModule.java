package io.github.protonmc.proton.module.decoration.tater;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.module.ProtonModule;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

// fixme: broken
/**
 * @author TehcJS
 * @author mounderfod
 */
public class TaterModule extends ProtonModule {
    public static FatPotatoBlock fatPotatoBlock;

    public TaterModule() {
        super(Proton.identifier("tater"));
        this.enabled = false;
    }

    @Override
    public void commonInit() {
        if (!this.enabled) { return; }

        fatPotatoBlock = new FatPotatoBlock();
        ProtonRegisterHandler.block("fat_potato", fatPotatoBlock, new Item.Settings()
                .group(ItemGroup.DECORATIONS));
    }
}
