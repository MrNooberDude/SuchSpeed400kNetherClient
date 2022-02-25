package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;

public class ToggleSprint extends HudMod
{
    public boolean flyBoost = false;
    public float flyBoostFactor = 1.0F;
    public int keyHoldTicks = 7;
    public boolean shiftToggled = false;

    public ToggleSprint()
    {
        super("ToggleSprint", 465, 340);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[Sprinting (Key Toggled)]");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    public void draw()
    {
        String s = Minecraft.thePlayer.movementInput.getDisplayText();
        this.fr.drawStringWithShadow(s, (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[" + Client.ColorM + "Sprinting" + Client.ColorW + " (Key Toggled)" + Client.Color0 + "]", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
}
