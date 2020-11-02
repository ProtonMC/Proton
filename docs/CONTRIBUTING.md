# Contributing
So you want to contribute to Proton? No problem! This guide will tell you how!

## Recommendations
* Use IntelliJ IDEA as your IDE, because we have an `.editorconfig` file that can tell IDEA how to format the code.
* When using IntelliJ, ***Don't format `ResourceHandler`***!
* Register models and loot tables using `ResourceHandler` and `DataHandler`.
* It's recommended to add translation keys to everything you create (Although YTG1234 will do it for you if you don't).
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

    public static final Block BLACK_GRASS = new Block(/* ... */);

    public BlackGrassModule() {
        super(Proton.identifier("black_grass")); // Proton.identifier constructs an identifier object with the namepsace "proton".
    }
  
    // This method is called in onInitialize.
    @Override
    public void commonInit() {
        if (!this.enabled) return; // This cancels the module's initialization if it's disabled - "enabled" is inherited from "ProtonModule".
        // This is where you'll register your items and blocks, using "ProtonRegisterHandler".
        /*
        * ProtonRegisterHandler.block(...);
        */
    }
  
    // This is called when it's time to register the Proton data pack.
    @Override
    public void registerData(DataHandler dataHandler) {
        // Register block loot and recipes here. Use "dataHandler" for this.
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

## Using Mixins in Proton
* Mixins in Proton are inside `io.github.protonmc.proton.mixin`.
* If your Mixin(s) is global across Proton and doesn't belong to any `ProtonModule` place it in that package.
* If your Mixin(s) does belong to a specific module, place your Mixin classes in a sub-package, which has the same name as the module's category.
    * If there are multiple Mixins that belong to one module you can place them in a sub-package with the name of the module.
* If there is already a Mixin class which targets the class you want to target, move it to the appropriate package (category if the two modules share the same category, or just in the mixin package).
* Create your Mixin and add whatever you want to it.
* Add your Mixin(s) to the Mixin configuration file `proton.mixins.json`.
* Run the Gradle `check` task to check if you forgot any `@FromModule` annotations.
    * If you forgot some, add them in. If two modules need an injection in the same place - make two separate injection methods and have a different `@FromModule` on each.
* If your Mixin isn't specific to one module, pass `ProtonModule.class` to the `@FromModule` annotation.
    * Another solution will be worked on later.
    
*Here is an example Mixin which targets `PlayerEntity` and injects into `tick`*:
```java
package io.github.protonmc.proton.mixin.building;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Inject(method = "tick()V", at = @At("RETURN"))
    @FromModule(MyExampleModule.class)
    private void aDescriptiveName(CallbackInfo info) {
        // do stuff
    }
}
```
