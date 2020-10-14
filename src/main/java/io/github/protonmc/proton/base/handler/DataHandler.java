package io.github.protonmc.proton.base.handler;

import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import io.github.protonmc.proton.base.module.ModuleManager;
import io.github.protonmc.proton.base.module.ProtonModule;

import static io.github.protonmc.proton.Proton.identifier;

public class DataHandler {
    private static ArtificeResourcePack DATA_PACK;

    public ArtificeResourcePack.ServerResourcePackBuilder pack;

    public DataHandler(ArtificeResourcePack.ServerResourcePackBuilder pack) {
        this.pack = pack;
    }

    public static void registerAssets() {
        DATA_PACK = Artifice.registerData(identifier("data"), pack -> {
            pack.setDisplayName("Proton's Data Pack");
            DataHandler dataHandler = new DataHandler(pack);
            for (ProtonModule m : ModuleManager.getInstance().getModules())
                m.registerData(dataHandler);
        });
    }
}
