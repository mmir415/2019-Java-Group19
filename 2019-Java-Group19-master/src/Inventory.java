import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory extends KeyManager{

    //The below list will keep track of all the items in the inventory currently
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    //Below are hardcoded regular values used to center the inventory's image
    private int invX = 102, invY = 77,
    invWidth = 819, invHeight = 614,
    invListCenterX = invX + 274,
    invListCenterY = invY + invHeight / 2 + 5,
    invListSpacing = 40;

    private int invImageX = 723, invImageY = 131,
    invImageWidth = 102, invImageHeight = 102;

    private int invCountX = 774, invCountY = 275;

    private int selectedItem = 0;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
    }

    public void tick(){
        //The below line toggles the inventory screen on and off
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I))
            active = !active;
        if(!active)
            return;

        //the below commands allow us to go up and down the inventory, to scroll through and
        //see what is inside the inventory
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            selectedItem--;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
            selectedItem++;

        if(selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if(selectedItem >= inventoryItems.size())
            selectedItem = 0;
    }

    public void render(Graphics g){
        if(!active)
            return;

        //This draws the inventory based off the image we gave it in the inventory screen
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        //If no items are in the inventory, we won't render any inventory.
        if(len == 0)
            return;

        //This is the graphical part of the inventory.
        //5 slots above and below the selected item in the inventory, for -5 and 6
        for(int i = -5;i < 6;i++){
            if(selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if(i == 0){
                //Below declares what item we have just scrolled to on the screen,
                //and also gives the photo of it
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
                        //if the item has been selected, the name of it in the inventory goes yellow
                        invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
            }else{
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                        invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
            }
        }

        //Draw the image of whatever item we are currently on
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);
    }

    // Inventory methods

    public void addItem(Item item){
        //when they pick up an item, increase the score by 10
        handler.getGame().incScore(10);
        //in our inventory list, if the item we have just picked up is something we already have, then we
        //will just increment the count for how many of that item is in the inventory already
        //if the item isn't already in the inventory, add it in.
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    // GETTERS SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public int getInventorySize() {
        return inventoryItems.size();
    }
}