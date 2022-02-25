package client.mods;

import client.Client;
import client.hud.impl.HudMod;

public class MemoryUsage extends HudMod
{
    public MemoryUsage()
    {
        super("MemoryUsage", 0, 40);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ Memory ] 100%");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    public void draw()
    {
        Runtime runtime = Runtime.getRuntime();
        String s = Client.Color0 + "[ " + Client.ColorM + "Memory" + Client.Color0 + " ] " + Client.ColorW + (runtime.totalMemory() - runtime.freeMemory()) * 100L / runtime.maxMemory() + "% ";
        this.fr.drawStringWithShadow(s, (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Memory" + Client.Color0 + " ] " + Client.ColorW + "100%", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
