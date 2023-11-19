package net.zekromaster.minecraft.sheepeatgrass.api;

import net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers.BlockMatcher;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.BlockReference;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.EatingLocation;

import java.util.Optional;
import java.util.Set;

public interface SheepEatingRegistry {

    /**
     * Allows for injection of a registry. Meant to be used by mods that want to
     * re-implement SheepEatGrass instead of using the default implementation.
     */
    static void inject(SheepEatingRegistry registry) {
        RegistryInstanceHolder.INSTANCE = registry;
    }

    /**
     * The singleton instance of this registry.
     * @return The singleton instance of this registry.
     */
    static SheepEatingRegistry getInstance() {
        return RegistryInstanceHolder.INSTANCE;
    }

    /**
     * Adds a new entry to the registry.
     * Doesn't need to accept an entry, for example if the exact matcher-location pair is already
     * present.
     *
     * @param matcher Matches the block that can be eaten.
     * @param location Where the block has to be to be eaten, expressed as an offset from the entity's location.
     * @param output The block that the eaten block turns into.
     */
    void add(
        BlockMatcher matcher,
        EatingLocation location,
        BlockReference output
    );

    /**
     * Gets the block that the given block turns into when eaten at the given location.
     * If multiple entries match the given block, the one returned is implementation-defined.
     *
     * @param location Where the block has to be to be eaten, expressed as an offset from the entity's location.
     * @param block The block that can be eaten.
     * @return The block that the eaten block turns into, or {@link Optional#empty()} if the given block can't be eaten
     */
    Optional<BlockReference> get(EatingLocation location, BlockReference block);

    /**
     * Gets the set of all possible eating locations.
     *
     * @return The set of all possible eating locations.
     */
    Set<EatingLocation> possibleLocations();

}
