import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display {
    //At the moment, the Canvas is being superimposed on top of the JFrame, but previously we just had the Game class
    //itself extend the Canvas and implement Runnable. The game can be changed back to this manner, since this currently
    //feels a bit too overcomplicated.
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        //This is called as soon as the Game class begins, and whatever title, wdith and height are passed into the
        //game are passed in here.
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
        //this creates the Frame that our game will be placed on. Using a Canvas on top of the JFrame seems like it would
        //bypass any limitations of either, as the Canvas allows us to draw graphics to it,
        // but in so far as I can see, we could still just use a Canvas. Will investigate
        //Below, the setLocationRelativeTo pushes everything to be centered on top of the JFrame display.Others are self
        //explanatory.
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Below is same as done with the canvas in the previous base, the size of the canvas will be fixed right
        //on top of the JFrame.

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        //This adds the canvas to the frame. I am still not yet convinced that the frame is at all necessary, but I will
        //test that later on today/
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}