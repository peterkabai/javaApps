import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;

public class dice {

    private static MyJButton dice = new MyJButton();
    private static String resources = System.getProperty("user.dir") + "/src/main/resources/";

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "Dice");

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu diceTab = new JMenu("Change number of dice...");
        menuBar.add(diceTab);

        JMenu exitTab = new JMenu("Exit");
        menuBar.add(exitTab);

        dice.setPreferredSize(new Dimension(90, 90));

        panel.add(dice);
        dice.addActionListener(ae -> diceClicked());
        diceClicked();
        setPos(frame);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    private static void diceClicked() {
        Random rand = new Random();
        int roll = rand.nextInt(6) + 1;
        String imagePath = resources + roll + ".png";
        dice.useImage(imagePath);
    }

    private static void setPos(JFrame frameInput) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        frameInput.setBounds(
                (int) Math.round(width / 2 - 100 / 2),
                (int) Math.round(height / 2 - 120 / 2),
                100, 120);
    }

    static class MyJButton extends JButton {
        MyJButton() {
            new JButton();
        }
        void useImage(String url) {
            try {
                Image img = ImageIO.read(new File(url));
                this.setIcon(new ImageIcon(img));
                this.setBorderPainted(false);
                this.setContentAreaFilled(false);
                this.setFocusPainted(false);
                this.setOpaque(false);
            }
            catch (Exception e) {
                System.out.println("ERROR: image file not found");
            }
        }
    }
}