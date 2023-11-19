package net.zekromaster.minecraft.sheepeatgrass.api.blocks;

public enum EatingLocation {
    UNDERNEATH(0, -1, 0),
    SAME_BLOCK(0, 0, 0);

    public final int offsetX;
    public final int offsetY;
    public final int offsetZ;

    EatingLocation(int offsetX, int offsetY, int offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

}
