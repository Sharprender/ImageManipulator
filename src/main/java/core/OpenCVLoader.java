package core;

import org.opencv.core.Core;

/**
 * @author Progmat aka Sharprender
 * @version 1.0
 */
public class OpenCVLoader {
    public static void init() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}
