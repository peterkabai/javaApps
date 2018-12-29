import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MyJPanel extends JPanel implements KeyListener {

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private int ovalX = 100;
    private int ovalY = 100;
    private int ballSpeedX = 3;
    private int ballSpeedY = 2;
    private int xPos = 20;
    private int xPosAI = 100;

    @Override
    public void paintComponent(Graphics graphic) {

        // get actual window width
        int panelWidth = this.getWidth();
        int panelHeight = (int) this.getSize().getHeight();

        // do things if the arrow keys are pressed
        int moveAmount = 3;
        int width = 50;
        if (leftPressed) {
            if (!isOutOfBoundsX((xPos - moveAmount), width, panelWidth)) {
                xPos -= moveAmount;
            }
        }
        if (rightPressed) {
            if (!isOutOfBoundsX((xPos + moveAmount), width, panelWidth)) {
                xPos += moveAmount;
            }
        }

        // move the AI paddle
        int diameter = 14;
        int aiSpeed = 3;
        if (ovalX + diameter / 2 > xPosAI + width / 2) {
            xPosAI += aiSpeed;
        } else {
            xPosAI -= aiSpeed;
        }

        // make the ball bounce off the side walls
        if (isOutOfBoundsX(ovalX, diameter, panelWidth)) {
            ballSpeedX = ballSpeedX * (-1);
        }

        // bounce off player paddle
        int height = 5;
        String bouncePlayer = circleOverlapsRectangle(xPos, panelHeight - height, width, height, ovalX, ovalY, diameter);
        if (bouncePlayer.equals("normal")) {
            ballSpeedY = ballSpeedY * (-1);
        } else if (bouncePlayer.equals("corner")) {
            ballSpeedY = ballSpeedY * (-1);
            ballSpeedX = ballSpeedX * (-1);
        }

        // bounce off AI paddle
        String bounceAI = circleOverlapsRectangle(xPosAI, 0, width, height, ovalX, ovalY, diameter);
        if (bounceAI.equals("normal")) {
            ballSpeedY = ballSpeedY * (-1);
        } else if (bounceAI.equals("corner")) {
            ballSpeedY = ballSpeedY * (-1);
            ballSpeedX = ballSpeedX * (-1);
        }

        // change ball position
        ovalX = ovalX + ballSpeedX;
        ovalY = ovalY + ballSpeedY;

        // resets the ball if it goes off the screen
        if (isOffScreen(ovalX, ovalY, diameter, diameter)) {
            ovalX = 100;
            ovalY = 100;
        }

        // draws the various elements
        String resources = System.getProperty("user.dir") + "/src/main/resources/";
        graphic.drawImage(getImage(resources + "background.png"), 0, 0, panelWidth, panelHeight, null);
        graphic.drawImage(getImage(resources + "paddle.png"), xPos, panelHeight - height, width, height, null);
        graphic.drawImage(getImage(resources + "paddle.png"), xPosAI, 0, width, height, null);
        graphic.drawImage(getImage(resources + "ball.png"), ovalX, ovalY, diameter, diameter, null);
    }

    private BufferedImage getImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            System.out.println("ERROR: image file not found");
        }
        return null;
    }

    MyJPanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void keyTyped(KeyEvent e) { }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
    }

    private String circleOverlapsRectangle(int xPos, int yPos, int width, int height, int ovalX, int ovalY, int diameter) {

        // variables for the ball and the sides of the rectangle
        int radius = diameter / 2;
        int centerX = ovalX + radius;
        int centerY = ovalY + radius;
        int bottom = yPos + height;
        int right = xPos + width;

        // if the corners of the ball contact the rectangle
        if ((ovalX + diameter >= xPos && ovalX <= right) && (ovalY + diameter >= yPos && ovalY <= bottom)) {

            // at top right corner
            if ((centerX > right) && (centerY < yPos)) {
                double distance = Math.sqrt(Math.pow(yPos - centerY, 2) + Math.pow(centerX - right, 2));
                if (distance <= radius) {
                    return "corner";
                } else {
                    return "no";
                }
            }

            // at bottom right corner
            else if ((centerX > right) && (centerY > bottom)) {
                double distance = Math.sqrt(Math.pow(centerY - bottom, 2) + Math.pow(centerX - right, 2));
                if (distance <= radius) {
                    return "corner";
                } else {
                    return "no";
                }
            }

            // at top left corner
            else if ((centerX < xPos) && (centerY < yPos)) {
                double distance = Math.sqrt(Math.pow(yPos - centerY, 2) + Math.pow(xPos - centerX, 2));
                if (distance <= radius) {
                    return "corner";
                } else {
                    return "no";
                }
            }

            // at bottom left corner
            else if ((centerX < xPos) && (centerY > bottom)) {
                double distance = Math.sqrt(Math.pow(centerY - bottom, 2) + Math.pow(xPos - centerX, 2));
                if (distance <= radius) {
                    return "corner";
                } else {
                    return "no";
                }
            }
            return "normal";
        } else {
            // if they do not overlap
            return "no";
        }
    }

    private boolean isOutOfBoundsX(int xPos, int width, int upper) {
        int right = xPos + width;
        return xPos < 0 || right > upper;
    }

    private boolean isOffScreen(int xPos, int yPos, int width, int height) {
        int bottom = yPos + height;
        int right = xPos + width;
        return bottom < 0 || right < 0 || xPos > this.getWidth() || yPos > (int) this.getSize().getHeight();
    }
}
