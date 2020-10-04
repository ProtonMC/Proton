package io.github.hydos.proton.module.bloat;

import io.github.hydos.proton.Proton;
import io.github.hydos.proton.module.Module;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundModule extends Module {
    public SoundModule() {
        super(Proton.identifier("sound"));
    }

    public static final SoundEvent ENTITY_STONELING_MEEP = registerSound(new Identifier(Proton.MOD_ID, "entity.stoneling.meep"));

    private static SoundEvent registerSound(Identifier identifier) {
        SoundEvent event = new SoundEvent(identifier);
        Registry.register(Registry.SOUND_EVENT, identifier, event);
        return event;
    }
}
