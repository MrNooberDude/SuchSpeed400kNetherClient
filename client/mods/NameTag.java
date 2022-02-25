package client.mods;

import client.hud.impl.HudMod;

public class NameTag extends HudMod
{
    public NameTag()
    {
        super("NameTagMod", 100000, 100000);
    }

    public int getWidth()
    {
        return 0;
    }

    public int getHeight()
    {
        return 0;
    }
}
