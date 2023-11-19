package net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers;

import net.zekromaster.minecraft.sheepeatgrass.api.blocks.BlockReference;

/**
 * Matches the exact block from the BlockReference.
 *
 * @param block The block to match.
 */
public record BlockReferenceMatcher(
    BlockReference block
) implements BlockMatcher {

    public BlockReferenceMatcher(int id, int meta) {
        this(new BlockReference(id, meta));
    }

    public boolean matches(BlockReference block) {
        return this.block().equals(block);
    }

}
