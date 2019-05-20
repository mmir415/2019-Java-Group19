import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import static java.lang.Math.round;

public class Assets {
    //This class is where we create our assets(static entities, creature entities, pop ups etc), load them with their
    //textures (from a spritesheet or an image). For the movable characters, like the player and the enemies, we need
    //several such images that will be in an array together.

    private static final int STD_TILE_SIZE = 32;
    public static Font font28;

    //Here, we declare all of our assets that need images.

    public static BufferedImage rock, wood, building, morning_star, heart;
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_outfits;
    public static BufferedImage[] attack_down, attack_up, attack_left, attack_right;
    public static BufferedImage[] shield_right, shield_left;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] dalek_down,dalek_up,dalek_left,dalek_right;
    public static BufferedImage[] boss_down, boss_up, boss_left, boss_right;
    public static BufferedImage[] grim_down, grim_up, grim_left, grim_right;
    public static BufferedImage[] btn_start, button, tree, door, portal;
    public static BufferedImage inventoryScreen;

    //The following are hashmaps of each of the possible terrains that will be used
    public static final HashMap<String, BufferedImage> BOSS_TERRAIN = new HashMap<>();
    public static final HashMap<String, BufferedImage> CASTLE_TERRAIN = new HashMap<>();
    public static final HashMap<String, BufferedImage> ASHLANDS_TERRAIN = new HashMap<>();
    public static final HashMap<String, BufferedImage> HOMETOWN_TERRAIN = new HashMap<>();

    public static void init_hashmaps(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("../res/boss.png"), 32, 32);
        BOSS_TERRAIN.put("MAT_TOP_LEFT_CORNER", sheet.crop(3, 2, 1, 1));
        BOSS_TERRAIN.put("MAT_TOP_EDGE", sheet.crop(4, 2, 1, 1));
        BOSS_TERRAIN.put("MAT_TOP_RIGHT_CORNER", sheet.crop(5, 2, 1, 1));
        BOSS_TERRAIN.put("MAT_LEFT_EDGE", sheet.crop(3, 3, 1, 1));
        BOSS_TERRAIN.put("MAT", sheet.crop(4, 3, 1, 1));
        BOSS_TERRAIN.put("MAT_RIGHT_EDGE", sheet.crop(5, 3, 1, 1));
        BOSS_TERRAIN.put("MAT_BOTTOM_LEFT_CORNER", sheet.crop(3, 4, 1, 1));
        BOSS_TERRAIN.put("MAT_BOTTOM_EDGE", sheet.crop(4, 4, 1, 1));
        BOSS_TERRAIN.put("MAT_BOTTOM_RIGHT_CORNER", sheet.crop(5, 4, 1, 1));
        BOSS_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(0, 0, 1, 1));
        BOSS_TERRAIN.put("TOP_EDGE", sheet.crop(1, 0, 1, 1));
        BOSS_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(2, 0, 1, 1));
        BOSS_TERRAIN.put("LEFT_EDGE", sheet.crop(0, 1, 1, 1));
        BOSS_TERRAIN.put("FLOOR", sheet.crop(1, 1, 1, 1));
        BOSS_TERRAIN.put("RIGHT_EDGE", sheet.crop(2, 1, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(0, 2, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_EDGE", sheet.crop(1, 2, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(2, 2, 1, 1));
        BOSS_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(4, 1, 1, 1));
        BOSS_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(3, 1, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(4, 0, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(3, 0, 1, 1));
        BOSS_TERRAIN.put("BLACK", sheet.crop(6, 3, 1, 1));
        
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/castle.png"), 16, 16);
        CASTLE_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(10, 1, 1, 1));
        CASTLE_TERRAIN.put("TOP_EDGE", sheet.crop(11, 1, 1, 1));
        CASTLE_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(12, 1, 1, 1));
        CASTLE_TERRAIN.put("LEFT_EDGE", sheet.crop(10, 2, 1, 1));
        CASTLE_TERRAIN.put("FLOOR", sheet.crop(11, 2, 1, 1)); 
        CASTLE_TERRAIN.put("RIGHT_EDGE", sheet.crop(12, 2, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(10, 3, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_EDGE", sheet.crop(11, 3, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(12, 3, 1, 1));
        CASTLE_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(15, 2, 1, 1));
        CASTLE_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(14, 2, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(15, 1, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(14, 1, 1, 1));
        CASTLE_TERRAIN.put("WALL", sheet.crop(10, 4, 1, 1));
        CASTLE_TERRAIN.put("GARGOYLE", sheet.crop(13, 17, 3, 3));

        sheet = new SpriteSheet(ImageLoader.loadImage("../res/ashlands.png"), 16, 16);
        ASHLANDS_TERRAIN.put("ASH", sheet.crop(2, 1, 1, 1));
        ASHLANDS_TERRAIN.put("DIRT", sheet.crop(1, 3, 1, 1));
        ASHLANDS_TERRAIN.put("LEFT_EDGE", sheet.crop(20, 2, 1, 1));
        ASHLANDS_TERRAIN.put("RIGHT_EDGE", sheet.crop(22, 2, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_EDGE", sheet.crop(21, 1, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_EDGE", sheet.crop(21, 3, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(20, 1, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(22, 1, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(20, 3, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(22, 3, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(23, 1, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(24, 1, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(23, 2, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(24, 2, 1, 1));
        ASHLANDS_TERRAIN.put("TREE", sheet.crop(35, 2, 3, 5));
        ASHLANDS_TERRAIN.put("TREE2", sheet.crop(33, 3, 2, 4));

        sheet = new SpriteSheet(ImageLoader.loadImage("../res/hometown.png"), 16, 16);
        HOMETOWN_TERRAIN.put("GRASS", sheet.crop(1, 1, 1, 1));
        HOMETOWN_TERRAIN.put("DIRT", sheet.crop(1, 2, 1, 1));
        HOMETOWN_TERRAIN.put("LEFT_EDGE", sheet.crop(18, 2, 1, 1));
        HOMETOWN_TERRAIN.put("RIGHT_EDGE", sheet.crop(16, 2, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_EDGE", sheet.crop(17, 3, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_EDGE", sheet.crop(17, 1, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(19, 1, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(20, 1, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(19, 2, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(20, 2, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(18, 3, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(16, 3, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(18, 1, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(16, 1, 1, 1));
    }

    public static void init(){

        //We initialise all those previously declared assets, and begin to load them with their images

        init_hashmaps();
        
        //Asha
        //Each direction of movement requires an array to describe it,
        //because each motion requires a "left and a right" motion
        player_down = new BufferedImage[24];
        player_up = new BufferedImage[24];
        player_left = new BufferedImage[24];
        player_right = new BufferedImage[24];
        player_outfits = new BufferedImage[5];

        //Attack Animations
        attack_up = new BufferedImage[24];
        attack_down = new BufferedImage[24];
        attack_left = new BufferedImage[24];
        attack_right = new BufferedImage[24];

        //Shield animations
        shield_left = new BufferedImage[7];
        shield_right = new BufferedImage[7];
        
        //outfits (preview)
        SpriteSheet outfit = new SpriteSheet(ImageLoader.loadImage("../res/Asha/blue/Asha WL.png"),65,64);
        player_outfits[0] = outfit.crop(0, 0, 1, 1);
        outfit = new SpriteSheet(ImageLoader.loadImage("../res/Asha/dark/Asha WL.png"),65,64);
        player_outfits[1] = outfit.crop(0, 0, 1, 1);
        outfit = new SpriteSheet(ImageLoader.loadImage("../res/Asha/green/Asha WL.png"),65,64);
        player_outfits[2] = outfit.crop(0, 0, 1, 1);
        outfit = new SpriteSheet(ImageLoader.loadImage("../res/Asha/pastel/Asha WL.png"),65,64);
        player_outfits[3] = outfit.crop(0, 0, 1, 1);
        outfit = new SpriteSheet(ImageLoader.loadImage("../res/Asha/purple/Asha WL.png"),65,64);
        player_outfits[4] = outfit.crop(0, 0, 1, 1);

        font28 = new Font("arial",Font.BOLD,28);
        inventoryScreen = ImageLoader.loadImage("../res/inventoryScreen.png");

        //The spritesheet contains almost all of the images of the assets, so it makes sense to just load sub images
        //from the main image.
        //crop is in the form (x, y, width, height)
        //with each being multiplied by the tilesized passed in when creating the spritesheet
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("../res/sheet.png"), STD_TILE_SIZE, STD_TILE_SIZE);
        wood = sheet.crop(1, 1, 1, 1);

        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(6, 4, 2, 1);
        btn_start[1] = sheet.crop(6, 5, 2, 1);

        //Boss 3
        boss_down = new BufferedImage[2];
        boss_up = new BufferedImage[2];
        boss_left = new BufferedImage[2];
        boss_right = new BufferedImage[2];

        SpriteSheet boss_sheet = new SpriteSheet(ImageLoader.loadImage("../res/boss_sheet.png"),64,64);

        boss_down[0] = boss_sheet.crop(0, 0, 1, 1);
        boss_down[1] = boss_sheet.crop(1, 0, 1, 1);
        boss_up[0] = boss_sheet.crop(0, 1, 1, 1);
        boss_up[1] = boss_sheet.crop(1, 1, 1, 1);
        boss_right[0] = boss_sheet.crop(0, 2, 1, 1);
        boss_right[1] = boss_sheet.crop(1, 2, 1, 1);
        boss_left[0] = boss_sheet.crop(0, 3, 1, 1);
        boss_left[1] = boss_sheet.crop(1, 3, 1, 1);

        //Boss 2
        SpriteSheet who_sheet = new SpriteSheet(ImageLoader.loadImage("../res/davros.png"),32,48);
        //Dalek movements
        dalek_down = new BufferedImage[2];
        dalek_up = new BufferedImage[2];
        dalek_left = new BufferedImage[2];
        dalek_right = new BufferedImage[2];

        dalek_down[0] = who_sheet.crop(0, 0, 1, 1);
        dalek_down[1] = who_sheet.crop(0, 0, 1, 1);
        dalek_up[0] = who_sheet.crop(0, 3, 1, 1);
        dalek_up[1] = who_sheet.crop(0, 3, 1, 1);
        dalek_right[0] = who_sheet.crop(0, 2, 1, 1);
        dalek_right[1] = who_sheet.crop(0, 2, 1, 1);
        dalek_left[0] = who_sheet.crop(0, 1, 1, 1);
        dalek_left[1] = who_sheet.crop(0, 1, 1, 1);

        //boss 1
        SpriteSheet grim_sheet = new SpriteSheet(ImageLoader.loadImage("../res/grim.png"),64,72);
 
        grim_down = new BufferedImage[2];
        grim_up = new BufferedImage[2];
        grim_left = new BufferedImage[2];
        grim_right = new BufferedImage[2];

        grim_down[0] = grim_sheet.crop(0,0,1,1);
        grim_down[1] = grim_sheet.crop(0,0,1,1);
        grim_up[0] = grim_sheet.crop(0,3,1,1);
        grim_up[1] = grim_sheet.crop(0,3,1,1);
        grim_left[0] = grim_sheet.crop(0,1,1,1);
        grim_left[1] = grim_sheet.crop(0,1,1,1);
        grim_right[0] = grim_sheet.crop(0,2,1,1);
        grim_right[1] = grim_sheet.crop(0,2,1,1);

        //Zombie movements

        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];

        zombie_down[0] = sheet.crop(4, 2, 1, 1);
        zombie_down[1] = sheet.crop(5, 2, 1, 1);
        zombie_up[0] = sheet.crop(6, 2, 1, 1);
        zombie_up[1] = sheet.crop(7, 2, 1, 1);
        zombie_right[0] = sheet.crop(4, 3, 1, 1);
        zombie_right[1] = sheet.crop(5, 3, 1, 1);
        zombie_left[0] = sheet.crop(6, 3, 1, 1);
        zombie_left[1] = sheet.crop(7, 3, 1, 1);

        //Background Assets
        rock = sheet.crop(0, 2, 1, 1);
        
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/heart.png"),125,125);
        heart=sheet.crop(0,0,1,1);

        sheet = new SpriteSheet(ImageLoader.loadImage("../res/morning_star.png"), 650, 750);
        morning_star = sheet.crop(0, 0, 1, 1);

        sheet = new SpriteSheet(ImageLoader.loadImage("../res/heart.png"),125,125);
        heart=sheet.crop(0,0,1,1);

        tree = new BufferedImage[14];
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/trees.png"), 55, 64);
        tree[0] = sheet.crop(0, 0, 1, 1);
        tree[1] = sheet.crop(0, 1, 1, 1);
        tree[2] = sheet.crop(0, 2, 1, 1);
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/trees2.png"), 40, 64);
        tree[3] = sheet.crop(0, 0, 1, 1);
        tree[4] = sheet.crop(0, 1, 1, 1);
        tree[5] = sheet.crop(0, 2, 1, 1);
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/trees3.png"), 35, 64);
        tree[6] = sheet.crop(0, 0, 1, 1);
        tree[7] = sheet.crop(0, 1, 1, 1);
        tree[8] = sheet.crop(0, 2, 1, 1);
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/trees4.png"), 30, 64);
        tree[9] = sheet.crop(0, 0, 1, 1);
        tree[10] = sheet.crop(0, 1, 1, 1);
        tree[11] = sheet.crop(0, 2, 1, 1);
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/pinktrees.png"), 64, 64);
        tree[12] = sheet.crop(0, 0, 1, 1);
        tree[13] = sheet.crop(1, 0, 1, 1);
        
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/home_town.png"), 704, 576);
        building = sheet.crop(0, 0, 1, 1);
      
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/morning_star.png"), 650, 750);
        morning_star = sheet.crop(0, 0, 1, 1);

        button = new BufferedImage[2];
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/button0.png"), 32, 32);
        button[0] = sheet.crop(0, 0, 1, 1);//0 if off
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/button1.png"), 32, 32);
        button[1] = sheet.crop(0, 0, 1, 1);//1 is on
        
        door = new BufferedImage[5];
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/door.png"), 48, 48);
        door[0] = sheet.crop(0, 1, 1, 1);
        door[1] = sheet.crop(0, 2, 1, 1);
        door[2] = sheet.crop(0, 3, 1, 1);
        door[3] = sheet.crop(0, 4, 1, 1);
        door[4] = sheet.crop(0, 0, 1, 1);
        
        portal = new BufferedImage[2];
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/portal1.png"), 360, 600);
        portal[0] = sheet.crop(0, 0, 1, 1);
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/portal2.png"), 285, 450);
        portal[1] = sheet.crop(0, 0, 1, 1);
    }

    public static void initAsha(String type){
        SpriteSheet sheet_player_right = new SpriteSheet(ImageLoader.loadImage("../res/Asha/" + type + "/Asha WR.png"),64,64);
        SpriteSheet sheet_player_left = new SpriteSheet(ImageLoader.loadImage("../res/Asha/" + type + "/Asha WL.png"),64,64);
        SpriteSheet sheet_attack_left = new SpriteSheet(ImageLoader.loadImage("../res/Asha/" + type + "/Asha AL.png"),64,64);
        SpriteSheet sheet_attack_right = new SpriteSheet(ImageLoader.loadImage("../res/Asha/" + type + "/Asha AR.png"),64,64);
        SpriteSheet sheet_shield_left = new SpriteSheet(ImageLoader.loadImage("../res/Asha/" + type + "/Asha SL.png"),64,64);
        SpriteSheet sheet_shield_right = new SpriteSheet(ImageLoader.loadImage("../res/Asha/" + type + "/Asha SR.png"),64,64);

        //shield
        shield_left[0] = sheet_shield_left.crop(0, 0, 1, 1);
        shield_left[1] = sheet_shield_left.crop(1, 0, 1, 1);
        shield_left[2] = sheet_shield_left.crop(2, 0, 1, 1);
        shield_left[3] = sheet_shield_left.crop(3, 0, 1, 1);
        shield_left[4] = sheet_shield_left.crop(4, 0, 1, 1);
        shield_left[5] = sheet_shield_left.crop(5, 0, 1, 1);
        shield_left[6] = sheet_shield_left.crop(6, 0, 1, 1);

        shield_right[0] = sheet_shield_right.crop(0, 0, 1, 1);
        shield_right[1] = sheet_shield_right.crop(1, 0, 1, 1);
        shield_right[2] = sheet_shield_right.crop(2, 0, 1, 1);
        shield_right[3] = sheet_shield_right.crop(3, 0, 1, 1);
        shield_right[4] = sheet_shield_right.crop(4, 0, 1, 1);
        shield_right[5] = sheet_shield_right.crop(5, 0, 1, 1);
        shield_right[6] = sheet_shield_right.crop(6, 0, 1, 1);

        //walking

        player_right[0] = sheet_player_right.crop(0, 0, 1, 1);
        player_right[1] = sheet_player_right.crop(1, 0, 1, 1);
        player_right[2] = sheet_player_right.crop(2, 0, 1, 1);
        player_right[3] = sheet_player_right.crop(3, 0, 1, 1);
        player_right[4] = sheet_player_right.crop(0, 1, 1, 1);
        player_right[5] = sheet_player_right.crop(1, 1, 1, 1);
        player_right[6] = sheet_player_right.crop(2, 1, 1, 1);
        player_right[7] = sheet_player_right.crop(3, 1, 1, 1);
        player_right[8] = sheet_player_right.crop(0, 2, 1, 1);
        player_right[9] = sheet_player_right.crop(1, 2, 1, 1);
        player_right[10] = sheet_player_right.crop(2, 2, 1, 1);
        player_right[11] = sheet_player_right.crop(3, 2, 1, 1);
        player_right[12] = sheet_player_right.crop(0, 3, 1, 1);
        player_right[13] = sheet_player_right.crop(1, 3, 1, 1);
        player_right[14] = sheet_player_right.crop(2, 3, 1, 1);
        player_right[15] = sheet_player_right.crop(3, 3, 1, 1);
        player_right[16] = sheet_player_right.crop(0, 4, 1, 1);
        player_right[17] = sheet_player_right.crop(1, 4, 1, 1);
        player_right[18] = sheet_player_right.crop(2, 4, 1, 1);
        player_right[19] = sheet_player_right.crop(3, 4, 1, 1);
        player_right[20] = sheet_player_right.crop(0, 5, 1, 1);
        player_right[21] = sheet_player_right.crop(1, 5, 1, 1);
        player_right[22] = sheet_player_right.crop(2, 5, 1, 1);
        player_right[23] = sheet_player_right.crop(3, 5, 1, 1);

        //Player Left
        player_left[0] = sheet_player_left.crop(0, 0, 1,1);
        player_left[1] = sheet_player_left.crop(1, 0, 1, 1);
        player_left[2] = sheet_player_left.crop(2, 0, 1, 1);
        player_left[3] = sheet_player_left.crop(3, 0, 1, 1);
        player_left[4] = sheet_player_left.crop(0, 1, 1, 1);
        player_left[5] = sheet_player_left.crop(1, 1, 1, 1);
        player_left[6] = sheet_player_left.crop(2, 1, 1, 1);
        player_left[7] = sheet_player_left.crop(3, 1, 1, 1);
        player_left[8] = sheet_player_left.crop(0, 2, 1, 1);
        player_left[9] = sheet_player_left.crop(1, 2, 1, 1);
        player_left[10] = sheet_player_left.crop(2, 2, 1, 1);
        player_left[11] = sheet_player_left.crop(3, 2, 1, 1);
        player_left[12] = sheet_player_left.crop(0, 3, 1, 1);
        player_left[13] = sheet_player_left.crop(1, 2, 1, 1);
        player_left[14] = sheet_player_left.crop(2, 3, 1, 1);
        player_left[15] = sheet_player_left.crop(3, 3, 1, 1);
        player_left[16] = sheet_player_left.crop(0, 4, 1, 1);
        player_left[17] = sheet_player_left.crop(1, 4, 1, 1);
        player_left[18] = sheet_player_left.crop(2, 4, 1, 1);
        player_left[19] = sheet_player_left.crop(3, 4, 1, 1);
        player_left[20] = sheet_player_left.crop(0, 5, 1, 1);
        player_left[21] = sheet_player_left.crop(1, 5, 1, 1);
        player_left[22] = sheet_player_left.crop(2, 5, 1, 1);
        player_left[23] = sheet_player_left.crop(3, 5, 1, 1);

        //Attacking Left
        attack_left[0] = sheet_attack_left.crop(0, 0, 1,1);
        attack_left[1] = sheet_attack_left.crop(1, 0, 1, 1);
        attack_left[2] = sheet_attack_left.crop(2, 0, 1, 1);
        attack_left[3] = sheet_attack_left.crop(3, 0, 1, 1);
        attack_left[4] = sheet_attack_left.crop(0, 1, 1, 1);
        attack_left[5] = sheet_attack_left.crop(1, 1, 1, 1);
        attack_left[6] = sheet_attack_left.crop(2, 1, 1, 1);
        attack_left[7] = sheet_attack_left.crop(3, 1, 1, 1);
        attack_left[8] = sheet_attack_left.crop(0, 2, 1, 1);
        attack_left[9] = sheet_attack_left.crop(1, 2, 1, 1);
        attack_left[10] = sheet_attack_left.crop(2, 2, 1, 1);
        attack_left[11] = sheet_attack_left.crop(3, 2, 1, 1);
        attack_left[12] = sheet_attack_left.crop(0, 3, 1, 1);
        attack_left[13] = sheet_attack_left.crop(1, 3, 1, 1);
        attack_left[14] = sheet_attack_left.crop(2, 3, 1, 1);
        attack_left[15] = sheet_attack_left.crop(3, 3, 1, 1);
        attack_left[16] = sheet_attack_left.crop(0, 4, 1, 1);
        attack_left[17] = sheet_attack_left.crop(1, 4, 1, 1);
        attack_left[18] = sheet_attack_left.crop(2, 4, 1, 1);
        attack_left[19] = sheet_attack_left.crop(3, 4, 1, 1);
        attack_left[20] = sheet_attack_left.crop(0, 5, 1, 1);
        attack_left[21] = sheet_attack_left.crop(1, 5, 1, 1);
        attack_left[22] = sheet_attack_left.crop(2, 5, 1, 1);
        attack_left[23] = sheet_attack_left.crop(3, 5, 1, 1);

        //Attacking Right
        attack_right[0] = sheet_attack_right.crop(0, 0, 1,1);
        attack_right[1] = sheet_attack_right.crop(1, 0, 1, 1);
        attack_right[2] = sheet_attack_right.crop(2, 0, 1, 1);
        attack_right[3] = sheet_attack_right.crop(3, 0, 1, 1);
        attack_right[4] = sheet_attack_right.crop(0, 1, 1, 1);
        attack_right[5] = sheet_attack_right.crop(1, 1, 1, 1);
        attack_right[6] = sheet_attack_right.crop(2, 1, 1, 1);
        attack_right[7] = sheet_attack_right.crop(3, 1, 1, 1);
        attack_right[8] = sheet_attack_right.crop(0, 2, 1, 1);
        attack_right[9] = sheet_attack_right.crop(1, 2, 1, 1);
        attack_right[10] = sheet_attack_right.crop(2, 2, 1, 1);
        attack_right[11] = sheet_attack_right.crop(3, 2, 1, 1);
        attack_right[12] = sheet_attack_right.crop(0, 3, 1, 1);
        attack_right[13] = sheet_attack_right.crop(1, 3, 1, 1);
        attack_right[14] = sheet_attack_right.crop(2, 3, 1, 1);
        attack_right[15] = sheet_attack_right.crop(3, 3, 1, 1);
        attack_right[16] = sheet_attack_right.crop(0, 4, 1, 1);
        attack_right[17] = sheet_attack_right.crop(1, 4, 1, 1);
        attack_right[18] = sheet_attack_right.crop(2, 4, 1, 1);
        attack_right[19] = sheet_attack_right.crop(3, 4, 1, 1);
        attack_right[20] = sheet_attack_right.crop(0, 5, 1, 1);
        attack_right[21] = sheet_attack_right.crop(1, 5, 1, 1);
        attack_right[22] = sheet_attack_right.crop(2, 5, 1, 1);
        attack_right[23] = sheet_attack_right.crop(3, 5, 1, 1);
    }
}
