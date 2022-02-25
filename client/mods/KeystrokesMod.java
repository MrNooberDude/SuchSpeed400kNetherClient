package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.opengl.GL11;

public class KeystrokesMod extends HudMod
{
    private KeystrokesMod.KeystrokesMode mode = KeystrokesMod.KeystrokesMode.WASD_JUMP_MOUSE;

    public KeystrokesMod()
    {
        super("Keystrokes", 622, 0);
    }

    public int getWidth()
    {
        return 60;
    }

    public int getHeight()
    {
        return 80;
    }

    public void draw()
    {
        GL11.glPushMatrix();
        KeystrokesMod.Key[] akeystrokesmod$key;

        for (KeystrokesMod.Key keystrokesmod$key : akeystrokesmod$key = this.mode.getKey())
        {
            int i = this.fr.getStringWidth(keystrokesmod$key.getName());
            Gui.drawRect(this.getX() + keystrokesmod$key.getX(), this.getY() + keystrokesmod$key.getY(), this.getX() + keystrokesmod$key.getX() + keystrokesmod$key.getWidth(), this.getY() + keystrokesmod$key.getY() + keystrokesmod$key.getHeight(), keystrokesmod$key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 120)).getRGB());
            this.fr.drawStringWithShadow(keystrokesmod$key.getName(), (float)(this.getX() + keystrokesmod$key.getX() + keystrokesmod$key.getWidth() / 2 - i / 2), (float)(this.getY() + keystrokesmod$key.getY() + keystrokesmod$key.getHeight() / 2 - 4), keystrokesmod$key.isDown() ? Client.MainColor : Client.MainColor);
        }

        GL11.glPopMatrix();
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        GL11.glPushMatrix();
        KeystrokesMod.Key[] akeystrokesmod$key;

        for (KeystrokesMod.Key keystrokesmod$key : akeystrokesmod$key = this.mode.getKey())
        {
            int i = this.fr.getStringWidth(keystrokesmod$key.getName());
            Gui.drawRect(this.getX() + keystrokesmod$key.getX(), this.getY() + keystrokesmod$key.getY(), this.getX() + keystrokesmod$key.getX() + keystrokesmod$key.getWidth(), this.getY() + keystrokesmod$key.getY() + keystrokesmod$key.getHeight(), keystrokesmod$key.isDown() ? (new Color(255, 255, 255, 102)).getRGB() : (new Color(0, 0, 0, 120)).getRGB());
            this.fr.drawStringWithShadow(keystrokesmod$key.getName(), (float)(this.getX() + keystrokesmod$key.getX() + keystrokesmod$key.getWidth() / 2 - i / 2), (float)(this.getY() + keystrokesmod$key.getY() + keystrokesmod$key.getHeight() / 2 - 4), keystrokesmod$key.isDown() ? Client.MainColor : Client.MainColor);
        }

        GL11.glPopMatrix();
        super.renderDummy(mouseX, mouseY);
    }

    public static class Key
    {
        public static Minecraft mc = Minecraft.getMinecraft();
        private static final KeystrokesMod.Key W;
        private static final KeystrokesMod.Key A;
        private static final KeystrokesMod.Key S;
        private static final KeystrokesMod.Key D;
        private static final KeystrokesMod.Key LMB;
        private static final KeystrokesMod.Key RMB;
        private static final KeystrokesMod.Key JUMP1;
        private static final KeystrokesMod.Key JUMP2;
        private final String name;
        private final KeyBinding keyBind;
        private final int x;
        private final int y;
        private final int w;
        private final int h;

        static
        {
            W = new KeystrokesMod.Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
            A = new KeystrokesMod.Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
            S = new KeystrokesMod.Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
            D = new KeystrokesMod.Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
            LMB = new KeystrokesMod.Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18);
            RMB = new KeystrokesMod.Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18);
            JUMP1 = new KeystrokesMod.Key("----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18);
            JUMP2 = new KeystrokesMod.Key("----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18);
        }

        public Key(String name, KeyBinding keyBind, int x, int y, int w, int h)
        {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public boolean isDown()
        {
            return this.keyBind.isKeyDown();
        }

        public int getHeight()
        {
            return this.h;
        }

        public int getWidth()
        {
            return this.w;
        }

        public String getName()
        {
            return this.name;
        }

        public int getX()
        {
            return this.x;
        }

        public int getY()
        {
            return this.y;
        }
    }

    public static enum KeystrokesMode
    {
        WASD(new KeystrokesMod.Key[]{KeystrokesMod.Key.W, KeystrokesMod.Key.A, KeystrokesMod.Key.S, KeystrokesMod.Key.D}),
        WASD_MOUSE(new KeystrokesMod.Key[]{KeystrokesMod.Key.W, KeystrokesMod.Key.A, KeystrokesMod.Key.S, KeystrokesMod.Key.D, KeystrokesMod.Key.LMB, KeystrokesMod.Key.RMB}),
        WASD_JUMP(new KeystrokesMod.Key[]{KeystrokesMod.Key.W, KeystrokesMod.Key.A, KeystrokesMod.Key.S, KeystrokesMod.Key.D, KeystrokesMod.Key.JUMP1}),
        WASD_JUMP_MOUSE(new KeystrokesMod.Key[]{KeystrokesMod.Key.W, KeystrokesMod.Key.A, KeystrokesMod.Key.S, KeystrokesMod.Key.D, KeystrokesMod.Key.LMB, KeystrokesMod.Key.RMB, KeystrokesMod.Key.JUMP2});

        private final KeystrokesMod.Key[] keys;
        private int width = 0;
        private int height = 0;

        private KeystrokesMode(KeystrokesMod.Key... keysIn)
        {
            this.keys = keysIn;

            for (KeystrokesMod.Key keystrokesmod$key : this.keys)
            {
                this.width = Math.max(this.width, keystrokesmod$key.getX() + keystrokesmod$key.getWidth());
                this.height = Math.max(this.height, keystrokesmod$key.getY() + keystrokesmod$key.getHeight());
            }
        }

        public int getHeight()
        {
            return this.height;
        }

        public int getWidth()
        {
            return this.width;
        }

        public KeystrokesMod.Key[] getKey()
        {
            return this.keys;
        }
    }
}
