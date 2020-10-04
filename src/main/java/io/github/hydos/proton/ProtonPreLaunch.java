package io.github.hydos.proton;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import io.github.hydos.proton.module.ModuleManager;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.Level;

public class ProtonPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        Proton.LOGGER.log(Level.INFO, "Loading the config...");
        Proton.config = new JsonObject();
        try {
            Proton.config = Jankson.builder().build().load(Proton.getConfigFile());
        } catch (SyntaxError syntaxError) {
            // todo: don't save the config when it can't load it due to a syntax error
            Proton.LOGGER.error("Couldn't load the config.");
            Proton.LOGGER.error(syntaxError);
        } catch (Exception ignored) {}

        Proton.LOGGER.info("Scanning for modules");
        ModuleManager.getInstance().scanAndRegisterModules();
    }

}
