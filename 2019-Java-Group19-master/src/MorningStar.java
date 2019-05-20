import java.awt.Graphics;
import java.awt.Rectangle;

public class MorningStar extends StaticEntity {

    protected Rectangle image_size;

    public MorningStar(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
        image_size = new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public void hurt(int amt){
        //object can't be destroyed
    }

    @Override
    public void tick() {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(image_size)){
            handler.getGame().won = true;
        }
    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.morning_star, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}