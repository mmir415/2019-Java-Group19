import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    //Everything in the game that isn't a Tile is an Entity. Entities will be divided into Creatures, Items and
    //Static Entities.
    //Each entity is given the below values. The floats of x and y determine where on the screen they will spawn.
    //Their width and height are how many pixels on the screen they'll take up.
    //The health is obvious for the enemies and the player, but with it, the static entities also become damageable,
    //and thus susceptible to item drops.
    //The Rectangle bounds is to be used in collisions. Each entity will have their own box, we will check when those
    //boxes intersect.
    public static final int DEFAULT_HEALTH = 3;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    //When active is false, we will implement code to remove the element from the game. hurt() does this.
    protected boolean active = true;
    protected Rectangle bounds;
    private boolean ontop = true;//will render over player. Set to false if you want to render under player.

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void die();

    public void hurt(int amt){
        //How much damage each of these entities takes.
        health -= amt;
        if(health <= 0){
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        //As previously mentioned, Entity collision will rely on checking the collision rectangles of all
        //entities in the generated world.
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            //We will pass over if the entity being checked is the same as the entity we are checking against for
            //collisions, as we don't want to worry about something colliding with itself
            if(e.equals(this))
                continue;
            //if there is no offset of x and y between the two objects, that means they are colliding, and we
            //return true to confirm that they are colliding.
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public boolean checkSolidEntityCollisions(float xOffset, float yOffset){
        //As previously mentioned, Entity collision will rely on checking the collision rectangles of all
        //entities in the generated world.
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            //We will pass over if the entity being checked is the same as the entity we are checking against for
            //collisions, as we don't want to worry about something colliding with itself
            if(e.equals(this))
                continue;
            //if the entity can be passed through
            if (e.isSolid()){
                //if there is no offset of x and y between the two objects, that means they are colliding, and we
                //return true to confirm that they are colliding.
                if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                    return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOntop() {
        return ontop;
    }

    public void setOntop(boolean ontop) {
        this.ontop = ontop;
    }

    public boolean isSolid(){
        return true;
    }

}