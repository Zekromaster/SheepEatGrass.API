# Sheep Eat Grass API

The API meant for my [Sheep Eat Grass](https://github.com/Zekromaster/SheepEatGrass) mod for Minecraft beta 1.7.3.

It's actually pretty much game-agnostic since it doesn't even use any Minecraft classes, and it's only meant to be
used as a shared data holder to allow for other mods to communicate with Sheep Eat Grass. 

## Installation
Jar-in-Jar it. I suggest using Jitpack to grab the mod, but I also keep a
version of it in the GitHub Package Registry for the mod's own repository,
if you're ok with needing to authenicate with GitHub to build your mod.


### Jitpack
(I use Gradle Kotlin DSL)

```kotlin
repositories {
    maven("https://jitpack.io")
}
```

To get the latest development version:
```kotlin
modImplementation(include("com.github.zekromaster:SheepEatGrass.API:trunk-SNAPSHOT"))
```


To get a specific version (i.e. 0.1.0):
```kotlin
modImplementation(include("com.github.zekromaster:SheepEatGrass.Api:0.1.0"))
```

### GitHub Package Registry
(I use Gradle Kotlin DSL)

Assuming you have a GitHub account, you can authenticate with the GitHub Package Registry with your username and a [personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens).

Assuming the username and token are stored in the `gpr.user` and `gpr.key` properties, respectively, or in the `GH_USERNAME` and `GH_TOKEN` environment variables, you can add the following to your `build.gradle.kts` file:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/Zekromaster/SheepEatGrass.API")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GH_USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GH_TOKEN")
        }
    }
}
```

To get a development version (i.e. 0.2.0 snapshots):
```kotlin
modImplementation(include("net.zekromaster.minecraft.sheepeatgrass:sheepeatgrass-api:0.2.0-SNAPSHOT"))
```

To get a specific version (i.e. 0.1.0)
```kotlin
modImplementation(include("net.zekromaster.minecraft.sheepeatgrass:sheepeatgrass-api:0.1.0"))
```

## Usage

The API is quite self-explanatory. At any point when you know the ID and
metadata of all blocks you want to use in your mod, you can just add them
to the API's SheepEatingRegistry. Get a reference to the registry with

```java
class MyModClass {
    // Other code omitted
    private void myMethod() {
        var registry = SheepEatingRegistry.getInstance();
        // Other stuff omitted
    }
}
```

The registry operates on the concept of `BlockMatchers`, which are just
predicates that, given a `BlockReference` (nothing more than a block ID and
metadata), return true if the block matches the predicate.

The API includes two BlockMatchers by default: `BlockReferenceMatcher` and
`BlockWithAnyMetaMatcher`. The former matches exactly an id and metadata
combination, while the latter matches any metadata for a given ID.

You can create your own `BlockMatcher`s by implementing the `BlockMatcher`
interface.

When adding a block to the registry, you specify a `BlockMatcher` the block
being eaten should match, the `EatingLocation` (an x, y, and z offset) the
block should be compared to the sheep to be eaten, and a `BlockReference` to
the block that should be placed in the eaten block's place.

```java
class MyModClass {
    // Other code omitted
    private void myMethod() {
        var registry = SheepEatingRegistry.getInstance();
        
        registry.addBlock(
            new BlockWithAnyMetaMatcher(BlockBase.GRASS.id),
            EatingLocation.SAME_BLOCK,
            new BlockReference(BlockBase.DIRT.id)
        );
    }
}
```

That's it! The actual mod implementing the behaviour (usually my own SheepEatGrass, but it's perfectly possible for someone to implement their own mod making use of the registry) will take care of the rest. 