package com.swordbeta.exilergb.Observers;

import com.swordbeta.exilergb.Player.Player;

import java.util.Observable;

public class ConsoleObserver extends AbstractPlayerObserver {

    private long startTime;

    public ConsoleObserver(Player player) {
        super(player);
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.printf(
                "Life: %s/%s Shield: %s/%s Mana: %s/%s Time since last update: %sms\n",
                this.player.getHealth().getCurrent(),
                this.player.getHealth().getTotal(),
                this.player.getShield().getCurrent(),
                this.player.getShield().getTotal(),
                this.player.getMana().getCurrent(),
                this.player.getMana().getTotal(),
                (System.currentTimeMillis() - this.startTime)
        );
        this.startTime = System.currentTimeMillis();
    }
}
