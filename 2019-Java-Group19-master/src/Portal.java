import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Portal extends StaticEntity {

    protected Rectangle image_size;
    private BufferedImage texture;
    private int to_x, to_y;

    public Portal(Handler handler, float x, float y, int to_x, int to_y, BufferedImage texture) {
        super(handler, x, y, Tile.TILEWIDTH * 3 / 2, Tile.TILEHEIGHT * 3 / 2);
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
        this.to_x = to_x;
        this.to_y = to_y;
        this.texture = texture;
        image_size = new Rectangle((int)x, (int)y, width, height);
        setOntop(false);
    }

    @Override
    public void tick() {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(image_size)){
            handler.getWorld().getEntityManager().getPlayer().setX(to_x);
            handler.getWorld().getEntityManager().getPlayer().setY(to_y);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void hurt(int amt){
        //object can't be destroyed
    }

    @Override
    public void die(){
    }
}