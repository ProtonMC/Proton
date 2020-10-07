package io.github.protonmc.proton.mixin.client;

import com.google.common.collect.ImmutableSet;
import io.github.protonmc.proton.base.client.screen.button.PButton;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init()V", at = @At("TAIL"))
    public void addPButton(CallbackInfo ci) {
        ImmutableSet<String> targets = ImmutableSet.of(I18n.translate("menu.online"));

        for (AbstractButtonWidget button : buttons) {
            if (targets.contains(button.getMessage().getString())) {
                ButtonWidget p = new PButton(button.x - 24, button.y + 12);
                this.addButton(p);
                return;
            }
        }
    }
}
