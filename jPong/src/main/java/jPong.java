import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class jPong {

    static MyJPanel panel = new MyJPanel();

    public static void main(String[] a) {
        JFrame frame = new JFrame("jPong");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPos(frame);
        frame.setVisible(true);
        Timer timer = new Timer();
        timer.schedule(new repaintScreen(), 0, 20);
    }

    static class repaintScreen extends TimerTask {
        public void run() {
            panel.repaint();
        }
    }


    private static void setPos(JFrame frameInput) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        frameInput.setBounds((int) Math.round(width / 2 - 300 / 2), (int) Math.round(height / 2 - 400 / 2), 300, 400);
    }

}
