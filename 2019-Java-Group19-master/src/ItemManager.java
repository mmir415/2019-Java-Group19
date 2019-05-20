import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    //Stores items that are in the game and lying on the ground, not ones that have been added to the
    //Inventory yet.
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick(){
        //We will use the iterator to go through every item in our list.
        Iterator<Item> it = items.iterator();
        //So we go through each object in the game, and if it has been picked up, then we should remove it from
        //the game world and add it to the inventory
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.isPickedUp())
                it.remove();
        }
    }

    //wipes all items from the manager
    public void wipeObjects(){
        items = new ArrayList<Item>();
    }

    public void render(Graphics g){
        for(Item i : items)
            i.render(g);
    }

    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }

    // Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}