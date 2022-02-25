package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;

public class DirectionMod extends HudMod
{
    public DirectionMod()
    {
        super("Direction Mod", 0, 20);
    }

    public void draw()
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Direction" + Client.Color0 + " ] " + Client.ColorW + Minecraft.thePlayer.getHorizontalFacing(), (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Direction" + Client.Color0 + " ] " + Client.ColorW + "east", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ Direction ] east");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }
}
