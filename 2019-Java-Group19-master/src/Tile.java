import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //Elements that the game's world is made up of.

    //STATIC
    //Below will just hold 256 tiles. This will hold one instance of every tile in our game.
    //If you cannnot walk through the tile, it will create a WallTile
    //Each tile gets passed in the image, then ID.  
    public static Tile[] tiles = new Tile[256];

    // 0 to 22 is boss terrain
    public static Tile B_floor = new Tile(Assets.BOSS_TERRAIN.get("FLOOR"), 0);
    public static Tile B_top_left_corner = new WallTile(Assets.BOSS_TERRAIN.get("TOP_LEFT_CORNER"), 1);
    public static Tile B_top_edge = new WallTile(Assets.BOSS_TERRAIN.get("TOP_EDGE"), 2);
    public static Tile B_top_right_corner = new WallTile(Assets.BOSS_TERRAIN.get("TOP_RIGHT_CORNER"), 3);
    public static Tile B_left_edge = new WallTile(Assets.BOSS_TERRAIN.get("LEFT_EDGE"), 4);
    public static Tile B_right_edge = new WallTile(Assets.BOSS_TERRAIN.get("RIGHT_EDGE"), 5);
    public static Tile B_bottom_left_corner = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_LEFT_CORNER"), 6);
    public static Tile B_bottom_edge = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_EDGE"), 7);
    public static Tile B_bottom_right_corner = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_RIGHT_CORNER"), 8);
    public static Tile B_black = new WallTile(Assets.BOSS_TERRAIN.get("BLACK"), 9);
    public static Tile B_top_right_inside = new WallTile(Assets.BOSS_TERRAIN.get("TOP_RIGHT_INSIDE"), 10);
    public static Tile B_top_left_inside = new WallTile(Assets.BOSS_TERRAIN.get("TOP_LEFT_INSIDE"), 11);
    public static Tile B_bottom_right_inside = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_RIGHT_INSIDE"), 12);
    public static Tile B_bottom_left_inside = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_LEFT_INSIDE"), 13);
    public static Tile B_mat_top_left_corner = new Tile(Assets.BOSS_TERRAIN.get("MAT_TOP_LEFT_CORNER"), 14);
    public static Tile B_mat_top_edge = new Tile(Assets.BOSS_TERRAIN.get("MAT_TOP_EDGE"), 15);
    public static Tile B_B_mat_top_right_corner = new Tile(Assets.BOSS_TERRAIN.get("MAT_TOP_RIGHT_CORNER"), 16);
    public static Tile B_B_mat_left_edge = new Tile(Assets.BOSS_TERRAIN.get("MAT_LEFT_EDGE"), 17);
    public static Tile B_mat = new Tile(Assets.BOSS_TERRAIN.get("MAT"), 18);
    public static Tile B_mat_right_edge = new Tile(Assets.BOSS_TERRAIN.get("MAT_RIGHT_EDGE"), 19);
    public static Tile B_mat_bottom_left_corner = new Tile(Assets.BOSS_TERRAIN.get("MAT_BOTTOM_LEFT_CORNER"), 20);
    public static Tile B_mat_bottom_edge = new Tile(Assets.BOSS_TERRAIN.get("MAT_BOTTOM_EDGE"), 21);
    public static Tile B_mat_botton_right_corner = new Tile(Assets.BOSS_TERRAIN.get("MAT_BOTTOM_RIGHT_CORNER"), 22);
    
    // 30 to 43 is castle terrain
    public static Tile C_floor = new Tile(Assets.CASTLE_TERRAIN.get("FLOOR"), 30);
    public static Tile C_top_left_corner = new WallTile(Assets.CASTLE_TERRAIN.get("TOP_LEFT_CORNER"), 31);
    public static Tile C_top_edge = new WallTile(Assets.CASTLE_TERRAIN.get("TOP_EDGE"), 32);
    public static Tile C_top_right_corner = new WallTile(Assets.CASTLE_TERRAIN.get("TOP_RIGHT_CORNER"), 33);
    public static Tile C_left_edge = new WallTile(Assets.CASTLE_TERRAIN.get("LEFT_EDGE"), 34);
    public static Tile C_right_edge = new WallTile(Assets.CASTLE_TERRAIN.get("RIGHT_EDGE"), 35);
    public static Tile C_bottom_left_corner = new WallTile(Assets.CASTLE_TERRAIN.get("BOTTOM_LEFT_CORNER"), 36);
    public static Tile C_bottom_edge = new WallTile(Assets.CASTLE_TERRAIN.get("BOTTOM_EDGE"), 37);
    public static Tile C_bottom_right_corner = new WallTile(Assets.CASTLE_TERRAIN.get("BOTTOM_RIGHT_CORNER"), 38);
    public static Tile C_wall = new WallTile(Assets.CASTLE_TERRAIN.get("WALL"), 39);
    public static Tile C_top_right_inside = new WallTile(Assets.CASTLE_TERRAIN.get("TOP_RIGHT_INSIDE"), 40);
    public static Tile C_top_left_inside = new WallTile(Assets.CASTLE_TERRAIN.get("TOP_LEFT_INSIDE"), 41);
    public static Tile C_bottom_right_inside = new WallTile(Assets.CASTLE_TERRAIN.get("BOTTOM_RIGHT_INSIDE"), 42);
    public static Tile C_bottom_left_inside = new WallTile(Assets.CASTLE_TERRAIN.get("BOTTOM_LEFT_INSIDE"), 43);

    // 50 to 63 is Ashlands terrain
    public static Tile A_floor = new Tile(Assets.ASHLANDS_TERRAIN.get("ASH"), 50);
    public static Tile A_top_left_corner = new WallTile(Assets.ASHLANDS_TERRAIN.get("TOP_LEFT_CORNER"), 51);
    public static Tile A_top_edge = new WallTile(Assets.ASHLANDS_TERRAIN.get("TOP_EDGE"), 52);
    public static Tile A_top_right_corner = new WallTile(Assets.ASHLANDS_TERRAIN.get("TOP_RIGHT_CORNER"), 53);
    public static Tile A_left_edge = new WallTile(Assets.ASHLANDS_TERRAIN.get("LEFT_EDGE"), 54);
    public static Tile A_right_edge = new WallTile(Assets.ASHLANDS_TERRAIN.get("RIGHT_EDGE"), 55);
    public static Tile A_bottom_left_corner = new WallTile(Assets.ASHLANDS_TERRAIN.get("BOTTOM_LEFT_CORNER"), 56);
    public static Tile A_bottom_edge = new WallTile(Assets.ASHLANDS_TERRAIN.get("BOTTOM_EDGE"), 57);
    public static Tile A_bottom_right_corner = new WallTile(Assets.ASHLANDS_TERRAIN.get("BOTTOM_RIGHT_CORNER"), 58);
    public static Tile A_dirt = new WallTile(Assets.ASHLANDS_TERRAIN.get("DIRT"), 59);
    public static Tile A_top_right_inside = new WallTile(Assets.ASHLANDS_TERRAIN.get("TOP_RIGHT_INSIDE"), 60);
    public static Tile A_top_left_inside = new WallTile(Assets.ASHLANDS_TERRAIN.get("TOP_LEFT_INSIDE"), 61);
    public static Tile A_bottom_right_inside = new WallTile(Assets.ASHLANDS_TERRAIN.get("BOTTOM_RIGHT_INSIDE"), 62);
    public static Tile A_bottom_left_inside = new WallTile(Assets.ASHLANDS_TERRAIN.get("BOTTOM_LEFT_INSIDE"), 63);
    
    // 70 to 83 is Hometown terrain
    public static Tile H_grass = new Tile(Assets.HOMETOWN_TERRAIN.get("GRASS"), 70);
    public static Tile H_top_left_corner = new WallTile(Assets.HOMETOWN_TERRAIN.get("TOP_LEFT_CORNER"), 71);
    public static Tile H_top_edge = new WallTile(Assets.HOMETOWN_TERRAIN.get("TOP_EDGE"), 72);
    public static Tile H_top_right_corner = new WallTile(Assets.HOMETOWN_TERRAIN.get("TOP_RIGHT_CORNER"), 73);
    public static Tile H_left_edge = new WallTile(Assets.HOMETOWN_TERRAIN.get("LEFT_EDGE"), 74);
    public static Tile H_right_edge = new WallTile(Assets.HOMETOWN_TERRAIN.get("RIGHT_EDGE"), 75);
    public static Tile H_bottom_left_corner = new WallTile(Assets.HOMETOWN_TERRAIN.get("BOTTOM_LEFT_CORNER"), 76);
    public static Tile H_bottom_edge = new WallTile(Assets.HOMETOWN_TERRAIN.get("BOTTOM_EDGE"), 77);
    public static Tile H_bottom_right_corner = new WallTile(Assets.HOMETOWN_TERRAIN.get("BOTTOM_RIGHT_CORNER"), 78);
    public static Tile H_dirt = new WallTile(Assets.HOMETOWN_TERRAIN.get("DIRT"), 79);
    public static Tile H_top_right_inside = new WallTile(Assets.HOMETOWN_TERRAIN.get("TOP_RIGHT_INSIDE"), 80);
    public static Tile H_top_left_inside = new WallTile(Assets.HOMETOWN_TERRAIN.get("TOP_LEFT_INSIDE"), 81);
    public static Tile H_bottom_right_inside = new WallTile(Assets.HOMETOWN_TERRAIN.get("BOTTOM_RIGHT_INSIDE"), 82);
    public static Tile H_bottom_left_inside = new WallTile(Assets.HOMETOWN_TERRAIN.get("BOTTOM_LEFT_INSIDE"), 83);

    // 90 to 91 are buttons
    public static Tile button_off = new WallTile(Assets.button[0], 90);
    public static Tile button_on = new WallTile(Assets.button[1], 91);

    //CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    //All tiles need textures/images to represent them.
    //The ID integer will be a way for us to identify the tile, each tile will have one.
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        //The element at this tile's id (when the constructor is called) is equal to the tile we're creating.
        //eg tiles[0]=this would be saying that this is a floor tile.
        tiles[id] = this; 
    }

    public void tick(){
    }

    public void render(Graphics g, int x, int y){
        //Draws the texture of the tile.
        //X or y decide the tile's position on the screen.
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        //Default is false, and can be overriden if the particular tile can be collided to.
        return false;
    }

    public int getId(){
        return id;
    }
}