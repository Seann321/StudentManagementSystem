import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player {

    BufferedImage straightBird = null;
    BufferedImage downBird = null;
    BufferedImage upBird = null;
    BufferedImage currentBird;
    public static final int SIZE = 50;
    private static int Speed = 0;
    Timer playerTick;
    Timer slowDown;

    public Rectangle playerBounds = new Rectangle(SIZE * 3, Display.WIDTH / 2, SIZE, SIZE);

    public Player() {
        try {
            straightBird = ImageIO.read(new File("C:\\Users\\info\\IdeaProjects\\StudentManagementSystem\\src\\assets\\Straight Bird.png"));
            downBird = ImageIO.read(new File("C:\\Users\\info\\IdeaProjects\\StudentManagementSystem\\src\\assets\\Down Bird.png"));
            upBird = ImageIO.read(new File("C:\\Users\\info\\IdeaProjects\\StudentManagementSystem\\src\\assets\\Up Bird.png"));
        } catch (
                IOException e) {
        }
        currentBird = straightBird;
        playerTick = new Timer();
        playerTick.schedule(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, 1);
    }

    public void tick() {
        if (playerBounds.y < 0) {
            playerBounds.y = 0;
        }

        if (playerBounds.y > Display.HEIGHT - SIZE) {
            playerBounds.y = Display.HEIGHT - SIZE;
        }
        if (Main.KeyManager.up) {
            jump();
        }
        if(Speed < -1){
            currentBird = upBird;
        }else if(Speed == 5){
            currentBird = downBird;
        }else{
            currentBird = straightBird;
        }
        playerBounds.y += Speed;
    }

    private void jump() {
        Speed -=2;
        slowDown = new Timer();
        slowDown.schedule(new TimerTask() {
            @Override
            public void run() {
                Speed+=1;
                if(Speed > 5){
                    Speed = 5;
                    this.cancel();
                }
            }
        }, 200, 100);
    }

    public void render(Graphics g) {
        g.setColor(new Color(200, 100, 0));
        g.drawImage(currentBird, playerBounds.x, playerBounds.y, playerBounds.width, playerBounds.height, null);
    }

}
