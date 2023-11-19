package net.zekromaster.minecraft.sheepeatgrass.api.blocks.matchers;

import net.zekromaster.minecraft.sheepeatgrass.api.blocks.BlockReference;

/**
 * Matches a block with the specified ID and any metadata.
 *
 * @param id The block ID.
 */
public record BlockWithAnyMetaMatcher(
    int id
) implements BlockMatcher {

    public BlockWithAnyMetaMatcher(BlockReference block) {
        this(block.id());
    }

    public boolean matches(BlockReference block) {
        return block.id() == id();
    }

}
