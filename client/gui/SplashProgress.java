package client.gui;

import client.Client;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;

public class SplashProgress
{
    private static final int MAX = 9;
    private static int PROGRESS = 0;
    private static String CURRENT = "";
    private static ResourceLocation splash;
    private static UnicodeFontRenderer ufr;

    public static void update()
    {
        if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().getLanguageManager() != null)
        {
            drawSplash(Minecraft.getMinecraft().getTextureManager());
        }
    }

    public static void setProgress(int givenProgress, String givenTEXT)
    {
        PROGRESS = givenProgress;
        CURRENT = givenTEXT;
        update();
    }

    public static void drawSplash(TextureManager tm)
    {
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int i = scaledresolution.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double)scaledresolution.getScaledWidth(), (double)scaledresolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();

        if (splash == null)
        {
            splash = new ResourceLocation(Client.SplashScreen);
        }

        tm.bindTexture(splash);
        GlStateManager.resetColor();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, 1920, 1080, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), 1920.0F, 1080.0F);
        drawProgress();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        Minecraft.getMinecraft().updateDisplay();
    }

    private static void drawProgress()
    {
        if (Minecraft.getMinecraft().gameSettings != null && Minecraft.getMinecraft().getTextureManager() != null)
        {
            if (ufr == null)
            {
                ufr = UnicodeFontRenderer.getFontOnPC("Arial", 20);
            }

            ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
            double d0 = (double)PROGRESS;
            double d1 = d0 / 9.0D * (double)scaledresolution.getScaledWidth();
            Gui.drawRect(0, scaledresolution.getScaledHeight() - 35, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), (new Color(0, 0, 0, 120)).getRGB());
            GlStateManager.resetColor();
            resetTexturestate();
            ufr.drawString(CURRENT, 20.0F, (float)(scaledresolution.getScaledHeight() - 25), -1);
            String s = String.valueOf(PROGRESS) + "/" + 9;
            ufr.drawString(s, (float)(scaledresolution.getScaledWidth() - 20 - ufr.getStringWidth(s)), (float)(scaledresolution.getScaledHeight() - 25), -505290241);
            GlStateManager.resetColor();
            resetTexturestate();
            Gui.drawRect(0, scaledresolution.getScaledHeight() - 3, (int)d1, scaledresolution.getScaledHeight(), Color.red.getRGB());
            Gui.drawRect(0, scaledresolution.getScaledHeight() - 3, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight(), (new Color(0, 0, 0, 10)).getRGB());
        }
    }

    private static void resetTexturestate()
    {
        GlStateManager.textureState[GlStateManager.activeTextureUnit].textureName = -1;
    }
}
