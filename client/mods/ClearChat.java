package client.mods;

import client.hud.impl.HudMod;

public class ClearChat extends HudMod
{
    public ClearChat()
    {
        super("Clear Chat", 100000, 100000);
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
