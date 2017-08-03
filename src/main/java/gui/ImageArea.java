package gui;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.Utils.*;

/**
 * @author Progmat aka Sharprender
 * @version 1.0
 */
public class ImageArea extends JPanel implements KeyListener {

    private Color backgroundColor = Color.BLACK;
    private Mat mat = null;
    private BufferedImage image = null;

    public ImageArea(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        this.setPreferredSize(new Dimension(1280, 720));
    }

    public void loadImage(File src) {
        this.mat = Imgcodecs.imread(src.getAbsolutePath());
        this.image = createImage(this.mat);
        if (this.image != null) {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parent != null) {
                parent.getContentPane().setPreferredSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
                parent.pack();
            }
        }
        /*try {
            this.image = ImageIO.read(src);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void unloadImage() {
        this.mat = null;
        this.image = null;

        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parent != null) {
            parent.getContentPane().setPreferredSize(new Dimension(1280, 720));
            parent.pack();
        }

        this.invalidate();
    }

    public static BufferedImage createImage(Mat mat) {
        int type = 0;
        if (mat.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (mat.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        } else {
            return null;
        }

        BufferedImage image = new BufferedImage(mat.width(), mat.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        mat.get(0, 0, data);

        return image;
    }

    public static String getRelativePath(String src) {
        System.out.println(src);
        Path pathAbsolute = Paths.get(src);
        Path pathBase = Paths.get("../src/main/resources/");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return pathRelative.toString();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(backgroundColor);
//        g.fillRect(0, 0, this.getWidth(), this.getHeight());
//
//        g.setColor(backgroundColor);
//
//        g.setColor(invertColor(backgroundColor));
//        Font font = new Font("Lucida Console", Font.PLAIN, 12);
//        FontMetrics metrics = getFontMetrics(font);
//        int d = metrics.getAscent();
//        g.setFont(font);
//        g.drawString("backgroundColor: " + colorToRGBString(backgroundColor) + "; " + colorToHSBString(backgroundColor), 0, d);
//        g.drawString("textColor: " + colorToRGBString(invertColor(backgroundColor)) + "; " + colorToHSBString(invertColor(backgroundColor)), 0, 2 * d + 5);

        if (image != null) {
            g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
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
            case KeyEvent.VK_L:
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("../src/main/resources/images/"));
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    loadImage(selectedFile);
                }
                break;
            case KeyEvent.VK_U:
                unloadImage();
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
