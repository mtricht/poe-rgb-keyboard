package com.swordbeta.exilergb.Observers.Keyboards.Coolermaster;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;

public interface Coolermaster extends Library {
    Coolermaster INSTANCE = (Coolermaster)
            Native.load(System.getProperty("user.dir") + "\\src\\main\\resources\\Coolermaster.dll", Coolermaster.class);

    boolean EnableLedControl(boolean bEnable, int devIndex);
    void SetControlDevice(int devIndex);
    boolean IsDevicePlug(int devIndex);
    boolean SetFullLedColor(byte r, byte g, byte b, int devIndex);
}