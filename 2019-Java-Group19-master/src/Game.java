
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.swing.Timer;

//Base of game inspired by:
//CodeNMore 2014 https://www.youtube.com/playlist?list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ
//RealTutsGML 25 May 2017 https://www.youtube.com/playlist?list=PLWms45O3n--5vDnNd6aiu1CSWX3JlCU1n
//TheChernoProject 5 Oct 2012 https://www.youtube.com/playlist?list=PLlrATfBNZ98eOOCk2fOFg7Qg5yoQfFAdf

public class Game extends Canvas implements Runnable {

    private Display display;
    private int width, height;
    private World world;
    public String title;
    public static boolean won = false;
    private static int sec = 0;
    private static int min = 0;

    public boolean running = false;
    public boolean paused = false;
    public boolean ticking = true;
    private Thread thread;
    //States
    public enum STATE{
        MENU,GAME,CREDITS,SCORE,OUTFIT
    }
    public static Game.STATE States = Game.STATE.MENU;

    private BufferStrategy bs;
    private Graphics g;
    private Player player;
    private String music;
    private String outfit;
    public String path = "../res/scores.txt";
    private String name = "Unknown";
    private String time = "00:00";
    private static int score = 5 * 60;
    private CommandList commandList;

    //Input
    private KeyManager keyManager;
    private MouseInput mouseInput;
    private Menu menu;

    //Camera
    private GameCamera gameCamera;
    int i = 0;

    //Handler
    private Handler handler;

    //Sound
    private MediaPlayer mediaPlayer;
    private Thread soundThread;
    private Timer soundTimer;

    public static void main(String[] args){
        Game game = new Game("Asha's Quest", 1024, 768);
        game.start();
    }

    public Game(String title, int width, int height){
        //sound setup
        com.sun.javafx.application.PlatformImpl.startup(()->{});

        //Takes in the 3 variables that are passed to the Display and initialises the key and mouse manager.
        this.width = width;
        this.height = height;
        this.title = title;
        handler = new Handler(this);
        menu = new Menu();
        keyManager = new KeyManager();
      //  mouseManager = new MouseManager();
        mouseInput = new MouseInput(handler);
        player = new Player(handler,0,0);
        commandList = new CommandList(handler);
    }

    private void init(){
        //Everything we want to initialise in the game goes here. The frame is used because it has several
        //in built commands regarding adding Mouse and Key Listeners, as seen below.
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addMouseListener(mouseInput);
        //Ensures that all sprites assigned to assets in the Assets class gets brought onto the Frame.
        Assets.init();

        // handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        world = new World(handler);
        handler.setWorld(world);
        playSound("title");
    }

    public void restart(){
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        world = new World(handler);
        handler.setWorld(world);
        changeSound("title");
        sec = 0;
        min = 0;
        time = "00:00";
        score = 5 * 60;
    }

    private void tick() {
        //Updates everything
        keyManager.tick();

        if (won){
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){

                won = false;
                try{
                    save_score(score);
                }
                catch (IOException e){
                }
                States = STATE.MENU;
                restart();
            }
            return;
        }

