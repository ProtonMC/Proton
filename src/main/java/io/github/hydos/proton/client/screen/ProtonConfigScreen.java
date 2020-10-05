package io.github.hydos.proton.client.screen;

import io.github.hydos.proton.client.screen.button.IconButton;
import io.github.hydos.proton.module.ModuleCategory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class ProtonConfigScreen extends Screen {

    public TextRenderer clientTextRenderer;

    public ProtonConfigScreen() {
        super(new LiteralText("Quark Configuration"));
    }

    @Override
    protected void init() {
        super.init();
        clientTextRenderer = client.textRenderer;

        int pad = 10;
        int vpad = 23;
        int bWidth = 150;
        int left = width / 2 - (bWidth + pad);
        int vStart = 60;

        int i = 0;
        for (ModuleCategory category : ModuleCategory.values()) {
            if (category.showInGui) {
                int x = left + (bWidth + pad) * (i % 2);
                int y = vStart + (i / 2) * vpad;

                addButton(new IconButton(x, y, bWidth - 20, 20, new LiteralText(category.name), new ItemStack(category.item), button -> {
                    System.out.println("I need to implement the clicking action for " + category.name);
                }));
//                addButton(new CheckboxButton(x + bWidth - 20, y, IngameConfigHandler.INSTANCE.getCategoryEnabledObject(category)));
                i++;
            }
        }

        pad = 3;
        vpad = 23;
        bWidth = 121;
        left = (width - (bWidth + pad) * 3) / 2;
        vStart = height - 30;

        //TODO: port rest of gui form quark
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        drawCenteredString(matrices, clientTextRenderer, Formatting.BOLD + "Proton Configuration", width / 2, 15, 0x48ddbc);
        drawCenteredString(matrices, clientTextRenderer, String.format("Proton is possible thanks to the Quark devs and the Proton Team", Formatting.GOLD, Formatting.RESET), width / 2, 28, 0xf96854);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
