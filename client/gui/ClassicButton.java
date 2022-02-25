package client.gui;

import client.Client;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class ClassicButton extends GuiButton
{
    private int j6;
    private int j10;
    int fade;
    int fade2;

    public ClassicButton(int i, int j, int k, String s)
    {
        this(i, j, k, 200, 21, s);
    }

    public ClassicButton(int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, l, i1, s);
        this.enabled = true;
        this.visible = true;
    }

    protected int getHoverState(boolean flag)
    {
        byte b0 = 1;

        if (!this.enabled)
        {
            b0 = 0;
        }
        else if (flag)
        {
            b0 = 2;
        }

        return b0;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        FontRenderer fontrenderer = mc.fontRendererObj;
        this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        int i = -1;

        if (this.hovered)
        {
            i = Client.ButtonString;
        }

        if (!this.hovered)
        {
            this.fade = 230;
        }
        else
        {
            if (this.fade <= 50)
            {
                return;
            }

            if (this.fade != 160)
            {
                this.fade -= 10;
            }
        }

        if (!this.hovered)
        {
            this.fade2 = 200;
        }
        else
        {
            if (this.fade2 <= 30)
            {
                return;
            }

            if (this.fade2 != 200)
            {
                this.fade2 += 10;
            }
        }

        float f = (float)(this.hovered ? (new Color(30, 0, 0, 100)).getRGB() : (new Color(30, 0, 0, 30)).getRGB());
        Color color = new Color(10, 10, 10, this.fade);
        Color color1 = Client.ButtonOutline;
        Color color2 = new Color(10, 10, 10, this.fade);

        if (this.xPosition >= this.xPosition && this.yPosition >= this.yPosition && this.xPosition < this.xPosition + this.width && this.yPosition < this.yPosition + this.height)
        {
            int j = 5;
            FontRenderer fontrenderer1 = mc.fontRendererObj;
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            this.drawRoundedRect(this.xPosition - 1, this.yPosition - 1, this.width + 2, this.height + 2, 3, color1);
            this.drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 3, color2);
            this.drawRoundedRect(this.xPosition, this.yPosition, this.width, this.height, 3, color);
            GlStateManager.color(1.0F, 0.0F, 0.0F);
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, i);
        }
    }

    private void drawRoundedRect(int x, int y, int width, int height, int cornerRadius, Color color)
    {
        Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.getRGB());
        Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.getRGB());
        Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.getRGB());
        this.drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
        this.drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
        this.drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color);
        this.drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color);
    }

    private void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color)
    {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
        WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
        worldrenderer.begin(6, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)x, (double)y, 0.0D).endVertex();

        for (int i = (int)((double)startAngle / 360.0D * 100.0D); i <= (int)((double)endAngle / 360.0D * 100.0D); ++i)
        {
            double d0 = (Math.PI * 2D) * (double)i / 100.0D + Math.toRadians(180.0D);
            worldrenderer.pos((double)x + Math.sin(d0) * (double)radius, (double)y + Math.cos(d0) * (double)radius, 0.0D).endVertex();
        }

        Tessellator.getInstance().draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    private void drawCircle(int x, int y, int width, int height, Color color)
    {
        this.drawArc(x + width, y + height / 2, width / 2, 0, 360, color);
    }
}
