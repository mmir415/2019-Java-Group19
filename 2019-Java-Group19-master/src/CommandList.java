import java.awt.*;
import java.awt.event.KeyEvent;

public class CommandList {

    private boolean exit_menu_active = false;
    private Handler handler;

    public CommandList(Handler handler){
        this.handler = handler;
    }
    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))
          exit_menu_active = !exit_menu_active;
        }

    public void render(Graphics g) {
        if (!exit_menu_active)
            return;
        if (exit_menu_active){
            g.setColor(Color.black);
            g.fillRect(305, 307,400, 210);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 400, 210);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Command List: " , 410, 327);
            Font fnt4 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt4);
            g.setColor(Color.white);
            // Left column
            g.drawString("W: Move Up", 320, 357);
            g.drawString("A: Move Left", 320, 377);
            g.drawString("S: Move Down", 320, 397);
            g.drawString("D: Move Right", 320, 417);
            g.drawString("Esc: Exit Game", 320, 437);
            g.drawString("M: Exit to Menu", 320, 457);
            g.drawString("I: Call Inventory", 320, 477);
            //Right column
            g.drawString("Shift: Use Alucard Shield",470, 357);
            g.drawString("P: Pause Game",470,377);
            g.drawString("Down Arrow: Attack Down",470,397);
            g.drawString("Up Arrow: Attack Up", 470, 417);
            g.drawString("Left Arrow: Attack Left", 470, 437);
            g.drawString("Right Arrow: Attack Right", 470, 457);
        }
    }

    // GETTERS SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isExit_menu_active() {
        return exit_menu_active;
    }
}