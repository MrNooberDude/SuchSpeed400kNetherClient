package client.mods;

import client.hud.impl.HudMod;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

public class PerspectiveMod extends HudMod
{
    public static float cameraYaw = 0.0F;
    public static float cameraPitch = 0.0F;
    public static int previousePrespective = 0;
    public static boolean perspectiveToggled = false;
    public static boolean returnOnRelease = true;

    public PerspectiveMod()
    {
        super("Perspective", 100000, 100000);
    }

    public int getWidth()
    {
        return 0;
    }

    public int getHeight()
    {
        return 0;
    }

    public static float getCameraYaw()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        return perspectiveToggled ? cameraYaw : Minecraft.thePlayer.rotationYaw;
    }

    public static float getCameraPitch()
    {
        Minecraft minecraft = Minecraft.getMinecraft();
        return perspectiveToggled ? cameraPitch : Minecraft.thePlayer.rotationPitch;
    }

    public static boolean overriderMouse()
    {
        Minecraft minecraft = Minecraft.getMinecraft();

        if (minecraft.inGameHasFocus && Display.isActive())
        {
            if (!perspectiveToggled)
            {
                return true;
            }

            minecraft.mouseHelper.mouseXYChange();
            float f = minecraft.gameSettings.mouseSensitivity * 0.6F + 0.2F;
            float f1 = f * f * f * 8.0F;
            float f2 = (float)minecraft.mouseHelper.deltaX * f1;
            float f3 = (float)minecraft.mouseHelper.deltaY * f1;
            cameraYaw += f2 * 0.15F;
            cameraPitch += f3 * 0.15F;

            if (cameraPitch > 90.0F)
            {
                cameraPitch = 90.0F;
            }

            if (cameraPitch < -90.0F)
            {
                cameraPitch = -90.0F;
            }
        }

        return false;
    }
}
