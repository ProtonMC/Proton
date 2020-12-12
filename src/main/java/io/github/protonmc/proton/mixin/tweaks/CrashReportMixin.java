package io.github.protonmc.proton.mixin.tweaks;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.module.tweaks.CustomCrashComments;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static io.github.protonmc.proton.module.tweaks.CustomCrashComments.customCrashComments;

@Mixin(CrashReport.class)
public abstract class CrashReportMixin {
    @ModifyVariable(method = "generateWittyComment()Ljava/lang/String;", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    @FromModule(CustomCrashComments.class)
    private static String[] addCustomCrashMessages(String[] orig) {
        if (ModuleManager.getInstance().isModuleEnabled(CustomCrashComments.class)) {
            if (!CustomCrashComments.replace) {
                String[] newStrings = new String[orig.length + customCrashComments.length];
                System.arraycopy(orig, 0, newStrings, 0, orig.length);
                System.arraycopy(customCrashComments, 0, newStrings, orig.length - 1, customCrashComments.length);
                return newStrings;
            }
            return customCrashComments;
        } else {
            return orig;
        }
    }
}
