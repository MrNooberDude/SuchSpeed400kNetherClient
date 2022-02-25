package client.cosmetics;

import client.hud.impl.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class CosmeticHat extends Cosmetic
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    public CosmeticHat(RenderPlayer player)
    {
        super(player);
        this.textureWidth = 64;
        this.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 10, 1, 10);
        this.Shape1.setRotationPoint(-5.0F, -9.0F, -5.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        (this.Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0F, 0.0F, 0.0F, 8, 5, 8);
        this.Shape2.setRotationPoint(-4.0F, -14.0F, -4.0F);
        this.Shape2.setTextureSize(64, 32);
        this.Shape2.mirror = true;
    }

    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale)
    {
        if (HudManager.cosmeticHat.isEnabled())
        {
            GlStateManager.pushMatrix();
            float f = this.getFirstRotationX(player, partialTicks);
            float f1 = this.getSecondRotationX(player, partialTicks);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.setRotationAngles(limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, player);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/cap.png"));
            GlStateManager.rotate(f, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(f1, 1.0F, 0.0F, 0.0F);
            GlStateManager.disableLighting();

            if (player.isSneaking())
            {
                GlStateManager.translate(0.0D, 0.26D, -0.047D);
            }

            this.Shape1.render(scale);
            this.Shape2.render(scale);
            GlStateManager.popMatrix();
        }
    }

    private float getFirstRotationX(AbstractClientPlayer Player, float partialTicks)
    {
        float f = this.interpolateRotation(Player.prevRenderYawOffset, Player.renderYawOffset, partialTicks);
        float f1 = this.interpolateRotation(Player.prevRotationYawHead, Player.rotationYawHead, partialTicks);
        float f2 = f1 - f;

        if (Player.isRiding() && Player.ridingEntity instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)Player.ridingEntity;
            f = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
            f2 = f1 - f;
            float f3 = MathHelper.wrapAngleTo180_float(f2);

            if (f3 < -85.0F)
            {
                f3 = -85.0F;
            }

            if (f3 >= 85.0F)
            {
                f3 = 85.0F;
            }

            float f41 = f1 - f3;
        }

        return f2;
    }

    private float getSecondRotationX(AbstractClientPlayer Player, float partialTicks)
    {
        return Player.prevRotationPitch + (Player.rotationPitch - Player.prevRotationPitch) * partialTicks;
    }

    private float interpolateRotation(float par1, float par2, float par3)
    {
        float f;

        for (f = par2 - par1; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return par1 + par3 * f;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
