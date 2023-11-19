package net.zekromaster.minecraft.sheepeatgrass.api.blocks;

/**
 * Refers to a block by its ID and metadata.
 *
 * @param id The block ID.
 * @param meta The block metadata.
 */
public record BlockReference(int id, int meta) {

    public BlockReference(int id) {
        this(id, 0);
    }

}
