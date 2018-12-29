import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

class MyJButton extends JButton {
    MyJButton() {
        new JButton();
    }

    void useImage(String url) {
        try {
            Image img = ImageIO.read(new File(url));
            this.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("ERROR: image file not found");
        }
    }
}