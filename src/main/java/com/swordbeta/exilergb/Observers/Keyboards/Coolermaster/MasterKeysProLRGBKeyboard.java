package com.swordbeta.exilergb.Observers.Keyboards.Coolermaster;

import com.swordbeta.exilergb.Observers.Keyboards.AbstractKeyboard;
import com.swordbeta.exilergb.Player.Player;

import java.awt.*;
import java.util.ArrayList;

public class MasterKeysProLRGBKeyboard extends AbstractKeyboard {

    private Coolermaster coolermaster;

    private boolean initiated = false;

    public MasterKeysProLRGBKeyboard(Player player) {
        super(player);
        this.coolermaster = Coolermaster.INSTANCE;
    }

    @Override
    protected int getKeyboardRows() {
        return 6;
    }

    @Override
    protected int getKeyboardColumns() {
        return 21;
    }

    @Override
    protected void setColors(ArrayList<Color[]> matrix) {
        if (!Coolermaster.INSTANCE.IsDevicePlug(0)) {
            return;
        }
        if (!this.initiated) {
            Coolermaster.INSTANCE.EnableLedControl(true, 0);
            Coolermaster.INSTANCE.SetFullLedColor((byte) 255, (byte) 0 ,(byte) 0, 0);
            this.initiated = true;
        }
    }

}
