package client.mods;

import client.hud.impl.HudMod;

public class FullBright extends HudMod
{
    public FullBright()
    {
        super("Full Bright", 100000, 100000);
    }

    public int getWidth()
    {
        return 0;
    }

    public int getHeight()
    {
        return 0;
    }

    public void onEnable()
    {
        this.mc.gameSettings.gammaSetting = 100.0F;
    }

    public void onDisable()
    {
        this.mc.gameSettings.gammaSetting = 1.0F;
    }
}
