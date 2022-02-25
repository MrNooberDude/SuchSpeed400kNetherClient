package client.hud.impl;

import client.mods.ArmorStatus;
import client.mods.ArrowCounter;
import client.mods.AutoGGMod;
import client.mods.BPSMod;
import client.mods.BiomeMod;
import client.mods.BlockOutline;
import client.mods.ButtonStyle;
import client.mods.CPSMod;
import client.mods.ClearChat;
import client.mods.ClientName;
import client.mods.CompactChat;
import client.mods.DirectionMod;
import client.mods.FPSMod;
import client.mods.FullBright;
import client.mods.ItemModel;
import client.mods.ItemViewer;
import client.mods.KeystrokesMod;
import client.mods.MemoryUsage;
import client.mods.NameTag;
import client.mods.OldAnimations;
import client.mods.PackDisplay;
import client.mods.PerspectiveMod;
import client.mods.PingMod;
import client.mods.PotionsStatus;
import client.mods.ScrollZoom;
import client.mods.ServerIPMod;
import client.mods.TargetHUD;
import client.mods.TimeMod;
import client.mods.ToggleSprint;
import client.mods.XYZMod;
import client.mods.cosmetics.CosmeticCape;
import client.mods.cosmetics.CosmeticDiamonds;
import client.mods.cosmetics.CosmeticEggs;
import client.mods.cosmetics.CosmeticHat;
import client.mods.cosmetics.CosmeticWings;
import client.mods.cosmetics.CosmeticWither;
import client.mods.cosmetics.RainbowWings;
import java.util.ArrayList;

public class HudManager
{
    public static ClientName clientName;
    public static TimeMod timeMod;
    public static FPSMod fpsMod;
    public static CPSMod cpsMod;
    public static XYZMod xyzMod;
    public static BiomeMod biomeMod;
    public static TargetHUD targetHUD;
    public static DirectionMod directionMod;
    public static ArmorStatus armorStatus;
    public static PotionsStatus potionsStatus;
    public static KeystrokesMod keystrokesMod;
    public static BlockOutline blockOutline;
    public static ToggleSprint toggleSprint;
    public static ButtonStyle buttonStyle;
    public static PerspectiveMod perspectiveMod;
    public static ScrollZoom scrollZoom;
    public static MemoryUsage memoryUsage;
    public static ServerIPMod serverIPMod;
    public static ItemViewer itemViewer;
    public static ArrowCounter arrowCounter;
    public static ClearChat clearChat;
    public static CompactChat compactChat;
    public static NameTag nameTag;
    public static FullBright fullBright;
    public static PingMod pingMod;
    public static AutoGGMod autoGGMod;
    public static OldAnimations oldAnimations;
    public static ItemModel itemModel;
    public static BPSMod bpsMod;
    public static PackDisplay packDisplay;
    public static CosmeticCape cosmeticCape;
    public static CosmeticWings cosmeticWings;
    public static RainbowWings rainbowWings;
    public static CosmeticDiamonds cosmeticDiamonds;
    public static CosmeticEggs cosmeticEggs;
    public static CosmeticHat cosmeticHat;
    public static CosmeticWither cosmeticWither;
    public ArrayList<HudMod> hudMods = new ArrayList();

    public HudManager()
    {
        this.hudMods.add(clientName = new ClientName());
        this.hudMods.add(fpsMod = new FPSMod());
        this.hudMods.add(cpsMod = new CPSMod());
        this.hudMods.add(timeMod = new TimeMod());
        this.hudMods.add(xyzMod = new XYZMod());
        this.hudMods.add(biomeMod = new BiomeMod());
        this.hudMods.add(targetHUD = new TargetHUD());
        this.hudMods.add(directionMod = new DirectionMod());
        this.hudMods.add(armorStatus = new ArmorStatus());
        this.hudMods.add(potionsStatus = new PotionsStatus());
        this.hudMods.add(keystrokesMod = new KeystrokesMod());
        this.hudMods.add(blockOutline = new BlockOutline());
        this.hudMods.add(toggleSprint = new ToggleSprint());
        this.hudMods.add(buttonStyle = new ButtonStyle());
        this.hudMods.add(perspectiveMod = new PerspectiveMod());
        this.hudMods.add(scrollZoom = new ScrollZoom());
        this.hudMods.add(memoryUsage = new MemoryUsage());
        this.hudMods.add(serverIPMod = new ServerIPMod());
        this.hudMods.add(itemViewer = new ItemViewer());
        this.hudMods.add(arrowCounter = new ArrowCounter());
        this.hudMods.add(clearChat = new ClearChat());
        this.hudMods.add(compactChat = new CompactChat());
        this.hudMods.add(nameTag = new NameTag());
        this.hudMods.add(fullBright = new FullBright());
        this.hudMods.add(pingMod = new PingMod());
        this.hudMods.add(autoGGMod = new AutoGGMod());
        this.hudMods.add(oldAnimations = new OldAnimations());
        this.hudMods.add(itemModel = new ItemModel());
        this.hudMods.add(bpsMod = new BPSMod());
        this.hudMods.add(packDisplay = new PackDisplay());
        this.hudMods.add(cosmeticCape = new CosmeticCape());
        this.hudMods.add(cosmeticWings = new CosmeticWings());
        this.hudMods.add(rainbowWings = new RainbowWings());
        this.hudMods.add(cosmeticDiamonds = new CosmeticDiamonds());
        this.hudMods.add(cosmeticEggs = new CosmeticEggs());
        this.hudMods.add(cosmeticHat = new CosmeticHat());
        this.hudMods.add(cosmeticWither = new CosmeticWither());
    }

    public void renderMods()
    {
        for (HudMod hudmod : this.hudMods)
        {
            if (hudmod.isEnabled())
            {
                hudmod.draw();
            }
        }
    }
}
