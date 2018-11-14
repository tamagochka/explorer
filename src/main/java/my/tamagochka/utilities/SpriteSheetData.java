package my.tamagochka.utilities;

public class SpriteSheetData {

    private String entityTypeName;
    private String directionName;
    private boolean mirroring;
    private int left, top, width, height;

    public SpriteSheetData() {
        this.entityTypeName = "";
        this.directionName = "";
        this.mirroring = false;
        this.left = 0;
        this.top = 0;
        this.width = 0;
        this.height = 0;
    }

    public SpriteSheetData(String entityTypeName, String directionName, boolean mirroring, int left, int top, int width, int height) {
        this.entityTypeName = entityTypeName;
        this.directionName = directionName;
        this.mirroring = mirroring;
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }


    public String getEntityTypeName() {
        return entityTypeName;
    }

    public String getDirectionName() {
        return directionName;
    }

    public boolean isMirroring() {
        return mirroring;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
