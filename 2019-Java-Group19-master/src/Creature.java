public abstract class Creature extends Entity {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
    DEFAULT_CREATURE_HEIGHT = 64;
    protected float speed;
    protected float xMove, yMove;
    protected float xAttacking, yAttacking;

    //This is for Enemies and Players, which are able to move around.
    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;

        xAttacking = 0;
        yAttacking = 0;
    }

    public void move(){
        //If the entity isn't currently colliding with something, allow it to move.
        if(!checkSolidEntityCollisions(xMove, 0f))
            moveX();
        if(!checkSolidEntityCollisions(0f, yMove))
            moveY();
    }

    public void moveX(){
        if(xMove > 0){//Moving right
            //Must account for the bounds box, its own x position, and its own width, as well as the tile
            //the player is moving on.
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            //Same as above, but this is for changing the y positions.
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        }else if(xMove < 0){//Moving left
            //Only major change here is that we aren't adding in the bounds width.
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }

        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }

        }
    }

    protected boolean collisionWithTile(int x, int y){
        //We must be checking to see if the Tile is Solid or not. If the tile is solid, it cannot be moved to, and will
        //instead be collided against.
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //GETTERS SETTERS

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getxAttacking(){
        return xAttacking;
    }

    public void setxAttacking(float xAttacking){
        this.xAttacking = xAttacking;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getyAttacking(){
        return yAttacking;
    }

    public void setyAttacking(float yAttacking){
        this.yAttacking = yAttacking;
    }



    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}