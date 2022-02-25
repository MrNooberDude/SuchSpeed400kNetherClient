package client.config;

import client.Client;
import client.hud.impl.HudMod;
import java.io.File;
import java.io.IOException;

public class Config
{
    public File configFolder = new File(Client.ClientName);
    public Configuration config;
    public Configuration configToSave = ConfigurationAPI.newConfiguration(new File(Client.ClientName + "/ModsConfig.json"));

    public void saveModConfig()
    {
        if (!this.configFolder.exists())
        {
            this.configFolder.mkdirs();
        }

        System.out.println("Saving Mods Config");

        for (HudMod hudmod : Client.INSTANCE.hudManager.hudMods)
        {
            this.configToSave.set(hudmod.name.toLowerCase() + " x", Integer.valueOf(hudmod.getX()));
            this.configToSave.set(hudmod.name.toLowerCase() + " y", Integer.valueOf(hudmod.getY()));
            this.configToSave.set(hudmod.name.toLowerCase() + " enabled", Boolean.valueOf(hudmod.isEnabled()));
        }

        try
        {
            this.configToSave.save();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void loadModConfig()
    {
        try
        {
            this.config = ConfigurationAPI.loadExistingConfiguration(new File(Client.ClientName + "/ModsConfig.json"));
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }
}
