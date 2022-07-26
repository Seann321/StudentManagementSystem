import javax.swing.*;
import java.awt.*;

public class Display {

    public static Canvas Canvas;
    public static JFrame frame;
    public static final int WIDTH = 1500, HEIGHT = 800;

    public Display(){
        Canvas = new Canvas();
        Canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        Canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        Canvas.setFocusable(false);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(Canvas);
        Canvas.setBackground(new Color(10,10,100));
        frame.pack();
        frame.setVisible(true);
    }

}
