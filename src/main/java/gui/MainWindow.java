package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 * @author Progmat aka Sharprender
 * @version 1.0
 */
public class MainWindow extends JFrame {

    private ImageArea imageArea;

    public MainWindow() {
        super();
        this.setTitle("ImageManipulator");

        this.setLayout(new BorderLayout());

        this.imageArea = new ImageArea(true);
        this.add(this.imageArea, BorderLayout.CENTER);

        this.addKeyListener(this.imageArea);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.getContentPane().setPreferredSize(new Dimension(1280, 720));
        this.pack();
        this.setVisible(true);
    }
}
