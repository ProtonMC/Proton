package io.github.protonmc.proton.client.screen.button;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class IconButton extends ButtonWidget {

    private final ItemStack icon;

    public IconButton(int x, int y, int w, int h, Text text, ItemStack icon, PressAction onClick) {
        super(x, y, w, h, text, onClick);
        this.icon = icon;
    }

    @Override
    public void render(MatrixStack mstack, int mouseX, int mouseY, float pticks) {
        super.render(mstack, mouseX, mouseY, pticks);
        MinecraftClient.getInstance().getItemRenderer().renderGuiItemIcon(icon, x + 5, y + 2);
    }

}