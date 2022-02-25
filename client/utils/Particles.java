package client.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import net.minecraft.client.gui.Gui;

public class Particles
{
    private final List<Particles.Particle> particles;
    private int width;
    private int height;
    private int count;

    public Particles(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.count = 70;
        this.particles = new ArrayList();

        for (int i = 0; i <= this.count; ++i)
        {
            this.particles.add(new Particles.Particle((new Random()).nextInt(width), (new Random()).nextInt(height)));
        }
    }

    public void drawParticles()
    {
        this.particles.forEach((particle) ->
        {
            particle.drawParticle();
        });
    }

    public class Particle
    {
        private int xPos;
        private int yPos;

        public Particle(int xPos, int yPos)
        {
            this.xPos = xPos;
            this.yPos = yPos;
        }

        public void drawParticle()
        {
            ++this.xPos;
            ++this.yPos;
            int i = 1;

            if (this.xPos > Particles.this.width)
            {
                this.xPos = -1;
            }

            if (this.yPos > Particles.this.height)
            {
                this.yPos = -1;
            }

            Gui.drawRect(this.xPos, this.yPos, this.xPos + 1, this.yPos + 1, Color.RED.getRGB());
        }
    }
}
