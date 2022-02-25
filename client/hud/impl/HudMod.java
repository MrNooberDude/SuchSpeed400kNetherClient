package client.hud.impl;

import client.Client;
import client.event.EventManager;
import client.hud.DraggableComponent;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class HudMod
{
    public Minecraft mc = Minecraft.getMinecraft();
    public FontRenderer fr;
    public String name;
    public boolean enabled;
    public DraggableComponent drag;
    public int x;
    public int y;

    public HudMod(String name, int x, int y)
    {
        this.fr = this.mc.fontRendererObj;
        this.name = name;

        try
        {
            this.x = ((Integer)Client.INSTANCE.config.config.get(name.toLowerCase() + " x")).intValue();
            this.y = ((Integer)Client.INSTANCE.config.config.get(name.toLowerCase() + " y")).intValue();
            this.setEnabled(((Boolean)Client.INSTANCE.config.config.get(name.toLowerCase() + " enabled")).booleanValue());
        }
        catch (NullPointerException nullpointerexception)
        {
            nullpointerexception.printStackTrace();
            this.x = x;
            this.y = y;
            this.enabled = false;
        }

        this.drag = new DraggableComponent(this.x, this.y, this.getWidth(), this.getHeight(), (new Color(0, 0, 0, 0)).getRGB());
    }

    public int getWidth()
    {
        return 50;
    }

    public int getHeight()
    {
        return 50;
    }

    public void draw()
    {
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        Gui.drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 872415231);
        this.drawHollowRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), -1996488705);
        this.drag.draw(mouseX, mouseY);
    }

    public void drawHollowRect(int x, int y, int w, int h, int color)
    {
        this.drawHorizontalLine(x, x + w, y, color);
        this.drawHorizontalLine(x, x + w, y + h, color);
        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);
    }

    protected void drawHorizontalLine(int startX, int endX, int y, int color)
    {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    protected void drawVerticalLine(int x, int startY, int endY, int color)
    {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }

    public static void drawRect(int left, int top, int right, int bottom, int color)
    {
        if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)left, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)top, 0.0D).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public int getX()
    {
        return this.drag.getxPosition();
    }

    public int getY()
    {
        return this.drag.getyPosition();
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        if (enabled)
        {
            this.onEnable();
        }
        else
        {
            this.onDisable();
        }
    }

    public void onEnable()
    {
        EventManager.register(this);
    }

    public void toggle()
    {
        this.setEnabled(!this.enabled);
    }

    public boolean isEnabled()
    {
        return this.enabled;
    }

    public void onDisable()
    {
        EventManager eventmanager = Client.INSTANCE.eventManager;
        EventManager.unregister(this);
    }
}
