package client.mods;

import client.Client;
import client.hud.impl.HudMod;

public class ClientName extends HudMod
{
    public ClientName()
    {
        super("Client Name", 0, 50);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ " + Client.ClientName + " ]");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    public void draw()
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + Client.ClientName + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + Client.ClientName + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
