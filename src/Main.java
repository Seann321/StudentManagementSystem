import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main {


    private static Map Map;
    private static Player Player;
    private static BufferStrategy bs;
    private static Graphics g;
    public static KeyManager KeyManager;
    private static Display Display;

    public static void main(String[] args) {
        Display = new Display();
        KeyManager = new KeyManager();
        Display.frame.addKeyListener(KeyManager);
        Map = new Map();
        Player = new Player();
        while(true){
            tick();
            render();
        }
    }

    public static void tick(){
        KeyManager.tick();
        checkLoss();
    }


    private static void checkLoss(){
        for(Rectangle r : Map.rectangles){
            if(r.intersects(Player.playerBounds)){
                Map.rectangleTick.cancel();
                Player.playerTick.cancel();
                JOptionPane.showMessageDialog(null,"Your score was " + Map.Score,"Score",1);
                Map = new Map();
                Player = new Player();
            }
        }
    }

    public static void render() {
        bs = Display.Canvas.getBufferStrategy();
        if (bs == null) {
            Display.Canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, Display.WIDTH, Display.HEIGHT);

        //Everything below is what is drawn on the screen.

        Map.renderMap(g);
        Player.render(g);

        //End Draw
        bs.show();

        g.dispose();
    }
}
