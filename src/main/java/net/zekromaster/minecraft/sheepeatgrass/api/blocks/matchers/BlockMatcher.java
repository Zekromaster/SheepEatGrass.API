package net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers;

import net.zekromaster.minecraft.sheepeatgrass.api.blocks.BlockReference;

@FunctionalInterface
public interface BlockMatcher {

    /**
     * Checks if this block matches the given block.
     * @param block The block to check against.
     * @return Whether this block matches the given block.
     */
    boolean matches(BlockReference block);

}
