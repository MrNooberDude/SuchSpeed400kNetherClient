package client.gui.clickgui;

import client.Client;
import client.gui.ClassicButton;
import client.gui.GuiServerSwitch;
import client.gui.clickgui.comp.ModButton;
import client.hud.HUDConfigScreen;
import client.hud.impl.HudManager;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.opengl.GL11;

public class ClickGUI_2 extends GuiScreen
{
    ArrayList<ModButton> modButtons = new ArrayList();

    public void initGui()
    {
        super.initGui();
        int i = this.width / 2 + 25;
        int j = this.height / 2 - 125;
        int k = this.fontRendererObj.FONT_HEIGHT + 4;
        HudManager hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 58, k, HudManager.itemViewer));
        i = this.width / 2 + 105;
        j = this.height / 2 - 125;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 72, k, HudManager.arrowCounter));
        i = this.width / 2 + 25;
        j = this.height / 2 - 100;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 56, k, HudManager.clearChat));
        i = this.width / 2 + 105;
        j = this.height / 2 - 100;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 65, k, HudManager.compactChat));
        i = this.width / 2 + 25;
        j = this.height / 2 - 75;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 63, k, HudManager.nameTag));
        i = this.width / 2 + 105;
        j = this.height / 2 - 75;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 55, k, HudManager.fullBright));
        i = this.width / 2 + 25;
        j = this.height / 2 - 50;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 60, k, HudManager.pingMod));
        i = this.width / 2 + 105;
        j = this.height / 2 - 50;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 60, k, HudManager.autoGGMod));
        i = this.width / 2 + 25;
        j = this.height / 2 - 25;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 67, k, HudManager.oldAnimations));
        i = this.width / 2 + 105;
        j = this.height / 2 - 25;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 53, k, HudManager.itemModel));
        i = this.width / 2 + 25;
        j = this.height / 2;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 60, k, HudManager.bpsMod));
        i = this.width / 2 + 105;
        j = this.height / 2;
        k = this.fontRendererObj.FONT_HEIGHT + 4;
        hudmanager = Client.INSTANCE.hudManager;
        this.modButtons.add(new ModButton(i, j, 63, k, HudManager.packDisplay));
        this.buttonList.add(new ClassicButton(10, this.width / 2 + 120, this.height / 2 + 100, 20, 20, I18n.format(">>", new Object[0])));
        this.buttonList.add(new ClassicButton(11, this.width / 2 + 60, this.height / 2 + 100, 20, 20, I18n.format("<<", new Object[0])));
        this.buttonList.add(new ClassicButton(1, this.width / 2 - 170, this.height / 2 + 70, 150, 20, I18n.format("Cosmetics Menu", new Object[0])));
        this.buttonList.add(new ClassicButton(2, this.width / 2 - 170, this.height / 2 + 100, 72, 20, I18n.format("Server List", new Object[0])));
        this.buttonList.add(new ClassicButton(3, this.width / 2 - 92, this.height / 2 + 100, 72, 20, I18n.format("Mods Config", new Object[0])));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 1:
                this.mc.displayGuiScreen(new CosmeticsMenu());
                break;

            case 2:
                this.mc.displayGuiScreen(new GuiServerSwitch(this));
                break;

            case 3:
                this.mc.displayGuiScreen(new HUDConfigScreen());

            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                break;

            case 10:
                this.mc.displayGuiScreen(new ClickGUI_2());
                break;

            case 11:
                this.mc.displayGuiScreen(new ClickGUI());
        }

        super.actionPerformed(button);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        Playerdraw(this.width / 2 - 100, this.height / 2 + 30, 25, 50.0F, 0.0F, Minecraft.thePlayer);
        Gui.drawRect(this.width / 2 + 200, this.height / 2 + 150, this.width / 2 - 200, this.height / 2 - 150, (new Color(25, 25, 25, 255)).getRGB());
        Gui.drawRect(this.width / 2 + 195, this.height / 2 + 145, this.width / 2 - 195, this.height / 2 - 145, (new Color(50, 50, 50, 255)).getRGB());
        Gui.drawRect(this.width / 2 + 190, this.height / 2 + 140, this.width / 2, this.height / 2 - 140, (new Color(35, 35, 35, 255)).getRGB());
        this.drawCenteredString(this.fontRendererObj, I18n.format("Mods Settings", new Object[0]), this.width / 2 - 100, this.height / 2 - 135, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (ModButton modbutton : this.modButtons)
        {
            modbutton.draw();
        }
    }

    public static void Playerdraw(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        if (MinecraftServer.getServer().isSinglePlayer())
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)posX, (float)posY, 50.0F);
            GlStateManager.scale(-65.0F, 65.0F, 65.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            float f = ent.renderYawOffset;
            float f1 = ent.rotationYaw;
            float f2 = ent.rotationPitch;
            float f3 = ent.prevRotationYawHead;
            float f4 = ent.rotationYawHead;
            GlStateManager.rotate(155.0F, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
            ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
            ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
            ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
            ent.rotationYawHead = ent.rotationYaw;
            ent.prevRotationYawHead = ent.rotationYaw;
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
            rendermanager.setPlayerViewY(180.0F);
            rendermanager.setRenderShadow(false);
            rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            rendermanager.setRenderShadow(true);
            ent.renderYawOffset = f;
            ent.rotationYaw = f1;
            ent.rotationPitch = f2;
            ent.prevRotationYawHead = f3;
            ent.rotationYawHead = f4;
            GlStateManager.popMatrix();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        }
        else if (Minecraft.getMinecraft().getCurrentServerData() != null)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)posX, (float)posY, 50.0F);
            GlStateManager.scale(-65.0F, 65.0F, 65.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            float f5 = ent.renderYawOffset;
            float f6 = ent.rotationYaw;
            float f7 = ent.rotationPitch;
            float f8 = ent.prevRotationYawHead;
            float f9 = ent.rotationYawHead;
            GlStateManager.rotate(155.0F, 0.0F, 1.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
            ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
            ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
            ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
            ent.rotationYawHead = ent.rotationYaw;
            ent.prevRotationYawHead = ent.rotationYaw;
            GlStateManager.translate(0.0F, 0.0F, 0.0F);
            RenderManager rendermanager1 = Minecraft.getMinecraft().getRenderManager();
            rendermanager1.setPlayerViewY(180.0F);
            rendermanager1.setRenderShadow(false);
            rendermanager1.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
            rendermanager1.setRenderShadow(true);
            ent.renderYawOffset = f5;
            ent.rotationYaw = f6;
            ent.rotationPitch = f7;
            ent.prevRotationYawHead = f8;
            ent.rotationYawHead = f9;
            GlStateManager.popMatrix();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (ModButton modbutton : this.modButtons)
        {
            modbutton.onClieck(mouseX, mouseY, mouseButton);
        }
    }
}
