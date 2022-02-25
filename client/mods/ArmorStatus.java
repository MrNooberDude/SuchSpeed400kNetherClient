package client.mods;

import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ArmorStatus extends HudMod
{
    public ArmorStatus()
    {
        super("ArmorStatus", 617, 287);
    }

    public void draw()
    {
        for (int i = 0; i < Minecraft.thePlayer.inventory.armorInventory.length; ++i)
        {
            ItemStack itemstack = Minecraft.thePlayer.inventory.armorInventory[i];
            this.renderItemStack(i, itemstack);
        }

        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.renderItemStack(3, new ItemStack(Items.diamond_helmet));
        this.renderItemStack(2, new ItemStack(Items.diamond_chestplate));
        this.renderItemStack(1, new ItemStack(Items.diamond_leggings));
        this.renderItemStack(0, new ItemStack(Items.diamond_boots));
        super.renderDummy(mouseX, mouseY);
    }

    public void renderItemStack(int i, ItemStack is)
    {
        if (is != null)
        {
            GL11.glPushMatrix();
            int i = -16 * i + 48;

            if (is.getItem().isDamageable())
            {
                double d0 = (double)(is.getMaxDamage() - is.getItemDamage()) / (double)is.getMaxDamage() * 100.0D;
                this.fr.drawStringWithShadow(String.format("%.2f%%", new Object[] {Double.valueOf(d0)}), (float)(this.getX() + 20), (float)(this.getY() + i + 5), -1);
            }

            RenderHelper.enableGUIStandardItemLighting();
            this.mc.getRenderItem().renderItemAndEffectIntoGUI(is, this.getX(), this.getY() + i);
            GL11.glPopMatrix();
        }
    }

    public int getWidth()
    {
        return 64;
    }

    public int getHeight()
    {
        return 64;
    }
}
