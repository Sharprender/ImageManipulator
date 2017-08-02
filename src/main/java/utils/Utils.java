package utils;


import java.awt.*;

/**
 * @author Progmat
 * @version 1.0
 */
public class Utils {

    public static String colorToRGBString(Color color) {
        return color == null ? "RGB(null, null, null)" : "RGB(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
    }

    public static String colorToHSBString(Color color) {
        if (color == null)
            return "HSB(null, null, null)";

        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return "HSB(" + String.format("%.2f", hsb[0]) + ", " + String.format("%.2f", hsb[1]) + ", " + String.format("%.2f", hsb[2]) + ")";
    }

    public static Color invertColor(Color color) {
        return color == null ? null : new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }

    public static Color increaseBrightness(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float new_b = hsb[2] < 1.0f ? hsb[2] + 0.01f : 1.0f;

        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], new_b > 1.0f ? 1.0f : new_b));
    }

    public static Color decreaseBrightness(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float new_b = hsb[2] > 0.0f ? hsb[2] - 0.01f : 0.0f;

        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], new_b < 0.0f ? 0.0f : new_b));
    }

    public static Color increaseSaturation(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float new_s = hsb[1] < 1.0f ? hsb[1] + 0.01f : 1.0f;

        return new Color(Color.HSBtoRGB(hsb[0], new_s > 1.0f ? 1.0f : new_s, hsb[2]));
    }

    public static Color decreaseSaturation(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float new_s = hsb[1] > 0.0f ? hsb[1] - 0.01f : 0.0f;

        return new Color(Color.HSBtoRGB(hsb[0], new_s < 0.0f ? 0.0f : new_s, hsb[2]));
    }

    public static Color increaseHue(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float new_h = hsb[0] < 1.0f ? hsb[0] + 0.01f : 1.0f;

        return new Color(Color.HSBtoRGB(new_h > 1.0f ? 1.0f : new_h, hsb[1], hsb[2]));
    }

    public static Color decreaseHue(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);

        float new_h = hsb[0] > 0.0f ? hsb[0] - 0.01f : 0.0f;

        return new Color(Color.HSBtoRGB(new_h < 0.0f ? 0.0f : new_h, hsb[1], hsb[2]));
    }
}
