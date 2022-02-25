package client.mods;

import client.hud.impl.HudMod;
import java.util.Collection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionsStatus extends HudMod
{
    protected float zLevelFloat;

    public PotionsStatus()
    {
        super("PotionsStatus", 0, 135);
    }

    public void draw()
    {
        int i = 21;
        int j = 14;
        int k = 80;
        int l = 16;
        Collection<PotionEffect> collection = Minecraft.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int i1 = 33;

            if (collection.size() > 5)
            {
                i1 = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : Minecraft.thePlayer.getActivePotionEffects())
            {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                if (potion.hasStatusIcon())
                {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    int j1 = potion.getStatusIconIndex();
                    this.drawTexturedModalRect(this.getX() + 21 - 20, this.getY() + l - 14, 0 + j1 % 8 * 18, 198 + j1 / 8 * 18, 18, 18);
                }

                String s1 = I18n.format(potion.getName(), new Object[0]);

                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = String.valueOf((Object)s1) + " " + I18n.format("enchantment.level.2", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = String.valueOf((Object)s1) + " " + I18n.format("enchantment.level.3", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = String.valueOf((Object)s1) + " " + I18n.format("enchantment.level.4", new Object[0]);
                }

                this.fr.drawString(s1, (float)(this.getX() + 21), (float)(this.getY() + l - 14), 16777215, true);
                String s = Potion.getDurationString(potioneffect);
                this.fr.drawString(s, (float)(this.getX() + 21), (float)(this.getY() + l + 10 - 14), 8355711, true);
                l += i1;
            }
        }

        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        int i = 21;
        int j = 14;
        int k = 80;
        int l = 16;
        PotionEffect[] apotioneffect = new PotionEffect[] {new PotionEffect(Potion.moveSpeed.id, 1200, 0), new PotionEffect(Potion.damageBoost.id, 1200, 0), new PotionEffect(Potion.fireResistance.id, 1200, 0)};
        int i1 = 33;

        if (apotioneffect.length > 5)
        {
            i1 = 132 / (apotioneffect.length - 1);
        }

        for (PotionEffect potioneffect : apotioneffect)
        {
            Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            if (potion.hasStatusIcon())
            {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                int j1 = potion.getStatusIconIndex();
                this.drawTexturedModalRect(this.getX() + 21 - 20, this.getY() + l - 14, 0 + j1 % 8 * 18, 198 + j1 / 8 * 18, 18, 18);
            }

            String s1 = I18n.format(potion.getName(), new Object[0]);

            if (potioneffect.getAmplifier() == 1)
            {
                s1 = String.valueOf((Object)s1) + " " + I18n.format("enchantment.level.2", new Object[0]);
            }
            else if (potioneffect.getAmplifier() == 2)
            {
                s1 = String.valueOf((Object)s1) + " " + I18n.format("enchantment.level.3", new Object[0]);
            }
            else if (potioneffect.getAmplifier() == 3)
            {
                s1 = String.valueOf((Object)s1) + " " + I18n.format("enchantment.level.4", new Object[0]);
            }

            this.fr.drawString(s1, (float)(this.getX() + 21), (float)(this.getY() + l - 14), 16777215, true);
            String s = Potion.getDurationString(potioneffect);
            this.fr.drawString(s, (float)(this.getX() + 21), (float)(this.getY() + l + 10 - 14), 8355711, true);
            l += i1;
        }

        super.renderDummy(mouseX, mouseY);
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevelFloat).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevelFloat).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + height) * 0.00390625F)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevelFloat).tex((double)((float)(textureX + width) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        worldrenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevelFloat).tex((double)((float)(textureX + 0) * 0.00390625F), (double)((float)(textureY + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
    }

    public int getWidth()
    {
        return 100;
    }

    public int getHeight()
    {
        return 90;
    }
}
