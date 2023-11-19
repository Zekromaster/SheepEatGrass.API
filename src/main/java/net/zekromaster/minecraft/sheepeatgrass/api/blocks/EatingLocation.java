package net.zekromaster.minecraft.sheepeatgrass.api.blocks;

/**
 * Represents a location relative to a sheep.
 *
 * @param offsetX The X offset.
 * @param offsetY The Y offset.
 * @param offsetZ The Z offset.
 */
public record EatingLocation(
    int offsetX,
    int offsetY,
    int offsetZ
) {

    public static final EatingLocation SAME_BLOCK = new EatingLocation(0, 0, 0);
    public static final EatingLocation UNDERNEATH = new EatingLocation(0, -1, 0);

}
