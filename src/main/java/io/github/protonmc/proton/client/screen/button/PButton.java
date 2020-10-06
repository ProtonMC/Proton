package io.github.protonmc.proton.client.screen.button;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.protonmc.proton.client.screen.ConfigScreenProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.Calendar;

public class PButton extends ButtonWidget {
    private final boolean gay;
    private float tick = 0;

    public PButton(int x, int y) {
        super(x, y, 20, 20, new LiteralText("p"), PButton::click);
        gay = Calendar.getInstance().get(Calendar.MONTH) == Calendar.JUNE;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(WIDGETS_LOCATION);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.drawTexture(matrices, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.drawTexture(matrices, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(matrices, client, mouseX, mouseY);
        int color = gay ? Color.HSBtoRGB((tick+=delta / 200F), 1F, 1F) : 0x48DDBC;
        drawCenteredText(matrices, client.textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, color | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }

    public static void click(ButtonWidget _buttonWidget) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.openScreen(ConfigScreenProvider.getScreen(client.currentScreen));
    }
}