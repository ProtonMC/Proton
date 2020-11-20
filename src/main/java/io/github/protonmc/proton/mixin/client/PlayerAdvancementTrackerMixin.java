package io.github.protonmc.proton.mixin.client;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.module.client.AdvancementScreenshot;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ScreenshotUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementTrackerMixin {
    @Inject(method = "grantCriterion(Lnet/minecraft/advancement/Advancement;Ljava/lang/String;)Z",
            at = @At(value = "INVOKE",
                     target = "Lnet/minecraft/advancement/PlayerAdvancementTracker;updateDisplay(Lnet/minecraft/advancement/Advancement;)V",
                     shift = At.Shift.AFTER))
    @FromModule(AdvancementScreenshot.class)
    private void screenshot(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.getInstance().isModuleEnabled(AdvancementScreenshot.class)) {
            MinecraftClient client = MinecraftClient.getInstance();
            ScreenshotUtils.saveScreenshot(
                    client.runDirectory,
                    client.getWindow().getFramebufferWidth(),
                    client.getWindow().getFramebufferHeight(),
                    client.getFramebuffer(),
                    text -> {
                    }
                                          );
        }
    }
}
