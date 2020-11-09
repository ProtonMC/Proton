package io.github.protonmc.proton.module.tweaks;

import io.github.protonmc.proton.Proton;
import io.github.protonmc.proton.base.module.ProtonModule;
import io.github.protonmc.tiny_config.Configurable;

public class MoreWittyMessages extends ProtonModule {
    @Configurable
    public static String[] wittyMessages = {
            "OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!"
    };

    /**
     * Reads config, sets up fields and constructs a ProtonModule.
     */
    public MoreWittyMessages() {
        super(Proton.identifier("more_witty_messages"));
    }
}
