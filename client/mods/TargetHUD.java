package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;

public class TargetHUD extends HudMod
{
    EntityLivingBase target;

    public TargetHUD()
    {
        super("Target HUD", 0, 110);
    }

    public void draw()
    {
        this.renderTargetHud();
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow("TargetHUD ", (float)(this.getX() + 2), (float)(this.getY() + 2), Client.TargetHUD);
        this.fr.drawStringWithShadow("20 \u00a7c\u2764", (float)(this.getX() + 2), (float)(this.getY() + 2 + this.fr.FONT_HEIGHT), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("TargetHUD ");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT * 2 + 4;
    }

    private void renderTargetHud()
    {
        if (!(this.mc.pointedEntity instanceof EntityItemFrame))
        {
            this.target = (EntityLivingBase)this.mc.pointedEntity;

            if (this.target != null)
            {
                this.fr.drawStringWithShadow(this.target.getName(), (float)(this.getX() + 2), (float)(this.getY() + 2), Client.TargetHUD);
                this.fr.drawStringWithShadow(String.valueOf((int)this.target.getHealth()) + "\u00a7c \u2764", (float)(this.getX() + 2), (float)(this.getY() + 2 + this.fr.FONT_HEIGHT), -1);
            }
        }
    }
}
