package com.jtc.Model.church.churchUi;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageConversion {

    public static BufferedImage convertToBufferedImage(BufferedImage img) {
        // Create a buffered image with transparency
        BufferedImage bi = new BufferedImage(
                150, 120,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bi.createGraphics();

        graphics2D.drawImage(img, 0, 0, 150,120, null);

        graphics2D.dispose();

        return bi;
    }
  /*  BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;*/


}
