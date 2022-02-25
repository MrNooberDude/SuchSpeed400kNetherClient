package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.src.Config;

public class PackDisplay extends HudMod
{
    public PackDisplay()
    {
        super("PackDisplay", 0, 60);
    }

    public void draw()
    {
        String s = "";

        if (!Config.getResourcePackNames().equalsIgnoreCase("default"))
        {
            s = Config.getResourcePackNames().split(",")[Config.getResourcePacks().length - 1];
        }
        else
        {
            s = "default";
        }

        if (!Config.getResourcePackNames().equals("default"))
        {
            this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Pack" + Client.Color0 + " : " + Client.ColorW + s + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        }
        else
        {
            this.fr.drawStringWithShadow("", (float)this.getX(), (float)this.getY(), -1);
        }

        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Pack" + Client.Color0 + " : " + Client.ColorW + "Default" + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ Pack : Default ]");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }
}
