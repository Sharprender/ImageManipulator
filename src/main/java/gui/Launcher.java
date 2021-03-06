package gui;

import core.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Progmat aka Sharprender
 * @version 1.0
 */
public class Launcher {

    public static void main(String[] args) {
        // init opencv
        OpenCVLoader.init();

        /*Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("mat = " + mat.dump());*/

        new MainWindow();
    }
}
