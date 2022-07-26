import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Map {

    BufferedImage tubeTexture = null;
    BufferedImage background = null;
    public ArrayList<Rectangle> rectangles = new ArrayList<>();
    private Random random = new Random();
    private int recWidth = 75, speed = 3;
    private boolean rendering = false;
    public static int Score = 0;
    public Timer rectangleTick;

    public Map() {
        Score = 0;
        try {
            tubeTexture = ImageIO.read(new File("C:\\Users\\info\\IdeaProjects\\StudentManagementSystem\\src\\assets\\Legally not a tube.png"));
        } catch (
                IOException e) {
        }
        try {
            background = ImageIO.read(new File("C:\\Users\\info\\IdeaProjects\\StudentManagementSystem\\src\\assets\\Background.png"));
        } catch (
                IOException e) {
        }
        createNewRectangle();
        rectangleTick = new Timer();
        rectangleTick.schedule(new TimerTask() {
            @Override
            public void run() {
                moveRectangle();
                destroyRectangle();
            }
        }, 0, 1);

        Timer speedUp = new Timer();
        speedUp.schedule(new TimerTask() {
            @Override
            public void run() {
                speed++;
                if (speed >= 8) {
                    speedUp.cancel();
                }
            }
        }, 0, 5000);
    }

    public void renderMap(Graphics g) {
        g.drawImage(background, 0, 0, Display.WIDTH, Display.HEIGHT, null);
        g.setColor(new Color(10, 200, 100));
        rendering = true;
        for (Rectangle r : rectangles) {
            if (r.y == 0) {
                g.drawImage(tubeTexture, r.x, r.y, r.width, r.height, null);
            } else {
                g.drawImage(tubeTexture, r.x, r.y + r.height, r.width, -r.height, null);
            }
        }
        rendering = false;
    }

    private void createNewRectangle() {
        int gap = random.nextInt(Display.HEIGHT);
        if (gap < Player.SIZE * 4) {
            gap = Player.SIZE * 4;
        }
        Rectangle top = new Rectangle(Display.WIDTH, 0, recWidth, gap - Player.SIZE * 4);
        Rectangle bottom = new Rectangle(Display.WIDTH, gap, recWidth, Display.HEIGHT);
        rectangles.add(top);
        rectangles.add(bottom);
    }

    private void destroyRectangle() {
        if (rendering)
            return;
        int before = rectangles.size();
        rectangles.removeIf(r -> r.x < -recWidth);
        int after = rectangles.size();
        if (before != after) {
            Score++;
        }
        if (rectangles.size() == 0) {
            createNewRectangle();
        }
        if (rectangles.size() == 2 && rectangles.get(0).x < recWidth * 8) {
            createNewRectangle();
        }
    }

    private void moveRectangle() {
        for (Rectangle r : rectangles) {
            r.x -= speed;
        }
    }

}
