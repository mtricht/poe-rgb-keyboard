package com.swordbeta.exilergb.Player;

import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HealthWatcher extends Watcher {

    private Rectangle rectangle;

    public HealthWatcher(Player player) throws AWTException {
        super(player);
        this.rectangle = new Rectangle(40, 810, 200, 50);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            BufferedImage healthImage;
            try {
                healthImage = this.captureScreen(rectangle);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            String[] healthResult;
            try {
                healthResult = this.tesseract.doOCR(healthImage).split("\n");
            } catch (TesseractException e) {
                e.printStackTrace();
                continue;
            }
            if (healthResult.length != 2) {
                continue;
            }
            String[] health = healthResult[0].split("/");
            String[] shield = healthResult[1].split("/");
            if (health.length != 2 || shield.length != 2 || health[0].equals("") || health[1].equals("")
                || shield[0].equals("") || shield[1].equals("")) {
                continue;
            }
            int currentHealth = this.getValue(health[0]);
            int totalHealth = this.getValue(health[1]);
            int currentShield = this.getValue(shield[0]);
            int totalShield = this.getValue(shield[1]);
            this.player.setHealth(new CurrentTotalPair(currentHealth, totalHealth));
            this.player.setShield(new CurrentTotalPair(currentShield, totalShield));
            this.player.notifyObservers();
        }
    }
}
