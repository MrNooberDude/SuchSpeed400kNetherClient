package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;

public class FPSMod extends HudMod
{
    public FPSMod()
    {
        super("FPS Display", 0, 90);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ FPS ] 100");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    public void draw()
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "FPS" + Client.Color0 + " ] " + Client.ColorW + Minecraft.getDebugFPS(), (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "FPS" + Client.Color0 + " ] " + Client.ColorW + "100", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
