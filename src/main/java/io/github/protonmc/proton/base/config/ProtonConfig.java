package io.github.protonmc.proton.base.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "proton")
public class ProtonConfig implements ConfigData {

    @ConfigEntry.Category("automation")
    @ConfigEntry.Gui.TransitiveObject
    Automation automation = new Automation();

    @ConfigEntry.Category("building")
    @ConfigEntry.Gui.TransitiveObject
    Building building = new Building();

    @ConfigEntry.Category("management")
    @ConfigEntry.Gui.TransitiveObject
    Management management = new Management();

    @ConfigEntry.Category("tools")
    @ConfigEntry.Gui.TransitiveObject
    Tools tools = new Tools();

    @ConfigEntry.Category("tweaks")
    @ConfigEntry.Gui.TransitiveObject
    Tweaks tweaks = new Tweaks();

    @ConfigEntry.Category("world")
    @ConfigEntry.Gui.TransitiveObject
    World world = new World();

    @ConfigEntry.Category("mobs")
    @ConfigEntry.Gui.TransitiveObject
    Mobs mobs = new Mobs();

    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject
    Client client = new Client();

    @ConfigEntry.Category("oddities")
    @ConfigEntry.Gui.TransitiveObject
    Oddities oddities = new Oddities();

    @ConfigEntry.Category("experimental")
    @ConfigEntry.Gui.TransitiveObject
    Experimental experimental = new Experimental();

    @ConfigEntry.Category("decoration")
    @ConfigEntry.Gui.TransitiveObject
    Decoration decoration = new Decoration();

    public static class Automation {

    }

    public static class Building {
        public static boolean bambooMat = true;

        public static boolean cobblestoneBricks = true;

        @ConfigEntry.Gui.CollapsibleObject
        CompressedItems compressedItems = new CompressedItems();

        public static class CompressedItems {
            public static boolean enabled = true;
            public static double bluerIceSlipperiness = 0.9998;
            public static double bluestIceSlipperiness = 1.1;
            public static int bluestIceLuminance = 3;
        }

        public static boolean magmaBrick = true;

        public static boolean stainedPlanks = true;

        @ConfigEntry.Gui.CollapsibleObject
        Thatch thatch = new Thatch();

        public static class Thatch {

            public static boolean enabled = true;
            public static double fallDamageMultiplier = 0.5;
        }

        public static boolean turf = true;
    }

    public static class Client {

        public static boolean angryCreepers = true;

        @ConfigEntry.Gui.CollapsibleObject
        VariantAnimalTextures variantAnimalTextures = new VariantAnimalTextures();

        public static class VariantAnimalTextures {
            public static boolean enabled = true;
            public static boolean enableCow = true;
            public static boolean enablePig = true;
            public static boolean enableChicken = true;
            public static boolean enableShinyRabbit = true;
            public static boolean enableShinyLlama = true;
            public static double prideBeeChance = .1;
            public static int shinyAnimalChance = 2048;
        }
    }

    public static class Decoration {

        public static boolean tater = false;
    }

    public static class Experimental {

        public static boolean testModule = true;
    }

    public static class Management {

    }

    public static class Mobs {

    }

    public static class Oddities {

    }

    public static class Tools {

    }

    public static class Tweaks {

        @ConfigEntry.Gui.CollapsibleObject
        CampfiresBoostElytra campfiresBoostElytra = new CampfiresBoostElytra();

        public static class CampfiresBoostElytra {
            public static boolean enabled = true;
            public static double boostStrength = 0.5;
            public static double maxSpeed = 1;
        }

        @ConfigEntry.Gui.CollapsibleObject
        CustomCrashComments customCrashComments = new CustomCrashComments();

        public static class CustomCrashComments {
            public static boolean enabled = false;
            @ConfigEntry.Gui.Excluded
            public static String[] comment = {
                    "OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!"
            };
            public static boolean replace = true;
        }

        public static boolean featherFallingFarmland = true;
        public static boolean tiltToDamage = true;
    }

    public static class World {

        @ConfigEntry.Gui.CollapsibleObject
        ClayInOverworld clayInOverworld = new ClayInOverworld();

        public static class ClayInOverworld {
            public static boolean enabled = true;
            public static int vein_size = 15;
            public static int veins_per_chunk = 10;
            public static int max_y_level = 256;
        }

    }

}

