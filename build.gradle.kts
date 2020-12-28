plugins {
    id("fabric-loom") version "0.5-SNAPSHOT"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("checkstyle")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// region Project Properties
val mod_version: String by project
val maven_group: String by project
val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project
val fabric_version: String by project
val modmenu_version: String by project
val classgraph_version: String by project
val jankson_version: String by project
val clothconfig_version: String by project
val artifice_version: String by project
val proton_checkstyle_checks_version: String by project
val checkstyle_version: String by project
// endregion

version = mod_version
group = maven_group

repositories {
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-release")
    maven(url = "https://maven.fabricmc.net/")
    maven(url = "https://jitpack.io") {
        name = "Jitpack"
        content {
            includeGroup("com.github.ProtonMC")
        }
    }
    jcenter()
}

dependencies {
    minecraft("com.mojang", "minecraft", minecraft_version)
    mappings("net.fabricmc", "yarn", yarn_mappings)
    modImplementation("net.fabricmc", "fabric-loader", loader_version)

    modImplementation("net.fabricmc.fabric-api", "fabric-api", fabric_version) {
        exclude(module = "fabric-biomes-v1")
    }

    // Mod menu
    modApi("io.github.prospector", "modmenu", modmenu_version) {
        exclude(group = "net.fabricmc.fabric-api")
    }

    // Modules
    implementation("io.github.classgraph", "classgraph", classgraph_version)

    // Json5
    modApi("io.github.cottonmc", "Jankson-Fabric", jankson_version) {
        exclude(group = "net.fabricmc.fabric-api")
    }
    include("io.github.cottonmc", "Jankson-Fabric", jankson_version)

    // Config
    implementation("com.github.ProtonMC", "tiny_config", "master-SNAPSHOT")
    include("com.github.ProtonMC", "tiny_config", "master-SNAPSHOT")

    // Config screen
    modApi("me.shedaniel.cloth", "config-2", clothconfig_version) {
        exclude(group = "net.fabricmc.fabric-api")
    }
    include("me.shedaniel.cloth", "config-2", clothconfig_version)

    // Always has been Json
    modImplementation("com.lettuce.fudge", "artifice", artifice_version)
    include("com.lettuce.fudge", "artifice", artifice_version)

    checkstyle("com.github.ProtonMC", "proton-checkstyle-checks", proton_checkstyle_checks_version)
}

tasks {
    shadowJar {
        relocate("io.github.classgraph", "proton.shaded.io.github.classgraph")
        relocate("nonapi.io.github.classgraph", "proton.shaded.nonapi.io.github.classgraph")
    }

    processResources {
        inputs.property("version", mod_version)
        from(sourceSets["main"].resources.srcDirs) {
            include("fabric.mod.json")
            expand("version" to mod_version)
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType(JavaCompile::class).configureEach {
        if (JavaVersion.current().isJava9Compatible) {
            options.compilerArgs.addAll(listOf("--release", "8"))
        } else {
            sourceCompatibility = "8"
            targetCompatibility = "8"
        }
    }

    register<Jar>("sourcesJar") {
        dependsOn(classes)
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
    
    jar {
        from("LICENSE")
    }
}

tasks.remapJar.get().dependsOn(tasks.shadowJar)
(tasks.remapJar.get().input as FileSystemLocationProperty<in FileSystemLocation>).set(tasks.shadowJar.get().archivePath)

checkstyle {
    toolVersion = checkstyle_version
    config = rootProject.resources.text.fromFile("config/checkstyle/checkstyle.xml")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(tasks.jar) {
                builtBy(tasks.remapJar)
            }
            artifact("${project.buildDir.absolutePath}/libs/${project.name}-$mod_version.jar") {
                builtBy(tasks.remapJar)
            }
            artifact(tasks.getByName("sourcesJar")) {
                builtBy(tasks.remapSourcesJar)
            }
        }
    }

    repositories {
        // uncomment to publish to the local maven
        // mavenLocal()
    }
}