        //Below checks if we currently have a State that actually exists.
        if (States == STATE.GAME) {
            world.tick();
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
                paused = !paused;
            }
           if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))){
                ticking = !ticking;
            }
           if (handler.getWorld().getEntityManager().getPlayer().dead){
               if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
                   States = STATE.MENU;
                   restart();
               }
           }
        }
    }

    private void save_score(int score) throws IOException {
        String line = Integer.toString(score)  + " " + name + "\n";
        FileWriter write = new FileWriter(path, true);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s", line);
        print_line.close();
    }

    private void render(){
        //Draws everything
        //The Buffer Strategy is inside the Canvas of our display.
        //We get the Canvas of the current window, and this sets whatever the buffer strategy is to be the
        //same as our current one.
        bs = display.getCanvas().getBufferStrategy();
        //Buffer Strategy tells the computer how to draw things using buffers.
        //A Buffer is a "hidden computer screen"(just memory) within the computer. We draw everything to one buffer,
        //which moves to the next buffer, until it gets to the computer. This is to prevent flickering.
        //Below, if there isn't already a Buffer Strategy, we initiate one.
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!

        //If we currently have a real state in place, then call the render function inside whatever state we're in.
        if (States==STATE.GAME) {
            world.render(g);
            Font fnt2 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("HP: " , 5, 25);
            g.setColor(Color.green);
            g.fillRect(25, 10,handler.getWorld().getEntityManager().getPlayer().getHealth()* 16, 20);
            g.setColor(Color.black);
            g.drawRect(25, 10, 320, 20);
            g.setColor(Color.WHITE);
            g.drawString("Press C for Commands",5,50);
            g.drawString(time,5,90);
            g.drawString("Score",5,70);
            g.drawString(Integer.toString(score),60,70);

            if (paused){
                Font fnt4 = new Font("helvetica",Font.BOLD, 60);
                g.setFont(fnt4);
                g.setColor(Color.white);
                g.drawString("GAME      PAUSED", 240, 400);
            }
            if (won){
                Font fnt9 = new Font("helvetica",Font.BOLD, 60);
                g.setFont(fnt9);
                g.setColor(Color.white);
                g.drawString("You   WON!", 240, 400);
                g.drawString("Press Q to continue!", 200, 500);



            }
        } else if (States==STATE.MENU){
            menu.paintComponent(g);
        } else if (States==STATE.OUTFIT){
            g.setColor(Color.black);
            g.fillRect(0,0,1024,768);
            g.setColor(Color.GRAY);
            g.fillRect(100,100,800,600);

            g.setColor(Color.WHITE);
            Font fnt5 = new Font("Calibri",Font.BOLD,30);
            g.setFont(fnt5);
            g.drawString("Choose your outfit", 380, 600);
            int image_size = 150;
            int image_height = 200;
            int image_left = 125;
            int font_height = image_height + image_size + 50;
            int font_left = image_left + 60;
            Font fnt6 = new Font("Arial",Font.BOLD,15);
            g.setFont(fnt6);
            g.drawString("Blue", font_left, font_height);
            g.drawImage(Assets.player_outfits[0], image_left, image_height, image_size, image_size, null);
            g.drawString("Dark", font_left + image_size, font_height);
            g.drawImage(Assets.player_outfits[1], image_left + image_size, image_height, image_size, image_size, null);
            g.drawString("Green", font_left + image_size * 2, font_height);
            g.drawImage(Assets.player_outfits[2], image_left + image_size * 2, image_height, image_size, image_size, null);
            g.drawString("Pastel", font_left + image_size * 3, font_height);
            g.drawImage(Assets.player_outfits[3], image_left + image_size * 3, image_height, image_size, image_size, null);
            g.drawString("Purple", font_left + image_size * 4, font_height);
            g.drawImage(Assets.player_outfits[4], image_left + image_size * 4, image_height, image_size, image_size, null);

            g.setColor(Color.white);
            g.fillRect(100,650,100,50);
            g.setColor(Color.black);
            g.drawString("Return to",110,670);
            g.drawString("Menu",125,690);
            
        } else if (States==STATE.CREDITS){
            g.setColor(Color.black);
            g.fillRect(0,0,1024,768);
            g.setColor(Color.GRAY);
            g.fillRect(200,150,600,400);

            g.setColor(Color.WHITE);
            Font fnt5 = new Font("Calibri",Font.BOLD,20);
            g.setFont(fnt5);
            g.drawString("UNICORN DRAGON STUDIOS", 380, 200);
            Font fnt6 = new Font("Arial",Font.BOLD,15);
            g.setFont(fnt6);
            g.drawString("Co Founders: Kimberley Evans-Parker and M.Hassaan Mirza", 280, 250);
            g.drawString("Here is our studio mascot, Midnight!", 360, 350);
            try {
                g.drawImage(new ImageIcon(ImageIO.read(new File("../res/midnight.png"))).getImage(), 430, 380, 120, 150, this);
            } catch (IOException e) {}

            g.setColor(Color.white);
            g.fillRect(200,500,100,50);
            g.setColor(Color.black);
            g.drawString("Return to",210,520);
            g.drawString("Menu",225,540);

        } else if (States==STATE.SCORE){
            g.setColor(Color.black);
            g.fillRect(0,0,1024,768);
            g.setColor(Color.GRAY);
            g.fillRect(200,150,600,400);

            g.setColor(Color.WHITE);
            Font fnt5 = new Font("Calibri",Font.BOLD,30);
            g.setFont(fnt5);
            g.drawString("Scores", 450, 200);
            Font fnt6 = new Font("Arial",Font.BOLD,15);
            g.setFont(fnt6);

            ArrayList<String[]> scores = getScores();
            if (scores.size() == 0){
                g.drawString("No previous scores",210,220);
            }
            else{
                int y = 250;
                for (String[] score : scores) {
                    g.drawString(score[1], 250, y);
                    g.drawString(score[0], 700, y);
                    y += 25;
                }
            }

            g.setColor(Color.white);
            g.fillRect(200,500,100,50);
            g.setColor(Color.black);
            g.drawString("Return to",210,520);
            g.drawString("Menu",225,540);

        }

            //End Drawing!
            bs.show();
            g.dispose();
    }
    public static String updateTime() {
        String stringTime = "";
        
        //if you've won, freeze time and score
        if (! won){
            //score decreases as time increases
            score--;
    
            if (sec < 59) { //Check min overflow
                sec++;
            } else {
                sec = 0;
                min++;
            }
        }

        if (sec < 10) { //Formatting
            stringTime += "0";
        }
        stringTime += sec;
        if (min > 0) {//ez timer
            stringTime = min + ":" + stringTime;
        }

        return stringTime;
    }

    public void run(){
        //Soon as the game runs, everything we have in the init method will begin.
        init();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        //Most of the below steps are to implement a timer, maximising the speed at which the game is running and
        //to have the game run at the same speed on any machine that it's on.

        //fps = Frames Per Second. How many times each second we want tick and render to run.
        int fps = 100;
        //i billion /fps. As there are 1 billion nanoseconds in 1 second.
        //The below gets us the max amount of time we are allowed to run tick and render per second.
        double timePerTick = 1000000000 / fps;
        double delta = 0;

        long now;
        //System.nanoTime returns current time of the computer in nano seconds.
        long lastTime = System.nanoTime();
        long lastClockTime = System.currentTimeMillis();
        long timer = 0;
        int ticks = 0;


        while(running){
            if (ticking){
                if ((System.currentTimeMillis() - lastClockTime > 1000) && (States == STATE.GAME)) {//Only call every second
                    time = updateTime(); //Update
                    lastClockTime = System.currentTimeMillis();
                }
           }
            //Delta is added to. Now - last time will give the amount of time that has been passed
            //since this line of code was called. Divided by the amount of time we are allowed to call them.
            //Delta essentially tells the computer how much time there is until they have to call tick and render.

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            //lastTime = currentTime again.
            lastTime = now;

            //this checks if we need to call tick or render yet or not.
            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                //Below just checks how many times tick and render are being called, to confirm the code works
                //This reset happens when the timer reaches 1 billion nanoseconds
                ticks = 0;
                timer = 0;
            }
            toolkit.sync();
        }
        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getOutfit(){
        return outfit;
    }

    public void setOutfit(String outfit){
        this.outfit = outfit;
    }

    public synchronized void start(){
        //When we call start, we need to check if the game is previously running.
        if(running)
            return;
        //the below steps will start a new thread and call the run method.
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        //We have to throw a try and catch statement around the thread joining, else an error is thrown.
        //Joining the thread is when the thread ends.
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    public ArrayList<String[]> getScores(){
        ArrayList<String[]> scores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] score = line.split(" ", 2);
                scores.add(score);
            }
        }
        catch(IOException e) {}
        
        return scores;
    }

    public void incScore(int amount){
        score += amount;
    }

    public void changeSound(String file){
        //if the it is already that music, don't change it
        if (file.equals(music)){
            return;
        }
        music = file;
        stopSound();
        soundTimer = new Timer(500, new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent arg0) {
                playSound(file);
            }
        });
        soundTimer.setRepeats(false);
        soundTimer.start();
    }
    
    public void playSound(String file) {
        soundThread = new Thread(new Runnable(){
        
            @Override
            public void run() {
                try {
                    File f = new File("../res/" + file + ".wav");
                    Media hit = new Media(f.toURI().toString());
                    mediaPlayer = new MediaPlayer(hit);
                    mediaPlayer.play();
                    mediaPlayer.setOnEndOfMedia(new Runnable() {
                        public void run() {
                          mediaPlayer.seek(Duration.ZERO);
                        }
                    });
                } catch(Exception ex) {
                }
            }
        });
        soundThread.start();
    }

    public void stopSound() {
        mediaPlayer.stop();
    }

}