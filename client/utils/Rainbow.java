package client.utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;

public class Rainbow
{
    public static Color rainbowEffect(long offset, float fade)
    {
        float f = (float)(System.nanoTime() + offset) / 1.0E1F % 1.0F;
        long i = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(f, 1.0F, 1.0F)).intValue()), 16);
        Color color = new Color((int)i);
        return new Color((float)color.getRed() / 255.0F * fade, (float)color.getGreen() / 255.0F * fade, (float)color.getBlue() / 255.0F * fade, (float)color.getAlpha() / 255.0F);
    }

    public static Color rainbowEffectFast(long offset, float fade)
    {
        float f = (float)(System.nanoTime() + offset) / 1.0E1F % 1.0F;
        long i = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(f, 1.0F, 1.0F)).intValue()), 19);
        Color color = new Color((int)i);
        return new Color((float)color.getRed() / 255.0F * fade, (float)color.getGreen() / 255.0F * fade, (float)color.getBlue() / 255.0F * fade, (float)color.getAlpha() / 255.0F);
    }

    public static Color colorLerpv2(Color start, Color end, float ratio)
    {
        int i = (int)Math.abs(ratio * (float)start.getRed() + (1.0F - ratio) * (float)end.getRed());
        int j = (int)Math.abs(ratio * (float)start.getGreen() + (1.0F - ratio) * (float)end.getGreen());
        int k = (int)Math.abs(ratio * (float)start.getBlue() + (1.0F - ratio) * (float)end.getBlue());
        return new Color(i, j, k);
    }

    public static void drawChromaString(String string, int x, int y, boolean shadow)
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        int i = x;
        char[] achar;

        for (char c0 : achar = string.toCharArray())
        {
            long j = System.currentTimeMillis() - (long)(i * 10 - y * 10);
            int k = Color.HSBtoRGB((float)(j % 2000L) / 2000.0F, 0.8F, 0.8F);
            String s = String.valueOf(c0);
            minecraft.fontRendererObj.drawString(s, (float)i, (float)y, k, shadow);
            i += minecraft.fontRendererObj.getCharWidth(c0);
        }
    }

    public static int getChromaColor()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        int i = 1;
        long j = System.currentTimeMillis() - (long)(i * 10 - 10);
        int k = Color.HSBtoRGB((float)(j % 2000L) / 2000.0F, 0.8F, 0.8F);
        return k;
    }

    public static class RainbowColor
    {
        public static int getColor()
        {
            long i = System.currentTimeMillis();
            return Color.HSBtoRGB((float)(i % 2000L) / 2000.0F, 0.8F, 0.8F);
        }
    }
}
