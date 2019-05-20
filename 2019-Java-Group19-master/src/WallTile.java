import java.awt.image.BufferedImage;

public class WallTile extends Tile {

    public WallTile(BufferedImage texture, int id) {
        //The id passed in will be used to identify what kind of tile it is.
        super(texture, id);
    }

    @Override
    //Below means that it can be collided with.
    public boolean isSolid(){
        return true;
    }

}