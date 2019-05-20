import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private int animation_speed = 15;//the smaller the number, the faster the anmation
    private Animation animDown, animUp, animLeft, animRight;
    public Animation anim_attack_down, anim_attack_up, anim_attack_left,anim_attack_right;
    public Animation anim_shield_left, anim_shield_right;
    public boolean exiting = false;
    public boolean return_to_menu = false;
    public boolean boss_ready = false;
    public boolean dead = false;
    public int dead_count = 0;
    public boolean retry = false;
    private int shield_count = 0;

    // Attack timer, limits speed at which player can attack
    //wait 800 milliseconds to attack again
    private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;

    // Inventory
    private Inventory inventory;

    //private Game game;
    private CommandList commandList;

    public boolean paused = false;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH * 3/2, Creature.DEFAULT_CREATURE_HEIGHT * 3/2);
        setHealth(20);

        //Below decides the dimensions for the player's collision box.
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        //Animatons
        animDown = new Animation(animation_speed, Assets.player_right);
        animUp = new Animation(animation_speed, Assets.player_left);
        animLeft = new Animation(animation_speed, Assets.player_left);
        animRight = new Animation(animation_speed, Assets.player_right);
        //Animations for Asha when she's attacking
        anim_attack_up = new Animation(animation_speed, Assets.attack_left);
        anim_attack_down = new Animation(animation_speed, Assets.attack_right);
        anim_attack_left = new Animation(animation_speed, Assets.attack_left);
        anim_attack_right = new Animation(animation_speed, Assets.attack_right);
        //Shield
        anim_shield_left = new Animation(75, Assets.shield_left);
        anim_shield_right = new Animation(75, Assets.shield_right);

        inventory = new Inventory(handler);
        commandList = new CommandList(handler);
    }

    public boolean reachedRight(){
        if (this.getX() + bounds.width >= World.getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH / 2){
            return true;
        }
        return false;
    }
    public boolean reachedLeft(){
        if (this.getX() <= 1){
            return true;
        }
        return false;
    }
    public boolean reachedTop(){
        if (this.getY() <= 1){
            return true;
        }
        return false;
    }
    public boolean reachedBottom(){
        if (this.getY() + bounds.height >= World.getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT){
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

        //Attack Animations
        anim_attack_down.tick();
        anim_attack_up.tick();
        anim_attack_right.tick();
        anim_attack_left.tick();

        //Shield animations
        anim_shield_left.tick();
        anim_shield_right.tick();
        if (shield_count != 0){
            shield_count--;
        }

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttacks();
        // Inventory
        inventory.tick();
        //Command List
        commandList.tick();
        //Exit Menu
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            //System.exit(1);
            exiting = !exiting;
        }
        if (exiting && handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)) {
            System.exit(1);
        }
        if (exiting && handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
            exiting = false;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)) {
            return_to_menu = !return_to_menu;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)) {
            paused = !paused;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_PAGE_DOWN)) {
            boss_ready = !boss_ready;
        }
        if (boss_ready && ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)) || (handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)))) {
            boss_ready = false;
        }
        if (return_to_menu && handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
            return_to_menu = false;
        }
        if (return_to_menu && handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)) {
            Game.States = Game.STATE.MENU;
        }

        if (dead) {
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) {
                Game.States = Game.STATE.MENU;
                handler.getGame().restart();
            }
        }
    }


    private void checkAttacks(){
        //time elapsed between now and the previous attack
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        //checking if we are able to attack
        if(attackTimer < attackCooldown)
            return;

        if(inventory.isActive() || commandList.isExit_menu_active()||exiting||return_to_menu||paused||boss_ready)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        //ar = attack rectangle
        Rectangle ar = new Rectangle();
        //if player is within 20 pixels of an entity, they will hit them
        
        int arSize = 175;
        ar.width = Creature.DEFAULT_CREATURE_WIDTH * 2;
        ar.height = Creature.DEFAULT_CREATURE_HEIGHT * 2;

        //handler.getKeyManager() checks which direction the player wants to attack in
        //So this will check where to draw the attack rectangle
        if(handler.getKeyManager().aUp){
            //the x of the attack rectangle gets us the centre point of the collision rectangle.
            //right above the collision bound.
            ar.x = cb.x - Creature.DEFAULT_CREATURE_WIDTH / 2;
            ar.y = cb.y - Creature.DEFAULT_CREATURE_HEIGHT;
            xAttacking = 1;

        }else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            //now it will be just below the collision bound.
            ar.x = cb.x - Creature.DEFAULT_CREATURE_WIDTH / 2;
            ar.y = cb.y;
            yAttacking= 2;
        }else if(handler.getKeyManager().aLeft){
            //must change y to centre it.
            ar.x = cb.x - Creature.DEFAULT_CREATURE_WIDTH;
            ar.y = cb.y - Creature.DEFAULT_CREATURE_HEIGHT / 2;
            xAttacking =1;
        }else if(handler.getKeyManager().aRight){
            ar.x = cb.x;
            ar.y = cb.y - Creature.DEFAULT_CREATURE_HEIGHT / 2;
            xAttacking = 2;
        }else{
            //check shield if they are not attacking
            checkShield();
            //if none of the attack buttons are pressed and not attacking, don't run the rest of the code.
            return;
        }

        attackTimer = 0;

        //for every entity in the world.
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            //make sure entity isn't ourselves
            if(e.equals(this))
                continue;
            //below means we have hit that entity. We are hurting them with a value of 1.
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
    }

    private void checkShield(){
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT)) {
            shield_count = 100;
        }

    }

    @Override
    public void hurt(int amt){
        //if shield is up, don't take damage
        if (shield_count > 0){
            return;
        }
        //How much damage each of these entities takes.
        health -= amt;
        if(health <= 0){
            active = false;
            die();
        }
    }

    @Override
    public void die(){
        dead = true;
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        yAttacking = 0;
        xAttacking = 0;

        if(inventory.isActive()||commandList.isExit_menu_active()||exiting||return_to_menu||paused||boss_ready)
            return;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;

        if(handler.getKeyManager().aUp)
            yAttacking = 1;
        if(handler.getKeyManager().aDown)
            yAttacking = 2;
        if(handler.getKeyManager().aLeft)
            xAttacking = 1;
        if(handler.getKeyManager().aRight)
            xAttacking = 2;
    }

    @Override
    public int getHealth(){
        return health;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void postRender(Graphics g){
        inventory.render(g);
        commandList.render(g);
        if (exiting){
            g.setColor(Color.black);
            g.fillRect(305, 307,400, 70);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 400, 70);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Are you sure you want to exit? " , 330, 327);
            g.drawString("Y:Yes", 410, 357);
            g.drawString("N:No", 490, 357);
        }
        if (return_to_menu){
            g.setColor(Color.black);
            g.fillRect(305, 307,450, 100);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 450, 100);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Are you sure you want to return to " , 340, 327);
            g.drawString("the title screen?", 420,357);
            g.drawString("Y:Yes", 440, 387);
            g.drawString("N:No", 520, 387);
        }
        if (dead){
            Font fnt7 = new Font("helvetica",Font.BOLD, 60);
            g.setFont(fnt7);
            g.setColor(Color.white);
            g.drawString("YOU    LOSE", 300, 400);
            g.drawString("Press Q to continue", 200, 490);
            dead_count++;
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xAttacking == 1){
            return anim_attack_left.getCurrentFrame();
        } else if (xAttacking==2){
            return anim_attack_right.getCurrentFrame();
        } else if (yAttacking == 1){
            return anim_attack_up.getCurrentFrame();
        } else if (yAttacking == 2){
            return anim_attack_down.getCurrentFrame();
        } else if (shield_count > 0){
            if (xMove < 0) {
                return anim_shield_left.getCurrentFrame();
            }
            else{
                return anim_shield_right.getCurrentFrame();
            }
        }else if(xMove < 0){
            return animLeft.getCurrentFrame();
        }else if(xMove > 0){
            return animRight.getCurrentFrame();
        }else if(yMove < 0){
            return animUp.getCurrentFrame();
        } else if (yMove > 0){
            return animDown.getCurrentFrame();
        }else{
            return Assets.player_right[0];
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
    public CommandList getCommandList(){
        return commandList;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setCommandList(CommandList commandList){
        this.commandList = commandList;
    }

}