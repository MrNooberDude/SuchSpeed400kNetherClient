package client.mods;

import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ItemViewer extends HudMod
{
    public ItemViewer()
    {
        super("Item Viewer", 188, 333);
    }

    public int getWidth()
    {
        return 60;
    }

    public int getHeight()
    {
        return 17;
    }

    public void draw()
    {
        ItemStack itemstack = Minecraft.thePlayer.getHeldItem();
        this.renderItemStack(2, itemstack);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.renderItemStack(2, new ItemStack(Items.diamond_sword));
        super.renderDummy(mouseX, mouseY);
    }

    private void renderItemStack(int i, ItemStack is)
    {
        if (is != null)
        {
            GL11.glPushMatrix();
            int i = 0;

            if (Minecraft.thePlayer != null && is != null)
            {
                if (is.getItem().isDamageable())
                {
                    double d0 = (double)(is.getMaxDamage() - is.getItemDamage()) / (double)is.getMaxDamage() * 100.0D;
                    this.fr.drawStringWithShadow(String.format("%.2f%%", new Object[] {Double.valueOf(d0)}), (float)(this.getX() + 20), (float)(this.getY() + 0 + 5), -1);
                }

                if (is.isStackable() && Minecraft.thePlayer.getHeldItem().stackSize != 1)
                {
                    this.fr.drawStringWithShadow(Integer.toString(Minecraft.thePlayer.getHeldItem().stackSize), (float)(this.getX() + 20), (float)(this.getY() + 0 + 5), -1);
                }

                RenderHelper.enableGUIStandardItemLighting();
                this.mc.getRenderItem().renderItemIntoGUI(is, this.getX(), this.getY() + 0);
                GL11.glPopMatrix();
            }
        }
    }
}
