import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public int right_x = 940;
    public int height = 55;
    
    private Handler handler;

    public MouseInput(Handler handler){
        this.handler = handler;
    }

    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //PlayButton
        if (mx >= 670 && mx <= right_x){
            if (my >= 455 && my <= 455 + height){
                Game.States = Game.STATE.OUTFIT;
            }
        }
        //Score button
        if (mx >= 810 && mx <= right_x){
            if (my >= 515 && my <= 515 + height){
                Game.States = Game.STATE.SCORE;
            }
        }
        //Credits button
        if (mx >= 790 && mx <= right_x){
            if (my >= 580 && my <= 580 + height){
                Game.States = Game.STATE.CREDITS;
            }
        }
        //QuitButton
        if (mx >= 835 && mx <= right_x){
            if (my >= 645 && my <= 645 + height){
                System.exit(1);
            }
        }
        //back button
        if (Game.States==Game.STATE.CREDITS || Game.States == Game.STATE.SCORE){
            if (mx>=200 && mx<=300){
                if (my>=500 && my<=550){
                    Game.States = Game.STATE.MENU;
                }
            }
        }
        //During Outfit Selection
        if (Game.States==Game.STATE.OUTFIT){
            if (my>=200 && my<=450){
                int start = 125;
                int space = 150;
                if (mx>=start && mx<=start + space){
                    handler.getGame().setOutfit("blue");
                    Assets.initAsha("blue");
                    Game.States = Game.STATE.GAME;
                    handler.getGame().changeSound("crawler");
                }
                else if (mx>=start + space && mx<=start + space * 2){
                    handler.getGame().setOutfit("dark");
                    Assets.initAsha("dark");
                    Game.States = Game.STATE.GAME;
                    handler.getGame().changeSound("crawler");
                }
                else if (mx>=start + space * 2 && mx<=start + space * 3){
                    handler.getGame().setOutfit("green");
                    Assets.initAsha("green");
                    Game.States = Game.STATE.GAME;
                    handler.getGame().changeSound("crawler");
                }
                else if (mx>=start + space * 3 && mx<=start + space * 4){
                    handler.getGame().setOutfit("pastel");
                    Assets.initAsha("pastel");
                    Game.States = Game.STATE.GAME;
                    handler.getGame().changeSound("crawler");
                }
                else if (mx>=start + space * 4 && mx<=start + space * 5){
                    handler.getGame().setOutfit("purple");
                    Assets.initAsha("purple");
                    Game.States = Game.STATE.GAME;
                    handler.getGame().changeSound("crawler");
                }
            }
            if (mx>=100 && mx<=200){
                if (my>=650 && my<=700){
                    Game.States = Game.STATE.MENU;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}