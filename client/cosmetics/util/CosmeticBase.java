package client.cosmetics.util;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public abstract class CosmeticBase implements LayerRenderer<AbstractClientPlayer>
{
    protected final RenderPlayer renderPlayer;

    public CosmeticBase(RenderPlayer player)
    {
        this.renderPlayer = player;
    }

    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale)
    {
        if (player.hasPlayerInfo() && !player.isInvisible())
        {
            this.render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, headYaw, headPitch, scale);
        }
    }

    public boolean shouldCombineTextures()
    {
        return false;
    }

    public abstract void render(AbstractClientPlayer var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8);
}
