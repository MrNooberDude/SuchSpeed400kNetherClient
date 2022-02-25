package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;

public class ServerIPMod extends HudMod
{
    public ServerIPMod()
    {
        super("ServerIP Mod", 0, 340);
    }

    public void draw()
    {
        String s = "Singleplayer";

        if (Minecraft.getMinecraft().getCurrentServerData() != null)
        {
            s = Minecraft.getMinecraft().getCurrentServerData().serverIP;
        }

        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "IP" + Client.Color0 + " : " + Client.ColorW + s + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "IP" + Client.Color0 + " : " + Client.ColorW + "hypixel.net" + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ IP : hypixel.net ]");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }
}
