package io.github.protonmc.proton.module.decoration;

import com.google.common.collect.ImmutableSet;
import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.handler.VariantHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.proton.base.annotation.DisabledByDefault;
import io.github.protonmc.proton.module.decoration.common.block.FatPotatoBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

import static io.github.protonmc.proton.Proton.identifier;

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

    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        String base = "fat_potato";

        resourceHandler.pack.addBlockState(identifier(base), blockStateBuilder -> {
            blockStateBuilder
                    .variant("facing=south", variant -> variant.model(identifier("block/" + base)))
                    .variant("facing=west", variant -> variant.model(identifier("block/" + base)).rotationY(90))
                    .variant("facing=north", variant -> variant.model(identifier("block/" + base)).rotationY(180))
                    .variant("facing=east", variant -> variant.model(identifier("block/" + base)).rotationY(270));
        });

        resourceHandler.pack.addBlockModel(identifier(base), modelBuilder -> {
            modelBuilder.parent(new Identifier("block/orientable"))
                    .texture("top", identifier("block/fat_potato_top"))
                    .texture("front", identifier("block/fat_potato_front"))
                    .texture("side", identifier("block/fat_potato_side"));
        });

        resourceHandler.generateBlockItems(ImmutableSet.of(base));
    }
}
