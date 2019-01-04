package com.swordbeta.exilergb.Player;

import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ManaWatcher extends Watcher {

    private Rectangle rectangle;

    public ManaWatcher(Player player) throws AWTException {
        super(player);
        this.rectangle = new Rectangle(1720, 810, 200, 50);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            BufferedImage manaImage;
            try {
                manaImage = captureScreen(rectangle);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            String[] mana;
            try {
                mana = this.tesseract.doOCR(manaImage).split("\n");
            } catch (TesseractException e) {
                e.printStackTrace();
                continue;
            }
            if (mana.length == 2) {
                mana = mana[1].split("/");
            }
            if (mana.length != 2 || mana[0].equals("") || mana[1].equals("")) {
                continue;
            }
            int currentMana = this.getValue(mana[0]);
            int totalMana = this.getValue(mana[1]);
            this.player.setMana(new CurrentTotalPair(currentMana, totalMana));
            this.player.notifyObservers();
        }
    }
}
