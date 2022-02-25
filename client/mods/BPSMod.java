package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;

public class BPSMod extends HudMod
{
    static Minecraft mc = Minecraft.getMinecraft();
    private int decimals;

    public BPSMod()
    {
        super("BPS Display", 0, 70);
    }

    public void draw()
    {
        float f = Minecraft.getMinecraft().timer.getTicksPerSecond() * Minecraft.getMinecraft().timer.timerSpeed;
        double d0 = Math.sqrt(Minecraft.thePlayer.motionX * Minecraft.thePlayer.motionX + Minecraft.thePlayer.motionZ * Minecraft.thePlayer.motionZ) * (double)f;
        mc.fontRendererObj.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "BPS" + Client.Color0 + " : " + Client.ColorW + this.getFormatter().format(d0) + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "BPS" + Client.Color0 + " : " + Client.ColorW + "0.00" + Client.Color0 + " ]", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ BPS : 0.00 ]");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    private DecimalFormat getFormatter()
    {
        StringBuilder stringbuilder = new StringBuilder("0.00");

        for (int i = 100; this.decimals > i; ++i)
        {
            stringbuilder.append('0');
        }

        return new DecimalFormat(stringbuilder.toString());
    }
}
