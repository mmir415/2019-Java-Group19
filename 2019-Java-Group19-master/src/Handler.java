public class Handler {

    //as good a time as any to explain what a Handler is. It just allows you to pass along a bunch of variables to
    //whatever you want to. It connects to everything.
    private Game game;
    private World world;

    public Handler(Game game){
        this.game = game;
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}