package client.hud;

import client.Client;
import client.gui.clickgui.ClickGUI;
import client.hud.impl.HudMod;
import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class HUDConfigScreen extends GuiScreen
{
    public void initGui()
    {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 2 - 10, 100, 20, "ClickGUI"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawHollowRect(0, 0, this.width - 1, this.height - 1, (new Color(0, 180, 235)).getRGB());

        for (HudMod hudmod : Client.INSTANCE.hudManager.hudMods)
        {
            if (hudmod.isEnabled())
            {
                hudmod.renderDummy(mouseX, mouseY);
            }
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void drawHollowRect(int x, int y, int w, int h, int color)
    {
        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);
        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        super.actionPerformed(button);

        switch (button.id)
        {
            case 1:
                this.mc.displayGuiScreen(new ClickGUI());

            default:
        }
    }
}
