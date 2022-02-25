package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;

public class PingMod extends HudMod
{
    public PingMod()
    {
        super("Ping Display", 0, 80);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ Ping ] 10ms");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    public void draw()
    {
        if (this.mc.getNetHandler() != null && Minecraft.thePlayer != null && this.mc.getNetHandler().getPlayerInfo(Minecraft.thePlayer.getUniqueID()) != null)
        {
            this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Ping" + Client.Color0 + " ] " + Client.ColorW + this.mc.getNetHandler().getPlayerInfo(Minecraft.thePlayer.getUniqueID()).getResponseTime() + "ms", (float)this.getX(), (float)this.getY(), -1);
        }

        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Ping" + Client.Color0 + " ] " + Client.ColorW + "10ms", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
