package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMod extends HudMod
{
    public TimeMod()
    {
        super("Time Display", 0, 10);
    }

    public void draw()
    {
        String s = "hh : mm a";
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
        String s1 = simpledateformat.format(new Date());
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Time" + Client.Color0 + " ] " + Client.ColorW + s1, (float)(this.getX() + 1), (float)(this.getY() + 1), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        String s = "hh : mm a";
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
        String s1 = simpledateformat.format(new Date());
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Time" + Client.Color0 + " ] " + Client.ColorW + s1, (float)(this.getX() + 1), (float)(this.getY() + 1), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        String s = "hh : mm a";
        SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
        String s1 = simpledateformat.format(new Date());
        return this.fr.getStringWidth(Client.Color0 + "[ " + Client.ColorM + "Time" + Client.Color0 + " ] " + Client.ColorW + s1);
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }
}
