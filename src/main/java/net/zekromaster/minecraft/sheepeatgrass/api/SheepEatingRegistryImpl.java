package net.zekromaster.minecraft.sheepeatgrass.api;

import net.zekromaster.minecraft.sheepeatgrass.api.blocks.BlockReference;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.EatingLocation;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers.BlockMatcher;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers.BlockReferenceMatcher;

import java.util.*;

final class SheepEatingRegistryImpl implements SheepEatingRegistry {

    private final Set<RegistryEntry> registry = new HashSet<>();

    private final Map<CacheKey, BlockReference> cache = new HashMap<>();


    @Override
    public void add(BlockMatcher matcher, EatingLocation location, BlockReference output) {
        registry.add(new RegistryEntry(matcher, location, output));

        // Specifically in the case of BlockReferenceMatchers, we can cache the result
        // right away
        if (matcher instanceof BlockReferenceMatcher brm) {
            cache.put(new CacheKey(brm.block(), location), output);
        }
    }

    @Override
    public Optional<BlockReference> get(EatingLocation location, BlockReference block) {
        return Optional.ofNullable(
            this.cache.computeIfAbsent(
                new CacheKey(block, location),
                key -> registry
                    .stream()
                    .filter(entry -> entry.location().equals(key.location()))
                    .filter(entry -> entry.matcher().matches(key.block()))
                    .map(RegistryEntry::output)
                    .findFirst()
                    .orElse(null)
                )
        );
    }

    @Override
    public SortedSet<EatingLocation> possibleLocations() {
        return new TreeSet<>(Arrays.asList(EatingLocation.values()));
    }

    private record RegistryEntry(
        BlockMatcher matcher,
        EatingLocation location,
        BlockReference output
    ) { }

    private record CacheKey(
        BlockReference block,
        EatingLocation location
    ) { }

}
