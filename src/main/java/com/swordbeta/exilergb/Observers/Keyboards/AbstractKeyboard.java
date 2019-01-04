package com.swordbeta.exilergb.Observers.Keyboards;

import com.swordbeta.exilergb.Observers.AbstractPlayerObserver;
import com.swordbeta.exilergb.Player.Player;

import java.awt.*;
import java.util.*;

abstract public class AbstractKeyboard extends AbstractPlayerObserver {

    public AbstractKeyboard(Player player) {
        super(player);
    }

    @Override
    public void update(Observable o, Object arg) {
        double manaPercentage = this.player.getMana().getCurrent()/(double)this.player.getMana().getTotal() * 100;
        double healthPercentage = this.player.getHealth().getCurrent()/(double)this.player.getHealth().getTotal() * 100;
        double shieldPercentage = this.player.getShield().getCurrent()/(double)this.player.getShield().getTotal() * 100;
        int rows = this.getKeyboardRows();
        int columns = this.getKeyboardColumns();
        double percentagePerRow = 100/(double) rows;
        double currentPercentage = 100;
        ArrayList<Color[]> matrix = new ArrayList<>();
        for (int row = 0; row <= rows; row++) {
            Color[] colors = new Color[columns];
            if (shieldPercentage >= currentPercentage) {
               Arrays.fill(colors, new Color(0, 0, 255));
           } else if (healthPercentage >= currentPercentage) {
                Arrays.fill(colors, new Color(255, 0, 0));
            } else {
                Arrays.fill(colors, new Color(0, 0, 0));
            }
            matrix.add(row, colors);
           currentPercentage -= percentagePerRow;
        }
        this.setColors(matrix);
    }

    abstract protected int getKeyboardRows();
    abstract protected int getKeyboardColumns();
    abstract protected void setColors(ArrayList<Color[]> matrix);

}
