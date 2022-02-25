package client.cosmetics.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CosmeticModelBase extends ModelBase
{
    protected ModelBiped playerModel;

    public CosmeticModelBase(RenderPlayer player)
    {
        this.playerModel = player.getMainModel();
    }
}