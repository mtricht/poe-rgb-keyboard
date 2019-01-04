package com.swordbeta.exilergb.Player;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

abstract public class Watcher implements Runnable {

    private Robot robot;
    protected ITesseract tesseract;
    protected Player player;

    public Watcher(Player player) throws AWTException {
        this.player = player;
        this.robot = new Robot();
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(System.getProperty("user.dir") + "\\src\\main\\resources");
        tesseract.setLanguage("Fontin");
        tesseract.setPageSegMode(6); // Assume a single uniform block of text.
        this.tesseract = tesseract;
    }

    protected BufferedImage captureScreen(Rectangle rectangle) throws IOException {
        BufferedImage image = this.robot.createScreenCapture(rectangle);
        onlyWhite(image);
        return toPngBufferedImage(image);
    }

    protected void onlyWhite(BufferedImage image) {
        int black = new Color(0, 0, 0).getRGB();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int diffBR = Math.abs(color.getBlue() - color.getRed());
                int diffBG = Math.abs(color.getBlue() - color.getGreen());
                int diffRG = Math.abs(color.getRed() - color.getGreen());
                if (diffBR <= 5 && diffBG <= 5 && diffRG <= 5) {
                    continue;
                }
                image.setRGB(x, y, black);
            }
        }
    }

    protected BufferedImage toPngBufferedImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
    }

    protected int getValue(String text) {
        return Integer.parseInt(text.replaceAll(".*? ", "").replaceAll("[^0-9]", ""));
    }

    abstract public void run();
}
