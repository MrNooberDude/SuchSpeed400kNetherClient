package client;

import client.config.Config;
import client.event.EventManager;
import client.event.EventTarget;
import client.event.impl.ClientTick;
import client.event.impl.KeyEvent;
import client.gui.clickgui.ClickGUI;
import client.hud.HUDConfigScreen;
import client.hud.impl.HudManager;
import client.mods.PerspectiveMod;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class Client
{
    public static String ClientName = "Nether Client";
    public static String SplashScreen = "client/splash.jpg";
    public static String Background = "client/main_menu.jpg";
    public static Color ButtonOutline = new Color(170, 0, 0);
    public static int ButtonString = (new Color(170, 0, 0)).getRGB();
    public static int MainColor = (new Color(170, 0, 0)).getRGB();
    public static int TargetHUD = (new Color(170, 0, 0)).getRGB();
    public static int BlockOutline = (new Color(170, 0, 0)).getRGB();
    public static String Color0 = "\u00a78";
    public static String ColorM = "\u00a74";
    public static String ColorW = "\u00a7f";
    public static Client INSTANCE = new Client();
    public Minecraft mc = Minecraft.getMinecraft();
    public EventManager eventManager;
    public Config config;
    public HudManager hudManager;

    public void init()
    {
        System.out.println("Starting " + ClientName + " by NexoLegend");
    }

    public void start()
    {
        this.eventManager = new EventManager();
        this.config = new Config();
        this.config.loadModConfig();
        this.hudManager = new HudManager();
        EventManager.register(this);
    }

    public void shutdown()
    {
        System.out.println("Shutting Down " + ClientName);
        this.config.saveModConfig();
        EventManager.unregister(this);
    }

    @EventTarget
    public void onTick(ClientTick event)
    {
        if (this.mc.gameSettings.Click_GUI.isPressed())
        {
            this.mc.displayGuiScreen(new ClickGUI());
        }

        if (this.mc.gameSettings.HUD_Config.isPressed())
        {
            this.mc.displayGuiScreen(new HUDConfigScreen());
        }
    }

    @EventTarget
    public void keyboardEvent(KeyEvent e)
    {
        if (HudManager.perspectiveMod.isEnabled())
        {
            Minecraft minecraft = Minecraft.getMinecraft();

            if (e.getKey() == minecraft.gameSettings.Perspective.getKeyCode())
            {
                if (Keyboard.getEventKeyState())
                {
                    PerspectiveMod.perspectiveToggled = !PerspectiveMod.perspectiveToggled;
                    PerspectiveMod.cameraYaw = Minecraft.thePlayer.rotationYaw;
                    PerspectiveMod.cameraPitch = Minecraft.thePlayer.rotationPitch;

                    if (PerspectiveMod.perspectiveToggled)
                    {
                        PerspectiveMod.previousePrespective = minecraft.gameSettings.thirdPersonView;
                        minecraft.gameSettings.thirdPersonView = 1;
                    }
                    else
                    {
                        minecraft.gameSettings.thirdPersonView = PerspectiveMod.previousePrespective;
                    }
                }
                else if (PerspectiveMod.returnOnRelease)
                {
                    PerspectiveMod.perspectiveToggled = false;
                    minecraft.gameSettings.thirdPersonView = PerspectiveMod.previousePrespective;
                }
            }

            if (Keyboard.getEventKey() == minecraft.gameSettings.keyBindTogglePerspective.getKeyCode())
            {
                PerspectiveMod.perspectiveToggled = false;
            }
        }
    }
}
