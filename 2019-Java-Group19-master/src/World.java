import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class World {

    //width and height will be used in terms of times of the world.
    private Handler handler;
    private static int width, height;
    private static int spawnX, spawnY;
    //this will hold the ids of tiles and what position they will be created in. There will be rows of tiles
    //and columns of tiles
    private int[][] tiles;
    //Entities
    private EntityManager entityManager;
    // Item
    private ItemManager itemManager;

    public int room = 1;
    private String map_path;
    public boolean boss_ready = false;
    private boolean boss1_alive = true;
    private boolean boss2_alive = true;
    private boolean boss3_alive = true;

    private ArrayList<Entity> room1_entities;
    private ArrayList<Entity> room2_entities;
    private ArrayList<Entity> room4_entities;
    private ArrayList<Entity> room5_entities;
    private ArrayList<Entity> room7_entities;
    private ArrayList<Entity> room8_entities;
    private ArrayList<Entity> room10_entities;
    private ArrayList<Entity> room11_entities;
    
    private int num_off_switches = 0;

    private void entity_init(){
        room1_entities = new ArrayList<Entity>();
        room1_entities.add(new Building(handler, 50, 0));
        room1_entities.add(new Tree(handler, 60, 550, -1));
        room1_entities.add(new Tree(handler, 850, 400, -1));
        room1_entities.add(new Tree(handler, 200, 900, -1));
        room1_entities.add(new Rock(handler, 132, 750));
        room1_entities.add(new Rock(handler, 350, 600));
        room1_entities.add(new Rock(handler, 400, 645));
        room1_entities.add(new Tree(handler, 625, 795, -1));
        room1_entities.add(new Tree(handler, 800, 890, -1));
        
        room2_entities = new ArrayList<Entity>();
        room2_entities.add(new Tree(handler, 100, 70, 14));
        room2_entities.add(new Tree(handler, 400, 120, 15));
        room2_entities.add(new Tree(handler, 300, 590, 15));
        room2_entities.add(new Tree(handler, 700, 500, 14));
        room2_entities.add(new Zombie(handler, 800, 400, 64, 64));

        room4_entities = new ArrayList<Entity>();
        room4_entities.add(new Reaper(handler,800,400,32,32));

        
        room5_entities = new ArrayList<Entity>();
        room5_entities.add(new Gargoyle(handler, 300, 0));
        room5_entities.add(new Gargoyle(handler, 600, 0));
        room5_entities.add(new Gargoyle(handler, 900, 300));
        room5_entities.add(new Gargoyle(handler, 900, 500));
        room5_entities.add(new Gargoyle(handler, 300, 950));
        room5_entities.add(new Gargoyle(handler, 600, 950));
        room5_entities.add(new Door(handler, 425, 950));

        for (int y = 200; y < 850; y += Tile.TILEHEIGHT) {
            for (int x = 100; x < 850; x += Tile.TILEWIDTH) {
                int n = ThreadLocalRandom.current().nextInt(3);
                if (n > 0) {
                    room5_entities.add(new PuzzleSwitch(handler, x, y));
                    num_off_switches++;
                }
            }
        }

        room7_entities = new ArrayList<Entity>();
        room7_entities.add(new Exterminator(handler, 800, 400, 64, 64));
        
        room8_entities = new ArrayList<Entity>();
        room8_entities.add(new Gargoyle(handler, 300, 0));
        room8_entities.add(new Gargoyle(handler, 600, 0));
        room8_entities.add(new Gargoyle(handler, 1250, 200));
        room8_entities.add(new Gargoyle(handler, 1250, 400));
        room8_entities.add(new Zombie(handler, 100, 200, 64, 64));
        room8_entities.add(new Portal(handler, 50, 100, 1200, 700, Assets.portal[0]));//PURPLE
        room8_entities.add(new Portal(handler, 1250, 700, 150, 100, Assets.portal[0]));
        room8_entities.add(new Zombie(handler, 1075, 650, 64, 64));
        room8_entities.add(new Portal(handler, 50, 600, 1200, 75, Assets.portal[1]));//GREEN
        room8_entities.add(new Portal(handler, 1250, 75, 150, 600, Assets.portal[1]));
        room8_entities.add(new Zombie(handler, 1075, 60, 64, 64));

        room10_entities = new ArrayList<Entity>();
        room10_entities.add(new Boss(handler, 600, 300, 128, 128));

        room11_entities = new ArrayList<Entity>();
        room11_entities.add(new MorningStar(handler, 610, 325));
    }

    public World(Handler handler){
        this.handler = handler;
        map_init();
        entity_init();

        itemManager = new ItemManager(handler);
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));

        loadRoom(1, "C");
    }

    public void loadRoom(int room, String spawn_pos){
        //We don't want any objects to appear from the previous room, therefore we call wipe objects
        entityManager.wipeObjects();
        //Again, we don't want any unpicked up items to remain, so we remove them
        itemManager.wipeObjects();
        this.room = room;

        switch (room) {
            case 0: {
                map_path = "../res/map0.txt";
                break;
            }
            case 1: {
                // These are the objects that get loaded into the room
                for (Entity entity : room1_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/map1.txt";
                break;
            }
            case 2: {
                for (Entity entity : room2_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/map2.txt";
                break;
            }
            case 5: {
                for (Entity entity : room5_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/map5.txt";
                break;
            }
            case 8: {
                for (Entity entity : room8_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/map8.txt";
                break;
            }
            case 3:
            case 6:
            case 9: {
                map_path = "../res/mapHoldingRoom.txt";
                break;
            }
            case 4: {
                for (Entity entity : room4_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/mapBoss.txt";
                if (boss1_alive) {
                    handler.getGame().changeSound("boss");
                }
                break;
            }
            case 7: {
                for (Entity entity : room7_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/mapBoss.txt";
                if (boss2_alive) {
                    handler.getGame().changeSound("boss");
                }
                break;
            }
            case 10: {
                for (Entity entity : room10_entities) {
                    entityManager.addEntity(entity);
                }
                if (boss3_alive) {
                    handler.getGame().changeSound("boss");
                }
                map_path = "../res/mapFinalBoss.txt";
                break;
            }
            case 11: {
                for (Entity entity : room11_entities) {
                    entityManager.addEntity(entity);
                }
                map_path = "../res/mapFinalRoom.txt";
                break;
            }
        }
        loadWorld(map_path);

        //This sets where on the map the player character will show up.
        //L = left, R = right, T = top, B = bottom, C = centre   (of map)
        if (spawn_pos.equals("C")){
            entityManager.getPlayer().setX(5 * Tile.TILEWIDTH);
            entityManager.getPlayer().setY(12 *  Tile.TILEHEIGHT);
        }
        if (spawn_pos.equals("L")){
            entityManager.getPlayer().setX(Tile.TILEWIDTH / 2);
            entityManager.getPlayer().setY(5 *  Tile.TILEHEIGHT);
            if (room == 11){
                handler.getGame().changeSound("crawler");
            }
        }
        if (spawn_pos.equals("R")){
            if (room == 1){
                entityManager.getPlayer().setX((width - 1) * Tile.TILEWIDTH - Tile.TILEWIDTH / 2);
                entityManager.getPlayer().setY(11 *  Tile.TILEHEIGHT);
            }
            else if (room == 5){
                entityManager.getPlayer().setX((width - 1) * Tile.TILEWIDTH - Tile.TILEWIDTH / 2);
                entityManager.getPlayer().setY(6 *  Tile.TILEHEIGHT + Tile.TILEWIDTH / 2);
            }
            else{
                entityManager.getPlayer().setX((width - 1) * Tile.TILEWIDTH - Tile.TILEWIDTH / 2);
                entityManager.getPlayer().setY(5 *  Tile.TILEHEIGHT);
            }
            if (room == 3 || room == 6 || room == 9){
                handler.getGame().changeSound("crawler");
            }
        }
        if (spawn_pos.equals("T")){
            entityManager.getPlayer().setX((8) * Tile.TILEWIDTH);
            entityManager.getPlayer().setY(1 *  Tile.TILEHEIGHT);
        }
        if (spawn_pos.equals("B")){
            entityManager.getPlayer().setX((8) * Tile.TILEWIDTH);
            entityManager.getPlayer().setY((height - 1) *  Tile.TILEHEIGHT - Tile.TILEWIDTH / 2);
        }

    }

    public void tick(){
        itemManager.tick();
        entityManager.tick();
        if  (handler.getKeyManager().keyJustPressed(KeyEvent.VK_PAGE_DOWN)){
            boss_ready = !boss_ready;
        }

        if ((boss_ready)&&(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y))){
            loadRoom(10,"L");
            boss_ready = false;
        } else if((boss_ready)&&(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N))){
            boss_ready = false;
        }

        //the below checks if the player has reached one of the edges of the map
        //if they have, it will load a new room
        if (entityManager.getPlayer().reachedRight()){
            if (room != 4 && room != 7 && room != 11){//these rooms have no room to the right
                room++;//the room is now the next room
            }
            loadRoom(room, "L");
        }
        if (entityManager.getPlayer().reachedLeft()){
            if (room == 0){
                room = 10;//goes to final boss room
            }
            if (room != 5 && room != 8){//these rooms have no room to the left
                room--;//the room is now the next room
            }
            loadRoom(room, "R");
        }
        if (entityManager.getPlayer().reachedTop()){
            if (room == 0){
                room = 4;//goes to first boss room
            }
            if (room == 5 || room == 8){//these rooms have a room above
                room -= 3;//the room is now the room above
            }
            loadRoom(room, "B");
        }
        if (entityManager.getPlayer().reachedBottom()){
            if (room == 0){
                room = 7;//goes to second boss room
            }
            if (room == 5 || room == 2){//these rooms have a room below
                room += 3;//the room is now the room below
            }
            loadRoom(room, "T");
        }
    }

    public void render(Graphics g){
        //Below is ensuring the boundaries of what is rendered on the map, keeping them within the tile limits
        //and the camera limits.
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);


        //Loop extends from beginning of map to end of map
        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                //Here, we are converting the tile co-ordinates to pixels. x by width, y by height.
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        // Items
        itemManager.render(g);
        //Entities
        entityManager.render(g);

        if (boss_ready){
            g.setColor(Color.black);
            g.fillRect(305, 307,500, 70);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 500, 70);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Ready to face the final boss Mikhael? " , 330, 327);
            g.drawString("Y:Yes", 410, 357);
            g.drawString("N:No", 490, 357);
        }
    }

    public Tile getTile(int x, int y){
        //gets x and y positions for the tile. It is going to look for the id in the tiles array at x,y.
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.B_black;

        //accesses tiles array in Tile class.
        Tile t = Tile.tiles[tiles[x][y]];
        //if the tile that we got is equal to nothing, we run a default black Tile.
        if(t == null)
            return Tile.B_black;
        return t;
    }

    private void loadWorld(String path){
        //This loads the world file(s) that we have in our res, and grabs tokens from it to use for indexing.
        String file = Utils.loadFileAsString(path);
        //We must split up each individual number in the file around every amount of white space.
        String[] tokens = file.split("\\s+");
        //The first two numbers at the very top of the world file tell what the world's width and height is.

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        //After that comes the world data. The tile data will be represented by an ID number on that position on that
        //file.Ensure that all of the ids are relative to the height and width you have set.

        tiles = new int[width][height];
        //This loops through every element of our tiles array and set the tiles in it equal to something
        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                //We are converting the x and y of the 4 loops into the 1 dimensional array index.
                //We are adding 4 because the first 4 numbers are not actual world data.
                if (room == 0 || room == 3 || room == 4 || room == 6 || room == 7 || room == 9 || room == 10 || room == 11){
                    tiles[x][y] = STR_TO_INT.get(tokens[(x + y * width) + 2]);
                } 
                else if (room == 5 || room == 8){
                    //adding 0, 30, 50, 70 to get a different terrain for the different rooms (see the Tile's ID)
                    tiles[x][y] = STR_TO_INT.get(tokens[(x + y * width) + 2]) + 30;
                }
                else if (room == 2){
                    tiles[x][y] = STR_TO_INT.get(tokens[(x + y * width) + 2]) + 50;
                }
                else if (room == 1){
                    tiles[x][y] = STR_TO_INT.get(tokens[(x + y * width) + 2]) + 70;
                }
            }
        }
    }

    public static final HashMap<String, Integer> STR_TO_INT = new HashMap<>();

    public static void map_init(){
        STR_TO_INT.put("0", 0);
        STR_TO_INT.put("1", 1);
        STR_TO_INT.put("2", 2);
        STR_TO_INT.put("3", 3);
        STR_TO_INT.put("4", 4);
        STR_TO_INT.put("5", 5);
        STR_TO_INT.put("6", 6);
        STR_TO_INT.put("7", 7);
        STR_TO_INT.put("8", 8);
        STR_TO_INT.put("9", 9);
        STR_TO_INT.put("A", 10);
        STR_TO_INT.put("B", 11);
        STR_TO_INT.put("C", 12);
        STR_TO_INT.put("D", 13);
        STR_TO_INT.put("E", 14);
        STR_TO_INT.put("F", 15);
        STR_TO_INT.put("G", 16);
        STR_TO_INT.put("H", 17);
        STR_TO_INT.put("I", 18);
        STR_TO_INT.put("J", 19);
        STR_TO_INT.put("K", 20);
        STR_TO_INT.put("L", 21);
        STR_TO_INT.put("M", 22);
        STR_TO_INT.put("N", 23);
    }

    public static int getWidth(){
        return width;
    }

    public static int getHeight(){
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getNumOffSwitches(){
        return num_off_switches;
    }

    public void incNumOffSwitches(){
        num_off_switches++;
    }

    public void decNumOffSwitches(){
        num_off_switches--;
    }

    public void setBoss3Alive(boolean boss_alive){
        this.boss3_alive = boss_alive;
    }

    public void setBoss2Alive(boolean boss_alive){
        this.boss2_alive = boss_alive;
    }

    public void setBoss1Alive(boolean boss_alive){
        this.boss1_alive = boss_alive;
    }
}