package io.github.protonmc.proton.mixin.tweaks;

import io.github.protonmc.proton.base.annotation.FromModule;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.module.tweaks.MoreWittyMessages;
import net.minecraft.util.crash.CrashReport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static io.github.protonmc.proton.module.tweaks.MoreWittyMessages.wittyMessages;

@Mixin(CrashReport.class)
public abstract class CrashReportMixin {
    @ModifyVariable(method = "generateWittyComment()Ljava/lang/String;", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    @FromModule(MoreWittyMessages.class)
    private static String[] stuff(String[] orig) {
        if (ModuleManager.getInstance().isModuleEnabled(MoreWittyMessages.class)) {
            String[] newStrings = new String[orig.length + wittyMessages.length];
            System.arraycopy(orig, 0, newStrings, 0, orig.length);
            System.arraycopy(wittyMessages, 0, newStrings, orig.length - 1, wittyMessages.length);
            return newStrings;
        } else {
            return orig;
        }
    }
}
