package io.github.protonmc.proton.module.decoration;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.base.annotation.DisabledByDefault;
import io.github.protonmc.proton.module.decoration.common.block.FatPotatoBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * @author TehcJS
 * @author mounderfod
 */
@DisabledByDefault
public class TaterModule extends ProtonModule {
    public static FatPotatoBlock fatPotatoBlock;

    public TaterModule() {
        super(Proton.identifier("tater"));
    }

    @Override
    public void commonInit() {
        if (!this.enabled) { return; }

        fatPotatoBlock = new FatPotatoBlock();
        ProtonRegisterHandler.block("fat_potato", fatPotatoBlock, new Item.Settings()
                .group(ItemGroup.DECORATIONS));
        VariantHandler.addSlabStairsWall(fatPotatoBlock);
    }
}
