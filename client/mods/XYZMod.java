package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;

public class XYZMod extends HudMod
{
    public XYZMod()
    {
        super("XYZ Display", 0, 0);
    }

    public void draw()
    {
        this.fr.drawStringWithShadow(this.getXYZString(), (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "XYZ" + Client.Color0 + " ] " + Client.ColorW + "100 / 50 / 100", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public String getXYZString()
    {
        return String.format(Client.Color0 + "[ " + Client.ColorM + "XYZ" + Client.Color0 + " ] " + Client.ColorW + (int)Minecraft.thePlayer.posX + " / " + (int)Minecraft.thePlayer.posY + " / " + (int)Minecraft.thePlayer.posZ, new Object[0]);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ XYZ ] 100 / 50 / 100");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }
}
