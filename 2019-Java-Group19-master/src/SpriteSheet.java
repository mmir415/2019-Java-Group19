import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;
    private int tile_width;
    private int tile_height;

    //Because a SpriteSheet is one image that contains several smaller images, we load the image
    public SpriteSheet(BufferedImage sheet, int tile_width, int tile_height){
        this.sheet = sheet;
        this.tile_width = tile_width;
        this.tile_height = tile_height;
    }

    //And then take out the specific smaller image we want at the time.
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x * tile_width, y *tile_height, tile_width * width, tile_height * height);
    }

}