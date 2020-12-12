package io.github.protonmc.proton.module.tweaks;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.annotation.DisabledByDefault;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.tiny_config.Configurable;

/**
 * <p>
 * This module allows you to choose custom crash comments (the comments that start with {@code //} in crash reports)
 * </p>
 *
 * @author YTG123
 * @see io.github.protonmc.proton.mixin.tweaks.CrashReportMixin
 */
@DisabledByDefault
public class CustomCrashComments extends ProtonModule {
    @Configurable
    public static String[] customCrashComments = {
            "OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!"
    };

    @Configurable
    public static boolean replace = true;

    public CustomCrashComments() {
        super(Proton.identifier("custom_crash_comments"));
    }
}
