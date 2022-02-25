package client.gui;

import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;

public class GuiServerSwitch extends GuiMultiplayer
{
    public GuiServerSwitch(GuiScreen parentScreen)
    {
        super((GuiScreen)null);
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 1 || button.id == 4)
        {
            this.disconnect();
        }

        super.actionPerformed(button);
    }

    public void connectToSelected()
    {
        this.disconnect();
        super.connectToSelected();
    }

    private void disconnect()
    {
        if (this.mc.theWorld != null)
        {
            this.mc.theWorld.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient)null);
            this.mc.displayGuiScreen((GuiScreen)null);
            this.parentScreen = null;
        }
    }
}
