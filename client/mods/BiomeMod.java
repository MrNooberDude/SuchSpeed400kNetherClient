package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class BiomeMod extends HudMod
{
    public BiomeMod()
    {
        super("Biome Display", 0, 30);
    }

    public void draw()
    {
        BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);
        Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(blockpos);
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Biome" + Client.Color0 + " ] " + Client.ColorW + chunk.getBiome(blockpos, this.mc.theWorld.getWorldChunkManager()).biomeName, (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "Biome" + Client.Color0 + " ] " + Client.ColorW + "Desert", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ Biome ] Desert");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }
}
