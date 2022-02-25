package client.cosmetics;

import client.cosmetics.util.CosmeticBase;
import client.cosmetics.util.CosmeticModelBase;
import client.hud.impl.HudManager;
import client.utils.Rainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RainbowWings extends CosmeticBase
{
    private final RainbowWings.CosmeticVilligerNose2 wingsModel;

    public RainbowWings(RenderPlayer renderPlayer)
    {
        super(renderPlayer);
        this.wingsModel = new RainbowWings.CosmeticVilligerNose2(renderPlayer);
    }

    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GL11.glPushMatrix();

        if (player.isSneaking())
        {
            GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.0D, 0.2D, -0.05D);
        }

        if (HudManager.rainbowWings.isEnabled())
        {
            Minecraft.getMinecraft();

            if (player == Minecraft.thePlayer)
            {
                Minecraft.getMinecraft().fontRendererObj.drawString("", 0.0F, 0.0F, Rainbow.rainbowEffect(1L, 1.0F).getRGB(), true);
                this.wingsModel.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            }
        }

        String s = player.getUniqueID().toString();

        if (s.contains("dwhnioad8zvwavdbwa8zdw"))
        {
            this.wingsModel.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GL11.glColor3d(1.0D, 1.0D, 1.0D);
        }

        GL11.glPopMatrix();
    }

    public class CosmeticVilligerNose2 extends CosmeticModelBase
    {
        private ModelRenderer wing;
        private ModelRenderer wingTip;

        public CosmeticVilligerNose2(RenderPlayer player)
        {
            super(player);
            this.setTextureOffset("wing.bone", 0, 0);
            this.setTextureOffset("wing.skin", -10, 8);
            this.setTextureOffset("wingtip.bone", 0, 5);
            this.setTextureOffset("wingtip.skin", -10, 18);
            this.wing = new ModelRenderer(this, "wing");
            this.wing.setTextureSize(30, 30);
            this.wing.setRotationPoint(-2.0F, 0.0F, 0.0F);
            this.wing.addBox("bone", -10.0F, -1.0F, -1.0F, 10, 2, 2);
            this.wing.addBox("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10);
            this.wingTip = new ModelRenderer(this, "wingtip");
            this.wingTip.setTextureSize(30, 30);
            this.wingTip.setRotationPoint(-10.0F, 0.0F, 0.0F);
            this.wingTip.addBox("bone", -10.0F, -0.5F, -0.5F, 10, 1, 1);
            this.wingTip.addBox("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10);
            this.wing.addChild(this.wingTip);
        }

        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale)
        {
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.scale(0.9D, 0.9D, 0.9D);
            GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.0D, 0.0D, 0.09D);
            GlStateManager.translate(0.0D, 0.2D, 0.0D);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/wings.png"));

            for (int i = 0; i < 2; ++i)
            {
                float f = (float)(System.currentTimeMillis() % 1000L) / 1000.0F * (float)Math.PI * 2.0F;
                this.wing.rotateAngleX = (float)Math.toRadians(-80.0D) - (float)Math.cos((double)f) * 0.4F;
                this.wing.rotateAngleY = (float)Math.toRadians(30.0D) + (float)Math.sin((double)f) * 0.2F;
                this.wing.rotateAngleZ = (float)Math.toRadians(20.0D);
                this.wingTip.rotateAngleZ = -((float)(Math.sin((double)(f + 2.0F)) + 0.9D)) * 0.75F;
                this.wing.render(0.0625F);
                GlStateManager.scale(-1.0F, 1.0F, 1.0F);

                if (i == 0)
                {
                    ;
                }
            }

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
