# Contributing
So you want to contribute to Proton? No problem! This guide will tell you how!

## Recommendations
* Use IntelliJ IDEA as your IDE, because we have an `.editorconfig` file that can tell IDEA how to format the code.
* When using IntelliJ, ***Don't format `ResourceHandler`***!
* Register models and loot tables using `ResourceHandler` and `DataHandler`.
<!-- * It's recommended to add translation keys to everything you create (Although YTG1234 will do it for you if you don't). -->
* When creating a new module, make sure to use the `proton` namespace in the constructor argument.
* Annotate all Mixin method injecitons (no need to annotate fields) with the `@FromModule` annotation to specify which module the method belongs to. For example: `@FromModule(MyModule.class)`.
    * Proton has a linter that enforces this rule, so run the Gradle `check` task before you open a PR.

### Naming Conventions
* For `static final` fields (constants) - use `SCREAMING_SNAKE_CASE`.
* For classes, use `PascalCase` (aka `UpperCamelCase`).
* Use `snake_case` in `Identifier`s because that's what Minecraft does.
* For anything else, use `camelCase`.

## Creating a new module
So you want to create a new module? Good for you.
First, fork the Proton repository.

* *When creating modules, create them under the correct package - We have a weird system that detects modules' categories based on package names*.
* Add a translation key for your module. `proton.module.<namespace>.<path>`.
* In Proton, modules are loaded automatically. You don't need to register your module or add it to any list.
* Proton uses `tiny_config`, a configuration API meant for modular projects.
    * If you want some values inside your module to be configurable, you can add the `@Configurable` annotation to them.
    * You do need to have a default value for configurable fields.
    * All `@Configurable` fields must also be static.
    * If a method requires a `float` value and you want to make it configurable, make it a `double` and cast it to `float` later.

Example:
If I want to create a black grass module, first I'll have to choose the category. As an example I've chosen `building`. (This example won't contain imports - your IDE should figure that out for you)
I also want the black grass block to damage me, and I want the damage amount to be configurable.

Keep in mind - You don't have to override every `ProtonModule` method if you don't need to.
```java
package io.github.protonmc.proton.module.building;

// Javadoc isn't needed here.
public class BlackGrassModule extends ProtonModule {
    @Configurable
    public static double blackGrassDamage = 0.0; // Example configurable field. This field will appear in the config screen, and will require a translation key.

    public BlackGrassModule() {
        super(Proton.identifier("black_grass")); // Proton.identifier constructs an identifier object with the namepsace "proton".
    }
  
    // This method is called in onInitialize.
    @Override
    public void commonInit() {
        if (!this.enabled) return; // This cancels the module's initialization if it's disabled - "enabled" is inherited from "ProtonModule".
        // This is where you'll register your items and blocks, ideally using "ProtonRegisterHandler".
    }
  
    // This is called when it's time to register the Proton data pack.
    @Override
    public void registerData(DataHandler dataHandler) {
        // Register block loot, and recipes in the future *here*. Use "dataHandler" for this.
    }
  
    // This method is called in onInitializeClient.
    @Override
    @Environment(EnvType.CLIENT)
    public void clientInit() {
        // Register rendering and client side thingies here
    }
  
    // This method is called when it's time to register the Proton resource pack.
    @Override
    public void registerResources(ResourceHandler resourceHandler) {
        // Register block models and stuff using "resourceHandler" here.
    }
}
```
* Don't override any of the other methods.
* Inside Proton, there are two common conventions for registering items and blocks.
    * Having your blocks and items as `static final` fields inside your module class.
    * Having a separate `ModuleItems` and `ModuleBlocks` inner class with the `static final` fields.

## Contributing to existing modules
* When adding new items/blocks, follow that module's convention.
    * For example, `CompressedItemsModule` has the `ModuleBlocks` and `ModuleItems` classes.
