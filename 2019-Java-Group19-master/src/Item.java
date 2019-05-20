import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {

    // Arrays of items
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item rockItem = new Item(Assets.rock, "Rock", 1);
    public static Item healthdrop = new Item(Assets.heart, "Health Drop",2);

    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    //count is used in the inventory, deciding how many of that item does the player have
    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public void tick(){
        //  Below checks to see if the player has collided with the item. If they have, they will pick up the item
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            if (this.id != 2) {
                pickedUp = true;
                //After picking up the item, they will add the item to their inventory
                handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
            }
            else {
                handler.getWorld().getEntityManager().getPlayer().setHealth(20);
                pickedUp = true;

            }
        }
    }

    public void render(Graphics g){
        if(handler == null)
            return;
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int count){
        Item i = new Item(texture, name, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    //Will create a copy of the Item class above, just so that we can copy whatever item we wish to generate
    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

}