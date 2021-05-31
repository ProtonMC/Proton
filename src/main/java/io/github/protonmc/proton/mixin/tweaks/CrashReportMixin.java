package io.github.protonmc.proton.mixin.tweaks;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.config.ProtonConfig;
import io.github.protonmc.proton.module.tweaks.CustomCrashComments;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CrashReport.class)
public abstract class CrashReportMixin {
    @ModifyVariable(method = "generateWittyComment()Ljava/lang/String;", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    @FromModule(CustomCrashComments.class)
    private static String[] addCustomCrashMessages(String[] orig) {
        if (ProtonConfig.Tweaks.CustomCrashComments.enabled) {
            if (!ProtonConfig.Tweaks.CustomCrashComments.replace) {
                String[] newStrings = new String[orig.length + ProtonConfig.Tweaks.CustomCrashComments.comment.length];
                System.arraycopy(orig, 0, newStrings, 0, orig.length);
                System.arraycopy(ProtonConfig.Tweaks.CustomCrashComments.comment, 0, newStrings, orig.length - 1, ProtonConfig.Tweaks.CustomCrashComments.comment.length);
                return newStrings;
            }
            return ProtonConfig.Tweaks.CustomCrashComments.comment;
        } else {
            return orig;
        }
    }
}
