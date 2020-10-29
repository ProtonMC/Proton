# Contributing
So you want to contribute to Proton? No problem! This guide will tell you how!

## Recommendations
* Use IntelliJ IDEA as your IDE, because we have an `.editorconfig` file that can tell IDEA how to format the code.

## Creating a new module
So you want to create a new module? Good for you.
First, fork the Proton repository.

* *When creating modules, create them under the correct package - We have a weird system that detects modules' categories based on package names*.
* Add a translation key for your module. `proton.module.<namespace>.<path>`.
* When creating the module class, make sure to use the `proton` namespace as the constructor argument. Example:
If I want to create a black grass module, first I'll have to choose the category. As an example I've chosen `building`. (This example won't contain imports - you IDE should figure that out for you)
```java
package io.github.protonmc.proton.module.building;

// Javadoc isn't needed here
public class BlackGrassModule extends ProtonModule {
  public BlackGrassModule() {
    super(Proton.identifier("black_grass")); // Proton.identifier constructs an identifier object with the namepsace "proton"
  }
}
```
