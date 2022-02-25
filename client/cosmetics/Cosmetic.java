package client.cosmetics;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public abstract class Cosmetic extends ModelBiped implements LayerRenderer<AbstractClientPlayer>
{
    ModelBiped playerModel;

    public Cosmetic(RenderPlayer player)
    {
        this.playerModel = player.getMainModel();
    }

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale)
    {
        if (player.hasPlayerInfo() && !player.isInvisible())
        {
            this.render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scale);
        }
    }

    public abstract void render(AbstractClientPlayer var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8);

    public boolean shouldCombineTextures()
    {
        return false;
    }

    private static float Sigmoid(double value)
    {
        return 1.0F / (1.0F + (float)Math.exp(-value));
    }
}
