package io.github.protonmc.proton.module.decoration;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.handler.ProtonRegisterHandler;
import io.github.protonmc.proton.base.handler.ResourceHandler;
import io.github.protonmc.proton.base.module.ProtonModule;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;

/**
 * Adds rainbow wool. Don't ask me why
 *
 * @author YTG1234
 */
public class RainbowWoolModule extends ProtonModule {
    public static Block rainbowWoolBlock;

    public RainbowWoolModule() {
        super(Proton.identifier("rainbow_wool"));
    }

    /**
     * @see ProtonModule#commonInit()
     */
    @Override
    public void commonInit() {
        if (!enabled) return;
        rainbowWoolBlock = ProtonRegisterHandler.block("rainbow_wool", new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), new FabricItemSettings().group(ItemGroup.DECORATIONS));
    }

    /**
     * @see ProtonModule#registerResources(ResourceHandler)
     */
    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        resourceHandler.generateSimpleBlock("rainbow_wool");
    }
}
