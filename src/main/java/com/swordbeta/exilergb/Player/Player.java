package com.swordbeta.exilergb.Player;

import java.util.Observable;

public class Player extends Observable {

    private CurrentTotalPair mana;
    private CurrentTotalPair health;
    private CurrentTotalPair shield;

    public Player() {
        this.mana = new CurrentTotalPair(0, 0);
        this.health = new CurrentTotalPair(0, 0);
        this.shield = new CurrentTotalPair(0, 0);
    }

    public CurrentTotalPair getMana() {
        return mana;
    }

    public void setMana(CurrentTotalPair mana) {
        this.mana = mana;
        setChanged();
    }

    public CurrentTotalPair getHealth() {
        return health;
    }

    public void setHealth(CurrentTotalPair health) {
        this.health = health;
        setChanged();
    }

    public CurrentTotalPair getShield() {
        return shield;
    }

    public void setShield(CurrentTotalPair shield) {
        this.shield = shield;
        setChanged();
    }

}
