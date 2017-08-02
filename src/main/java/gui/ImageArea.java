package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Utils.*;

/**
 * @author Progmat aka Sharprender
 * @version 1.0
 */
public class ImageArea extends JPanel implements KeyListener {

    private Color backgroundColor = Color.BLACK;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(backgroundColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        g.setColor(backgroundColor);

        g.setColor(invertColor(backgroundColor));
        Font font = new Font("Lucida Console", Font.PLAIN, 12);
        FontMetrics metrics = getFontMetrics(font);
        int d = metrics.getAscent();
        g.setFont(font);
        g.drawString("backgroundColor: " + colorToRGBString(backgroundColor) + "; " + colorToHSBString(backgroundColor), 0, d);
        g.drawString("textColor: " + colorToRGBString(invertColor(backgroundColor)) + "; " + colorToHSBString(invertColor(backgroundColor)), 0, 2 * d + 5);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                backgroundColor = increaseBrightness(backgroundColor);
                break;
            case KeyEvent.VK_DOWN:
                backgroundColor = decreaseBrightness(backgroundColor);
                break;
            case KeyEvent.VK_LEFT:
                backgroundColor = decreaseSaturation(backgroundColor);
                break;
            case KeyEvent.VK_RIGHT:
                backgroundColor = increaseSaturation(backgroundColor);
                break;
            case KeyEvent.VK_PAGE_UP:
                backgroundColor = increaseHue(backgroundColor);
                break;
            case KeyEvent.VK_PAGE_DOWN:
                backgroundColor = decreaseHue(backgroundColor);
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
