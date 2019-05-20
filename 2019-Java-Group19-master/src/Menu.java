import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements MouseListener {
    boolean started = false;

    public Menu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(280));
        addMouseListener(this);
    }

    public void start(){
        removeAll();
        started = true;
        repaint();
    }

    //could follow RealTutGML's tutorial for menu design, this following part is the only necessary part from WaffleStar
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (!started) {
            try {
            g.drawImage(new ImageIcon(ImageIO.read(new File("../res/title_screen.png"))).getImage(), 0, 0, 1024, 768, this);
            } catch (IOException e) {}
            Graphics2D g2d = (Graphics2D) g;
            Font fnt1 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt1);

        }else{
            setBackground(Color.BLUE);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //QuitButton
        //Functionally the quit button is complete
        //For this reason, the rectangle for it is no longer necessary
        if (mx >= 832 && mx <= 992){
            if (my >= 672 && my <= 800){
                //Pressed QuitButton
                System.exit(1);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
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
