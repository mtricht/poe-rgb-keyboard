package com.swordbeta.exilergb.Observers;

import com.swordbeta.exilergb.Player.Player;

import java.util.Observer;

abstract public class AbstractPlayerObserver implements Observer {

    protected Player player;

    public AbstractPlayerObserver(Player player) {
        this.player = player;
    }

}
