package net.zekromaster.minecraft.sheepeatgrass.api;

import net.zekromaster.minecraft.sheepeatgrass.api.blocks.BlockReference;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.EatingLocation;
import net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers.BlockMatcher;

import java.util.*;
import java.util.stream.Collectors;

final class SheepEatingRegistryImpl implements SheepEatingRegistry {

    private final Set<RegistryEntry> registry = new HashSet<>();


    @Override
    public void add(BlockMatcher matcher, EatingLocation location, BlockReference output) {
        registry.add(new RegistryEntry(matcher, location, output));
    }

    @Override
    public Optional<BlockReference> get(EatingLocation location, BlockReference block) {
        return registry
            .stream()
            .filter(entry -> entry.location().equals(location))
            .filter(entry -> entry.matcher().matches(block))
            .map(RegistryEntry::output)
            .findFirst();
    }

    @Override
    public Set<EatingLocation> possibleLocations() {
        return registry
            .stream()
            .map(RegistryEntry::location)
            .collect(Collectors.toSet());
    }

    private record RegistryEntry(
        BlockMatcher matcher,
        EatingLocation location,
        BlockReference output
    ) { }
}
