package com.swordbeta.exilergb;

import com.swordbeta.exilergb.Observers.ConsoleObserver;
import com.swordbeta.exilergb.Observers.Keyboards.Coolermaster.MasterKeysProLRGBKeyboard;
import com.swordbeta.exilergb.Player.*;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws AWTException {
        Player player = new Player();
        ConsoleObserver consoleObserver = new ConsoleObserver(player);
        player.addObserver(consoleObserver);
        MasterKeysProLRGBKeyboard coolermasterMasterKeysProLRGBKeyboard = new MasterKeysProLRGBKeyboard(player);
        player.addObserver(coolermasterMasterKeysProLRGBKeyboard);
        Thread healthThread = new Thread(new HealthWatcher(player));
        healthThread.start();
        Thread manaThread = new Thread(new ManaWatcher(player));
        manaThread.start();
    }

}
