package client.gui;

import client.Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class UnicodeFontRenderer
{
    public final int FONT_HEIGHT = 9;
    private final int[] colorCodes = new int[32];
    private final float kerning;
    private final Map<String, Float> cachedStringWidth = new HashMap();
    private float antiAliasingFactor;
    private UnicodeFont unicodeFont;

    public static UnicodeFontRenderer getFontOnPC(String name, int size)
    {
        return getFontOnPC(name, size, 0);
    }

    public static UnicodeFontRenderer getFontOnPC(String name, int size, int fontType)
    {
        return getFontOnPC(name, size, fontType, 0.0F);
    }

    public static UnicodeFontRenderer getFontOnPC(String name, int size, int fontType, float kerning)
    {
        return getFontOnPC(name, size, fontType, kerning, 3.0F);
    }

    public static UnicodeFontRenderer getFontOnPC(String name, int size, int fontType, float kerning, float antiAliasingFactor)
    {
        return new UnicodeFontRenderer(new Font(name, fontType, size), kerning, antiAliasingFactor);
    }

    public static UnicodeFontRenderer getFontFromAssets(String name, int size)
    {
        return getFontOnPC(name, size, 0);
    }

    public static UnicodeFontRenderer getFontFromAssets(String name, int size, int fontType)
    {
        return getFontOnPC(name, fontType, size, 0.0F);
    }

    public static UnicodeFontRenderer getFontFromAssets(String name, int size, float kerning, int fontType)
    {
        return getFontFromAssets(name, size, fontType, kerning, 3.0F);
    }

    public static UnicodeFontRenderer getFontFromAssets(String name, int size, int fontType, float kerning, float antiAliasingFactor)
    {
        return new UnicodeFontRenderer(name, fontType, (float)size, kerning, antiAliasingFactor);
    }

    private UnicodeFontRenderer(String fontName, int fontType, float fontSize, float kerning, float antiAliasingFactor)
    {
        this.antiAliasingFactor = antiAliasingFactor;

        try
        {
            this.unicodeFont = new UnicodeFont(this.getFontByName(fontName).deriveFont(fontSize * this.antiAliasingFactor));
        }
        catch (IOException | FontFormatException fontformatexception)
        {
            fontformatexception.printStackTrace();
        }

        this.kerning = kerning;
        this.unicodeFont.addAsciiGlyphs();
        this.unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));

        try
        {
            this.unicodeFont.loadGlyphs();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        for (int i = 0; i < 32; ++i)
        {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i & 1) * 170 + j;

            if (i == 6)
            {
                k += 85;
            }

            if (i >= 16)
            {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }

            this.colorCodes[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
        }
    }

    private UnicodeFontRenderer(Font font, float kerning, float antiAliasingFactor)
    {
        this.antiAliasingFactor = antiAliasingFactor;
        this.unicodeFont = new UnicodeFont(new Font(font.getName(), font.getStyle(), (int)((float)font.getSize() * antiAliasingFactor)));
        this.kerning = kerning;
        this.unicodeFont.addAsciiGlyphs();
        this.unicodeFont.getEffects().add(new ColorEffect(Color.WHITE));

        try
        {
            this.unicodeFont.loadGlyphs();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        for (int i = 0; i < 32; ++i)
        {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i & 1) * 170 + j;

            if (i == 6)
            {
                k += 85;
            }

            if (i >= 16)
            {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }

            this.colorCodes[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
        }
    }

    private Font getFontByName(String name) throws IOException, FontFormatException
    {
        return this.getFontFromInput("/assets/minecraft/clientname/fonts/" + name + ".ttf");
    }

    private Font getFontFromInput(String path) throws IOException, FontFormatException
    {
        return Font.createFont(0, (InputStream)Client.class.getResourceAsStream(path));
    }

    public void drawStringScaled(String text, int givenX, int givenY, int color, double givenScale)
    {
        GL11.glPushMatrix();
        GL11.glTranslated((double)givenX, (double)givenY, 0.0D);
        GL11.glScaled(givenScale, givenScale, givenScale);
        this.drawString(text, 0.0F, 0.0F, color);
        GL11.glPopMatrix();
    }

    public int drawString(String text, float x, float y, int color)
    {
        if (text == null)
        {
            return 0;
        }
        else
        {
            x = x * 2.0F;
            y = y * 2.0F;
            float f = x;
            GL11.glPushMatrix();
            GlStateManager.scale(1.0F / this.antiAliasingFactor, 1.0F / this.antiAliasingFactor, 1.0F / this.antiAliasingFactor);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
            x = x * this.antiAliasingFactor;
            y = y * this.antiAliasingFactor;
            float f1 = (float)(color >> 16 & 255) / 255.0F;
            float f2 = (float)(color >> 8 & 255) / 255.0F;
            float f3 = (float)(color & 255) / 255.0F;
            float f4 = (float)(color >> 24 & 255) / 255.0F;
            GlStateManager.color(f1, f2, f3, f4);
            boolean flag = GL11.glIsEnabled(GL11.GL_BLEND);
            boolean flag1 = GL11.glIsEnabled(GL11.GL_LIGHTING);
            boolean flag2 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);

            if (!flag)
            {
                GL11.glEnable(GL11.GL_BLEND);
            }

            if (flag1)
            {
                GL11.glDisable(GL11.GL_LIGHTING);
            }

            if (flag2)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
            }

            int i = color;
            char[] achar = text.toCharArray();
            int j = 0;

            for (char c0 : achar)
            {
                if (c0 == 13)
                {
                    x = f;
                }

                if (c0 == 10)
                {
                    y += this.getHeight(Character.toString(c0)) * 2.0F;
                }

                if (c0 == 167 || j != 0 && j != achar.length - 1 && achar[j - 1] == 167)
                {
                    if (c0 == 32)
                    {
                        x += (float)this.unicodeFont.getSpaceWidth();
                    }
                    else if (c0 == 167 && j != achar.length - 1)
                    {
                        int k = "0123456789abcdefg".indexOf(text.charAt(j + 1));

                        if (k < 0)
                        {
                            continue;
                        }

                        i = this.colorCodes[k];
                    }
                }
                else
                {
                    this.unicodeFont.drawString(x, y, Character.toString(c0), new org.newdawn.slick.Color(i));
                    x += this.getWidth(Character.toString(c0)) * 2.0F * this.antiAliasingFactor;
                }

                ++j;
            }

            GL11.glScaled(2.0D, 2.0D, 2.0D);

            if (flag2)
            {
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            if (flag1)
            {
                GL11.glEnable(GL11.GL_LIGHTING);
            }

            if (!flag)
            {
                GL11.glDisable(GL11.GL_BLEND);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
            return (int)x / 2;
        }
    }

    public int drawStringWithShadow(String text, float x, float y, int color)
    {
        this.drawString(StringUtils.stripControlCodes(text), x + 0.5F, y + 0.5F, 0);
        return this.drawString(text, x, y, color);
    }

    public void drawCenteredString(String text, float x, float y, int color)
    {
        this.drawString(text, x - (float)((int)this.getWidth(text) / 2), y, color);
    }

    public void drawCenteredTextScaled(String text, int givenX, int givenY, int color, double givenScale)
    {
        GL11.glPushMatrix();
        GL11.glTranslated((double)givenX, (double)givenY, 0.0D);
        GL11.glScaled(givenScale, givenScale, givenScale);
        this.drawCenteredString(text, 0.0F, 0.0F, color);
        GL11.glPopMatrix();
    }

    public void drawCenteredStringWithShadow(String text, float x, float y, int color)
    {
        this.drawCenteredString(StringUtils.stripControlCodes(text), x + 0.5F, y + 0.5F, color);
        this.drawCenteredString(text, x, y, color);
    }

    public float getWidth(String s)
    {
        if (this.cachedStringWidth.size() > 1000)
        {
            this.cachedStringWidth.clear();
        }

        return ((Float)this.cachedStringWidth.computeIfAbsent(s, (e) ->
        {
            float float = 0.0F;
            String string1 = StringUtils.stripControlCodes(s);

            char[] achar;

            for (char char : achar = string1.toCharArray())
            {
                float += (float)this.unicodeFont.getWidth(Character.toString(char)) + this.kerning;
            }

            return Float.valueOf(float / 2.0F / this.antiAliasingFactor);
        })).floatValue();
    }

    public int getStringWidth(String text)
    {
        if (text == null)
        {
            return 0;
        }
        else
        {
            int int = 0;
            boolean boolean = false;

            for (int int = 0; int < text.length(); ++int)
            {
                char char = text.charAt(int);
                float float = this.getWidth(String.valueOf(char));

                if (float < 0.0F && int < text.length() - 1)
                {
                    ++int;
                    char = text.charAt(int);

                    if (char != 108 && char != 76)
                    {
                        if (char == 114 || char == 82)
                        {
                            boolean = false;
                        }
                    }
                    else
                    {
                        boolean = true;
                    }

                    float = 0.0F;
                }

                int = (int)((float)int + float);

                if (boolean && float > 0.0F)
                {
                    ++int;
                }
            }

            return int;
        }
    }

    public float getCharWidth(char c)
    {
        return (float)this.unicodeFont.getWidth(String.valueOf(c));
    }

    public float getHeight(String s)
    {
        return (float)this.unicodeFont.getHeight(s) / 2.0F;
    }

    public UnicodeFont getFont()
    {
        return this.unicodeFont;
    }

    public String trimStringToWidth(String par1Str, int par2)
    {
        StringBuilder stringbuilder = new StringBuilder();
        float float = 0.0F;
        int int = 0;
        int int = 1;
        boolean boolean = false;
        boolean boolean = false;

        for (int int = int; int >= 0 && int < par1Str.length() && float < (float)par2; int += int)
        {
            char char = par1Str.charAt(int);
            float float = this.getCharWidth(char);

            if (boolean)
            {
                boolean = false;

                if (char != 108 && char != 76)
                {
                    if (char == 114 || char == 82)
                    {
                        boolean = false;
                    }
                }
                else
                {
                    boolean = true;
                }
            }
            else if (float < 0.0F)
            {
                boolean = true;
            }
            else
            {
                float += float;

                if (boolean)
                {
                    ++float;
                }
            }

            if (float > (float)par2)
            {
                break;
            }

            stringbuilder.append(char);
        }

        return stringbuilder.toString();
    }

    public void drawSplitString(ArrayList<String> lines, int x, int y, int color)
    {
        this.drawString(String.join("\n\r", (Iterable)lines), (float)x, (float)y, color);
    }

    public List<String> splitString(String text, int wrapWidth)
    {
        List<String> list = new ArrayList();
        String[] astring = text.split(" ");
        StringBuilder stringbuilder = new StringBuilder();

        for (String string : astring)
        {
            String string1 = stringbuilder + " " + string;

            if (this.getWidth(string1) >= (float)wrapWidth)
            {
                list.add(stringbuilder.toString());
                stringbuilder = new StringBuilder();
            }

            stringbuilder.append(string).append(" ");
        }

        list.add(stringbuilder.toString());
        return list;
    }
}
