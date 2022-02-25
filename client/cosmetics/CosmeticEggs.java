package client.cosmetics;

import client.cosmetics.util.CosmeticBase;
import client.cosmetics.util.CosmeticModelBase;
import client.hud.impl.HudManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class CosmeticEggs extends CosmeticBase
{
    private final CosmeticEggs.CosmeticVilligerNose2 EggsModel;

    public CosmeticEggs(RenderPlayer renderPlayer)
    {
        super(renderPlayer);
        this.EggsModel = new CosmeticEggs.CosmeticVilligerNose2(renderPlayer);
    }

    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GL11.glPushMatrix();

        if (player.isSneaking())
        {
            GlStateManager.translate(0.0D, 0.262D, 0.0D);
        }

        GlStateManager.rotate(netHeadYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(headPitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(ageInTicks * 17.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        String s = player.getUniqueID().toString();

        if (s.contains("dwabdwbo8adb8wbdwa"))
        {
            this.EggsModel.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GL11.glColor3d(1.0D, 1.0D, 1.0D);
        }

        Minecraft.getMinecraft();

        if (player == Minecraft.thePlayer && HudManager.cosmeticEggs.isEnabled())
        {
            this.EggsModel.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }

        GL11.glPopMatrix();
    }

    public class CosmeticVilligerNose2 extends CosmeticModelBase
    {
        public CosmeticVilligerNose2(RenderPlayer player)
        {
            super(player);
        }

        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.25D, 0.25D, 0.25D);
            GlStateManager.translate(2.0D, 1.5D, 0.0D);
            ItemStack itemstack = new ItemStack(Items.egg);
            Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.translate(-4.0F, 0.0F, 0.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.translate(2.0F, 0.0F, 2.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.translate(0.0F, 0.0F, -4.0F);
            Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }
}
