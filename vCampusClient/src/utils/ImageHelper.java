package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHelper {

    public static ImageIcon resizeImage(Image image, int maxWidth, int maxHeight) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);
        double scaleFactor = 1.0;

        if (originalWidth > maxWidth || originalHeight > maxHeight) {
            double widthScaleFactor = (double) maxWidth / originalWidth;
            double heightScaleFactor = (double) maxHeight / originalHeight;
            scaleFactor = Math.min(widthScaleFactor, heightScaleFactor);
        }

        int scaledWidth = (int) (originalWidth * scaleFactor);
        int scaledHeight = (int) (originalHeight * scaleFactor);

        // Create a new scaled image
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        // Create a BufferedImage and enable anti-aliasing
        BufferedImage bufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the scaled image
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }
}