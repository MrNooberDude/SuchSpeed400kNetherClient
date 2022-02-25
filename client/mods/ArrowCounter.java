package client.mods;

import client.hud.impl.HudMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArrowCounter extends HudMod
{
    public ArrowCounter()
    {
        super("ArrowCounter", 433, 327);
    }

    public void draw()
    {
        if (this.getRemainingArrows() < 1)
        {
            this.fr.drawStringWithShadow(String.valueOf(this.getRemainingArrows()), (float)(this.getX() + 8), (float)(this.getY() + 15), Color.RED.getRGB());
        }
        else
        {
            this.fr.drawStringWithShadow(String.valueOf(this.getRemainingArrows()), (float)(this.getX() + 8), (float)(this.getY() + 15), Color.WHITE.getRGB());
        }

        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.arrow), this.getX() + 3, this.getY() - 1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        if (this.getRemainingArrows() < 1)
        {
            this.fr.drawStringWithShadow(String.valueOf(this.getRemainingArrows()), (float)(this.getX() + 8), (float)(this.getY() + 15), Color.RED.getRGB());
        }
        else
        {
            this.fr.drawStringWithShadow(String.valueOf(this.getRemainingArrows()), (float)(this.getX() + 8), (float)(this.getY() + 15), Color.WHITE.getRGB());
        }

        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.arrow), this.getX() + 3, this.getY() - 1);
        super.renderDummy(mouseX, mouseY);
    }

    private int getRemainingArrows()
    {
        int i = 0;

        for (ItemStack itemstack : Minecraft.thePlayer.inventory.mainInventory)
        {
            if (itemstack != null && itemstack.getItem().equals(Items.arrow))
            {
                i += itemstack.stackSize;
            }
        }

        return i;
    }

    public int getWidth()
    {
        return 23;
    }

    public int getHeight()
    {
        return 23;
    }
}
