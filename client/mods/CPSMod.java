package client.mods;

import client.Client;
import client.hud.impl.HudMod;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.lwjgl.input.Mouse;

public class CPSMod extends HudMod
{
    public List<Long> clicks = new ArrayList();
    public boolean wasPressed;
    public long lastPressed;
    private List<Long> clicks2 = new ArrayList();
    private boolean wasPressed2;
    private long lastPressed2;

    public CPSMod()
    {
        super("CPS Display", 0, 100);
    }

    public int getWidth()
    {
        return this.fr.getStringWidth("[ CPS ] 0 | 0");
    }

    public int getHeight()
    {
        return this.fr.FONT_HEIGHT;
    }

    public void draw()
    {
        boolean flag = Mouse.isButtonDown(0);
        boolean flag1 = Mouse.isButtonDown(1);

        if (flag != this.wasPressed)
        {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = flag;

            if (flag)
            {
                this.clicks.add(Long.valueOf(this.lastPressed));
            }
        }

        if (flag1 != this.wasPressed2)
        {
            this.lastPressed2 = System.currentTimeMillis() + 10L;
            this.wasPressed2 = flag1;

            if (flag1)
            {
                this.clicks2.add(Long.valueOf(this.lastPressed2));
            }
        }

        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "CPS" + Client.Color0 + " ] " + Client.ColorW + this.getCPS() + " | " + this.getCPS2(), (float)this.getX(), (float)this.getY(), -1);
        super.draw();
    }

    public void renderDummy(int mouseX, int mouseY)
    {
        this.fr.drawStringWithShadow(Client.Color0 + "[ " + Client.ColorM + "CPS" + Client.Color0 + " ] " + Client.ColorW + "0 | 0", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }

    public int getCPS()
    {
        final long i = System.currentTimeMillis();
        this.clicks.removeIf(new Predicate<Long>()
        {
            public boolean test(Long aLong)
            {
                return aLong.longValue() + 1000L < i;
            }
        });
        return this.clicks.size();
    }

    public int getCPS2()
    {
        final long i = System.currentTimeMillis();
        this.clicks2.removeIf(new Predicate<Long>()
        {
            public boolean test(Long aLong2)
            {
                return aLong2.longValue() + 1000L < i;
            }
        });
        return this.clicks2.size();
    }
}
